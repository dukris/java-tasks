package by.epam.lab;

public class Byn implements Comparable<Byn> {
    private final int value;

    public Byn() {
        this.value = 0;
    }

    public Byn(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException();
        }
        this.value = value;
    }

    public Byn(int rubs, int coins) {
        this(rubs * 100 + coins);
    }

    public Byn(Byn byn) {
        this(byn.value);
    }

    public Byn add(Byn byn) {
        return new Byn(this.value + byn.value);
    }

    public Byn sub(Byn byn) {
        return new Byn(this.value - byn.value);
    }

    public Byn mul(int k) {
        return new Byn(this.value * k);
    }

    public int getRubs() {
        return value / 100;
    }

    public int getCoins() {
        return value % 100;
    }

    public Byn mul(double k, RoundMethod roundMethod, int d) {
        int value = roundMethod.round(this.value * k, d);
        return new Byn(value);
    }

    public Byn round(RoundMethod roundMethod, int d) {
        return new Byn(roundMethod.round(value, d));
    }

    @Override
    public String toString() {
        return String.format("%d.%02d", getRubs(), getCoins());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Byn byn = (Byn) o;
        return value == byn.value;
    }

    @Override
    public int compareTo(Byn o) {
        return Integer.compare(this.value, o.value);
    }
}
