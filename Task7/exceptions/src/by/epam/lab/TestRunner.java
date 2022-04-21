package by.epam.lab;

import by.epam.lab.beans.Byn;
import by.epam.lab.beans.Purchase;
import by.epam.lab.beans.PurchaseFactory;
import by.epam.lab.beans.PurchaseList;
import by.epam.lab.exceptions.CsvLineException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Comparator;
import java.util.Locale;
import java.util.Scanner;

import static org.junit.Assert.assertTrue;

public class TestRunner {
    private PurchaseList purchases;
    private Scanner scanner;

    @Before
    public void initObjects() throws FileNotFoundException {
        try {
            purchases = new PurchaseList("src/in.csv", Comparator.comparing(Purchase::getNumber));
            scanner = new Scanner(new FileReader("src/in.csv"));
            scanner.useLocale(Locale.ENGLISH);
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File not found!");
        }
    }

    @Test
    public void testConstructor() {
        boolean result = true;
        try {
            new PurchaseList("src/in.csv", Comparator.comparing(Purchase::getNumber));
            new PurchaseList("src/in.csv", Comparator.comparing(purchase -> purchase.getCost().getRubs()));
            new PurchaseList("src/in.csv", Comparator.comparing(Purchase::getName));
            new PurchaseList("src/in.csv", Comparator.comparing(Purchase::getCost));
        } catch (FileNotFoundException e) {
            result = false;
        }
        assertTrue(result);
    }

    @Test(expected = FileNotFoundException.class)
    public void testWrongCsvName() throws FileNotFoundException, CsvLineException {
        PurchaseList purchaseList = new PurchaseList("src/1.csv", Comparator.comparing(Purchase::getNumber));
    }

    @Test
    public void testInsert() {
        boolean result = true;
        try {
            purchases.insert(1, new Purchase("Name", new Byn(100), 2));
            purchases.insert(1, new Purchase("Name", new Byn(100), 3));
            purchases.insert(1, new Purchase("Name", new Byn(100), 5));
        } catch (IndexOutOfBoundsException e) {
            result = false;
        }
        assertTrue(result);
    }

    @Test
    public void testSearch() {
        Assert.assertNull(purchases.search(new Purchase("Name", new Byn(100), 18)));
    }

    @Test
    public void testDelete() {
        boolean result = true;
        try {
            purchases.delete(1, 4);
            purchases.delete(1, 3);
            purchases.delete(0, 2);
        } catch (IndexOutOfBoundsException e) {
            result = false;
        }
        assertTrue(result);
    }

    @Test(expected = CsvLineException.class)
    public void testFactory() throws CsvLineException {
        while (scanner.hasNext()) {
            Purchase purchase = PurchaseFactory.getPurchaseFromFactory(scanner);
        }
    }
}
