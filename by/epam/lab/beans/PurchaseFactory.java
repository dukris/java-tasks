package by.epam.lab.beans;

import by.epam.lab.exceptions.CsvLineException;

import java.util.Scanner;

public class PurchaseFactory {
    public static Purchase getPurchaseFromFactory(Scanner scanner) throws CsvLineException {
        Purchase purchase = null;
        String value = "";
        try {
            value = scanner.nextLine();
            String[] values = value.split(";");
            String name = values[0];
            if (name.equals("")){
                throw new NumberFormatException();
            }
            Byn price = new Byn(Integer.parseInt(values[1]));
            int number = Integer.parseInt(values[2]);
            if (values.length == 3) {
                purchase = new Purchase(name, price, number);
            } else {
                Byn discount = new Byn(Integer.parseInt(values[3]));
                purchase = new PricePurchase(name, price, number, discount);
            }
        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
            throw new CsvLineException(value);
        }
        return purchase;
    }
}
