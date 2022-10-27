package de.xiekang.jsontokafka;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class test {

    public static void main(String[] args) throws IOException {
        /*Path file = Paths.get("java_kafka/src/main/java/de/xiekang/jsontokafka/data/id.txt");
        BufferedReader reader = Files.newBufferedReader(file);
        Set<String> lines = reader.lines().collect(Collectors.toSet());
        lines.forEach(System.out::println);
        reader.close();

        JsonToKafka jsonToKafka = new JsonToKafka(null, null);
        jsonToKafka.updateKeysFile("jfdksl \njfdksjdlfslldkjfls \n");*/
        JsonToKafka jsonToKafka = new JsonToKafka("", "");
        jsonToKafka.getKeysFromFile();
        Set<String> stringSet = new HashSet<>(jsonToKafka.getKeys());
        //stringSet.add("871.TA.92-301-j22-1.19.R 20221025 23:19:00");
        System.out.println(stringSet.size());
        System.out.println(stringSet.contains("871.TA.92-301-j22-1.19.R 20221026 23:19:00"));
    }
}
