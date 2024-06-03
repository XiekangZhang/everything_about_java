package de.xiekang.basic;

import java.util.ArrayList;
import java.util.List;

public class MyArray {
    public static void main(String[] args) {
       String kundennummer = "123456789";
       String energieArt = "E";
       int satz = 123;
        System.out.println(String.format("%s%s%09d", kundennummer, energieArt, satz));
    }
}

