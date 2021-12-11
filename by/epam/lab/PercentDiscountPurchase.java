package by.epam.lab;

import static by.epam.lab.RoundMethod.FLOOR;

public class PercentDiscountPurchase extends AbstractPurchase {
    private static final int AMOUNT = 5;
    private double percent;

    public PercentDiscountPurchase() {

    }

    public PercentDiscountPurchase(Product product, int number, double percent) {
        super(product, number);
        this.percent = percent;
    }

    public PercentDiscountPurchase(String name, Byn price, int number, double percent) {
        super(name, price, number);
        this.percent = percent;
    }

    @Override
    public Byn getCost() {
        Byn result = new Byn(getProduct().getPrice());
        result.mul(getNumber());
        if (Integer.compare(getNumber(), AMOUNT) == 1){
            result.mul((100 - percent) / 100, FLOOR, 0);
        }
        return result;
    }

    @Override
    protected String fieldsToString() {
        return super.fieldsToString() + ";" + percent;
    }
}
