package de.xiekang.reflection;

public abstract class Animal implements Eating{
    public static String CATEGORY = "domestic";
    private String name;

    public Animal(String name) {
        this.name = name;
    }

    protected abstract String getSound();

    @Override
    public String eats() {
        return null;
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
