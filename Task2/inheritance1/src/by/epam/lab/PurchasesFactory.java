package by.epam.lab;

import java.util.Scanner;

public class PurchasesFactory {

    private enum PurchaseKind {
        GENERAL_PURCHASE {
            Purchase getPurchase(Scanner scanner) {
                return new Purchase(scanner);
            }
        },
        PRICE_DISCOUNT_PURCHASE {
            Purchase getPurchase(Scanner scanner) {
                return new PriceDiscountPurchase(scanner);
            }
        },
        PERCENT_DISCOUNT_PURCHASE {
            Purchase getPurchase(Scanner scanner) {
                return new PercentDiscountPurchase(scanner);
            }
        };

        abstract Purchase getPurchase(Scanner scanner);
    }

    public static Purchase getPurchaseFromFactory(Scanner scanner) {
        PurchaseKind purchaseKind = PurchaseKind.valueOf(scanner.next().toUpperCase());
        return purchaseKind.getPurchase(scanner);
    }
}
