package de.javaGeneral.Generics;

public class TestGenerics3 {
    public static void main(String[] args) {
        MyGen<Integer> m = new MyGen<>();
        MyGen<String> stringMyGen = new MyGen<>();
        try {
            stringMyGen.newInstance("this is a string");
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        m.add(2);
        System.out.println(m.getObj());
        System.out.println(stringMyGen.getObj());
    }
}
