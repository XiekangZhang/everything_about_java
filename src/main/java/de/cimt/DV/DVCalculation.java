package de.cimt.DV;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class DVCalculation {

    private final String input;
    private final String hash;
    private final String basehash;

    public DVCalculation(String basehash, String input, String hash) {
        this.basehash = basehash;
        this.input = input;
        this.hash = hash;
    }

    public static String BaseToHash(String basehash) {
        return DatatypeConverter.printHexBinary(Base64.getDecoder().decode(basehash));
    }

    public static String StringToHash(String input) throws NoSuchAlgorithmException {
        return DatatypeConverter.printHexBinary(MessageDigest.getInstance("SHA-1")
                .digest(input.getBytes(StandardCharsets.UTF_8)));
    }

    public static int compare(String input, String basehash) throws NoSuchAlgorithmException {
        String stringtohash = StringToHash(input);
        System.out.println(stringtohash);
        String basetohash = BaseToHash(basehash);
        System.out.println(basetohash);
        return stringtohash.equalsIgnoreCase(basetohash) ? 1 : 0;
    }

    public String getInput() {
        return input;
    }

    public String getHash() {
        return hash;
    }

    public String getBasehash() {
        return basehash;
    }
}
