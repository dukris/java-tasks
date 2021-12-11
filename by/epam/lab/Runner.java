package by.epam.lab;

import java.util.Arrays;
import java.util.Collections;

public class Runner {

    public static void main(String[] args) {
        Product product = new Product("Milk", new Byn(180));
        AbstractPurchase[] purchases = {
                new PriceDiscountPurchase(product, 5, new Byn(80)),
                new PriceDiscountPurchase(product, 2, new Byn(40)),
                new PercentDiscountPurchase(product, 4, 5.7),
                new PercentDiscountPurchase(product, 8, 10.5),
                new FarePurchase(product, 4),
                new FarePurchase(product, 8)
        };
        printPurchases(purchases);
        Arrays.sort(purchases, Collections.reverseOrder());
        System.out.println("The array after sorting: ");
        printPurchases(purchases);
        calculateMinimumCost(purchases);
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

    private static void calculateMinimumCost(AbstractPurchase[] purchases) {
        Byn minCost = new Byn(10000);
        for (AbstractPurchase purchase : purchases) {
            if (minCost.compareTo(purchase.getCost()) > -1) {
                minCost = purchase.getCost();
            }
        }
        System.out.println("Minimum cost = " + minCost);
    }
}
