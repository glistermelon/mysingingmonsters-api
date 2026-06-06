package com.glisterbyte.singingmonsters.sfsmapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.glisterbyte.singingmonsters.sfsmapping.exceptions.*;
import com.glisterbyte.singingmonsters.sfsmapping.exceptions.InstantiationException;
import com.glisterbyte.singingmonsters.sfsmodels.SfsCorrelatedResultResponse;
import com.glisterbyte.singingmonsters.sfsmodels.SfsModel;
import com.glisterbyte.singingmonsters.sfsmodels.SfsResultResponse;
import com.smartfoxserver.v2.entities.data.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class SfsMapper {

    private static final Logger logger = LoggerFactory.getLogger(SfsMapper.class);

    private static String getFieldKey(Field field) {
        return field.isAnnotationPresent(SfsKey.class)
                ? field.getAnnotation(SfsKey.class).value()
                : field.getName();
    }

    public static <T> T mapSFSObjectToClass(Class<T> tClass, ISFSObject sfsObject)
            throws SfsMapException {
        return mapSFSObjectToClass(tClass, sfsObject, false);
    }

    public static <T> T mapSFSObjectToClass(Class<T> tClass, ISFSObject sfsObject, boolean optional)
        throws SfsMapException {

        if (getFields(tClass).stream().noneMatch(f -> sfsObject.containsKey(getFieldKey(f)))) {
            if (optional) return null;
            else throw new MissingKeyException(
                    sfsObject, "Missing all keys when mapping to non-optional type '" + tClass.getName() + "'"
            );
        }

        T obj;
        try {
            obj = tClass.getDeclaredConstructor().newInstance();
        }
        catch (
                NoSuchMethodException
                | InvocationTargetException
                | java.lang.InstantiationException
                | IllegalAccessException ex
        ) {
            throw new InstantiationException("Instantiating class '" + tClass.getName() + "' failed.", ex);
        }

        // Not very elegant, but I can do whatever I want right...
        boolean isFailedResultResponse = false;
        if (obj instanceof SfsResultResponse || obj instanceof SfsCorrelatedResultResponse) {
            if (!sfsObject.containsKey("success")) {
                throw new MissingKeyException(sfsObject, "Missing key: 'success'");
            }
            var value = sfsObject.get("success");
            if (value.getTypeId() != SFSDataType.BOOL) {
                throw new MissingKeyException(sfsObject, "Key lacks boolean value: 'success'");
            }
            isFailedResultResponse = !sfsObject.getBool("success");
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

                if (field.isAnnotationPresent(SfsInline.class)) {
                    field.set(obj, mapSFSObjectToClass(field.getType(), sfsObject, field.isAnnotationPresent(SfsOptional.class)));
                    continue;
                }

                String key = getFieldKey(field);

                if (!sfsObject.containsKey(key)) {
                    if (isFailedResultResponse) continue;
                    if (field.isAnnotationPresent(SfsOptional.class)) {
                        field.set(obj, null);
                        continue;
                    }
                    if (List.class.isAssignableFrom(field.getType())) {
                        field.set(obj, new ArrayList<>());
                        continue;
                    }
                    throw new MissingKeyException(sfsObject, "Missing key: '" + key + "'");
                }

                var type = field.getType();

                if (type == int.class || type == Integer.class) {
                    var value = sfsObject.get(key);
                    if (value.getTypeId() != SFSDataType.INT) {
                        throw new MissingKeyException(sfsObject, "Key lacks int value: '" + key + "'");
                    }
                    field.set(obj, value.getObject());
                } else if (type == long.class || type == Long.class) {
                    var value = sfsObject.get(key);
                    if (value.getTypeId() != SFSDataType.LONG) {
                        throw new MissingKeyException(sfsObject, "Key lacks long value: '" + key + "'");
                    }
                    field.set(obj, value.getObject());
                } else if (type == short.class || type == Short.class) {
                    var value = sfsObject.get(key);
                    if (value.getTypeId() != SFSDataType.SHORT) {
                        throw new MissingKeyException(sfsObject, "Key lacks short value: '" + key + "'");
                    }
                    field.set(obj, value.getObject());
                } else if (type == double.class || type == Double.class) {
                    var value = sfsObject.get(key);
                    if (value.getTypeId() != SFSDataType.DOUBLE) {
                        throw new MissingKeyException(sfsObject, "Key lacks double value: '" + key + "'");
                    }
                    field.set(obj, value.getObject());
                } else if (type == float.class || type == Float.class) {
                    var value = sfsObject.get(key);
                    if (value.getTypeId() != SFSDataType.FLOAT) {
                        throw new MissingKeyException(sfsObject, "Key lacks float value: '" + key + "'");
                    }
                    field.set(obj, value.getObject());
                } else if (type == boolean.class || type == Boolean.class) {
                    var value = sfsObject.get(key);
                    if (value.getTypeId() != SFSDataType.BOOL) {
                        throw new MissingKeyException(sfsObject, "Key lacks boolean value: '" + key + "'");
                    }
                    field.set(obj, value.getObject());
                } else if (type == String.class) {
                    var value = sfsObject.get(key);
                    if (value.getTypeId() != SFSDataType.UTF_STRING) {
                        throw new MissingKeyException(sfsObject, "Key lacks string value: '" + key + "'");
                    }
                    field.set(obj, value.getObject());
                }
                else if (field.isAnnotationPresent(SfsJsonObject.class)) {
                    String jsonStr = sfsObject.getUtfString(key);
                    var mapper = new ObjectMapper();
                    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                    field.set(obj, mapper.readValue(jsonStr, field.getType()));
                }
                else if (List.class.isAssignableFrom(type)) {

                    if (!field.isAnnotationPresent(SfsArrayElementType.class)) {
                        throw new UnmappableTypeException("Can't map unannotated list '" + key + "'");
                    }
                    Class<?> elementType = field.getAnnotation(SfsArrayElementType.class).value();

                    SFSDataWrapper array = sfsObject.get(key);

                    List<Object> list = new ArrayList<>();

                    if (
                            field.isAnnotationPresent(SfsJsonArray.class)
                            && array.getTypeId() == SFSDataType.UTF_STRING
                    ) {
                        String jsonStr = sfsObject.getUtfString(key);
                        ObjectMapper mapper = new ObjectMapper();
                        var listType = mapper.getTypeFactory()
                                .constructCollectionType(List.class, elementType);
                        list = mapper.readValue(jsonStr, listType);
                    }
                    else if (elementType == Integer.class) {
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
                            throw new UnmappableTypeException("Can't map '" + array.getTypeId() + "' for key '" + key + "'");
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
                            throw new UnmappableTypeException("Can't map '" + array.getTypeId() + "' for key '" + key + "'");
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
                            throw new UnmappableTypeException("Can't map '" + array.getTypeId() + "' for key '" + key + "'");
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
                            throw new UnmappableTypeException("Can't map '" + array.getTypeId() + "' for key '" + key + "'");
                        }
                    }
                    else if (elementType == Float.class) {
                        if (array.getTypeId() == SFSDataType.FLOAT_ARRAY) {
                            list.addAll(sfsObject.getFloatArray(key));
                        }
                        else if (array.getTypeId() == SFSDataType.SFS_ARRAY) {
                            var sfsArray = sfsObject.getSFSArray(key);
                            for (int i = 0; i < sfsArray.size(); i++) {
                                list.add(sfsArray.getFloat(i));
                            }
                        }
                        else {
                            throw new UnmappableTypeException("Can't map '" + array.getTypeId() + "' for key '" + key + "'");
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
                            throw new UnmappableTypeException(
                                    "Can't map '" + array.getTypeId() + "' for key '" + key + "'"
                            );
                        }
                    }
                    else if (elementType == String.class) {
                        if (array.getTypeId() == SFSDataType.UTF_STRING_ARRAY) {
                            list.addAll(sfsObject.getUtfStringArray(key));
                        }
                        else if (array.getTypeId() == SFSDataType.SFS_ARRAY) {
                            var sfsArray = sfsObject.getSFSArray(key);
                            for (int i = 0; i < sfsArray.size(); i++) {
                                var element = sfsArray.get(i).getObject();
                                if (!(element instanceof String)) {
                                    throw new UnmappableTypeException(String.class, element);
                                }
                                list.add(element);
                            }
                        }
                        else {
                            throw new UnmappableTypeException(
                                    "Can't map '" + array.getTypeId() + "' for key '" + key + "'"
                            );
                        }
                    }
                    else {
                        if (array.getTypeId() == SFSDataType.SFS_ARRAY) {
                            var sfsArray = sfsObject.getSFSArray(key);
                            for (int i = 0; i < sfsArray.size(); i++) {
                                list.add(mapSFSObjectToClass(elementType, sfsArray.getSFSObject(i), false));
                            }
                        }
                        else {
                            throw new UnmappableTypeException(
                                    "Can't map '" + array.getTypeId() + "' for key '" + key + "'"
                            );
                        }
                    }

                    field.set(obj, list);

                }
                else if (type.isAnnotationPresent(SfsPropertyArray.class)) {
                    var value = sfsObject.get(key);
                    if (value.getTypeId() != SFSDataType.SFS_ARRAY) {
                        throw new MissingKeyException(sfsObject, "Key lacks property SFS array value: '" + key + "'");
                    }
                    SFSArray array = (SFSArray)value.getObject();
                    SFSObject properties = new SFSObject();
                    for (int i = 0; i < array.size(); i++) {
                        ISFSObject property = array.getSFSObject(i);
                        var entry = property.iterator().next();
                        properties.put(entry.getKey(), entry.getValue());
                    }
                    field.set(obj, mapSFSObjectToClass(type, properties, field.isAnnotationPresent(SfsOptional.class)));
                }
                else if (!type.isPrimitive()) {
                    var value = sfsObject.get(key);
                    if (value.getTypeId() != SFSDataType.SFS_OBJECT) {
                        throw new MissingKeyException(sfsObject, "Key lacks SFS object value: '" + key + "'");
                    }
                    field.set(obj, mapSFSObjectToClass(
                            type, (ISFSObject)value.getObject(), field.isAnnotationPresent(SfsOptional.class)
                    ));
                }
                else {
                    throw new UnmappableTypeException(type);
                }

            }
            catch (IllegalAccessException ex) {
                throw new InaccessibleFieldException("Inaccessible field: '" + field.getName() + "'");
            } catch (JsonProcessingException ex) {
                throw new UnmappableTypeException(ex.getMessage());
            }
        }

        return obj;

    }

    private static SfsModel castToModel(Object object) throws UnmappableTypeException {
        if (!SfsModel.class.isAssignableFrom(object.getClass())) {
            throw new UnmappableTypeException(object.getClass());
        }
        return (SfsModel)object;
    }

    public static <T extends SfsModel> SFSObject mapToSFSObject(T obj) throws SfsMapException  {

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
                } else if (type == float.class || type == Float.class) {
                    sfsObject.putFloat(key, (Float)value);
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
                    } else if (elementType == Float.class) {
                        @SuppressWarnings("unchecked")
                        List<Float> floatList = (List<Float>)value;
                        sfsObject.putFloatArray(key, floatList);
                    } else if (elementType == Boolean.class) {
                        @SuppressWarnings("unchecked")
                        List<Boolean> boolList = (List<Boolean>)value;
                        sfsObject.putBoolArray(key, boolList);
                    } else if (elementType == String.class) {
                        @SuppressWarnings("unchecked")
                        List<String> stringList = (List<String>) value;
                        sfsObject.putUtfStringArray(key, stringList);
                    } else if (elementType != null && SFSObject.class.isAssignableFrom(elementType)) {
                        SFSArray sfsArray = new SFSArray();
                        for (Object element : list) {
                            sfsArray.addSFSObject((SFSObject)element);
                        }
                        sfsObject.putSFSArray(key, sfsArray);
                    } else {
                        SFSArray sfsArray = new SFSArray();
                        for (Object element : list) {
                            sfsArray.addSFSObject(mapToSFSObject(castToModel(element)));
                        }
                        sfsObject.putSFSArray(key, sfsArray);
                    }
                }
                else {
                    sfsObject.putSFSObject(key, mapToSFSObject(castToModel(value)));
                }

            }
            catch (IllegalAccessException ex) {
                throw new InaccessibleFieldException("Inaccessible field: '" + field.getName() + "'");
            }
        }

        return sfsObject;

    }

    private static List<Field> getFields(Class<?> classType) {
        List<Field> fields = new ArrayList<>();
        while (classType != null) {
            fields.addAll(0, List.of(classType.getDeclaredFields()));
            classType = classType.getSuperclass();
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
