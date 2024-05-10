package de.xiekang.talend;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SortJSON {
    Logger logger = Logger.getLogger(this.getClass().getName());


    public void sortDateOutOfJson(String jsonString) {
        List<String> dateList = Arrays.asList(jsonString.
                replace("[", "").replace("]", "").split(","));
        List<String> sortedList = new ArrayList<>(dateList);
        List<String> helpList = new ArrayList<>(dateList);
        List<Integer> indexes = new ArrayList<>();
        sortedList.sort(Comparator.reverseOrder());
        for (String d : sortedList) {
            int index = dateList.indexOf(d);
            indexes.add(index);
            // good idea?
            dateList.set(index, null);
        }
        // test
        for (int i=0; i < sortedList.size(); i++) {
            System.out.println("index = " + i + ": " + sortedList.get(i) + " = " + helpList.get(indexes.get(i)));
        }
    }


    public static void main(String[] args) {
        SortJSON related = new SortJSON();
        related.sortDateOutOfJson("[\"2020-04-16\",\"2021-10-31\",\"2024-04-25\",\"2023-12-31\",\"2024-04-22\"," +
                "\"2023-10-31\",\"2020-04-16\",\"2020-12-01\",\"2020-10-31\",\"2021-12-19\",\"2020-04-16\",\"2024-04-23\"," +
                "\"2024-04-10\",\"2021-11-01\",\"2022-10-31\",\"2020-12-01\",\"2020-04-16\",\"2020-10-31\",\"2020-10-31\"]");
    }
}
