package de.cimt.DV;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class DVCalculationTest {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        try {
            String input = "teftest";
            byte[] hashBytes = MessageDigest.getInstance("SHA-1")
                    .digest(input.getBytes(StandardCharsets.UTF_8));
            String hash = DatatypeConverter.printHexBinary(hashBytes);
            System.out.println(hash.toLowerCase());
            byte[] result = Base64.getEncoder().encode(hashBytes);
            System.out.println(new String(result));
        } catch (NoSuchAlgorithmException exception) {
            exception.printStackTrace();
        }

        /*String input = "110000000";
        String basehash = "QyQD6OI348J4Bdkwgj4e0YSUbhM=";

        System.out.println(DVCalculation.compare(input, basehash));*/
    }
}
