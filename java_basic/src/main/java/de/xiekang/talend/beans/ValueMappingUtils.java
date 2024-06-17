package de.xiekang.talend.beans;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class ValueMappingUtils {
    static Logger logger = LogManager.getLogger(ValueMappingUtils.class);
    protected static Map<Object, Object> objectMap = new LinkedHashMap<>();

    public static Map<Object, Object> createMapping(String fileName) {
        Configurator.setLevel(logger.getName(), Level.INFO);
        List<ValueMapping> valueMappingList = new ArrayList<>();
        logger.info("Initial objectMap!");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            valueMappingList = objectMapper.readValue(ValueMappingUtils.class.getResourceAsStream(fileName),
                    new TypeReference<ArrayList<ValueMapping>>() {
                    });
            //valueMappingList = objectMapper.readValue(Files.newInputStream(Path.of(fileName)), new TypeReference<ArrayList<ValueMapping>>(){});
            objectMap = valueMappingList.stream().collect(Collectors.toMap(ValueMapping::getKey, ValueMapping::getValue));
            //logger.info(valueMappingList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objectMap;
    }

    public static Object findValueOnKey(String fileName, Object key) {
        if (objectMap.isEmpty()) {
            logger.info("objectMap is Empty!");
            return createMapping(fileName).get(key);
        }
        return objectMap.get(key);
    }

    public static int findIntValueOnKey(String fileName, Object key) {
        Object o = findValueOnKey(fileName, key);
        if (o instanceof Integer) {
            return (int) o;
        } else {
            throw new RuntimeException("Found Value can not be casted to int");
        }
    }

    public static String findStringValueOnKey(String fileName, Object key) {
        Object o = findValueOnKey(fileName, key);
        if (o instanceof String) {
            return (String) o;
        } else {
            throw new RuntimeException("Found Value can not be casted to String");
        }
    }

    public static Object findKeyOnValue(String fileName, Object value) {
        if (objectMap.isEmpty()) {
            logger.info("objectMap is Empty!");
            return createMapping(fileName).entrySet().stream()
                    .filter(objectObjectEntry -> Objects.equals(objectObjectEntry.getValue(), value))
                    .map(Map.Entry::getKey).collect(Collectors.toList()).get(0);
        }
        return objectMap.entrySet().stream().filter(objectObjectEntry -> Objects.equals(objectObjectEntry.getValue(), value))
                .map(Map.Entry::getKey).collect(Collectors.toList()).get(0);
    }

    public static int findIntKeyOnValue(String fileName, Object value) {
        Object o = findKeyOnValue(fileName, value);
        if (o instanceof Integer) {
            return (int) o;
        } else {
            throw new RuntimeException("Found Key can not be casted to int");
        }
    }

    public static String findStringKeyOnValue(String fileName, Object value) {
        Object o = findKeyOnValue(fileName, value);
        if (o instanceof String) {
            return (String) o;
        } else {
            throw new RuntimeException("Found Key can not be casted to String");
        }
    }
}
