package fxexample;

public enum PersonAge {
    YOUTH(0,18),
    YOUNG_ADULT(19, 40),
    ADULT(41,65),
    SENIOR(66,111);

    private final int min;
    private final int max;

    PersonAge(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public static PersonAge getPersonAge(int age) {
        for (PersonAge p : PersonAge.values()){
            if (p.getMin() <= age && p.getMax() >= age) {
                return p;
            }
        }

        return null;
    }
}
