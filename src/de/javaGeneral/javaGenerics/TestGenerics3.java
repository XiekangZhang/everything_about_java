package de.javaGeneral.javaGenerics;

public class TestGenerics3 {
    public static void main(String[] args) {
        MyGen<Integer> m = new MyGen<>();
        m.add(2);
        System.out.println(m.getObj());
    }
}
