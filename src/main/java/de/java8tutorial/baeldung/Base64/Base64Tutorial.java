package de.java8tutorial.baeldung.Base64;

import java.nio.ByteBuffer;
import java.util.Base64;

/**
 * Base64: A-Za-z0-9+/
 */
public class Base64Tutorial {
    public static void main(String[] args) {
        // encoding process
        String originalInput = "test input";
        String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
        System.out.println("encoded string: " + encodedString);

        String encodedString1 = Base64.getEncoder().withoutPadding().encodeToString(originalInput.getBytes());
        System.out.println("encoded string without padding =: " + encodedString1);

        /// decoding process
        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
        System.out.println("decoded string: " + new String(decodedBytes));
    }
}
