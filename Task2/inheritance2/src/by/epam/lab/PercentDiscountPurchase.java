package by.epam.lab;

import static by.epam.lab.RoundMethod.ROUND;

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
    protected Byn getFinalCost(Byn baseCost) {
        double discount = 1.0;
        if (Integer.compare(getNumber(), AMOUNT) == 1){
            discount = (1 - percent / 100);
        }
        return baseCost.mul(discount, ROUND, 1);
    }

    @Override
    protected String fieldsToString() {
        return super.fieldsToString() + ";" + percent;
    }
}
