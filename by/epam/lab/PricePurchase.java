package by.epam.lab;

public class PricePurchase extends Purchase {
    private final Byn discount;

    public PricePurchase(String name, Byn price, int number, Byn discount) {
        super(name, price, number);
        if (discount.compareTo(price) > -1) {
            throw new IllegalArgumentException();
        }
        this.discount = discount;
    }

    @Override
    public Byn getCost() {
        return super.getCost().sub(discount.mul(getNumber()));
    }

    @Override
    protected String fieldsToString() {
        return super.fieldsToString() + ";" + discount;
    }
}
