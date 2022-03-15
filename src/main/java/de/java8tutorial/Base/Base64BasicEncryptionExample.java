package de.java8tutorial.Base;

import java.util.Base64;

public class Base64BasicEncryptionExample {
    public static void main(String[] args) {
        // Getting encoder
        Base64.Encoder encoder = Base64.getEncoder();
        // Creating byte array
        byte[] bytes = {1, 2};
        // Encoding byte array
        byte[] byteEncoding = encoder.encode(bytes);
        System.out.println("Encoded byte array: " + byteEncoding);

        byte[] bytes1 = new byte[5];
        int x = encoder.encode(bytes, bytes1); // encode(byte[] src, byte[] dst)
        System.out.println("Encoded byte array written to another array: " + bytes1);
        System.out.println("Number of bytes written: " + x);

        // Encoding string
        String str = encoder.encodeToString("JavaPoint".getBytes());
        System.out.println("Encoded string: " +str);

        // Getting decoder
        Base64.Decoder decoder = Base64.getDecoder();
        // Decoding string
        String dstr = new String(decoder.decode(str));
        System.out.println("Decoded string: " + dstr);
    }
}
