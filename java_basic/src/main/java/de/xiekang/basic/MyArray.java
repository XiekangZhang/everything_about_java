package de.xiekang.basic;

import java.util.ArrayList;
import java.util.List;

public class MyArray {
    public static void main(String[] args) {
       String kundennummer = "123456789";
       String energieArt = "E";
       int satz = 123;
        System.out.println(String.format("%s%s%09d", kundennummer, energieArt, satz));
        String test = "{\n" +
                "    \"kunden\": {\n" +
                "        \"typ\": \"G\",\n" +
                "        \"stammdaten\": [\n" +
                "            {\n" +
                "                \"anrede\": \"?\",\n" +
                "                \"firmaName\": \"Peter Meier GmbH\",\n" +
                "                \"zusatzinfo\": \"\",\n" +
                "                \"steuernummer\": \"123/456/78914\",\n" +
                "                \"kontakt\": [\n" +
                "                    {\n" +
                "                   \"typ\": \"Fax\",\n" +
                "                   \"kontaktdetails\": \"+49123456789\",\n" +
                "                   \"zusatzinfo\": \"nur Vormittag erreichbar\"\n" +
                "                   }\n" +
                "                ],\n" +
                "                \"adresse\": [\n" +
                "                    {\n" +
                "                        \"adresseTyp\": \"sfsdfsfsdfsf\",\n" +
                "                        \"strasse\": \"sfdsdfs\",\n" +
                "                        \"adresszusatz\": \"\",\n" +
                "                        \"hausnummer\": 123,\n" +
                "                        \"hausnummerzusatz\": \"\",\n" +
                "                        \"postleitzahl\": \"12345\",\n" +
                "                        \"postfach\": \"\",\n" +
                "                        \"ort\": \"Berlin\",\n" +
                "                        \"ortsteil\": \"Schöneberg\",\n" +
                "                        \"land\": \"DE\"\n" +
                "                    }\n" +
                "                ]\n" +
                "            }\n" +
                "        ],\n" +
                "        \"kundenSonstigeAttribute\": [\n" +
                "            {\n" +
                "                \"attribute\": \"geschaeftspartnernummer\",\n" +
                "                \"wert\": \"12345678\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"attribute\": \"WerbeInfoSperre\",\n" +
                "                \"wert\": \"\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"attribute\": \"Vorsteuerkennzeichen\",\n" +
                "                \"wert\": \"K\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"attribute\": \"Mahnausschlusskennzeichen\",\n" +
                "                \"wert\": 0\n" +
                "            },\n" +
                "            {\n" +
                "                \"attribute\": \"KundenZusatzinfo\",\n" +
                "                \"wert\": \"\"\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "}";
    }
}

