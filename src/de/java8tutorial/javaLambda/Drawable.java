package de.java8tutorial.javaLambda;

interface Drawable {
    public void draw();
}

class LambdaExpressionExample {
    public static void main(String[] args) {
        int width = 10;
        Drawable d = new Drawable() {
            @Override
            public void draw() {
                System.out.println("Drawing " + width);
            }
        };
        d.draw();
    }
}
