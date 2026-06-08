package com.smartfoxserver.v2.entities.data;

import com.smartfoxserver.bitswarm.util.ByteUtils;
import com.smartfoxserver.v2.protocol.serialization.DefaultObjectDumpFormatter;
import com.smartfoxserver.v2.protocol.serialization.DefaultSFSDataSerializer;
import com.smartfoxserver.v2.protocol.serialization.ISFSDataSerializer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class SFSArray implements ISFSArray {
   private ISFSDataSerializer serializer = DefaultSFSDataSerializer.getInstance();
   private List<SFSDataWrapper> dataHolder = new ArrayList();

   public static SFSArray newFromBinaryData(byte[] bytes) {
      return (SFSArray)DefaultSFSDataSerializer.getInstance().binary2array(bytes);
   }

   public static SFSArray newFromResultSet(ResultSet rset) throws SQLException {
      return DefaultSFSDataSerializer.getInstance().resultSet2array(rset);
   }

   public static SFSArray newInstance() {
      return new SFSArray();
   }

   public String getDump() {
      return this.size() == 0 ? "[ Empty SFSArray ]" : DefaultObjectDumpFormatter.prettyPrintDump(this.dump());
   }

   public String getDump(boolean noFormat) {
      return !noFormat ? this.dump() : this.getDump();
   }

   private String dump() {
      StringBuilder sb = new StringBuilder();
      sb.append('{');
      Object objDump = null;

      for(SFSDataWrapper wrappedObject : this.dataHolder) {
         if (wrappedObject.getTypeId() == SFSDataType.SFS_OBJECT) {
            objDump = ((ISFSObject)wrappedObject.getObject()).getDump(false);
         } else if (wrappedObject.getTypeId() == SFSDataType.SFS_ARRAY) {
            objDump = ((ISFSArray)wrappedObject.getObject()).getDump(false);
         } else if (wrappedObject.getTypeId() == SFSDataType.BYTE_ARRAY) {
            objDump = DefaultObjectDumpFormatter.prettyPrintByteArray((byte[])wrappedObject.getObject());
         } else if (wrappedObject.getTypeId() == SFSDataType.CLASS) {
            objDump = wrappedObject.getObject().getClass().getName();
         } else {
            objDump = wrappedObject.getObject();
         }

         sb.append(" (").append(wrappedObject.getTypeId().name().toLowerCase()).append(") ").append(objDump).append(';');
      }

      if (this.size() > 0) {
         sb.delete(sb.length() - 1, sb.length());
      }

      sb.append('}');
      return sb.toString();
   }

   public String getHexDump() {
      return ByteUtils.fullHexDump(this.toBinary());
   }

   public byte[] toBinary() {
      return this.serializer.array2binary(this);
   }

   public boolean isNull(int index) {
      SFSDataWrapper wrapper = (SFSDataWrapper)this.dataHolder.get(index);
      if (wrapper == null) {
         return false;
      } else {
         return wrapper.getTypeId() == SFSDataType.NULL;
      }
   }

   public SFSDataWrapper get(int index) {
      return (SFSDataWrapper)this.dataHolder.get(index);
   }

   public Boolean getBool(int index) {
      SFSDataWrapper wrapper = (SFSDataWrapper)this.dataHolder.get(index);
      return wrapper != null ? (Boolean)wrapper.getObject() : null;
   }

   public Byte getByte(int index) {
      SFSDataWrapper wrapper = (SFSDataWrapper)this.dataHolder.get(index);
      return wrapper != null ? (Byte)wrapper.getObject() : null;
   }

   public Integer getUnsignedByte(int index) {
      SFSDataWrapper wrapper = (SFSDataWrapper)this.dataHolder.get(index);
      return wrapper != null ? DefaultSFSDataSerializer.getInstance().getUnsignedByte((Byte)wrapper.getObject()) : null;
   }

   public Short getShort(int index) {
      SFSDataWrapper wrapper = (SFSDataWrapper)this.dataHolder.get(index);
      return wrapper != null ? (Short)wrapper.getObject() : null;
   }

   public Integer getInt(int index) {
      SFSDataWrapper wrapper = (SFSDataWrapper)this.dataHolder.get(index);
      return wrapper != null ? (Integer)wrapper.getObject() : null;
   }

   public Long getLong(int index) {
      SFSDataWrapper wrapper = (SFSDataWrapper)this.dataHolder.get(index);
      return wrapper != null ? (Long)wrapper.getObject() : null;
   }

   public Float getFloat(int index) {
      SFSDataWrapper wrapper = (SFSDataWrapper)this.dataHolder.get(index);
      return wrapper != null ? (Float)wrapper.getObject() : null;
   }

   public Double getDouble(int index) {
      SFSDataWrapper wrapper = (SFSDataWrapper)this.dataHolder.get(index);
      return wrapper != null ? (Double)wrapper.getObject() : null;
   }

   public String getUtfString(int index) {
      SFSDataWrapper wrapper = (SFSDataWrapper)this.dataHolder.get(index);
      return wrapper != null ? (String)wrapper.getObject() : null;
   }

   public String getText(int index) {
      return this.getUtfString(index);
   }

   public Collection<Boolean> getBoolArray(int index) {
      SFSDataWrapper wrapper = (SFSDataWrapper)this.dataHolder.get(index);
      return wrapper != null ? (Collection)wrapper.getObject() : null;
   }

   public byte[] getByteArray(int index) {
      SFSDataWrapper wrapper = (SFSDataWrapper)this.dataHolder.get(index);
      return wrapper != null ? (byte[])wrapper.getObject() : null;
   }

   public Collection<Integer> getUnsignedByteArray(int index) {
      SFSDataWrapper wrapper = (SFSDataWrapper)this.dataHolder.get(index);
      if (wrapper == null) {
         return null;
      } else {
         DefaultSFSDataSerializer serializer = DefaultSFSDataSerializer.getInstance();
         Collection<Integer> intCollection = new ArrayList();

         byte[] var8;
         for(byte b : var8 = (byte[])wrapper.getObject()) {
            intCollection.add(serializer.getUnsignedByte(b));
         }

         return intCollection;
      }
   }

   public Collection<Short> getShortArray(int index) {
      SFSDataWrapper wrapper = (SFSDataWrapper)this.dataHolder.get(index);
      return wrapper != null ? (Collection)wrapper.getObject() : null;
   }

   public Collection<Integer> getIntArray(int index) {
      SFSDataWrapper wrapper = (SFSDataWrapper)this.dataHolder.get(index);
      return wrapper != null ? (Collection)wrapper.getObject() : null;
   }

   public Collection<Long> getLongArray(int index) {
      SFSDataWrapper wrapper = (SFSDataWrapper)this.dataHolder.get(index);
      return wrapper != null ? (Collection)wrapper.getObject() : null;
   }

   public Collection<Float> getFloatArray(int index) {
      SFSDataWrapper wrapper = (SFSDataWrapper)this.dataHolder.get(index);
      return wrapper != null ? (Collection)wrapper.getObject() : null;
   }

   public Collection<Double> getDoubleArray(int index) {
      SFSDataWrapper wrapper = (SFSDataWrapper)this.dataHolder.get(index);
      return wrapper != null ? (Collection)wrapper.getObject() : null;
   }

   public Collection<String> getUtfStringArray(int index) {
      SFSDataWrapper wrapper = (SFSDataWrapper)this.dataHolder.get(index);
      return wrapper != null ? (Collection)wrapper.getObject() : null;
   }

   public ISFSArray getSFSArray(int index) {
      SFSDataWrapper wrapper = (SFSDataWrapper)this.dataHolder.get(index);
      return wrapper != null ? (ISFSArray)wrapper.getObject() : null;
   }

   public ISFSObject getSFSObject(int index) {
      SFSDataWrapper wrapper = (SFSDataWrapper)this.dataHolder.get(index);
      return wrapper != null ? (ISFSObject)wrapper.getObject() : null;
   }

   public Object getClass(int index) {
      SFSDataWrapper wrapper = (SFSDataWrapper)this.dataHolder.get(index);
      return wrapper != null ? wrapper.getObject() : null;
   }

   public void addBool(boolean value) {
      this.addObject(value, SFSDataType.BOOL);
   }

   public void addBoolArray(Collection<Boolean> value) {
      this.addObject(value, SFSDataType.BOOL_ARRAY);
   }

   public void addByte(byte value) {
      this.addObject(value, SFSDataType.BYTE);
   }

   public void addByteArray(byte[] value) {
      this.addObject(value, SFSDataType.BYTE_ARRAY);
   }

   public void addDouble(double value) {
      this.addObject(value, SFSDataType.DOUBLE);
   }

   public void addDoubleArray(Collection<Double> value) {
      this.addObject(value, SFSDataType.DOUBLE_ARRAY);
   }

   public void addFloat(float value) {
      this.addObject(value, SFSDataType.FLOAT);
   }

   public void addFloatArray(Collection<Float> value) {
      this.addObject(value, SFSDataType.FLOAT_ARRAY);
   }

   public void addInt(int value) {
      this.addObject(value, SFSDataType.INT);
   }

   public void addIntArray(Collection<Integer> value) {
      this.addObject(value, SFSDataType.INT_ARRAY);
   }

   public void addLong(long value) {
      this.addObject(value, SFSDataType.LONG);
   }

   public void addLongArray(Collection<Long> value) {
      this.addObject(value, SFSDataType.LONG_ARRAY);
   }

   public void addNull() {
      this.addObject((Object)null, SFSDataType.NULL);
   }

   public void addSFSArray(ISFSArray value) {
      this.addObject(value, SFSDataType.SFS_ARRAY);
   }

   public void addSFSObject(ISFSObject value) {
      this.addObject(value, SFSDataType.SFS_OBJECT);
   }

   public void addShort(short value) {
      this.addObject(value, SFSDataType.SHORT);
   }

   public void addShortArray(Collection<Short> value) {
      this.addObject(value, SFSDataType.SHORT_ARRAY);
   }

   public void addUtfString(String value) {
      this.addObject(value, SFSDataType.UTF_STRING);
   }

   public void addText(String value) {
      this.addObject(value, SFSDataType.TEXT);
   }

   public void addUtfStringArray(Collection<String> value) {
      this.addObject(value, SFSDataType.UTF_STRING_ARRAY);
   }

   public void addClass(Object o) {
      this.addObject(o, SFSDataType.CLASS);
   }

   public void add(SFSDataWrapper wrappedObject) {
      this.dataHolder.add(wrappedObject);
   }

   public boolean contains(Object obj) {
      if (!(obj instanceof ISFSArray) && !(obj instanceof ISFSObject)) {
         boolean found = false;
         Iterator<SFSDataWrapper> iter = this.dataHolder.iterator();

         while(iter.hasNext()) {
            Object item = ((SFSDataWrapper)iter.next()).getObject();
            if (item.equals(obj)) {
               found = true;
               break;
            }
         }

         return found;
      } else {
         throw new UnsupportedOperationException("ISFSArray and ISFSObject are not supported by this method.");
      }
   }

   public Object getElementAt(int index) {
      Object item = null;
      SFSDataWrapper wrapper = (SFSDataWrapper)this.dataHolder.get(index);
      if (wrapper != null) {
      }

      item = wrapper.getObject();
      return item;
   }

   public Iterator<SFSDataWrapper> iterator() {
      return this.dataHolder.iterator();
   }

   public void removeElementAt(int index) {
      this.dataHolder.remove(index);
   }

   public int size() {
      return this.dataHolder.size();
   }

   public String toString() {
      return "[SFSArray, size: " + this.size() + "]";
   }

   private void addObject(Object value, SFSDataType typeId) {
      this.dataHolder.add(new SFSDataWrapper(typeId, value));
   }

   private List<Object> flatten() {
      List<Object> list = new ArrayList();
      DefaultSFSDataSerializer.getInstance().flattenArray(list, this);
      return list;
   }
}
