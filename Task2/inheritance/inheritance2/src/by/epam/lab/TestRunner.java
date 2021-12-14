package by.epam.lab;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestRunner {

    private final static int PURCHASES_NUMBER = 6;
    private AbstractPurchase[] purchasesForTest;
    private Byn[] costsForTest;

    @Before
    public void initObjects() {
        purchasesForTest = new AbstractPurchase[]{
                new PriceDiscountPurchase("Milk", new Byn(180), 5, new Byn(80)),
                new PriceDiscountPurchase("Milk", new Byn(180), 2, new Byn(40)),
                new PercentDiscountPurchase("Milk", new Byn(180), 4, 5.7),
                new PercentDiscountPurchase("Milk", new Byn(180), 8, 10.5),
                new FarePurchase("Milk", new Byn(180), 4),
                new FarePurchase("Milk", new Byn(180), 8)
        };
        costsForTest = new Byn[]{
                new Byn(500),
                new Byn(200),
                new Byn(700),
                new Byn(1200),
                new Byn(1000),
                new Byn(1700)
        };
    }

    @Test
    public void testConstructors() {
        boolean result = true;
        try {
            new Product("Milk", new Byn(200));
            new Product("Bread", new Byn(500));
            new Product("Ham", new Byn(220));
            new Product("Cheese", new Byn(218));
            new Product("Potato", new Byn(100));
            new Product("Orange", new Byn(53));
            new Product("Apple", new Byn(850));
            new Product("Lemon", new Byn(330));
            new Product("Lime", new Byn(240));
            new Product("Name", new Byn(70));
            new PriceDiscountPurchase("Milk", new Byn(190), 5, new Byn(80));
            new PriceDiscountPurchase("Milk", new Byn(190), 2, new Byn(40));
            new PriceDiscountPurchase("Milk", new Byn(10), 5, new Byn(80));
            new PriceDiscountPurchase("Milk", new Byn(500), 2, new Byn(40));
            new PercentDiscountPurchase("Milk", new Byn(190), 4, 5.7);
            new PercentDiscountPurchase("Milk", new Byn(190), 8, 10.5);
            new PercentDiscountPurchase("Milk", new Byn(180), 4, 5.0);
            new PercentDiscountPurchase("Milk", new Byn(190), 8, 3.2);
            new FarePurchase("Milk", new Byn(190), 4);
            new FarePurchase("Milk", new Byn(40), 8);
            new FarePurchase("Milk", new Byn(75), 7);
            new FarePurchase("Milk", new Byn(500), 3);
        } catch (RuntimeException exception) {
            result = false;
        }
        assertTrue(result);
    }

    @Test
    public void testGetCost() {
        Byn[] costs = new Byn[PURCHASES_NUMBER];
        for (int i = 0; i < purchasesForTest.length; i++) {
            costs[i] = purchasesForTest[i].getCost();
        }
        assertEquals(costsForTest, costs);
    }

    @Test
    public void testCompareTo() {
        boolean result = true;
        AbstractPurchase[] purchases = {
                new PriceDiscountPurchase("Milk", new Byn(180), 5, new Byn(80)),
                new PriceDiscountPurchase("Milk", new Byn(180), 2, new Byn(40)),
                new PercentDiscountPurchase("Milk", new Byn(180), 4, 5.7),
                new PercentDiscountPurchase("Milk", new Byn(180), 8, 10.5),
                new FarePurchase("Milk", new Byn(180), 4),
                new FarePurchase("Milk", new Byn(180), 8)
        };
        Byn[] costs = {
                new Byn(500),
                new Byn(200),
                new Byn(700),
                new Byn(1200),
                new Byn(1000),
                new Byn(1700)
        };
        for (int i = 0; i < purchasesForTest.length; i++) {
            if (purchases[i].compareTo(purchasesForTest[i]) != 0 || costs[i].compareTo(costsForTest[i]) != 0) {
                result = false;
            }
        }
        assertTrue(result);
    }

    @Test
    public void testSearch() {
        boolean result = true;
        Arrays.sort(purchasesForTest);
        int firstIndex = Arrays.binarySearch(purchasesForTest, new PriceDiscountPurchase("Product name", new Byn(1700), 1, new Byn(0)));
        int secondIndex = Arrays.binarySearch(purchasesForTest, new PriceDiscountPurchase("Product name", new Byn(200), 1, new Byn(0)));
        int thirdIndex = Arrays.binarySearch(purchasesForTest, new PriceDiscountPurchase("Product name", new Byn(1), 1, new Byn(0)));
        if (firstIndex > -1 && secondIndex > -1 && thirdIndex <= -1) {
            System.out.println("Some purchase with cost equalled to 17.40 BYN: " + purchasesForTest[firstIndex]);
            System.out.println("Some purchase with cost equalled to 1.80 BYN: " + purchasesForTest[secondIndex]);
        } else {
            result = false;
        }
        assertTrue(result);
    }
}

