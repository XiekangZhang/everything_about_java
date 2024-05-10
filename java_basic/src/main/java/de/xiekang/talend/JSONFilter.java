package de.xiekang.talend;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jayway.jsonpath.JsonPath;

import java.util.*;
import java.util.stream.Collectors;

public class JSONFilter {
    public static List<Map<String, Object>> filterJSON(String jsonString, String path) {
        return JsonPath.read(jsonString, path);
    }

    public static void main(String[] args) throws JsonProcessingException {
        String jsonString = "[\n" +
                "        {\n" +
                "            \"zaehlerStand\": \"6606\",\n" +
                "            \"ableseDatum\": \"2023-10-31\",\n" +
                "            \"ableseKennzeichen\": \"P\",\n" +
                "            \"ablesehinweis\": \"(P) Schätzung (programmseitig)\",\n" +
                "            \"setAktive\": \"0\",\n" +
                "            \"Status\": \"S\",\n" +
                "            \"Vorgang\": \"(27) Standmitteilung\",\n" +
                "            \"Anschlussnutzer\": \"Stahl,Sarah\",\n" +
                "            \"ErstelltDatum\": \"2023-11-07\",\n" +
                "            \"ErstelltZeit\": \"10:04:20+01:00\",\n" +
                "            \"AenderungDatum\": \"2023-11-13\",\n" +
                "            \"AenderungZeit\": \"10:41:31+01:00\",\n" +
                "            \"LetzteAenderung\": \"2024-04-23T13:20:57.867111+01:00\",\n" +
                "            \"Einheit\": \"\",\n" +
                "            \"Vorgangsdatum\": \"2023-10-31\",\n" +
                "            \"KonvSatzart\": 20,\n" +
                "            \"LaufendeNr\": 3,\n" +
                "            \"Satzart\": 27\n" +
                "        },\n" +
                "        {\n" +
                "            \"zaehlerStand\": \"7613\",\n" +
                "            \"ableseDatum\": \"2023-12-31\",\n" +
                "            \"ableseKennzeichen\": \"2\",\n" +
                "            \"ablesehinweis\": \"(2) Ablesung durch Kunden\",\n" +
                "            \"setAktive\": \"0\",\n" +
                "            \"Status\": \"\",\n" +
                "            \"Vorgang\": \"(27) Standmitteilung\",\n" +
                "            \"Anschlussnutzer\": \"Stahl,Sarah\",\n" +
                "            \"ErstelltDatum\": \"2024-01-02\",\n" +
                "            \"ErstelltZeit\": \"10:28:50+01:00\",\n" +
                "            \"AenderungDatum\": \"2024-01-02\",\n" +
                "            \"AenderungZeit\": \"10:28:50+01:00\",\n" +
                "            \"LetzteAenderung\": \"2024-04-23T13:20:57.867111+01:00\",\n" +
                "            \"Einheit\": \"\",\n" +
                "            \"Vorgangsdatum\": \"2023-12-31\",\n" +
                "            \"KonvSatzart\": 20,\n" +
                "            \"LaufendeNr\": 1,\n" +
                "            \"Satzart\": 27\n" +
                "        },\n" +
                "        {\n" +
                "            \"zaehlerStand\": \"8000\",\n" +
                "            \"ableseDatum\": \"2024-02-19\",\n" +
                "            \"ableseKennzeichen\": \"I\",\n" +
                "            \"ablesehinweis\": \"(I) per Internet mitgeteilt\",\n" +
                "            \"setAktive\": \"0\",\n" +
                "            \"Status\": \"S\",\n" +
                "            \"Vorgang\": \"(27) Standmitteilung\",\n" +
                "            \"Anschlussnutzer\": \"Stahl,Sarah\",\n" +
                "            \"ErstelltDatum\": \"2024-02-19\",\n" +
                "            \"ErstelltZeit\": \"09:12:17+01:00\",\n" +
                "            \"AenderungDatum\": \"2024-02-19\",\n" +
                "            \"AenderungZeit\": \"09:17:20+01:00\",\n" +
                "            \"LetzteAenderung\": \"2024-04-23T13:20:57.867111+01:00\",\n" +
                "            \"Einheit\": \"\",\n" +
                "            \"Vorgangsdatum\": \"2024-02-19\",\n" +
                "            \"KonvSatzart\": 20,\n" +
                "            \"LaufendeNr\": 2,\n" +
                "            \"Satzart\": 27\n" +
                "        },\n" +
                "        {\n" +
                "            \"zaehlerStand\": \"6727\",\n" +
                "            \"ableseDatum\": \"2023-11-13\",\n" +
                "            \"ableseKennzeichen\": \"2\",\n" +
                "            \"ablesehinweis\": \"(2) Ablesung durch Kunden\",\n" +
                "            \"setAktive\": \"0\",\n" +
                "            \"Status\": \"\",\n" +
                "            \"Vorgang\": \"(27) Standmitteilung\",\n" +
                "            \"Anschlussnutzer\": \"Stahl,Sarah\",\n" +
                "            \"ErstelltDatum\": \"2023-11-13\",\n" +
                "            \"ErstelltZeit\": \"10:40:54+01:00\",\n" +
                "            \"AenderungDatum\": \"2023-11-13\",\n" +
                "            \"AenderungZeit\": \"10:40:54+01:00\",\n" +
                "            \"LetzteAenderung\": \"2024-04-23T13:20:57.867111+01:00\",\n" +
                "            \"Einheit\": \"\",\n" +
                "            \"Vorgangsdatum\": \"2023-11-13\",\n" +
                "            \"KonvSatzart\": 20,\n" +
                "            \"LaufendeNr\": 1,\n" +
                "            \"Satzart\": 27\n" +
                "        },\n" +
                "        {\n" +
                "            \"zaehlerStand\": \"8000\",\n" +
                "            \"ableseDatum\": \"2024-02-20\",\n" +
                "            \"ableseKennzeichen\": \"I\",\n" +
                "            \"ablesehinweis\": \"(I) per Internet mitgeteilt\",\n" +
                "            \"setAktive\": \"0\",\n" +
                "            \"Status\": \"S\",\n" +
                "            \"Vorgang\": \"(27) Standmitteilung\",\n" +
                "            \"Anschlussnutzer\": \"Stahl,Sarah\",\n" +
                "            \"ErstelltDatum\": \"2024-02-20\",\n" +
                "            \"ErstelltZeit\": \"08:32:43+01:00\",\n" +
                "            \"AenderungDatum\": \"2024-02-20\",\n" +
                "            \"AenderungZeit\": \"08:33:07+01:00\",\n" +
                "            \"LetzteAenderung\": \"2024-04-23T13:20:57.867111+01:00\",\n" +
                "            \"Einheit\": \"\",\n" +
                "            \"Vorgangsdatum\": \"2024-02-20\",\n" +
                "            \"KonvSatzart\": 20,\n" +
                "            \"LaufendeNr\": 2,\n" +
                "            \"Satzart\": 27\n" +
                "        },\n" +
                "        {\n" +
                "            \"zaehlerStand\": \"8000\",\n" +
                "            \"ableseDatum\": \"2024-02-20\",\n" +
                "            \"ableseKennzeichen\": \"I\",\n" +
                "            \"ablesehinweis\": \"(I) per Internet mitgeteilt\",\n" +
                "            \"setAktive\": \"0\",\n" +
                "            \"Status\": \"S\",\n" +
                "            \"Vorgang\": \"(27) Standmitteilung\",\n" +
                "            \"Anschlussnutzer\": \"Stahl,Sarah\",\n" +
                "            \"ErstelltDatum\": \"2024-02-20\",\n" +
                "            \"ErstelltZeit\": \"08:33:17+01:00\",\n" +
                "            \"AenderungDatum\": \"2024-02-20\",\n" +
                "            \"AenderungZeit\": \"08:34:17+01:00\",\n" +
                "            \"LetzteAenderung\": \"2024-04-23T13:20:57.867111+01:00\",\n" +
                "            \"Einheit\": \"\",\n" +
                "            \"Vorgangsdatum\": \"2024-02-20\",\n" +
                "            \"KonvSatzart\": 20,\n" +
                "            \"LaufendeNr\": 2,\n" +
                "            \"Satzart\": 27\n" +
                "        },\n" +
                "        {\n" +
                "            \"zaehlerStand\": \"5937\",\n" +
                "            \"ableseDatum\": \"2023-08-08\",\n" +
                "            \"ableseKennzeichen\": \"2\",\n" +
                "            \"ablesehinweis\": \"(2) Ablesung durch Kunden\",\n" +
                "            \"setAktive\": \"0\",\n" +
                "            \"Status\": \"*\",\n" +
                "            \"Vorgang\": \"(20) Startsatz\",\n" +
                "            \"Anschlussnutzer\": \"Stahl,Sarah\",\n" +
                "            \"ErstelltDatum\": \"2023-09-11\",\n" +
                "            \"ErstelltZeit\": \"06:48:18+01:00\",\n" +
                "            \"AenderungDatum\": \"2023-11-12\",\n" +
                "            \"AenderungZeit\": \"10:29:15+01:00\",\n" +
                "            \"LetzteAenderung\": \"2024-04-23T13:20:57.867111+01:00\",\n" +
                "            \"Einheit\": \"\",\n" +
                "            \"Vorgangsdatum\": \"2023-08-08\",\n" +
                "            \"KonvSatzart\": 0,\n" +
                "            \"LaufendeNr\": 3,\n" +
                "            \"Satzart\": 20\n" +
                "        },\n" +
                "        {\n" +
                "            \"zaehlerStand\": \"8000\",\n" +
                "            \"ableseDatum\": \"2024-02-19\",\n" +
                "            \"ableseKennzeichen\": \"I\",\n" +
                "            \"ablesehinweis\": \"(I) per Internet mitgeteilt\",\n" +
                "            \"setAktive\": \"0\",\n" +
                "            \"Status\": \"S\",\n" +
                "            \"Vorgang\": \"(27) Standmitteilung\",\n" +
                "            \"Anschlussnutzer\": \"Stahl,Sarah\",\n" +
                "            \"ErstelltDatum\": \"2024-02-19\",\n" +
                "            \"ErstelltZeit\": \"11:02:09+01:00\",\n" +
                "            \"AenderungDatum\": \"2024-02-19\",\n" +
                "            \"AenderungZeit\": \"11:25:15+01:00\",\n" +
                "            \"LetzteAenderung\": \"2024-04-23T13:20:57.867111+01:00\",\n" +
                "            \"Einheit\": \"\",\n" +
                "            \"Vorgangsdatum\": \"2024-02-19\",\n" +
                "            \"KonvSatzart\": 20,\n" +
                "            \"LaufendeNr\": 2,\n" +
                "            \"Satzart\": 27\n" +
                "        },\n" +
                "        {\n" +
                "            \"zaehlerStand\": \"5937\",\n" +
                "            \"ableseDatum\": \"2023-08-08\",\n" +
                "            \"ableseKennzeichen\": \"2\",\n" +
                "            \"ablesehinweis\": \"(2) Ablesung durch Kunden\",\n" +
                "            \"setAktive\": \"0\",\n" +
                "            \"Status\": \"K\",\n" +
                "            \"Vorgang\": \"(20) Startsatz\",\n" +
                "            \"Anschlussnutzer\": \"Stahl,Sarah\",\n" +
                "            \"ErstelltDatum\": \"2023-09-11\",\n" +
                "            \"ErstelltZeit\": \"06:48:18+01:00\",\n" +
                "            \"AenderungDatum\": \"2023-09-11\",\n" +
                "            \"AenderungZeit\": \"06:48:18+01:00\",\n" +
                "            \"LetzteAenderung\": \"2024-04-23T13:20:57.867111+01:00\",\n" +
                "            \"Einheit\": \"\",\n" +
                "            \"Vorgangsdatum\": \"2023-08-08\",\n" +
                "            \"KonvSatzart\": 0,\n" +
                "            \"LaufendeNr\": 3,\n" +
                "            \"Satzart\": 20\n" +
                "        },\n" +
                "        {\n" +
                "            \"zaehlerStand\": \"5937\",\n" +
                "            \"ableseDatum\": \"2023-08-08\",\n" +
                "            \"ableseKennzeichen\": \"2\",\n" +
                "            \"ablesehinweis\": \"(2) Ablesung durch Kunden\",\n" +
                "            \"setAktive\": \"0\",\n" +
                "            \"Status\": \"K\",\n" +
                "            \"Vorgang\": \"(20) Startsatz\",\n" +
                "            \"Anschlussnutzer\": \"Stahl,Sarah\",\n" +
                "            \"ErstelltDatum\": \"2023-09-11\",\n" +
                "            \"ErstelltZeit\": \"06:48:18+01:00\",\n" +
                "            \"AenderungDatum\": \"2023-09-13\",\n" +
                "            \"AenderungZeit\": \"01:20:23+01:00\",\n" +
                "            \"LetzteAenderung\": \"2024-04-23T13:20:57.867111+01:00\",\n" +
                "            \"Einheit\": \"\",\n" +
                "            \"Vorgangsdatum\": \"2023-08-08\",\n" +
                "            \"KonvSatzart\": 0,\n" +
                "            \"LaufendeNr\": 3,\n" +
                "            \"Satzart\": 20\n" +
                "        },\n" +
                "        {\n" +
                "            \"zaehlerStand\": \"9000\",\n" +
                "            \"ableseDatum\": \"2024-04-10\",\n" +
                "            \"ableseKennzeichen\": \"2\",\n" +
                "            \"ablesehinweis\": \"(2) Ablesung durch Kunden\",\n" +
                "            \"setAktive\": \"1\",\n" +
                "            \"Status\": \"\",\n" +
                "            \"Vorgang\": \"(27) Standmitteilung\",\n" +
                "            \"Anschlussnutzer\": \"Stahl,Sarah\",\n" +
                "            \"ErstelltDatum\": \"2024-04-23\",\n" +
                "            \"ErstelltZeit\": \"13:20:57+01:00\",\n" +
                "            \"AenderungDatum\": \"2024-04-23\",\n" +
                "            \"AenderungZeit\": \"13:20:57+01:00\",\n" +
                "            \"LetzteAenderung\": \"2024-04-23T13:20:57.867111+01:00\",\n" +
                "            \"Einheit\": \"\",\n" +
                "            \"Vorgangsdatum\": \"2024-04-10\",\n" +
                "            \"KonvSatzart\": 20,\n" +
                "            \"LaufendeNr\": 1,\n" +
                "            \"Satzart\": 27\n" +
                "        },\n" +
                "        {\n" +
                "            \"zaehlerStand\": \"6533\",\n" +
                "            \"ableseDatum\": \"2023-10-31\",\n" +
                "            \"ableseKennzeichen\": \"P\",\n" +
                "            \"ablesehinweis\": \"(P) Schätzung (programmseitig)\",\n" +
                "            \"setAktive\": \"0\",\n" +
                "            \"Status\": \"S\",\n" +
                "            \"Vorgang\": \"(27) Standmitteilung(programmseitig)\",\n" +
                "            \"Anschlussnutzer\": \"Stahl,Sarah\",\n" +
                "            \"ErstelltDatum\": \"2023-11-07\",\n" +
                "            \"ErstelltZeit\": \"10:04:20+01:00\",\n" +
                "            \"AenderungDatum\": \"2023-11-07\",\n" +
                "            \"AenderungZeit\": \"10:04:20+01:00\",\n" +
                "            \"LetzteAenderung\": \"2024-04-23T13:20:57.867111+01:00\",\n" +
                "            \"Einheit\": \"\",\n" +
                "            \"Vorgangsdatum\": \"2023-10-31\",\n" +
                "            \"KonvSatzart\": 5,\n" +
                "            \"LaufendeNr\": 1,\n" +
                "            \"Satzart\": 27\n" +
                "        },\n" +
                "        {\n" +
                "            \"zaehlerStand\": \"6533\",\n" +
                "            \"ableseDatum\": \"2023-10-31\",\n" +
                "            \"ableseKennzeichen\": \"#\",\n" +
                "            \"ablesehinweis\": \"(#) Schätzung durch Vertrieb\",\n" +
                "            \"setAktive\": \"0\",\n" +
                "            \"Status\": \"*\",\n" +
                "            \"Vorgang\": \"(27) Standmitteilung\",\n" +
                "            \"Anschlussnutzer\": \"Stahl,Sarah\",\n" +
                "            \"ErstelltDatum\": \"2023-11-07\",\n" +
                "            \"ErstelltZeit\": \"10:04:20+01:00\",\n" +
                "            \"AenderungDatum\": \"2023-11-13\",\n" +
                "            \"AenderungZeit\": \"13:06:42+01:00\",\n" +
                "            \"LetzteAenderung\": \"2024-04-23T13:20:57.867111+01:00\",\n" +
                "            \"Einheit\": \"\",\n" +
                "            \"Vorgangsdatum\": \"2023-10-31\",\n" +
                "            \"KonvSatzart\": 20,\n" +
                "            \"LaufendeNr\": 3,\n" +
                "            \"Satzart\": 27\n" +
                "        },\n" +
                "        {\n" +
                "            \"zaehlerStand\": \"8827\",\n" +
                "            \"ableseDatum\": \"2024-03-18\",\n" +
                "            \"ableseKennzeichen\": \"2\",\n" +
                "            \"ablesehinweis\": \"(2) Ablesung durch Kunden\",\n" +
                "            \"setAktive\": \"0\",\n" +
                "            \"Status\": \"\",\n" +
                "            \"Vorgang\": \"(27) Standmitteilung\",\n" +
                "            \"Anschlussnutzer\": \"Stahl,Sarah\",\n" +
                "            \"ErstelltDatum\": \"2024-03-18\",\n" +
                "            \"ErstelltZeit\": \"15:29:15+01:00\",\n" +
                "            \"AenderungDatum\": \"2024-03-18\",\n" +
                "            \"AenderungZeit\": \"15:29:15+01:00\",\n" +
                "            \"LetzteAenderung\": \"2024-04-23T13:20:57.867111+01:00\",\n" +
                "            \"Einheit\": \"\",\n" +
                "            \"Vorgangsdatum\": \"2024-03-18\",\n" +
                "            \"KonvSatzart\": 20,\n" +
                "            \"LaufendeNr\": 1,\n" +
                "            \"Satzart\": 27\n" +
                "        }\n" +
                "    ]\n" +
                "}\n";

        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, Object>> t = objectMapper.readValue(jsonString, List.class);
        System.out.println(t.get(0).getClass().getName());

        /**
         String jsonPath = "$.xpds_responseBody[?(@.setAktive == \"0\" && @.Status == \"\")]";
         List<Map<String, Object>> jsonFilterString = JSONFilter.filterJSON(jsonString, jsonPath);
         List<Map<String, Object>> sortedFilterString = new ArrayList<>();
         String keyToSort = "ableseDatum";
         List<String> valuesToSort = new ArrayList<>();


         for (int i = 0; i < jsonFilterString.size(); i++) {
         valuesToSort.add(JsonPath.read(jsonFilterString, String.format("$[%s]['%s']", i, keyToSort)));
         }
         System.out.println(valuesToSort);
         List<String> valueToSortCopy = new ArrayList<>(valuesToSort);

         valuesToSort.sort(Comparator.reverseOrder());
         System.out.println(valuesToSort);
         List<Integer> index = new ArrayList<>();

         for (String d : valuesToSort) {
         int i = valueToSortCopy.indexOf(d);
         index.add(i);
         valueToSortCopy.set(i, null);
         }
         System.out.println(index);

         List<String> r = new ArrayList<>();
         ObjectMapper objectMapper = new ObjectMapper();
         for (int i : index) {
         r.add(objectMapper.writeValueAsString(JsonPath.read(jsonFilterString, String.format("$[%s]", i))));
         }

         System.out.println(r);*/
    }
}
