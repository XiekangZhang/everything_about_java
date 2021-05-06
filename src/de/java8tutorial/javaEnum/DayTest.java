package de.java8tutorial.javaEnum;

public class DayTest {
    public static void main(String[] args) {
        for (Day day: Day.values()) {
            System.out.println(day + "(" + day.getDayNumber() + "), next is " + day.next());
        }
    }
}
