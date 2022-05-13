package de.java8tutorial.Base;

import java.util.Base64;

public class Base64BasicEncryptionExample1 {
    public static void main(String[] args) {
        // Getting encoder
        Base64.Encoder encoder = Base64.getUrlEncoder();
        // Encoding URL
        String estr = encoder.encodeToString("http://www.javapoint.com/java-tutoral".getBytes());
        System.out.println("Encoded URL: " + estr);

        // Getting decoder
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String dstr = new String(decoder.decode(estr));
        System.out.println("Decoded URL: " + dstr);
    }
}
