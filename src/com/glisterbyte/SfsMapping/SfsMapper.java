package com.glisterbyte.SfsMapping;

import com.glisterbyte.SfsMapping.SfsMapperException.InaccessibleField;
import com.glisterbyte.SfsMapping.SfsMapperException.InstantiationFailed;
import com.glisterbyte.SfsMapping.SfsMapperException.MissingKey;
import com.glisterbyte.SfsMapping.SfsMapperException.UnmappableType;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSDataType;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class SfsMapper {

    public static <T> T mapObject(Class<T> tClass, ISFSObject sfsObject) {

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

        for (Field field : tClass.getDeclaredFields()) {

            // Uncomment this if this ever needs to work on non-public fields
            // field.setAccessible(true);

            String key = field.isAnnotationPresent(SfsKey.class)
                    ? field.getAnnotation(SfsKey.class).value()
                    : camelToSnakeCase(field.getName());

            try {
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
                        throw new UnmappableType("Unmappable type: '" + type.getName() + "'");
                    }
                    Class<?> elementType = field.getAnnotation(SfsArrayElementType.class).value();
                    List<Object> list = new ArrayList<>();
                    var sfsArray = sfsObject.getSFSArray(key);
                    for (int i = 0; i < sfsArray.size(); i++) {
                        list.add(mapObject(elementType, sfsArray.getSFSObject(i)));
                    }
                    field.set(obj, list);
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
