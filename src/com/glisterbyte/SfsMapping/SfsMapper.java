package com.glisterbyte.SfsMapping;

import com.glisterbyte.SfsMapping.SfsMapperException.InaccessibleField;
import com.glisterbyte.SfsMapping.SfsMapperException.InstantiationFailed;
import com.glisterbyte.SfsMapping.SfsMapperException.MissingKey;
import com.glisterbyte.SfsMapping.SfsMapperException.UnmappableType;
import com.glisterbyte.SingingMonsters.SfsModels.Server.BuyIslandResponse;
import com.smartfoxserver.v2.entities.data.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class SfsMapper {

    private static final Logger logger = LoggerFactory.getLogger(SfsMapper.class);

    public static <T> T mapSFSObjectToClass(Class<T> tClass, ISFSObject sfsObject) {

        T obj;
        try {
            obj = tClass.getDeclaredConstructor().newInstance();
        }
        catch (
                NoSuchMethodException
                | InvocationTargetException
                | InstantiationException
                |IllegalAccessException ex
        ) {
            throw new InstantiationFailed("Instantiating class '" + tClass.getName() + "' failed.", ex);
        }


        for (Field field : getFields(tClass)) {

            // Uncomment this if this ever needs to work on non-public fields
            // field.setAccessible(true);

            if (field.isAnnotationPresent(SfsMapperIgnore.class)) continue;

            try {

                if (field.isAnnotationPresent(SfsObjectField.class)) {
                    field.set(obj, sfsObject);
                    continue;
                }

                String key = field.isAnnotationPresent(SfsKey.class)
                        ? field.getAnnotation(SfsKey.class).value()
                        : camelToSnakeCase(field.getName());

                if (!sfsObject.containsKey(key)) {
                    if (field.isAnnotationPresent(SfsOptional.class)) {
                        field.set(obj, null);
                        continue;
                    }
                    throw new MissingKey("Missing key: '" + key + "'");
                }

                var type = field.getType();

                if (type == int.class || type == Integer.class) {
                    var value = sfsObject.get(key);
                    if (value.getTypeId() != SFSDataType.INT) {
                        throw new MissingKey("Key lacks int value: '" + key + "'");
                    }
                    field.set(obj, value.getObject());
                } else if (type == long.class || type == Long.class) {
                    var value = sfsObject.get(key);
                    if (value.getTypeId() != SFSDataType.LONG) {
                        throw new MissingKey("Key lacks long value: '" + key + "'");
                    }
                    field.set(obj, value.getObject());
                } else if (type == short.class || type == Short.class) {
                    var value = sfsObject.get(key);
                    if (value.getTypeId() != SFSDataType.SHORT) {
                        throw new MissingKey("Key lacks short value: '" + key + "'");
                    }
                    field.set(obj, value.getObject());
                } else if (type == double.class || type == Double.class) {
                    var value = sfsObject.get(key);
                    if (value.getTypeId() != SFSDataType.DOUBLE) {
                        throw new MissingKey("Key lacks double value: '" + key + "'");
                    }
                    field.set(obj, value.getObject());
                } else if (type == boolean.class || type == Boolean.class) {
                    var value = sfsObject.get(key);
                    if (value.getTypeId() != SFSDataType.BOOL) {
                        throw new MissingKey("Key lacks boolean value: '" + key + "'");
                    }
                    field.set(obj, value.getObject());
                } else if (type == String.class) {
                    var value = sfsObject.get(key);
                    if (value.getTypeId() != SFSDataType.UTF_STRING) {
                        throw new MissingKey("Key lacks string value: '" + key + "'");
                    }
                    field.set(obj, value.getObject());
                }
                else if (List.class.isAssignableFrom(type)) {

                    if (!field.isAnnotationPresent(SfsArrayElementType.class)) {
                        throw new UnmappableType("Can't map unannotated list '" + key + "'");
                    }
                    Class<?> elementType = field.getAnnotation(SfsArrayElementType.class).value();

                    SFSDataWrapper array = sfsObject.get(key);

                    if (
                            // The object can contain either a JSON list or another type of list
                            field.isAnnotationPresent(SfsJsonArray.class)
                            && array.getTypeId() == SFSDataType.UTF_STRING
                    ) {
                        String jsonStr = sfsObject.getUtfString(key);
                        ISFSObject jsonObject = SFSObject.newFromJsonData(
                                "{\"k\":" + jsonStr + "}"
                        );
                        sfsObject.put(key, jsonObject.get("k"));
                        array = sfsObject.get(key);
                    }

                    List<Object> list = new ArrayList<>();

                    if (elementType == Integer.class) {
                        if (array.getTypeId() == SFSDataType.INT_ARRAY) {
                            list.addAll(sfsObject.getIntArray(key));
                        }
                        else if (array.getTypeId() == SFSDataType.SFS_ARRAY) {
                            var sfsArray = sfsObject.getSFSArray(key);
                            for (int i = 0; i < sfsArray.size(); i++) {
                                list.add(sfsArray.getInt(i));
                            }
                        }
                        else {
                            throw new UnmappableType("Can't map '" + array.getTypeId() + "' for key '" + key + "'");
                        }
                    }
                    else if (elementType == Long.class) {
                        if (array.getTypeId() == SFSDataType.LONG_ARRAY) {
                            list.addAll(sfsObject.getLongArray(key));
                        }
                        else if (array.getTypeId() == SFSDataType.SFS_ARRAY) {
                            var sfsArray = sfsObject.getSFSArray(key);
                            for (int i = 0; i < sfsArray.size(); i++) {
                                list.add(sfsArray.getLong(i));
                            }
                        }
                        else {
                            throw new UnmappableType("Can't map '" + array.getTypeId() + "' for key '" + key + "'");
                        }
                    }
                    else if (elementType == Short.class) {
                        if (array.getTypeId() == SFSDataType.SFS_ARRAY) {
                            var sfsArray = sfsObject.getSFSArray(key);
                            for (int i = 0; i < sfsArray.size(); i++) {
                                list.add(sfsArray.getShort(i));
                            }
                        }
                        else {
                            throw new UnmappableType("Can't map '" + array.getTypeId() + "' for key '" + key + "'");
                        }
                    }
                    else if (elementType == Double.class) {
                        if (array.getTypeId() == SFSDataType.DOUBLE_ARRAY) {
                            list.addAll(sfsObject.getDoubleArray(key));
                        }
                        else if (array.getTypeId() == SFSDataType.SFS_ARRAY) {
                            var sfsArray = sfsObject.getSFSArray(key);
                            for (int i = 0; i < sfsArray.size(); i++) {
                                list.add(sfsArray.getDouble(i));
                            }
                        }
                        else {
                            throw new UnmappableType("Can't map '" + array.getTypeId() + "' for key '" + key + "'");
                        }
                    }
                    else if (elementType == Boolean.class) {
                        if (array.getTypeId() == SFSDataType.BOOL_ARRAY) {
                            list.addAll(sfsObject.getBoolArray(key));
                        }
                        else if (array.getTypeId() == SFSDataType.SFS_ARRAY) {
                            var sfsArray = sfsObject.getSFSArray(key);
                            for (int i = 0; i < sfsArray.size(); i++) {
                                list.add(sfsArray.getBool(i));
                            }
                        }
                        else {
                            throw new UnmappableType("Can't map '" + array.getTypeId() + "' for key '" + key + "'");
                        }
                    }
                    else if (elementType == String.class) {
                        if (array.getTypeId() == SFSDataType.UTF_STRING_ARRAY) {
                            list.addAll(sfsObject.getUtfStringArray(key));
                        }
                        else if (array.getTypeId() == SFSDataType.SFS_ARRAY) {
                            var sfsArray = sfsObject.getSFSArray(key);
                            for (int i = 0; i < sfsArray.size(); i++) {
                                list.add(sfsArray.getUtfString(i));
                            }
                        }
                        else {
                            throw new UnmappableType("Can't map '" + array.getTypeId() + "' for key '" + key + "'");
                        }
                    }
                    else {
                        if (array.getTypeId() == SFSDataType.SFS_ARRAY) {
                            var sfsArray = sfsObject.getSFSArray(key);
                            for (int i = 0; i < sfsArray.size(); i++) {
                                list.add(mapSFSObjectToClass(elementType, sfsArray.getSFSObject(i)));
                            }
                        }
                        else {
                            throw new UnmappableType("Can't map '" + array.getTypeId() + "' for key '" + key + "'");
                        }
                    }

                    field.set(obj, list);

                }
                else if (type.isAnnotationPresent(SfsPropertyArray.class)) {
                    var value = sfsObject.get(key);
                    if (value.getTypeId() != SFSDataType.SFS_ARRAY) {
                        throw new MissingKey("Key lacks property SFS array value: '" + key + "'");
                    }
                    SFSArray array = (SFSArray)value.getObject();
                    SFSObject properties = new SFSObject();
                    for (int i = 0; i < array.size(); i++) {
                        ISFSObject property = array.getSFSObject(i);
                        var entry = property.iterator().next();
                        properties.put(entry.getKey(), entry.getValue());
                    }
                    field.set(obj, mapSFSObjectToClass(type, properties));
                }
                else if (!type.isPrimitive()) {
                    var value = sfsObject.get(key);
                    if (value.getTypeId() != SFSDataType.SFS_OBJECT) {
                        throw new MissingKey("Key lacks SFS object value: '" + key + "'");
                    }
                    field.set(obj, mapSFSObjectToClass(type, (ISFSObject)value.getObject()));
                }
                else {
                    throw new UnmappableType("Unmappable type: '" + type.getName() + "'");
                }

            }
            catch (IllegalAccessException ex) {
                throw new InaccessibleField("Inaccessible field: '" + field.getName() + "'");
            }
        }

        return obj;

    }

    public static <T> SFSObject mapToSFSObject(T obj) {

        SFSObject sfsObject = new SFSObject();

        for (Field field : getFields(obj.getClass())) {

            if (
                    field.isAnnotationPresent(SfsMapperIgnore.class)
                    || field.isAnnotationPresent(SfsObjectField.class)
            ) continue;

            try {

                Object value = field.get(obj);
                if (value == null) continue;

                String key = field.isAnnotationPresent(SfsKey.class)
                        ? field.getAnnotation(SfsKey.class).value()
                        : camelToSnakeCase(field.getName());

                var type = field.getType();

                if (type == int.class || type == Integer.class) {
                    sfsObject.putInt(key, (Integer)value);
                } else if (type == long.class || type == Long.class) {
                    sfsObject.putLong(key, (Long)value);
                } else if (type == short.class || type == Short.class) {
                    sfsObject.putShort(key, (Short)value);
                } else if (type == double.class || type == Double.class) {
                    sfsObject.putDouble(key, (Double)value);
                } else if (type == boolean.class || type == Boolean.class) {
                    sfsObject.putBool(key, (Boolean)value);
                } else if (type == String.class) {
                    sfsObject.putUtfString(key, (String)value);
                }
                else if (List.class.isAssignableFrom(type)) {
                    List<?> list = (List<?>)value;
                    Class<?> elementType;
                    if (field.isAnnotationPresent(SfsArrayElementType.class)) {
                        elementType = field.getAnnotation(SfsArrayElementType.class).value();
                    }
                    else {
                        logger.warn("""
                                No element type notation present for field '{}';
                                attempting to guess the type.""", field.getName());
                        elementType = list.isEmpty() ? null : list.getFirst().getClass();
                    }
                    if (elementType == Integer.class) {
                        @SuppressWarnings("unchecked")
                        List<Integer> intList = (List<Integer>)value;
                        sfsObject.putIntArray(key, intList);
                    } else if (elementType == Long.class) {
                        @SuppressWarnings("unchecked")
                        List<Long> longList = (List<Long>)value;
                        sfsObject.putLongArray(key, longList);
                    } else if (elementType == Short.class) {
                        @SuppressWarnings("unchecked")
                        List<Short> shortList = (List<Short>)value;
                        sfsObject.putShortArray(key, shortList);
                    } else if (elementType == Double.class) {
                        @SuppressWarnings("unchecked")
                        List<Double> doubleList = (List<Double>)value;
                        sfsObject.putDoubleArray(key, doubleList);
                    } else if (elementType == Boolean.class) {
                        @SuppressWarnings("unchecked")
                        List<Boolean> boolList = (List<Boolean>)value;
                        sfsObject.putBoolArray(key, boolList);
                    } else if (elementType == String.class) {
                        @SuppressWarnings("unchecked")
                        List<String> stringList = (List<String>)value;
                        sfsObject.putUtfStringArray(key, stringList);
                    } else {
                        SFSArray sfsArray = new SFSArray();
                        for (Object element : list) {
                            sfsArray.addSFSObject(mapToSFSObject(element));
                        }
                        sfsObject.putSFSArray(key, sfsArray);
                    }
                }
                else {
                    throw new UnmappableType("Unmappable type: '" + type.getName() + "'");
                }

            }
            catch (IllegalAccessException ex) {
                throw new InaccessibleField("Inaccessible field: '" + field.getName() + "'");
            }
        }

        return sfsObject;

    }

    private static Iterable<Field> getFields(Class<?> _class) {
        List<Field> fields = new ArrayList<>();
        while (_class != null) {
            fields.addAll(List.of(_class.getDeclaredFields()));
            _class = _class.getSuperclass();
        }
        return fields;
    }

    private static String camelToSnakeCase(String input) {

        StringBuilder output = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (Character.isUpperCase(c)) output.append("_").append(Character.toLowerCase(c));
            else output.append(c);
        }
        return output.toString();

    }

}
