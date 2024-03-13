import de.xiekang.talend.DataMasking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {
    static Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        /**
         String mainKey = DataMasking.createRandomString(16);
         String[] stringsTobeMasked = {"A1SADF3723", "c28a2641b3664a66bf01913d71abe73a"};
         logger.log(Level.WARNING, "main key: " + mainKey);


         for (String s : stringsTobeMasked) {
         logger.log(Level.WARNING, "original value: " + s);
         String masked = DataMasking.encryptAESGCM(s, mainKey);
         logger.log(Level.WARNING, "masked value: " + masked);
         String unmasked = DataMasking.decryptAESGCM(masked, mainKey);
         logger.log(Level.WARNING, "unmasked value: " + unmasked);
         logger.log(Level.WARNING, "-----------------------------------");
         //mainkey: pHuIS9MhpnMvpXDm
         //[encrypted]zw53BaK+FNg0KWurCRbQpv3Ls+ZKthquZnZyTbLm1knH1Kw1ZJzoB+sd
         //[encrypted]MMTwvuu8T884/nphmqCvVj3r1Aj5NQvSumQWWv5yruZHmDH9TAM8J4qzgDqgh1033e4cA1Aq1RyA4atjDDtiEg==
         }

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
    }

}
