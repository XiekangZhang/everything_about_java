package de.javaAPIs.LangAPI;

import de.javaAPIs.LangAPI.LangClassLoader.ClassLoaderTest;
import de.javaGeneral.Generics.MyGen;

import java.lang.Boolean;

public class javaLangAPI {
    public static void BooleanTest() {
        // construct
        Boolean b = new Boolean(true);
        Boolean b1 = new Boolean("true");
        // primitive
        boolean b2 = b;
        boolean b3 = b1.booleanValue(); // return boolean (Primitive)
        // useful methods
        System.out.println(Boolean.valueOf("true"));
        System.out.println(Boolean.parseBoolean("true"));
    }

    public static void ByteTest() {
        // Field
        int byteBytes = Byte.BYTES;
        System.out.println(byteBytes + " Size: " + Byte.SIZE + " Max: " + Byte.MAX_VALUE + " Min: " + Byte.MIN_VALUE);
        // Constructors
        Byte b1 = new Byte((byte) 10);
        Byte b2 = new Byte("3");
        // Methods
        System.out.println(b1.byteValue());
        // info: -/+ 0x, 0X, #, 0
        System.out.println(Byte.decode("-10")); // String to Byte decimal
        System.out.println(Byte.decode("-0x10")); // String to Byte Hex
        System.out.println(Byte.decode("-0X10")); // String to Byte Hex
        System.out.println(Byte.decode("-#10")); // String to Byte Hex
        System.out.println(Byte.decode("-010")); // String to Byte Octo

        System.out.println(Byte.parseByte("16", 16)); // #16 back to decimal = 22
        System.out.println(b2.shortValue()); // return a short type
    }

    public static void CharacterTest() {
        // Fields
        System.out.println(Character.BYTES);
        System.out.println(Character.COMBINING_SPACING_MARK);

        // Methods
        Character character = new Character('9'); // or = '1'
        char[] characters = {'1', '2', '3', '4', '5'};
        // warning: String: immutable, CharSequence: mutable & immutable
        CharSequence charSequence = "12345";
        CharSequence charSequence1 = new StringBuffer("12345");
        CharSequence charSequence2 = new StringBuilder("12345");
        System.out.println(Character.codePointAt(characters, 1, 2)); // return unicode back
        System.out.println(Character.codePointAt(charSequence, 2));
        System.out.println(Character.codePointCount(characters, 0, 2));
        System.out.println(Character.highSurrogate('\uD800'));
        System.out.println(Character.isWhitespace(' '));
    }

    public static void ClassTest()throws InstantiationException, IllegalAccessException {
        MyGen<CharSequence> sequenceMyGen = new MyGen<>();
        sequenceMyGen.newInstance("this is a charsequence");
        System.out.println(sequenceMyGen.getObj());
    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        //BooleanTest();
        //ByteTest();
        //CharacterTest();
        /*try {
            ClassTest();
        } catch (InstantiationException | IllegalAccessException e) {
            throw e;
        }*/
        ClassLoaderTest.printClassLoaders();
    }
}
