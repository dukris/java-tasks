package by.epam.lab;

import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Locale;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestRunner extends Assert {

    private final static int PURCHASES_NUMBER = 6;
    private Purchase[] purchasesForTest;

    @Before
    public void initObjects() {
        purchasesForTest = new Purchase[]{
                new Purchase("Milk", new Byn(180), 3),
                new PriceDiscountPurchase("Milk", new Byn(180), 4, 30),
                new PercentDiscountPurchase("Milk", new Byn(180), 20, 5.825),
                new PercentDiscountPurchase("Milk", new Byn(180), 5, 10.0),
                new Purchase("Milk", new Byn(180), 3),
                new Purchase("Milk", new Byn(180), 1)
        };
    }

    @Test
    public void testConstructors() {
        boolean result = true;
        try {
            Purchase[] purchases = new Purchase[12];
            for (int i = 0; i < purchases.length; i++) {
                if (i < 4) {
                    purchases[i] = new Purchase("Milk", new Byn(i * 100), i + 2);
                } else if (i < 8) {
                    purchases[i] = new PriceDiscountPurchase("Milk", new Byn(i * 100), i + 2, i + 10);
                } else {
                    purchases[i] = new PercentDiscountPurchase("Milk", new Byn(i * 100), i + 2, 5.4);
                }
            }
        } catch (RuntimeException exception) {
            result = false;
        }
        assertTrue(result);
    }

    @Test
    public void testFactory() {
        boolean result = true;
        try {
            Scanner scanner = new Scanner(new FileReader("src/in.txt"));
            scanner.useLocale(Locale.ENGLISH);
            Purchase[] purchases = new Purchase[6];
            for (int i = 0; i < purchases.length; i++) {
                purchases[i] = PurchasesFactory.getPurchaseFromFactory(scanner);
            }
        } catch (RuntimeException | FileNotFoundException exception) {
            result = false;
        }
        assertTrue(result);
    }

    @Test
    public void testGetCost() {
        Byn[] costs = {
                new Byn(540),
                new Byn(600),
                new Byn(3400),
                new Byn(900),
                new Byn(540),
                new Byn(180)
        };
        Byn[] costsTest = new Byn[PURCHASES_NUMBER];
        for (int i = 0; i < purchasesForTest.length; i++) {
            costsTest[i] = purchasesForTest[i].getCost();
        }
        assertEquals(costs, costsTest);
    }

    @Test
    public void testEquals() {
        boolean result = true;
        Purchase[] purchases = {
                new Purchase("Milk", new Byn(180), 3),
                new PriceDiscountPurchase("Milk", new Byn(180), 4, 30),
                new PercentDiscountPurchase("Milk", new Byn(180), 20, 5.825),
                new PercentDiscountPurchase("Milk", new Byn(180), 5, 10.0),
                new Purchase("Milk", new Byn(180), 3),
                new Purchase("Milk", new Byn(180), 1)
        };
        for (int i = 0; i < purchasesForTest.length; i++) {
            if(!purchases[i].equals(purchasesForTest[i])){
                result = false;
            }
        }
        assertTrue(result);
    }
}

