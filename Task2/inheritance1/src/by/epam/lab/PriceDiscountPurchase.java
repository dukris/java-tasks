package by.epam.lab;

import java.util.Scanner;

public class PriceDiscountPurchase extends Purchase {
    private int discount;

    public PriceDiscountPurchase(){

    }

    public PriceDiscountPurchase(String name, Byn price, int number, int discount) {
        super(name, price, number);
        this.discount = discount;
    }

    public PriceDiscountPurchase(Scanner scanner) {
        super(scanner);
        this.discount = scanner.nextInt();
    }

    @Override
    public Byn getCost() {
        Byn byn = new Byn(getPrice());
        byn.sub(new Byn(discount));
        return byn.mul(getNumber());
    }

    @Override
    protected String fieldsToString() {
        return super.fieldsToString() + ";" + discount;
    }

}
