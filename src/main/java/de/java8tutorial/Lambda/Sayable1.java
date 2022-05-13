package de.java8tutorial.Lambda;

interface Sayable1 {
    public String say(String name);
}

class LambdaExpressionExample4 {
    public static void main(String[] args) {
        Sayable1 s1 = (name) -> {
            return "Hello, " + name;
        };
        System.out.println(s1.say("Sonoo"));

        Sayable1 s2 = name -> {
            return "Hello, " + name;
        };
        System.out.println(s2.say("Sonoo"));
    }
}