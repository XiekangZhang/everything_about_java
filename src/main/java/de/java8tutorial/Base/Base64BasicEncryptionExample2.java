package de.java8tutorial.Base;

import java.util.Base64;

public class Base64BasicEncryptionExample2 {
    public static void main(String[] args) {
        // Getting encoder
        Base64.Encoder encoder = Base64.getMimeEncoder();
        String message = "Hello, \nYou are informed regarding your inconsistency of work.";
        String estr = encoder.encodeToString(message.getBytes());
        System.out.println("Encoded MIME message: " + estr);

        // Getting MIME decoder
        Base64.Decoder decoder = Base64.getMimeDecoder();
        String dstr = new String(decoder.decode(estr));
        System.out.println("Decoded message: " +dstr);
    }
}
