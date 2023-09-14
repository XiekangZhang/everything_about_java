package de;


import java.io.*;

public class main {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src/main/java/de/javaGeneral/InOutputStream/test.txt");
        //OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file));

        InputStream inputStream = new BufferedInputStream(new FileInputStream(file)); // throw error.
    }
}
