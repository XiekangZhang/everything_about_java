package de.javaAPIs.LangAPI.LangClassLoader;

import com.sun.javafx.binding.Logging;

import java.util.ArrayList;

/**
 * loading java classes during runtime dynamically to the JVN
 * these java classes aren't loaded into memory all at once, but when required by an application.
 */

public class ClassLoaderTest {
    // type of class loaders
    public static void printClassLoaders() throws ClassNotFoundException {
        System.out.println("Classloader of this class: " + ClassLoaderTest.class.getClassLoader()); // application
        System.out.println("Classloader of Logging: " + Logging.class.getClassLoader()); // extension
        System.out.println("Classloader of ArrayList: " + ArrayList.class.getClassLoader()); // bootstrap --> parent of all the others
    }
}
