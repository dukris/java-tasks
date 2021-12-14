package by.epam.lab;

import java.util.Arrays;
import java.util.Collections;

public class Runner {

    public static void main(String[] args) {
        Product product = new Product("Milk", new Byn(180));
        AbstractPurchase[] purchases = {
                new PriceDiscountPurchase(product, 5, new Byn(10)),
                new PriceDiscountPurchase(product, 2, new Byn(40)),
                new PercentDiscountPurchase(product, 4, 5.7),
                new PercentDiscountPurchase(product, 8, 10.5),
                new FarePurchase(product, 4),
                new FarePurchase(product, 8)
        };
        printPurchases(purchases);
        Arrays.sort(purchases);
        System.out.println("The array after sorting: ");
        printPurchases(purchases);
        System.out.println("Minimum cost = " + purchases[purchases.length - 1].getCost());
        int index = search(purchases, new Byn(500));
        if (index > -1) {
            System.out.println("Some purchase with cost equalled to 5.00 BYN: " + purchases[index]);
        } else {
            System.out.println("Instance not found");
        }
    }

    private static int search(AbstractPurchase[] purchases, Byn cost) {
        return Arrays.binarySearch(purchases, new PriceDiscountPurchase("Product name", cost, 1, new Byn(0)));
    }

    private static void printPurchases(AbstractPurchase[] purchases) {
        for (AbstractPurchase purchase : purchases) {
            System.out.println(purchase);
        }
    }
}
