package de.javaGeneral.javaReflect;

public abstract class Animal implements Eating{
    public static String CATEGORY = "domestic";
    private String name;
    protected abstract String getSound();

    public Animal(String name) {
        this.name = name;
    }

    public static String getCATEGORY() {
        return CATEGORY;
    }

    public static void setCATEGORY(String CATEGORY) {
        Animal.CATEGORY = CATEGORY;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
