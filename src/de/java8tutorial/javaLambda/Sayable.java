package de.java8tutorial.javaLambda;

// no parameter
interface Sayable {
    public String say();
}
class LambdaExpressionExample3 {
    public static void main(String[] args) {
        Sayable s = () -> {
            return "I have nothing to say.";
        };
        System.out.println(s.say());
    }
}
