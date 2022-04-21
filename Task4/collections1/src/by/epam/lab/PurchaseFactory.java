package by.epam.lab;

import java.util.Scanner;

public class PurchaseFactory {
    public static Purchase getPurchaseFromFactory(Scanner scanner) {
        Purchase purchase;
        String value = scanner.nextLine();
        String[] values = value.split(";");
        String name = values[0];
        Byn price = new Byn(Integer.parseInt(values[1]));
        int number = Integer.parseInt(values[2]);
        if (values.length == 3) {
            purchase = new Purchase(name, price, number);
        } else {
            Byn discount = new Byn(Integer.parseInt(values[3]));
            purchase = new PricePurchase(name, price, number, discount);
        }
        return purchase;
    }
}
