import de.xiekang.talend.DataMasking;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.lang.Integer;

public class Main {
    static Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        String test ="{\n" +
                "    \"xpds_responseHeader\": {\n" +
                "        \"sessionID\": 9020453,\n" +
                "        \"sessionKey\": \"LIMA-47ad148b-317e-4949-a586-792882f2fdc6-772733593\",\n" +
                "        \"version\": 0,\n" +
                "        \"timestamp\": \"2024-06-21T14:42:26.527+01:00\",\n" +
                "        \"errorType\": \"\",\n" +
                "        \"errorMessage\": \"\",\n" +
                "        \"errorMessageId\": \"\",\n" +
                "        \"ignoreWarning\": \"\",\n" +
                "        \"listHeader\": {\n" +
                "            \"lastRow\": \"0\",\n" +
                "            \"offset\": 0\n" +
                "        },\n" +
                "        \"meta\": \"\"\n" +
                "    },\n" +
                "    \"xpds_responseBody\": {\n" +
                "        \"preallocationJson\": \"{\\\"Netzanschlusskapazitaet\\\":0.00000,\\\"Kundengruppenschluessel\\\":15,\\\"Obis\\\":\\\"7-20:3.0.0\\\",\\\"LaufzeitSync\\\":true,\\\"PreisschluesselMessdienstl\\\":0,\\\"MengenverteilungsKz\\\":\\\"\\\",\\\"MURefGeraeteFabrikat\\\":0,\\\"Vertragsverlaengerung\\\":1,\\\"ObiskennzahlMalo\\\":\\\"7-0:33.86.0\\\",\\\"FreifeldCrm4\\\":\\\"\\\",\\\"abweichenderBrennwert\\\":0.0000,\\\"Anschlusswert8\\\":0,\\\"AngemeldeterVerbrauch\\\":6000.00000,\\\"PreisschluesselTechSteuMSB\\\":0,\\\"TechnSteuereinr\\\":\\\"\\\",\\\"BerechnenAlt\\\":\\\"J\\\",\\\"MindestlaufzeitBonus\\\":0,\\\"VertragsmengeJahr\\\":0,\\\"FreifeldCrm10\\\":\\\"\\\",\\\"Grundpreisvorgriff\\\":0,\\\"XProduktname\\\":123011438,\\\"Lastprofil\\\":\\\"UNBEK\\\",\\\"wievieltesZaehlwerkHauptzaehler\\\":0,\\\"Oeffnungsklausel\\\":\\\"\\\",\\\"Durchschnittspreis\\\":0.00000000,\\\"Steuerboxnummer\\\":\\\"\\\",\\\"InfoKennzeichen\\\":\\\"\\\",\\\"Haendlerkennung\\\":\\\"EVM\\\",\\\"HauptNebenzaehler\\\":\\\"\\\",\\\"Kommunikationseinr\\\":\\\"\\\",\\\"Anschlusswert9\\\":0,\\\"PreisschluesselWandlUmwandlMSB\\\":0,\\\"Freifeld5\\\":\\\"\\\",\\\"Zaehlertyp\\\":\\\"\\\",\\\"FreifeldCrm2\\\":\\\"\\\",\\\"Anschlusswert14\\\":0.00000,\\\"NNVertragsstatus\\\":\\\"L\\\",\\\"Bilanzierungskreiskennung\\\":\\\"THE0BFH028760075\\\",\\\"BonusText\\\":\\\"\\\",\\\"MaximumZeit\\\":\\\"00:00:00\\\",\\\"PreisschluesselMSB\\\":0,\\\"UmlageKZStomALV\\\":\\\"\\\",\\\"Vertragsart\\\":\\\"\\\",\\\"AngebotsID\\\":0,\\\"UebertragungEDMSysteme\\\":\\\"\\\",\\\"Schmutzfaktor\\\":0.000,\\\"Lieferart\\\":\\\"\\\",\\\"NNPSTechSteuMSB\\\":0,\\\"PreisAusENET\\\":\\\"N\\\",\\\"Unterbrechbarkeit\\\":\\\"\\\",\\\"Zaehlernummer\\\":0,\\\"Kuendigungsfrist1\\\":1,\\\"Verbrauchsart\\\":\\\"\\\",\\\"PreisschluesselTechSteuMdl\\\":0,\\\"Gesamtstellenanzahl\\\":8,\\\"Kuendigungsfrist2\\\":0,\\\"PreisschluesselUmwMDL\\\":0,\\\"KWKGKennzeichen\\\":\\\"\\\",\\\"VarTBPreisschluessel\\\":0,\\\"Vertragssatz\\\":23,\\\"Kundenwert\\\":0.0000,\\\"MSBMelo\\\":\\\"\\\",\\\"Messstellenbetreiber\\\":\\\"9800231500006\\\",\\\"FreifeldCrm5\\\":\\\"\\\",\\\"Netzbetreibernummer\\\":700477,\\\"Berechnen\\\":\\\"J\\\",\\\"EigenschaftMsbMelo\\\":\\\"\\\",\\\"Kontierungskriterium\\\":\\\"\\\",\\\"Mandant\\\":\\\"V\\\",\\\"Abrechnungskennzeichen\\\":\\\"\\\",\\\"FreifeldCrm8\\\":\\\"\\\",\\\"Einspeiselieferant\\\":\\\"\\\",\\\"PreisschluesselKommMDL\\\":0,\\\"MaximumDatum\\\":\\\"0001-01-01\\\",\\\"Verbrauchsart3\\\":\\\"\\\",\\\"Energierichtung\\\":\\\"\\\",\\\"PreisschluesselWandlerUmwMdl\\\":0,\\\"ProduktID\\\":123011438,\\\"angemMengebeibehalt\\\":\\\"\\\",\\\"Anschlusswert5\\\":0,\\\"APRabattBetrag\\\":0.00000000,\\\"ZustandszahlZWert\\\":0.0000,\\\"Zaehlerfabrikat\\\":0,\\\"ArtDerLeistungserbringungMelo\\\":\\\"VAN\\\",\\\"Anschlusswert12\\\":0.00000,\\\"FabrikatHauptzaehler\\\":0,\\\"AblesekennzeichenAlt\\\":\\\"2\\\",\\\"NachkommaStellenzahl\\\":0,\\\"Umrechnungsfaktor\\\":0.0000,\\\"ZFAobis\\\":\\\"7-20:3.0.0\\\",\\\"SpezifischeArbeit\\\":0.000,\\\"MaxWertAbrechnungsjahr\\\":0.00000,\\\"BonusschablonenID\\\":0,\\\"Waermenutzung\\\":\\\"\\\",\\\"NNProduktID\\\":993979998,\\\"ArtDerLeistungserbringungMalo\\\":\\\"VAN\\\",\\\"NummerHauptzaehler\\\":0,\\\"EnergieartHauptzaehler\\\":\\\"\\\",\\\"Abschlagsvorgabe\\\":100.00,\\\"Freifeld8\\\":\\\"\\\",\\\"ArbeitspreisFaktor\\\":0.0000,\\\"PreisschluesselMDL\\\":0,\\\"VertragslaufzeitBis\\\":\\\"2024-09-01\\\",\\\"KzUmlageStromNEV\\\":\\\"\\\",\\\"RundenNachkommastellen\\\":0,\\\"Ablesekennzeichen\\\":\\\"2\\\",\\\"Vertragskategorie\\\":\\\"\\\",\\\"MindestmengeJahr\\\":0,\\\"Stromsteuer\\\":\\\"\\\",\\\"Beschaffungsportfolio\\\":\\\"\\\",\\\"DurchschnittspreisVorgabe\\\":0.00000000,\\\"Mehrwertsteuersatz\\\":0.1900,\\\"PreisschluesselUmwMSB\\\":0,\\\"EEGKZ\\\":\\\"\\\",\\\"BonusbetragBrutto\\\":11.90,\\\"Bonusbeibehalten\\\":\\\"\\\",\\\"Messdienstleister\\\":\\\"\\\",\\\"RemitKz\\\":\\\"\\\",\\\"Grundpreiskennzeichen\\\":\\\"T\\\",\\\"Auftragsnummer\\\":0,\\\"FreifeldCrm6\\\":\\\"\\\",\\\"EinheitMindestlaufzeit\\\":\\\"\\\",\\\"DruckInPfCt\\\":\\\"\\\",\\\"HTNT\\\":\\\"\\\",\\\"Anschlusswert1\\\":0,\\\"PreisschluesselKommMSB\\\":0,\\\"EinheitKuenFrist1\\\":\\\"T\\\",\\\"Energiesteuerkennzeichen\\\":\\\"R\\\",\\\"Anschlusswert3\\\":0,\\\"Anlagenschluessel\\\":\\\"\\\",\\\"Konvertierungsumlage\\\":\\\"\\\",\\\"WievieltesZaehlwerk\\\":1,\\\"ArtNSP\\\":\\\"\\\",\\\"MaxwertVorjahr\\\":0.00000,\\\"Lieferbeginn\\\":\\\"2024-06-01\\\",\\\"NetzVzMaxlaufendeAbrechnung\\\":\\\"\\\",\\\"NetzPreisschluesselAbrechnung\\\":0,\\\"Sperrkennzeichen\\\":\\\"\\\",\\\"VertragslaufzeitVon\\\":\\\"2024-06-01\\\",\\\"Gemeinderabatt\\\":0.00,\\\"Anschlusswert4\\\":0,\\\"XNNProduktname\\\":993979998,\\\"AbschlagsvorgabeBeibehalten\\\":true,\\\"Emobilitätart\\\":\\\"\\\",\\\"EinheitKuendigungsfrist2\\\":\\\"\\\",\\\"AbrMSBueberNNAbrechnung\\\":\\\"\\\",\\\"VersorgungsartMalo\\\":\\\"\\\",\\\"Messwerterfassung\\\":\\\"\\\",\\\"FreifeldCrm1\\\":\\\"\\\",\\\"Wandlerfaktor\\\":0,\\\"ZaehlerstandAlt\\\":100,\\\"OffshoreUmlageKz\\\":\\\"\\\",\\\"Vertriebspartner\\\":\\\"evm\\\",\\\"AbschlagVariabel\\\":\\\"\\\",\\\"Wandlertyp\\\":\\\"\\\",\\\"Ablesekarte\\\":690248283,\\\"Gasspeicherumlage\\\":\\\"R\\\",\\\"Zaehleinrichtung\\\":\\\"\\\",\\\"Energiemengenabrechnung\\\":\\\"\\\",\\\"AbschlagsvorgabeistNull\\\":false,\\\"NettoBetragBonus\\\":10.00,\\\"Redispatch\\\":\\\"\\\",\\\"Provision\\\":0.00000000,\\\"CO2Kennzeichen\\\":\\\"R\\\",\\\"Geraeteart\\\":\\\"ZF\\\",\\\"KzKonzessionsabgabe\\\":\\\"A\\\",\\\"Anschlusswert6\\\":0,\\\"Verbrauchsart2\\\":\\\"\\\",\\\"PreisschluesselAbrechnung\\\":0,\\\"AutrargsIdVertragsPartner\\\":\\\"\\\",\\\"RWEKennzeichenHauptzaehler\\\":0,\\\"Grundpreisberechnung\\\":\\\"\\\",\\\"Marktgebiet\\\":\\\"THE\\\",\\\"Anschlussdatum\\\":\\\"0001-01-01\\\",\\\"FreifeldCrm3\\\":\\\"\\\",\\\"Zaehlergroesse\\\":\\\"\\\",\\\"Konvertierungsentgelt\\\":\\\"\\\",\\\"Versorgungsart\\\":\\\"\\\",\\\"MindestmengeBonus\\\":0.00,\\\"FreifeldCrm7\\\":\\\"\\\",\\\"Zaehlerstand\\\":100,\\\"UnterVertriebspartner\\\":\\\"\\\",\\\"Zaehlverfahren\\\":\\\"\\\",\\\"Mengenumwertertyp\\\":\\\"\\\",\\\"mittlGeodHoehe\\\":0,\\\"Betriebsdruck\\\":0,\\\"SVKKennzeichen\\\":\\\"\\\",\\\"Befestigungsart\\\":\\\"\\\",\\\"additiv\\\":\\\"J\\\",\\\"Gatewaynummer\\\":\\\"\\\",\\\"PreisschluesselMessStBetr\\\":0,\\\"Anschlusswert15\\\":0.00000,\\\"Steuerungskennzeichen\\\":\\\"\\\",\\\"Ereignisdatum\\\":\\\"2024-06-15\\\",\\\"TempMessstelle\\\":\\\"\\\",\\\"Verbrauchsart4\\\":\\\"\\\",\\\"prognVerbrauch\\\":0.00000,\\\"TarifanzahlZaehlereinrichtung\\\":\\\"\\\",\\\"PSKommEinhMSB\\\":0,\\\"Energieart\\\":\\\"G\\\",\\\"Netzkopplungspunkt\\\":\\\"\\\",\\\"KuendigungZum\\\":\\\"\\\",\\\"MURefGeraeteArt\\\":\\\"\\\",\\\"Kuendigungsgrund\\\":\\\"\\\",\\\"DruckNachkommastellen\\\":0,\\\"Anschlusswert13\\\":0.00000,\\\"BonusbetraginEAW11\\\":\\\"\\\",\\\"Anschlusswert11\\\":0.00000,\\\"GrundpreisFaktor\\\":0.0000,\\\"MURefGerateNummer\\\":0,\\\"Typ\\\":\\\"\\\",\\\"AbrMDLueberNNAbrechnung\\\":\\\"J\\\",\\\"EigenschaftMsbMalo\\\":\\\"\\\",\\\"Messeinrichtung\\\":\\\"\\\",\\\"Zeitreihentyp\\\":\\\"\\\",\\\"NettoBonusProzent\\\":0.00,\\\"Anschlusswert10\\\":0,\\\"FreifeldCrm9\\\":\\\"\\\",\\\"Verbrauchsart5\\\":\\\"\\\",\\\"Anschlusswert2\\\":0,\\\"GeraeteartHauptzaehler\\\":\\\"\\\",\\\"Anschlusswert7\\\":0,\\\"ProzessId\\\":32,\\\"Bezirk\\\":9231,\\\"EreignisdatumKey\\\":\\\"2024-06-21\\\",\\\"LetzteAenderung\\\":\\\"2024-06-21-10.56.06.432188\\\",\\\"VertragsID\\\":\\\"690248283G000000023\\\"}\"\n" +
                "    }\n" +
                "}";





