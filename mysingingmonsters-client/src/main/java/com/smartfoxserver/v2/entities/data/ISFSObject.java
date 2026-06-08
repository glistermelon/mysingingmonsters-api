package com.smartfoxserver.v2.entities.data;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public interface ISFSObject extends Iterable<Map.Entry<String, SFSDataWrapper>> {
   boolean isNull(String var1);

   boolean containsKey(String var1);

   boolean removeElement(String var1);

   Set<String> getKeys();

   int size();

   Iterator<Map.Entry<String, SFSDataWrapper>> iterator();

   byte[] toBinary();

   String getDump();

   String getDump(boolean var1);

   String getHexDump();

   SFSDataWrapper get(String var1);

   Boolean getBool(String var1);

   Byte getByte(String var1);

   Integer getUnsignedByte(String var1);

   Short getShort(String var1);

   Integer getInt(String var1);

   Long getLong(String var1);

   Float getFloat(String var1);

   Double getDouble(String var1);

   String getUtfString(String var1);

   String getText(String var1);

   Collection<Boolean> getBoolArray(String var1);

   byte[] getByteArray(String var1);

   Collection<Integer> getUnsignedByteArray(String var1);

   Collection<Short> getShortArray(String var1);

   Collection<Integer> getIntArray(String var1);

   Collection<Long> getLongArray(String var1);

   Collection<Float> getFloatArray(String var1);

   Collection<Double> getDoubleArray(String var1);

   Collection<String> getUtfStringArray(String var1);

   ISFSArray getSFSArray(String var1);

   ISFSObject getSFSObject(String var1);

   Object getClass(String var1);

   void putNull(String var1);

   void putBool(String var1, boolean var2);

   void putByte(String var1, byte var2);

   void putShort(String var1, short var2);

   void putInt(String var1, int var2);

   void putLong(String var1, long var2);

   void putFloat(String var1, float var2);

   void putDouble(String var1, double var2);

   void putUtfString(String var1, String var2);

   void putText(String var1, String var2);

   void putBoolArray(String var1, Collection<Boolean> var2);

   void putByteArray(String var1, byte[] var2);

   void putShortArray(String var1, Collection<Short> var2);

   void putIntArray(String var1, Collection<Integer> var2);

   void putLongArray(String var1, Collection<Long> var2);

   void putFloatArray(String var1, Collection<Float> var2);

   void putDoubleArray(String var1, Collection<Double> var2);

   void putUtfStringArray(String var1, Collection<String> var2);

   void putSFSArray(String var1, ISFSArray var2);

   void putSFSObject(String var1, ISFSObject var2);

   void putClass(String var1, Object var2);

   void put(String var1, SFSDataWrapper var2);
}
