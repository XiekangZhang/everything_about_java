package de.javaGeneral.javaReflect.Methods;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class OperationTest {
    public static void main(String[] args) {
        try {
            // info: getMethod(name) gets public declared methods
            Method sumInstanceMethod = Operations.class.getMethod("publicSum", int.class, double.class);
            Method multiplyStaticMethod = Operations.class.getMethod("publicStaticMultiply",
                    float.class, long.class);

            // info: getDeclaredMethod(name) gets all methods defined in the class
            Method andPrivateMethod = Operations.class.getDeclaredMethod("privateAnd", boolean.class, boolean.class);
            Method maxProtectedMethod = Operations.class.getDeclaredMethod("protectedMax", int.class, int.class);

            /**
             * Invoking Methods:
             * - Instance Methods
             * - setAccessible to true to access private and protected method
             */
            Operations operations = new Operations();
            Double sumResult = (Double) sumInstanceMethod.invoke(operations, 1, 3);
            System.out.println("1 + 3 = " + sumResult);

            Double multiplyResult = (Double) multiplyStaticMethod.invoke(null, 3.5f, 2);
            System.out.println("3.5 * 2 = " + multiplyResult);

            andPrivateMethod.setAccessible(true);
            Boolean andResult = (Boolean) andPrivateMethod.invoke(operations, true, false);
            System.out.println("private and result: " + andResult);

            maxProtectedMethod.setAccessible(true);
            int maxResult = (int) maxProtectedMethod.invoke(operations, 3, 7);
            System.out.println("protected max result: " + maxResult);

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
