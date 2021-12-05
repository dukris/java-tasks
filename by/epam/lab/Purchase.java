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

    public Purchase(Scanner scanner) {
        this.name = scanner.next();
        this.price = new Byn(scanner.nextInt());
        this.number = scanner.nextInt();
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
        return name + ";" + price + ";" + number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Purchase)) return false;
        Purchase purchase = (Purchase) o;
        return Objects.equals(name, purchase.name) && Objects.equals(price, purchase.price);
    }

    @Override
    public String toString() {
        return fieldsToString() + ";" + getCost();
    }
}
