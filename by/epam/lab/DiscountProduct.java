package by.epam.lab;

public class DiscountProduct extends Product {
    private Byn discount;

    public DiscountProduct() {
        super();
        this.discount = new Byn(0);
    }

    public DiscountProduct(String name, Byn price, Byn discount) {
        super(name, price);
        this.discount = discount;
    }

    @Override
    public Byn getPrice() {
        return super.getPrice().sub(discount);
    }

    @Override
    protected String fieldsToString() {
        return super.fieldsToString() + ";" + discount;
    }
}
