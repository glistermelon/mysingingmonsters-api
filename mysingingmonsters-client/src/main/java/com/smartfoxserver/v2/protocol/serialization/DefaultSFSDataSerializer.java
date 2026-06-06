package com.smartfoxserver.v2.protocol.serialization;

import com.smartfoxserver.v2.entities.data.ISFSArray;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSArray;
import com.smartfoxserver.v2.entities.data.SFSArrayLite;
import com.smartfoxserver.v2.entities.data.SFSDataType;
import com.smartfoxserver.v2.entities.data.SFSDataWrapper;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.entities.data.SFSObjectLite;
import com.smartfoxserver.v2.exceptions.SFSCodecException;
import com.smartfoxserver.v2.exceptions.SFSRuntimeException;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.ByteBuffer;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultSFSDataSerializer implements ISFSDataSerializer {
   private static final DefaultSFSDataSerializer instance = new DefaultSFSDataSerializer();
   private static final int BUFFER_CHUNK_SIZE = 512;
   private final Logger logger = LoggerFactory.getLogger(this.getClass());

   public static DefaultSFSDataSerializer getInstance() {
      return instance;
   }

   private DefaultSFSDataSerializer() {
   }

   public int getUnsignedByte(byte b) {
      return 255 & b;
   }

   public ISFSArray binary2array(byte[] data) {
      if (data.length < 3) {
         throw new IllegalStateException("Can't decode an SFSArray. Byte data is insufficient. Size: " + data.length + " bytes");
      } else {
         ByteBuffer buffer = ByteBuffer.allocate(data.length);
         buffer.put(data);
         buffer.flip();
         return this.decodeSFSArray(buffer);
      }
   }

   private ISFSArray decodeSFSArray(ByteBuffer buffer) {
      ISFSArray sfsArray = SFSArray.newInstance();
      byte headerBuffer = buffer.get();
      if (headerBuffer != SFSDataType.SFS_ARRAY.getTypeID()) {
         throw new IllegalStateException("Invalid SFSDataType. Expected: " + SFSDataType.SFS_ARRAY.getTypeID() + ", found: " + headerBuffer);
      } else {
         short size = buffer.getShort();
         if (size < 0) {
            throw new IllegalStateException("Can't decode SFSArray. Size is negative = " + size);
         } else {
            try {
               for(int i = 0; i < size; ++i) {
                  SFSDataWrapper decodedObject = this.decodeObject(buffer);
                  if (decodedObject == null) {
                     throw new IllegalStateException("Could not decode SFSArray item at index: " + i);
                  }

                  sfsArray.add(decodedObject);
               }

               return sfsArray;
            } catch (SFSCodecException codecError) {
               throw new IllegalArgumentException(codecError.getMessage());
            }
         }
      }
   }

   public ISFSObject binary2object(byte[] data) {
      if (data.length < 3) {
         throw new IllegalStateException("Can't decode an SFSObject. Byte data is insufficient. Size: " + data.length + " bytes");
      } else {
         ByteBuffer buffer = ByteBuffer.allocate(data.length);
         buffer.put(data);
         buffer.flip();
         return this.decodeSFSObject(buffer);
      }
   }

   private ISFSObject decodeSFSObject(ByteBuffer buffer) {
      ISFSObject sfsObject = SFSObject.newInstance();
      byte headerBuffer = buffer.get();
      if (headerBuffer != SFSDataType.SFS_OBJECT.getTypeID()) {
         throw new IllegalStateException("Invalid SFSDataType. Expected: " + SFSDataType.SFS_OBJECT.getTypeID() + ", found: " + headerBuffer);
      } else {
         short size = buffer.getShort();
         if (size < 0) {
            throw new IllegalStateException("Can't decode SFSObject. Size is negative = " + size);
         } else {
            try {
               for(int i = 0; i < size; ++i) {
                  short keySize = buffer.getShort();
                  if (keySize < 0 || keySize > 255) {
                     throw new IllegalStateException("Invalid SFSObject key length. Found = " + keySize);
                  }

                  byte[] keyData = new byte[keySize];
                  buffer.get(keyData, 0, keyData.length);
                  String key = new String(keyData);
                  SFSDataWrapper decodedObject = this.decodeObject(buffer);
                  if (decodedObject == null) {
                     throw new IllegalStateException("Could not decode value for key: " + Arrays.toString(keyData));
                  }

                  sfsObject.put(key, decodedObject);
               }

               return sfsObject;
            } catch (SFSCodecException codecError) {
               throw new IllegalArgumentException(codecError.getMessage());
            }
         }
      }
   }

   public SFSObject resultSet2object(ResultSet resultSet) throws SQLException {
      ResultSetMetaData metaData = resultSet.getMetaData();
      SFSObject sfso = new SFSObject();
      if (resultSet.isBeforeFirst()) {
         resultSet.next();
      }

      for(int col = 1; col <= metaData.getColumnCount(); ++col) {
         String colName = metaData.getColumnLabel(col);
         int type = metaData.getColumnType(col);
         Object rawDataObj = resultSet.getObject(col);
         if (rawDataObj != null) {
            if (type == 0) {
               sfso.putNull(colName);
            } else if (type == 16) {
               sfso.putBool(colName, resultSet.getBoolean(col));
            } else if (type == 91) {
               sfso.putLong(colName, resultSet.getDate(col).getTime());
            } else if (type != 6 && type != 3 && type != 8 && type != 7) {
               if (type != 4 && type != -6 && type != 5) {
                  if (type != -1 && type != 12 && type != 1) {
                     if (type != -9 && type != -16 && type != -15) {
                        if (type == 93) {
                           sfso.putLong(colName, resultSet.getTimestamp(col).getTime());
                        } else if (type == -5) {
                           sfso.putLong(colName, resultSet.getLong(col));
                        } else if (type == -4) {
                           byte[] binData = this.getBlobData(colName, resultSet.getBinaryStream(col));
                           if (binData != null) {
                              sfso.putByteArray(colName, binData);
                           }
                        } else if (type == 2004) {
                           Blob blob = resultSet.getBlob(col);
                           sfso.putByteArray(colName, blob.getBytes(0L, (int)blob.length()));
                        } else {
                            this.logger.info("Skipping Unsupported SQL TYPE: {}, Column:{}", type, colName);
                        }
                     } else {
                        sfso.putUtfString(colName, resultSet.getNString(col));
                     }
                  } else {
                     sfso.putUtfString(colName, resultSet.getString(col));
                  }
               } else {
                  sfso.putInt(colName, resultSet.getInt(col));
               }
            } else {
               sfso.putDouble(colName, resultSet.getDouble(col));
            }
         }
      }

      return sfso;
   }

   private byte[] getBlobData(String colName, InputStream stream) {
      BufferedInputStream bis = new BufferedInputStream(stream);
      byte[] bytes = null;

      try {
         bytes = new byte[bis.available()];
          //noinspection ResultOfMethodCallIgnored
          bis.read(bytes);
      } catch (IOException var9) {
          this.logger.warn("SFSObject serialize error. Failed reading BLOB data for column: {}", colName);
      } finally {
         IOUtils.closeQuietly(bis);
      }

      return bytes;
   }

   public SFSArray resultSet2array(ResultSet rset) throws SQLException {
      SFSArray sfsa = new SFSArray();

      while(rset.next()) {
         sfsa.addSFSObject(this.resultSet2object(rset));
      }

      return sfsa;
   }

   public byte[] object2binary(ISFSObject object) {
      ByteBuffer buffer = ByteBuffer.allocate(BUFFER_CHUNK_SIZE);
      buffer.put((byte)SFSDataType.SFS_OBJECT.getTypeID());
      buffer.putShort((short)object.size());
      return this.obj2bin(object, buffer);
   }

   private byte[] obj2bin(ISFSObject object, ByteBuffer buffer) {
      for(String key : object.getKeys()) {
         SFSDataWrapper wrapper = object.get(key);
         Object dataObj = wrapper.getObject();
         buffer = this.encodeSFSObjectKey(buffer, key);
         buffer = this.encodeObject(buffer, wrapper.getTypeId(), dataObj);
      }

      int pos = buffer.position();
      byte[] result = new byte[pos];
      buffer.flip();
      buffer.get(result, 0, pos);
      return result;
   }

   public byte[] array2binary(ISFSArray array) {
      ByteBuffer buffer = ByteBuffer.allocate(BUFFER_CHUNK_SIZE);
      buffer.put((byte)SFSDataType.SFS_ARRAY.getTypeID());
      buffer.putShort((short)array.size());
      return this.arr2bin(array, buffer);
   }

   private byte[] arr2bin(ISFSArray array, ByteBuffer buffer) {
      for(SFSDataWrapper wrapper : array) {
         Object dataObj = wrapper.getObject();
         buffer = this.encodeObject(buffer, wrapper.getTypeId(), dataObj);
      }

      int pos = buffer.position();
      byte[] result = new byte[pos];
      buffer.flip();
      buffer.get(result, 0, pos);
      return result;
   }

   public void flattenObject(Map<String, Object> map, SFSObject sfsObj) {
      for(Map.Entry<String, SFSDataWrapper> entry : sfsObj) {
         String key = (String)entry.getKey();
         SFSDataWrapper value = (SFSDataWrapper)entry.getValue();
         if (value.getTypeId() == SFSDataType.SFS_OBJECT) {
            Map<String, Object> newMap = new HashMap<>();
            map.put(key, newMap);
            this.flattenObject(newMap, (SFSObject)value.getObject());
         } else if (value.getTypeId() == SFSDataType.SFS_ARRAY) {
            List<Object> newList = new ArrayList<>();
            map.put(key, newList);
            this.flattenArray(newList, (SFSArray)value.getObject());
         } else {
            map.put(key, value.getObject());
         }
      }

   }

   public void flattenArray(List<Object> array, SFSArray sfsArray) {
      for(SFSDataWrapper value : sfsArray) {
         if (value.getTypeId() == SFSDataType.SFS_OBJECT) {
            Map<String, Object> newMap = new HashMap<>();
            array.add(newMap);
            this.flattenObject(newMap, (SFSObject)value.getObject());
         } else if (value.getTypeId() == SFSDataType.SFS_ARRAY) {
            List<Object> newList = new ArrayList<>();
            array.add(newList);
            this.flattenArray(newList, (SFSArray)value.getObject());
         } else {
            array.add(value.getObject());
         }
      }

   }

   private SFSDataWrapper decodeObject(ByteBuffer buffer) throws SFSCodecException {
      SFSDataWrapper decodedObject = null;
      byte headerByte = buffer.get();
      if (headerByte == SFSDataType.NULL.getTypeID()) {
         decodedObject = this.binDecode_NULL(buffer);
      } else if (headerByte == SFSDataType.BOOL.getTypeID()) {
         decodedObject = this.binDecode_BOOL(buffer);
      } else if (headerByte == SFSDataType.BOOL_ARRAY.getTypeID()) {
         decodedObject = this.binDecode_BOOL_ARRAY(buffer);
      } else if (headerByte == SFSDataType.BYTE.getTypeID()) {
         decodedObject = this.binDecode_BYTE(buffer);
      } else if (headerByte == SFSDataType.BYTE_ARRAY.getTypeID()) {
         decodedObject = this.binDecode_BYTE_ARRAY(buffer);
      } else if (headerByte == SFSDataType.SHORT.getTypeID()) {
         decodedObject = this.binDecode_SHORT(buffer);
      } else if (headerByte == SFSDataType.SHORT_ARRAY.getTypeID()) {
         decodedObject = this.binDecode_SHORT_ARRAY(buffer);
      } else if (headerByte == SFSDataType.INT.getTypeID()) {
         decodedObject = this.binDecode_INT(buffer);
      } else if (headerByte == SFSDataType.INT_ARRAY.getTypeID()) {
         decodedObject = this.binDecode_INT_ARRAY(buffer);
      } else if (headerByte == SFSDataType.LONG.getTypeID()) {
         decodedObject = this.binDecode_LONG(buffer);
      } else if (headerByte == SFSDataType.LONG_ARRAY.getTypeID()) {
         decodedObject = this.binDecode_LONG_ARRAY(buffer);
      } else if (headerByte == SFSDataType.FLOAT.getTypeID()) {
         decodedObject = this.binDecode_FLOAT(buffer);
      } else if (headerByte == SFSDataType.FLOAT_ARRAY.getTypeID()) {
         decodedObject = this.binDecode_FLOAT_ARRAY(buffer);
      } else if (headerByte == SFSDataType.DOUBLE.getTypeID()) {
         decodedObject = this.binDecode_DOUBLE(buffer);
      } else if (headerByte == SFSDataType.DOUBLE_ARRAY.getTypeID()) {
         decodedObject = this.binDecode_DOUBLE_ARRAY(buffer);
      } else if (headerByte == SFSDataType.UTF_STRING.getTypeID()) {
         decodedObject = this.binDecode_UTF_STRING(buffer);
      } else if (headerByte == SFSDataType.TEXT.getTypeID()) {
         decodedObject = this.binDecode_TEXT(buffer);
      } else if (headerByte == SFSDataType.UTF_STRING_ARRAY.getTypeID()) {
         decodedObject = this.binDecode_UTF_STRING_ARRAY(buffer);
      } else if (headerByte == SFSDataType.SFS_ARRAY.getTypeID()) {
         buffer.position(buffer.position() - 1);
         decodedObject = new SFSDataWrapper(SFSDataType.SFS_ARRAY, this.decodeSFSArray(buffer));
      } else {
         if (headerByte != SFSDataType.SFS_OBJECT.getTypeID()) {
            throw new SFSCodecException("Unknow SFSDataType ID: " + headerByte);
         }

         buffer.position(buffer.position() - 1);
         ISFSObject sfsObj = this.decodeSFSObject(buffer);
         SFSDataType type = SFSDataType.SFS_OBJECT;
         Object finalSfsObj = sfsObj;
         if (sfsObj.containsKey("$C") && sfsObj.containsKey("$F")) {
            type = SFSDataType.CLASS;
            finalSfsObj = this.sfs2pojo(sfsObj);
         }

         decodedObject = new SFSDataWrapper(type, finalSfsObj);
      }

      return decodedObject;
   }

   @SuppressWarnings("unchecked")
   private ByteBuffer encodeObject(ByteBuffer buffer, SFSDataType typeId, Object object) {
      switch (typeId) {
         case NULL:
            buffer = this.binEncode_NULL(buffer);
            break;
         case BOOL:
            buffer = this.binEncode_BOOL(buffer, (Boolean)object);
            break;
         case BYTE:
            buffer = this.binEncode_BYTE(buffer, (Byte)object);
            break;
         case SHORT:
            buffer = this.binEncode_SHORT(buffer, (Short)object);
            break;
         case INT:
            buffer = this.binEncode_INT(buffer, (Integer)object);
            break;
         case LONG:
            buffer = this.binEncode_LONG(buffer, (Long)object);
            break;
         case FLOAT:
            buffer = this.binEncode_FLOAT(buffer, (Float)object);
            break;
         case DOUBLE:
            buffer = this.binEncode_DOUBLE(buffer, (Double)object);
            break;
         case UTF_STRING:
            buffer = this.binEncode_UTF_STRING(buffer, (String)object);
            break;
         case BOOL_ARRAY:
            buffer = this.binEncode_BOOL_ARRAY(buffer, (Collection<Boolean>)object);
            break;
         case BYTE_ARRAY:
            buffer = this.binEncode_BYTE_ARRAY(buffer, (byte[])object);
            break;
         case SHORT_ARRAY:
            buffer = this.binEncode_SHORT_ARRAY(buffer, (Collection<Short>)object);
            break;
         case INT_ARRAY:
            buffer = this.binEncode_INT_ARRAY(buffer, (Collection<Integer>)object);
            break;
         case LONG_ARRAY:
            buffer = this.binEncode_LONG_ARRAY(buffer, (Collection<Long>)object);
            break;
         case FLOAT_ARRAY:
            buffer = this.binEncode_FLOAT_ARRAY(buffer, (Collection<Float>)object);
            break;
         case DOUBLE_ARRAY:
            buffer = this.binEncode_DOUBLE_ARRAY(buffer, (Collection<Double>)object);
            break;
         case UTF_STRING_ARRAY:
            buffer = this.binEncode_UTF_STRING_ARRAY(buffer, (Collection<String>)object);
            break;
         case SFS_ARRAY:
            buffer = this.addData(buffer, this.array2binary((SFSArray)object));
            break;
         case SFS_OBJECT:
            buffer = this.addData(buffer, this.object2binary((SFSObject)object));
            break;
         case CLASS:
            buffer = this.addData(buffer, this.object2binary(this.pojo2sfs(object)));
            break;
         case TEXT:
            buffer = this.binEncode_TEXT(buffer, (String)object);
            break;
         default:
            throw new IllegalArgumentException("Unrecognized type in SFSObject serialization: " + typeId);
      }

      return buffer;
   }

   private SFSDataWrapper binDecode_NULL(ByteBuffer buffer) {
      return new SFSDataWrapper(SFSDataType.NULL, null);
   }

   private SFSDataWrapper binDecode_BOOL(ByteBuffer buffer) throws SFSCodecException {
      byte boolByte = buffer.get();
      Boolean bool = null;
      if (boolByte == 0) {
         bool = Boolean.FALSE;
      } else {
         if (boolByte != 1) {
            throw new SFSCodecException("Error decoding Bool type. Illegal value: " + bool);
         }

         bool = Boolean.TRUE;
      }

      return new SFSDataWrapper(SFSDataType.BOOL, bool);
   }

   private SFSDataWrapper binDecode_BYTE(ByteBuffer buffer) {
      byte boolByte = buffer.get();
      return new SFSDataWrapper(SFSDataType.BYTE, boolByte);
   }

   private SFSDataWrapper binDecode_SHORT(ByteBuffer buffer) {
      short shortValue = buffer.getShort();
      return new SFSDataWrapper(SFSDataType.SHORT, shortValue);
   }

   private SFSDataWrapper binDecode_INT(ByteBuffer buffer) {
      int intValue = buffer.getInt();
      return new SFSDataWrapper(SFSDataType.INT, intValue);
   }

   private SFSDataWrapper binDecode_LONG(ByteBuffer buffer) {
      long longValue = buffer.getLong();
      return new SFSDataWrapper(SFSDataType.LONG, longValue);
   }

   private SFSDataWrapper binDecode_FLOAT(ByteBuffer buffer) {
      float floatValue = buffer.getFloat();
      return new SFSDataWrapper(SFSDataType.FLOAT, floatValue);
   }

   private SFSDataWrapper binDecode_DOUBLE(ByteBuffer buffer) {
      double doubleValue = buffer.getDouble();
      return new SFSDataWrapper(SFSDataType.DOUBLE, doubleValue);
   }

   private SFSDataWrapper binDecode_UTF_STRING(ByteBuffer buffer) throws SFSCodecException {
      short strLen = buffer.getShort();
      if (strLen < 0) {
         throw new SFSCodecException("Error decoding UtfString. Negative size: " + strLen);
      } else {
         byte[] strData = new byte[strLen];
         buffer.get(strData, 0, strLen);
         String decodedString = new String(strData);
         return new SFSDataWrapper(SFSDataType.UTF_STRING, decodedString);
      }
   }

   private SFSDataWrapper binDecode_TEXT(ByteBuffer buffer) throws SFSCodecException {
      int strLen = buffer.getInt();
      if (strLen < 0) {
         throw new SFSCodecException("Error decoding UtfString. Negative size: " + strLen);
      } else {
         byte[] strData = new byte[strLen];
         buffer.get(strData, 0, strLen);
         String decodedString = new String(strData);
         return new SFSDataWrapper(SFSDataType.TEXT, decodedString);
      }
   }

   private SFSDataWrapper binDecode_BOOL_ARRAY(ByteBuffer buffer) throws SFSCodecException {
      short arraySize = this.getTypeArraySize(buffer);
      List<Boolean> array = new ArrayList<>();

      for(int j = 0; j < arraySize; ++j) {
         byte boolData = buffer.get();
         if (boolData == 0) {
            array.add(false);
         } else {
            if (boolData != 1) {
               throw new SFSCodecException("Error decoding BoolArray. Invalid bool value: " + boolData);
            }

            array.add(true);
         }
      }

      return new SFSDataWrapper(SFSDataType.BOOL_ARRAY, array);
   }

   private SFSDataWrapper binDecode_BYTE_ARRAY(ByteBuffer buffer) throws SFSCodecException {
      int arraySize = buffer.getInt();
      if (arraySize < 0) {
         throw new SFSCodecException("Error decoding typed array size. Negative size: " + arraySize);
      } else {
         byte[] byteData = new byte[arraySize];
         buffer.get(byteData, 0, arraySize);
         return new SFSDataWrapper(SFSDataType.BYTE_ARRAY, byteData);
      }
   }

   private SFSDataWrapper binDecode_SHORT_ARRAY(ByteBuffer buffer) throws SFSCodecException {
      short arraySize = this.getTypeArraySize(buffer);
      List<Short> array = new ArrayList<>();

      for(int j = 0; j < arraySize; ++j) {
         short shortValue = buffer.getShort();
         array.add(shortValue);
      }

      return new SFSDataWrapper(SFSDataType.SHORT_ARRAY, array);
   }

   private SFSDataWrapper binDecode_INT_ARRAY(ByteBuffer buffer) throws SFSCodecException {
      short arraySize = this.getTypeArraySize(buffer);
      List<Integer> array = new ArrayList<>();

      for(int j = 0; j < arraySize; ++j) {
         int intValue = buffer.getInt();
         array.add(intValue);
      }

      return new SFSDataWrapper(SFSDataType.INT_ARRAY, array);
   }

   private SFSDataWrapper binDecode_LONG_ARRAY(ByteBuffer buffer) throws SFSCodecException {
      short arraySize = this.getTypeArraySize(buffer);
      List<Long> array = new ArrayList<>();

      for(int j = 0; j < arraySize; ++j) {
         long longValue = buffer.getLong();
         array.add(longValue);
      }

      return new SFSDataWrapper(SFSDataType.LONG_ARRAY, array);
   }

   private SFSDataWrapper binDecode_FLOAT_ARRAY(ByteBuffer buffer) throws SFSCodecException {
      short arraySize = this.getTypeArraySize(buffer);
      List<Float> array = new ArrayList<>();

      for(int j = 0; j < arraySize; ++j) {
         float floatValue = buffer.getFloat();
         array.add(floatValue);
      }

      return new SFSDataWrapper(SFSDataType.FLOAT_ARRAY, array);
   }

   private SFSDataWrapper binDecode_DOUBLE_ARRAY(ByteBuffer buffer) throws SFSCodecException {
      short arraySize = this.getTypeArraySize(buffer);
      List<Double> array = new ArrayList<>();

      for(int j = 0; j < arraySize; ++j) {
         double doubleValue = buffer.getDouble();
         array.add(doubleValue);
      }

      return new SFSDataWrapper(SFSDataType.DOUBLE_ARRAY, array);
   }

   private SFSDataWrapper binDecode_UTF_STRING_ARRAY(ByteBuffer buffer) throws SFSCodecException {
      short arraySize = this.getTypeArraySize(buffer);
      List<String> array = new ArrayList<>();

      for(int j = 0; j < arraySize; ++j) {
         short strLen = buffer.getShort();
         if (strLen < 0) {
            throw new SFSCodecException("Error decoding UtfStringArray element. Element has negative size: " + strLen);
         }

         byte[] strData = new byte[strLen];
         buffer.get(strData, 0, strLen);
         array.add(new String(strData));
      }

      return new SFSDataWrapper(SFSDataType.UTF_STRING_ARRAY, array);
   }

   private short getTypeArraySize(ByteBuffer buffer) throws SFSCodecException {
      short arraySize = buffer.getShort();
      if (arraySize < 0) {
         throw new SFSCodecException("Error decoding typed array size. Negative size: " + arraySize);
      } else {
         return arraySize;
      }
   }

   private ByteBuffer binEncode_NULL(ByteBuffer buffer) {
      return this.addData(buffer, new byte[1]);
   }

   private ByteBuffer binEncode_BOOL(ByteBuffer buffer, Boolean value) {
      byte[] data = new byte[2];
      data[0] = (byte)SFSDataType.BOOL.getTypeID();
      data[1] = (byte)(value ? 1 : 0);
      return this.addData(buffer, data);
   }

   private ByteBuffer binEncode_BYTE(ByteBuffer buffer, Byte value) {
      byte[] data = new byte[2];
      data[0] = (byte)SFSDataType.BYTE.getTypeID();
      data[1] = value;
      return this.addData(buffer, data);
   }

   private ByteBuffer binEncode_SHORT(ByteBuffer buffer, Short value) {
      ByteBuffer buf = ByteBuffer.allocate(3);
      buf.put((byte)SFSDataType.SHORT.getTypeID());
      buf.putShort(value);
      return this.addData(buffer, buf.array());
   }

   private ByteBuffer binEncode_INT(ByteBuffer buffer, Integer value) {
      ByteBuffer buf = ByteBuffer.allocate(5);
      buf.put((byte)SFSDataType.INT.getTypeID());
      buf.putInt(value);
      return this.addData(buffer, buf.array());
   }

   private ByteBuffer binEncode_LONG(ByteBuffer buffer, Long value) {
      ByteBuffer buf = ByteBuffer.allocate(9);
      buf.put((byte)SFSDataType.LONG.getTypeID());
      buf.putLong(value);
      return this.addData(buffer, buf.array());
   }

   private ByteBuffer binEncode_FLOAT(ByteBuffer buffer, Float value) {
      ByteBuffer buf = ByteBuffer.allocate(5);
      buf.put((byte)SFSDataType.FLOAT.getTypeID());
      buf.putFloat(value);
      return this.addData(buffer, buf.array());
   }

   private ByteBuffer binEncode_DOUBLE(ByteBuffer buffer, Double value) {
      ByteBuffer buf = ByteBuffer.allocate(9);
      buf.put((byte)SFSDataType.DOUBLE.getTypeID());
      buf.putDouble(value);
      return this.addData(buffer, buf.array());
   }

   private ByteBuffer binEncode_UTF_STRING(ByteBuffer buffer, String value) {
      byte[] stringBytes = value.getBytes();
      ByteBuffer buf = ByteBuffer.allocate(3 + stringBytes.length);
      buf.put((byte)SFSDataType.UTF_STRING.getTypeID());
      buf.putShort((short)stringBytes.length);
      buf.put(stringBytes);
      return this.addData(buffer, buf.array());
   }

   private ByteBuffer binEncode_TEXT(ByteBuffer buffer, String value) {
      byte[] stringBytes = value.getBytes();
      ByteBuffer buf = ByteBuffer.allocate(5 + stringBytes.length);
      buf.put((byte)SFSDataType.TEXT.getTypeID());
      buf.putInt(stringBytes.length);
      buf.put(stringBytes);
      return this.addData(buffer, buf.array());
   }

   private ByteBuffer binEncode_BOOL_ARRAY(ByteBuffer buffer, Collection<Boolean> value) {
      ByteBuffer buf = ByteBuffer.allocate(3 + value.size());
      buf.put((byte)SFSDataType.BOOL_ARRAY.getTypeID());
      buf.putShort((short)value.size());

      for(boolean b : value) {
         buf.put((byte)(b ? 1 : 0));
      }

      return this.addData(buffer, buf.array());
   }

   private ByteBuffer binEncode_BYTE_ARRAY(ByteBuffer buffer, byte[] value) {
      ByteBuffer buf = ByteBuffer.allocate(5 + value.length);
      buf.put((byte)SFSDataType.BYTE_ARRAY.getTypeID());
      buf.putInt(value.length);
      buf.put(value);
      return this.addData(buffer, buf.array());
   }

   private ByteBuffer binEncode_SHORT_ARRAY(ByteBuffer buffer, Collection<Short> value) {
      ByteBuffer buf = ByteBuffer.allocate(3 + 2 * value.size());
      buf.put((byte)SFSDataType.SHORT_ARRAY.getTypeID());
      buf.putShort((short)value.size());

      for(short item : value) {
         buf.putShort(item);
      }

      return this.addData(buffer, buf.array());
   }

   private ByteBuffer binEncode_INT_ARRAY(ByteBuffer buffer, Collection<Integer> value) {
      ByteBuffer buf = ByteBuffer.allocate(3 + 4 * value.size());
      buf.put((byte)SFSDataType.INT_ARRAY.getTypeID());
      buf.putShort((short)value.size());

      for(int item : value) {
         buf.putInt(item);
      }

      return this.addData(buffer, buf.array());
   }

   private ByteBuffer binEncode_LONG_ARRAY(ByteBuffer buffer, Collection<Long> value) {
      ByteBuffer buf = ByteBuffer.allocate(3 + 8 * value.size());
      buf.put((byte)SFSDataType.LONG_ARRAY.getTypeID());
      buf.putShort((short)value.size());

      for(long item : value) {
         buf.putLong(item);
      }

      return this.addData(buffer, buf.array());
   }

   private ByteBuffer binEncode_FLOAT_ARRAY(ByteBuffer buffer, Collection<Float> value) {
      ByteBuffer buf = ByteBuffer.allocate(3 + 4 * value.size());
      buf.put((byte)SFSDataType.FLOAT_ARRAY.getTypeID());
      buf.putShort((short)value.size());

      for(float item : value) {
         buf.putFloat(item);
      }

      return this.addData(buffer, buf.array());
   }

   private ByteBuffer binEncode_DOUBLE_ARRAY(ByteBuffer buffer, Collection<Double> value) {
      ByteBuffer buf = ByteBuffer.allocate(3 + 8 * value.size());
      buf.put((byte)SFSDataType.DOUBLE_ARRAY.getTypeID());
      buf.putShort((short)value.size());

      for(double item : value) {
         buf.putDouble(item);
      }

      return this.addData(buffer, buf.array());
   }

   private ByteBuffer binEncode_UTF_STRING_ARRAY(ByteBuffer buffer, Collection<String> value) {
      int stringDataLen = 0;
      byte[][] binStrings = new byte[value.size()][];
      int count = 0;

      for(String item : value) {
         byte[] binStr = item.getBytes();
         binStrings[count++] = binStr;
         stringDataLen += 2 + binStr.length;
      }

      ByteBuffer buf = ByteBuffer.allocate(3 + stringDataLen);
      buf.put((byte)SFSDataType.UTF_STRING_ARRAY.getTypeID());
      buf.putShort((short)value.size());

      for(byte[] binItem : binStrings) {
         buf.putShort((short)binItem.length);
         buf.put(binItem);
      }

      return this.addData(buffer, buf.array());
   }

   private ByteBuffer encodeSFSObjectKey(ByteBuffer buffer, String value) {
      ByteBuffer buf = ByteBuffer.allocate(2 + value.length());
      buf.putShort((short)value.length());
      buf.put(value.getBytes());
      return this.addData(buffer, buf.array());
   }

   private ByteBuffer addData(ByteBuffer buffer, byte[] newData) {
      if (buffer.remaining() < newData.length) {
         int newSize = BUFFER_CHUNK_SIZE;
         if (newSize < newData.length) {
            newSize = newData.length;
         }

         ByteBuffer newBuffer = ByteBuffer.allocate(buffer.capacity() + newSize);
         buffer.flip();
         newBuffer.put(buffer);
         buffer = newBuffer;
      }

      buffer.put(newData);
      return buffer;
   }

   public ISFSObject pojo2sfs(Object pojo) {
      ISFSObject sfsObj = SFSObject.newInstance();

      try {
         this.convertPojo(pojo, sfsObj);
         return sfsObj;
      } catch (Exception e) {
         throw new SFSRuntimeException(e);
      }
   }

   private void convertPojo(Object pojo, ISFSObject sfsObj) throws Exception {
      Class<?> pojoClazz = pojo.getClass();
      String classFullName = pojoClazz.getCanonicalName();
      if (classFullName == null) {
         throw new IllegalArgumentException("Anonymous classes cannot be serialized!");
      } else if (!(pojo instanceof SerializableSFSType)) {
         throw new IllegalStateException("Cannot serialize object: " + pojo + ", type: " + classFullName + " -- It doesn't implement the SerializableSFSType interface");
      } else {
         ISFSArray fieldList = SFSArray.newInstance();
         sfsObj.putUtfString("$C", classFullName);
         sfsObj.putSFSArray("$F", fieldList);

         Field[] var9;
         for(Field field : var9 = pojoClazz.getDeclaredFields()) {
            try {
               int modifiers = field.getModifiers();
               if (!Modifier.isTransient(modifiers) && !Modifier.isStatic(modifiers)) {
                  String fieldName = field.getName();
                  Object fieldValue = null;
                  if (Modifier.isPublic(modifiers)) {
                     fieldValue = field.get(pojo);
                  } else {
                     fieldValue = this.readValueFromGetter(fieldName, field.getType().getSimpleName(), pojo);
                  }

                  ISFSObject fieldDescriptor = SFSObject.newInstance();
                  fieldDescriptor.putUtfString("N", fieldName);
                  fieldDescriptor.put("V", this.wrapPojoField(fieldValue));
                  fieldList.addSFSObject(fieldDescriptor);
               }
            } catch (NoSuchMethodException err) {
               this.logger.info("-- No public getter -- Serializer skipping private field: " + field.getName() + ", from class: " + pojoClazz);
               err.printStackTrace();
            }
         }

      }
   }

   private Object readValueFromGetter(String fieldName, String type, Object pojo) throws Exception {
      Object value = null;
      boolean isBool = type.equalsIgnoreCase("boolean");
      String getterName = isBool ? "is" + StringUtils.capitalize(fieldName) : "get" + StringUtils.capitalize(fieldName);
      Method getterMethod = pojo.getClass().getMethod(getterName);
      value = getterMethod.invoke(pojo);
      return value;
   }

   private SFSDataWrapper wrapPojoField(Object value) {
      if (value == null) {
         return new SFSDataWrapper(SFSDataType.NULL, (Object)null);
      } else {
         SFSDataWrapper wrapper = null;
         if (value instanceof Boolean) {
            wrapper = new SFSDataWrapper(SFSDataType.BOOL, value);
         } else if (value instanceof Byte) {
            wrapper = new SFSDataWrapper(SFSDataType.BYTE, value);
         } else if (value instanceof Short) {
            wrapper = new SFSDataWrapper(SFSDataType.SHORT, value);
         } else if (value instanceof Integer) {
            wrapper = new SFSDataWrapper(SFSDataType.INT, value);
         } else if (value instanceof Long) {
            wrapper = new SFSDataWrapper(SFSDataType.LONG, value);
         } else if (value instanceof Float) {
            wrapper = new SFSDataWrapper(SFSDataType.FLOAT, value);
         } else if (value instanceof Double) {
            wrapper = new SFSDataWrapper(SFSDataType.DOUBLE, value);
         } else if (value instanceof String) {
            wrapper = new SFSDataWrapper(SFSDataType.UTF_STRING, value);
         } else if (value.getClass().isArray()) {
            wrapper = new SFSDataWrapper(SFSDataType.SFS_ARRAY, this.unrollArray((Object[])value));
         } else if (value instanceof Collection) {
            wrapper = new SFSDataWrapper(SFSDataType.SFS_ARRAY, this.unrollCollection((Collection<?>)value));
         } else if (value instanceof Map) {
            wrapper = new SFSDataWrapper(SFSDataType.SFS_OBJECT, this.unrollMap((Map<?, ?>)value));
         } else if (value instanceof SerializableSFSType) {
            wrapper = new SFSDataWrapper(SFSDataType.SFS_OBJECT, this.pojo2sfs(value));
         }

         return wrapper;
      }
   }

   private ISFSArray unrollArray(Object[] arr) {
      ISFSArray array = SFSArray.newInstance();

      for(Object item : arr) {
         array.add(this.wrapPojoField(item));
      }

      return array;
   }

   private ISFSArray unrollCollection(Collection collection) {
      ISFSArray array = SFSArray.newInstance();

      for(Object item : collection) {
         array.add(this.wrapPojoField(item));
      }

      return array;
   }

   private ISFSObject unrollMap(Map<?, ?> map) {
      ISFSObject sfsObj = SFSObject.newInstance();

      for(var item : map.entrySet()) {
         Object key = item.getKey();
         if (key instanceof String) {
            sfsObj.put((String)key, this.wrapPojoField(item.getValue()));
         }
      }

      return sfsObj;
   }

   public Object sfs2pojo(ISFSObject sfsObj) {
      Object pojo = null;
      if (!sfsObj.containsKey("$C") && !sfsObj.containsKey("$F")) {
         throw new SFSRuntimeException("The SFSObject passed does not represent any serialized class.");
      } else {
         try {
            String className = sfsObj.getUtfString("$C");
            Class<?> theClass = Class.forName(className);
            pojo = theClass.newInstance();
            if (!(pojo instanceof SerializableSFSType)) {
               throw new IllegalStateException("Cannot deserialize object: " + pojo + ", type: " + className + " -- It doesn't implement the SerializableSFSType interface");
            } else {
               this.convertSFSObject(sfsObj.getSFSArray("$F"), pojo);
               return pojo;
            }
         } catch (Exception e) {
            throw new SFSRuntimeException(e);
         }
      }
   }

   private void convertSFSObject(ISFSArray fieldList, Object pojo) throws Exception {
      for(int j = 0; j < fieldList.size(); ++j) {
         ISFSObject fieldDescriptor = fieldList.getSFSObject(j);
         String fieldName = fieldDescriptor.getUtfString("N");
         Object fieldValue = this.unwrapPojoField(fieldDescriptor.get("V"));
         this.setObjectField(pojo, fieldName, fieldValue);
      }

   }

   private void setObjectField(Object pojo, String fieldName, Object fieldValue) throws Exception {
      Class pojoClass = pojo.getClass();
      Field field = pojoClass.getDeclaredField(fieldName);
      int fieldModifier = field.getModifiers();
      if (!Modifier.isTransient(fieldModifier)) {
         boolean isArray = field.getType().isArray();
         if (isArray) {
            if (!(fieldValue instanceof Collection)) {
               throw new SFSRuntimeException("Problem during SFSObject => POJO conversion. Found array field in POJO: " + fieldName + ", but data is not a Collection!");
            }

            Collection collection = (Collection<?>)fieldValue;
            Object var11 = collection.toArray();
            int arraySize = collection.size();
            Object typedArray = Array.newInstance(field.getType().getComponentType(), arraySize);
            System.arraycopy(var11, 0, typedArray, 0, arraySize);
            fieldValue = typedArray;
         } else if (fieldValue instanceof Collection<?> collection) {
             String fieldClass = field.getType().getSimpleName();
            if (fieldClass.equals("ArrayList") || fieldClass.equals("List")) {
               fieldValue = new ArrayList<>(collection);
            }

            if (fieldClass.equals("CopyOnWriteArrayList")) {
               fieldValue = new CopyOnWriteArrayList<>(collection);
            } else if (fieldClass.equals("LinkedList")) {
               fieldValue = new LinkedList<>(collection);
            } else if (fieldClass.equals("Vector")) {
               fieldValue = new Vector<>(collection);
            } else if (!fieldClass.equals("Set") && !fieldClass.equals("HashSet")) {
               if (fieldClass.equals("LinkedHashSet")) {
                  fieldValue = new LinkedHashSet<>(collection);
               } else if (fieldClass.equals("TreeSet")) {
                  fieldValue = new TreeSet<>(collection);
               } else if (fieldClass.equals("CopyOnWriteArraySet")) {
                  fieldValue = new CopyOnWriteArraySet<>(collection);
               } else if (!fieldClass.equals("Queue") && !fieldClass.equals("PriorityQueue")) {
                  if (!fieldClass.equals("BlockingQueue") && !fieldClass.equals("LinkedBlockingQueue")) {
                     if (fieldClass.equals("PriorityBlockingQueue")) {
                        fieldValue = new PriorityBlockingQueue<>(collection);
                     } else if (fieldClass.equals("ConcurrentLinkedQueue")) {
                        fieldValue = new ConcurrentLinkedQueue<>(collection);
                     } else if (fieldClass.equals("DelayQueue")) {
                        fieldValue = new DelayQueue(collection);
                     } else if (!fieldClass.equals("Deque") && !fieldClass.equals("ArrayDeque")) {
                        if (fieldClass.equals("LinkedBlockingDeque")) {
                           fieldValue = new LinkedBlockingDeque<>(collection);
                        }
                     } else {
                        fieldValue = new ArrayDeque<>(collection);
                     }
                  } else {
                     fieldValue = new LinkedBlockingQueue<>(collection);
                  }
               } else {
                  fieldValue = new PriorityQueue<>(collection);
               }
            } else {
               fieldValue = new HashSet<>(collection);
            }
         }

         if (Modifier.isPublic(fieldModifier)) {
            field.set(pojo, fieldValue);
         } else {
            this.writeValueFromSetter(field, pojo, fieldValue);
         }

      }
   }

   private void writeValueFromSetter(Field field, Object pojo, Object fieldValue) throws Exception {
      String setterName = "set" + StringUtils.capitalize(field.getName());

      try {
         Method setterMethod = pojo.getClass().getMethod(setterName, field.getType());
         setterMethod.invoke(pojo, fieldValue);
      } catch (NoSuchMethodException var7) {
         this.logger.info("-- No public setter -- Serializer skipping private field: " + field.getName() + ", from class: " + pojo.getClass().getName());
      }

   }

   private Object unwrapPojoField(SFSDataWrapper wrapper) {
      Object obj = null;
      SFSDataType type = wrapper.getTypeId();
      if (type.getTypeID() <= SFSDataType.UTF_STRING.getTypeID()) {
         obj = wrapper.getObject();
      } else if (type == SFSDataType.SFS_ARRAY) {
         obj = this.rebuildArray((ISFSArray)wrapper.getObject());
      } else if (type == SFSDataType.SFS_OBJECT) {
         obj = this.rebuildMap((ISFSObject)wrapper.getObject());
      } else if (type == SFSDataType.CLASS) {
         obj = wrapper.getObject();
      }

      return obj;
   }

   private Object rebuildArray(ISFSArray sfsArray) {
      Collection<Object> collection = new ArrayList<>();
      Iterator<SFSDataWrapper> iter = sfsArray.iterator();

      while(iter.hasNext()) {
         Object item = this.unwrapPojoField((SFSDataWrapper)iter.next());
         collection.add(item);
      }

      return collection;
   }

   private Object rebuildMap(ISFSObject sfsObj) {
      Map<String, Object> map = new HashMap();

      for(String key : sfsObj.getKeys()) {
         SFSDataWrapper wrapper = sfsObj.get(key);
         map.put(key, this.unwrapPojoField(wrapper));
      }

      return map;
   }

}
