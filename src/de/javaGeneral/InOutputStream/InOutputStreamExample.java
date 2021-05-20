package de.javaGeneral.InOutputStream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class InOutputStreamExample {

    public String readFromInputStream(InputStream inputStream) {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
           String line;
           while ((line = br.readLine()) != null) {
               resultStringBuilder.append(line).append("\n");
           }
        } catch (IOException ioException) {
            ioException.getStackTrace();
        }
        return resultStringBuilder.toString();
    }
    public static void main(String[] args) throws IOException {
        InOutputStreamExample inOutputStreamExample = new InOutputStreamExample();
        InputStream test = InOutputStreamExample.class.getResourceAsStream("fileTest.txt");
        System.out.println(inOutputStreamExample.readFromInputStream(test));
        // warning: open streams should always be closed
        test.close();
    }
}
