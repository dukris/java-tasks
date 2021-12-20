package by.epam.lab;

import java.util.Objects;
import java.util.Scanner;

public class Purchase {
    private String name;
    private Byn price;
    private int number;

    public Purchase() {

    }

    public Purchase(String name, Byn price, int number) {
        this.name = name;
        this.price = price;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byn getPrice() {
        return price;
    }

    public void setPrice(Byn price) {
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Byn getCost() {
        Byn byn = new Byn(price);
        return byn.mul(number);
    }

    protected String fieldsToString() {
        return this.getClass().getSimpleName() + ";" + name + ";" + price + ";" + number;
    }

    public boolean findByName(Purchase other) {
        return name.equals(other.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Purchase)) return false;
        Purchase purchase = (Purchase) o;
        return price.equals(purchase.price);
    }

    @Override
    public int hashCode() {
        return price.getRubs() * 100 + price.getCoins();
    }

    @Override
    public String toString() {
        return fieldsToString() + ";" + getCost();
    }
}