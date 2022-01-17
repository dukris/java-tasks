package by.epam.lab.beans;

import by.epam.lab.exceptions.NonpositiveArgumentException;

public class Purchase {
    private final String name;
    private final Byn price;
    private final int number;

    public Purchase() {
        this.name = "";
        this.price = new Byn(0);
        this.number = 0;
    }

    public Purchase(String name, Byn price, int number) {
        this.name = name;
        this.price = price;
        if (number <= 0) {
            throw new NonpositiveArgumentException();
        }
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public Byn getPrice() {
        return price;
    }

    public int getNumber() {
        return number;
    }

    public Purchase getCopy(){
        return new Purchase(name, price, number);
    }

    public Byn getCost() {
        Byn byn = new Byn(price);
        return byn.mul(number);
    }

    protected String fieldsToString() {
        return this.getClass().getSimpleName() + ";" + name + ";" + price + ";" + number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Purchase)) return false;
        Purchase purchase = (Purchase) o;
        return name.equals(purchase.name) && price.equals(purchase.price);
    }

    @Override
    public int hashCode() {
        int result = price == null ? 0 : price.hashCode();
        result = 31 * result + (name == null ? 0 : name.hashCode());
        return result;

    }

    @Override
    public String toString() {
        return fieldsToString() + ";" + getCost();
    }
}