        String mainKey = DataMasking.createRandomString(16);
        String[] stringsTobeMasked = {"\"{\\\"WarningCode\\\": [ \\\"AEP0379\\\"]}\""};
        logger.log(Level.WARNING, "main key: " + mainKey);


        for (String s : stringsTobeMasked) {
            logger.log(Level.WARNING, "original value: " + s);
            String masked = DataMasking.encryptAESGCM(s, mainKey);
            logger.log(Level.WARNING, "masked value: " + masked);
            String unmasked = DataMasking.decryptAESGCM(masked, mainKey);
            logger.log(Level.WARNING, "unmasked value: " + unmasked);
            logger.log(Level.WARNING, "-----------------------------------");
            //mainkey: 0WEwDTJIjPKURdOY
            //[encrypted]5Fa4UbP84jNcBy2abkUwoRDQ5aJIkybYD59bPtlrnSGyEE67cwS1VnKBmGQkrdDoVZ1NmkLTcUqUzk8IW/e7LsEu1Q==
            //[encrypted]MMTwvuu8T884/nphmqCvVj3r1Aj5NQvSumQWWv5yruZHmDH9TAM8J4qzgDqgh1033e4cA1Aq1RyA4atjDDtiEg==
        }

        String info = "xxxxxlL92HzloucSUprF3Jv4JmD3J7q3PQa6nE/59V7Du69eu9/w6m2YwMpk9lul2Stje9McMVagCF3JJyrHr0c6ulILGBz+eLAnheXT6A==";
        String key = info.substring(0, 16);
        String i = info.substring(16);
        System.out.println(key + ": " + i);
        logger.log(Level.WARNING,DataMasking.decryptAESGCM(i, key));



