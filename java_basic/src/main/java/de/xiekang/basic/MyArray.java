package de.xiekang.basic;


import java.util.Arrays;

/**
 * TODO:
 * 1. Create a two dimensional array with number
 * 2. Add two methods to sum the all columns and rows
 */
public class MyArray {

    public static int sum(int[][] a) {
        int sumR = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                sumR += a[i][j];
            }
        }
            return sumR;
    }

    public int[] sumRow(int[][] a) {
        int[] sumZ = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            int helpsum = 0;
            for (int j = 0; j < a[i].length; j++) {
                helpsum += a[i][j];
            }
            sumZ[i] = helpsum;
        }
        return sumZ;
    }

    public int[] sumColumn(int[][] a) {
        int[] sumZ = new int[a.length];
        for (int j = 0; j < a.length; j++) {
            int helpsum = 0;
            for (int i = 0; i < a[j].length; i++) {
                helpsum += a[i][j];
            }
            sumZ[j] = helpsum;
        }
        return sumZ;
    }

    public static void main(String[] args) {
        int[][] test = new int[2][2];
        test[0][0] = 1;
        test[0][1] = 3;
        test[1][0] = 9;
        test[1][1] = 6;

        // instance --> Initilazierung
        MyArray myArray = new MyArray();

        System.err.println("Sum Row:");
        Arrays.stream(myArray.sumRow(test)).forEach(System.err::println);

        System.err.println("Sum Col:");
        Arrays.stream(myArray.sumColumn(test)).forEach(System.err::println);

        System.err.println(MyArray.sum(test));

        System.err.println(Arrays.stream(test[0]).sum());
    }
}
/*public class MyArray {



    public static void main(String[] args) {

        int [] arrayZwei = {7,18};//Deklarieren und Initialisieren des Arrays feld fängt bei null an zu zählen für die erste Position ergo letzte n-
        int [] arrayDrei = {5,9};


        System.out.println(arrayZwei[0];arrayZwei[1]);//Rückgabe
        System.out.println(arrayDrei[0]; arrayDrei[1]);//Rückgabe

    }

}*/

