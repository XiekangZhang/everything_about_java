package de.javaGeneral.javaReflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Bird extends Animal{
    private boolean walks;

    public Bird() {
        super("bird");
    }

    public Bird(String name, boolean walks) {
        super(name);
        setWalks(walks);
    }

    public Bird(String name) {
        super(name);
    }

    public boolean isWalks() {
        return walks;
    }

    public void setWalks(boolean walks) {
        this.walks = walks;
    }

    @Override
    protected String getSound() {
        return null;
    }

    @Override
    public String eats() {
        return null;
    }

    public static void main(String[] args) {
        try {
            Class<?> birdClass = Class.forName("de.javaGeneral.javaReflect.Bird");
            Constructor<?>[] constructors = birdClass.getConstructors();
            System.out.println(constructors.length);
            // info: instantiate objects at runtime
            Constructor<?> cons1 = birdClass.getConstructor();
            Constructor<?> cons2 = birdClass.getConstructor(String.class);
            Constructor<?> cons3 = birdClass.getConstructor(String.class, boolean.class);

            Bird bird1 = (Bird) cons1.newInstance();
            Bird bird2 = (Bird) cons2.newInstance("Weaver bird");
            Bird bird3 = (Bird) cons3.newInstance("Dove", true);

            System.out.println(bird1.getName());
            System.out.println(bird2.getName());
            System.out.println(bird3.isWalks());

        } catch (ClassNotFoundException | NoSuchMethodException classNotFoundException) {
            classNotFoundException.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