        /**
         String endpoint = "V1/vertraege/%s/zaehlerstand/vorgangsdatum/%s";
         String.format("V1/vertraege/%s/zaehlerstand/vorgangsdatum/%s", "123", "2020-01-01".replaceAll("\"", ""));

         String url = "\"{\"aaa\": \"bbb\",\"downloadUri\": \"https://www.google.com\", \"ccc\": \"ddd\"}\"";
         logger.log(Level.WARNING, url.replace(url.substring(url.indexOf("https"), url.indexOf("\"", url.indexOf("https"))), "https://www.bing.com")); **/
        //String s = "123465789";
        //Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        //Matcher matcher = pattern.matcher(s);
        //logger.log(Level.WARNING, "matcher: " + matcher.matches());
        //test("lsjfisjelkfsjkljdkfjskdflsdfslkdfkjklfjkjslfjierowirjekljflksl\n lsjdfiweutjirsjglksjgkhsdjfajskljlkdjfksjdkfjskdllllllllllljkdjfsldkkkkkkkkkkkkkkkkkkkkkkkkkjff");
        //merge();
        ArrayList<String> f = new ArrayList<>();
        f.add("element 1");
        //f.add("element 2");
        System.out.println(f.stream().collect(Collectors.joining(", Änderung um ")));
        HashMap<String, ArrayList<String>> t = new HashMap<>();

    }


    public static List<String> test(String input) {
        List<String> t = new ArrayList<>(Arrays.asList(input.split("\n")));
        logger.log(Level.WARNING, "t: " + t);
        List<String> a = new ArrayList<>();

        t.forEach(s -> {
            a.addAll(Arrays.asList(s.split("(?<=\\G.{80})")));
            System.out.println(a);
        });

        return t;
    }

    public static void merge() {
        String one = "{\"body\":{\"Kundennummer\":123456789,\"Debitornummer\":0,\"Geraetenummer\":\"\",\"Notizart\":\"K\",\"Kontakttyp\":\"S\",\"Kontaktgrund\":\"304\",\"Kontaktrichtung\":\"E\",\"Kontaktmedium\":\"X\",\"Kontaktzeitpunkt\":\"2024-03-07 14:00:25\",\"Kontaktstatus\":\"O\"}}";
        String two = "\"Data\": [ {\"Notiz\": \"Das ist eine Test Notiz. \"}, {\"Notiz\": \" Nur ein Kunde mit Vertrag kann ein Notiz erstellen. \"}, {\"Notiz\": \" Der Kunde ist zufrieden. \"}]";
        String merged = one.substring(0, one.lastIndexOf("}}")) + "," + two + one.substring(one.lastIndexOf("}}"));
        logger.log(Level.WARNING, "merged: " + merged);

        String string = "\"{\\\"BonusbetragBrutto\\\":0.0,\\\"Mandant\\\":\\\"V\\\",\\\"Messlokation\\\":\\\"DExxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\\\",\\\"Zaehlernummer\\\":0,\\\"Zaehlerstand\\\":12345,\\\"BonusschablonenID\\\":0,\\\"Typ\\\":\\\"\\\",\\\"Messstellenbetreiber\\\":\\\"\\\",\\\"Ersatzwertbildungsgrund\\\":\\\"\\\",\\\"PreisAusENET\\\":\\\"J\\\",\\\"Ablesekennzeichen\\\":\\\"x\\\",\\\"Versorgungsart\\\":\\\"\\\",\\\"Netzbetreibernummer\\\":469,\\\"Ereignisdatum\\\":\\\"yyyy-MM-dd\\\",\\\"Lastprofil\\\":\\\"H0\\\",\\\"ObjektNummer\\\":\\\"9976 208 0021 01 0000\\\",\\\"Einspeiselieferant\\\":\\\"\\\",\\\"Energieart\\\":\\\"E\\\",\\\"Mehrwertsteuersatz\\\":0,\\\"Messdienstleister\\\":\\\"\\\",\\\"ProzessId\\\":227,\\\"Bezirk\\\":9976,\\\"EreignisdatumKey\\\":\\\"yyyy-MM-dd\\\",\\\"LetzteAenderung\\\":\\\"2024-04-23-15.24.18.531594\\\",\\\"VertragsID\\\":\\\"\\\",\\\"ZaehlerstandCheckbox\\\":false,\\\"unbart\\\":\\\"V\\\",\\\"LaufendeNummer\\\":0,\\\"konvertierteSatzart\\\":0}\"";
        String test = "\"\\\"{\\\\\\\"BonusbetragBrutto\\\\\\\":0.0,\\\\\\\"Mandant\\\\\\\":\\\\\\\"V\\\\\\\",\\\\\\\"Messlokation\\\\\\\":\\\\\\\"DExxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\\\\\\\",\\\\\\\"Zaehlernummer\\\\\\\":0,\\\\\\\"Zaehlerstand\\\\\\\":12345,\\\\\\\"BonusschablonenID\\\\\\\":0,\\\\\\\"Typ\\\\\\\":\\\\\\\"\\\\\\\",\\\\\\\"Messstellenbetreiber\\\\\\\":\\\\\\\"\\\\\\\",\\\\\\\"Ersatzwertbildungsgrund\\\\\\\":\\\\\\\"\\\\\\\",\\\\\\\"PreisAusENET\\\\\\\":\\\\\\\"J\\\\\\\",\\\\\\\"Ablesekennzeichen\\\\\\\":\\\\\\\"x\\\\\\\",\\\\\\\"Versorgungsart\\\\\\\":\\\\\\\"\\\\\\\",\\\\\\\"Netzbetreibernummer\\\\\\\":469,\\\\\\\"Ereignisdatum\\\\\\\":\\\\\\\"yyyy-MM-dd\\\\\\\",\\\\\\\"Lastprofil\\\\\\\":\\\\\\\"H0\\\\\\\",\\\\\\\"ObjektNummer\\\\\\\":\\\\\\\"9976 208 0021 01 0000\\\\\\\",\\\\\\\"Einspeiselieferant\\\\\\\":\\\\\\\"\\\\\\\",\\\\\\\"Energieart\\\\\\\":\\\\\\\"E\\\\\\\",\\\\\\\"Mehrwertsteuersatz\\\\\\\":0,\\\\\\\"Messdienstleister\\\\\\\":\\\\\\\"\\\\\\\",\\\\\\\"ProzessId\\\\\\\":227,\\\\\\\"Bezirk\\\\\\\":9976,\\\\\\\"EreignisdatumKey\\\\\\\":\\\\\\\"yyyy-MM-dd\\\\\\\",\\\\\\\"LetzteAenderung\\\\\\\":\\\\\\\"2024-04-23-15.24.18.531594\\\\\\\",\\\\\\\"VertragsID\\\\\\\":\\\\\\\"\\\\\\\",\\\\\\\"ZaehlerstandCheckbox\\\\\\\":false,\\\\\\\"unbart\\\\\\\":\\\\\\\"V\\\\\\\",\\\\\\\"LaufendeNummer\\\\\\\":0,\\\\\\\"konvertierteSatzart\\\\\\\":0}\\\"\"";
    }


}
