package de.java8tutorial.Enum;

public enum Day {
    // define the instances
    MONDAY(1) {
        @Override
        public Day next() {
            return TUESDAY;
        }
    },
    TUESDAY(2) {
        @Override
        public Day next() {
            return WEDNESDAY;
        }
    },
    WEDNESDAY(3) {
        @Override
        public Day next() {
            return THURSDAY;
        }
    },
    THURSDAY(4) {
        @Override
        public Day next() {
            return FRIDAY;
        }
    },
    FRIDAY(5) {
        @Override
        public Day next() {
            return SATURDAY;
        }
    },
    SATURDAY(6) {
        @Override
        public Day next() {
            return SUNDAY;
        }
    },
    SUNDAY(7) {
        @Override
        public Day next() {
            return MONDAY;
        }
    };

    // define parameter and constructor
    private final int dayNumber;

    Day(int dayNumber) {
        this.dayNumber = dayNumber;
    }

    // define getter
    public int getDayNumber() {
        return dayNumber;
    }

    // define abstract --> should be defined in each instance
    public abstract Day next();
}
