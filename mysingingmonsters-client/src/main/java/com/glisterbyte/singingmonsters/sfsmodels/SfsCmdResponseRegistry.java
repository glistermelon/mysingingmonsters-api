package com.glisterbyte.singingmonsters.sfsmodels;

import com.glisterbyte.singingmonsters.sfsmapping.exceptions.MissingCommandException;
import com.glisterbyte.singingmonsters.sfsmapping.SfsCmd;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;

import java.util.Map;
import java.util.stream.Collectors;

public class SfsCmdResponseRegistry {

    private static final Map<String, Class<? extends SfsEventModel>> registry;

    static {
        Reflections reflections = new Reflections("com.glisterbyte.singingmonsters", Scanners.TypesAnnotated);
        registry = reflections.getTypesAnnotatedWith(SfsCmd.class).stream()
                .filter(SfsEventModel.class::isAssignableFrom)
                .collect(Collectors.toMap(
                        c -> c.getAnnotation(SfsCmd.class).value(),
                        c -> {
                            @SuppressWarnings("unchecked")
                            Class<? extends SfsEventModel> castedType = (Class<? extends SfsEventModel>) c;
                            return castedType;
                        }
                ));
    }

    public static Class<? extends SfsEventModel> getCmdResponseType(String cmd) throws MissingCommandException {
        var classType = registry.get(cmd);
        if (classType == null) throw new MissingCommandException(cmd);
        return classType;
    }

}