package by.epam.lab;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Locale;
import java.util.Scanner;

public class Runner {

    private final static int PURCHASES_NUMBER = 6;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new FileReader("src/in.txt"))) {
            scanner.useLocale(Locale.ENGLISH);
            Purchase[] purchases = new Purchase[PURCHASES_NUMBER];
            Byn maxCost = new Byn(0);
            boolean areEqual = true;
            for (int i = 0; i < purchases.length; i++) {
                purchases[i] = PurchasesFactory.getPurchaseFromFactory(scanner);
                System.out.println(purchases[i]);
                if (maxCost.compareTo(purchases[i].getCost()) < 0) {
                    maxCost = purchases[i].getCost();
                }
                if (areEqual) {
                    areEqual = purchases[i].equals(purchases[0]);
                }
            }
            System.out.println("The purchase with maximum cost: " + maxCost);
            System.out.println("All purchases are equal: " + areEqual);
        } catch (FileNotFoundException e) {
            System.err.println("Input file is not found");
        }
    }
}
