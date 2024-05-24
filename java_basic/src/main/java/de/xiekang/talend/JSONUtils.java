package de.xiekang.talend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class JSONUtils {

    public static String convertListOfMaptoJSON(List<Map<String, Object>> JSON) {
        String result = "";
        try {
            result = new ObjectMapper().writeValueAsString(JSON);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static List<Map<String, Object>> convertStringtoListOfMap(String jsonString) {
        List<Map<String, Object>> jsonStructure = new ArrayList<>();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            jsonStructure = objectMapper.readValue(jsonString, List.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonStructure;
    }

    // JSON Filter + Sorted
    public static List<String> getAllSortedJSON(List<Map<String, Object>> JSON, String keyToSort, String filterPath, String sortPath) {
        List<String> result = new ArrayList<>();
        List<String> valuesToSort = JSONFilter(JSON, keyToSort, filterPath);
        List<String> sortedValues = new ArrayList<>(valuesToSort);
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Sort Values
            sortedValues.sort(Comparator.reverseOrder());
            for (String s : sortedValues) {
                int index = valuesToSort.indexOf(s);
                result.add(objectMapper.writeValueAsString(JsonPath.read(JSON, String.format(sortPath, index))));
                valuesToSort.set(index, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static List<String> getAllSortedJSON(List<Map<String, Object>> JSON, String keyToSort, String filterPath) {
        return getAllSortedJSON(JSON, keyToSort, filterPath, "$[%s]");
    }

    public static List<String> getAllSortedJSON(List<Map<String, Object>> JSON, String keyToSort) {
        return getAllSortedJSON(JSON, keyToSort, "$[%s]['%s']", "$[%s]");
    }

    public static String getSortedJSON(List<Map<String, Object>> JSON, String keyToSort, int index) {
        return getAllSortedJSON(JSON, keyToSort).get(index);
    }

    public static String getSortedJSON(List<Map<String, Object>> JSON, String keyToSort, String filterPath, int index) {
        return getAllSortedJSON(JSON, keyToSort, filterPath).get(index);
    }

    public static String getSortedJSON(List<Map<String, Object>> JSON, String keyToSort, String filterPath,
                                       String sortPath, int index) {
        return getAllSortedJSON(JSON, keyToSort, filterPath, sortPath).get(index);
    }

    private static List<String> JSONFilter(List<Map<String, Object>> JSON, String keyToSort, String path) {
        List<String> valuesToSort = new ArrayList<>();
        for (int i = 0; i < JSON.size(); i++) {
            valuesToSort.add(JsonPath.read(JSON, String.format(path, i, keyToSort)));
        }
        return valuesToSort;
    }

    public static List<Map<String, Object>> JSONFilter(String JSONString, String filter) {
        return JsonPath.read(JSONString, filter);
    }

    // 
}
