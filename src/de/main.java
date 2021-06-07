package de;

public class main {
    public static void main(String[] args) {
        String remotePath = "/folder/test/input";
        if (String.valueOf(remotePath.charAt(0)).equals("/") == true) {
            remotePath = remotePath.substring(1);
            System.out.println("fist: " + remotePath);
        }
        String[] array = remotePath.split("/", 2);
        System.out.println("second: " + array[0] + " " + array[1]);
    }
}
