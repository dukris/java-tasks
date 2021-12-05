package by.epam.lab;

import java.util.Scanner;

import static by.epam.lab.RoundMethod.CEIL;

public class PercentDiscountPurchase extends Purchase {
    private static final int AMOUNT = 5;
    private double percent;

    public PercentDiscountPurchase() {

    }

    public PercentDiscountPurchase(String name, Byn price, int number, double percent) {
        super(name, price, number);
        this.percent = percent;
    }

    public PercentDiscountPurchase(Scanner scanner) {
        super(scanner);
        this.percent = scanner.nextDouble();
    }

    @Override
    public Byn getCost() {
        Byn result = new Byn(getPrice());
        if (Integer.compare(getNumber(), AMOUNT) == 1){
            result.mul((100 - percent) / 100, CEIL, 0);
        }
        result.mul(getNumber());
        return result;
    }

    @Override
    protected String fieldsToString() {
        return super.fieldsToString() + ";" + percent;
    }
}
