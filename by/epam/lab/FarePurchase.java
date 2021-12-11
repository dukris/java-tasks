package by.epam.lab;

public class FarePurchase extends AbstractPurchase{
    private static final Byn FARE = new Byn(300);

    public FarePurchase() {

    }

    public FarePurchase(Product product, int number) {
        super(product, number);
    }

    public FarePurchase(String name, Byn price, int number) {
        super(name, price, number);
    }

    @Override
    public Byn getCost() {
        return new Byn(super.getCost().add(FARE));
    }
}
