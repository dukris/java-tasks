package by.epam.lab;

public class Product implements Priceable {
    private final String name;
    private final Byn price;

    public Product() {
        this.name = "";
        this.price = new Byn(0);
    }

    public Product(String name, Byn price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Byn getPrice() {
        return price;
    }

    protected String fieldsToString() {
        return name + ";" + price;
    }

    @Override
    public String toString() {
        return fieldsToString() + ";" + getPrice();
    }
}
