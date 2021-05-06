package de.java8tutorial.javaLambda;

@FunctionalInterface
interface Drawable1 {
    public void draw();
}

class LambdaExpressionExample2 {
    public static void main(String[] args) {
        int width = 10;
        Drawable1 d2 = () -> {
            System.out.println("Drawing " + width);
        };
        d2.draw();
    }
}
