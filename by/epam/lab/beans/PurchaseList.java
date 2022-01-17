package by.epam.lab.beans;

import by.epam.lab.exceptions.CsvLineException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

public class PurchaseList {
    private List<Purchase> purchases;
    private final Comparator<Purchase> comparator;
    private boolean isSorted = false;

    public PurchaseList(String csvName, Comparator<Purchase> comparator) throws FileNotFoundException {
        this.comparator = comparator;
        try (Scanner scanner = new Scanner(new FileReader(csvName))) {
            scanner.useLocale(Locale.ENGLISH);
            purchases = new ArrayList<>();
            while (scanner.hasNext()) {
                Purchase purchase = null;
                try {
                    purchase = PurchaseFactory.getPurchaseFromFactory(scanner);
                } catch (CsvLineException e) {
                    System.err.println(e.getMessage());
                }
                if (purchase != null) {
                    purchases.add(purchase);
                }
            }
        } catch (FileNotFoundException e) {
            purchases = new ArrayList<>();
            throw new FileNotFoundException("File not found!");
        }
    }

    public void print() {
        purchases.forEach(System.out::println);
    }

    public void insert(int index, Purchase purchase) {
        try {
            purchases.add(index, purchase);
            isSorted = false;
        } catch (IndexOutOfBoundsException e) {
            purchases.add(purchase);
            throw new IndexOutOfBoundsException();
        }
    }

    public void delete(int indexFrom, int indexTo) {
        try {
            if (indexFrom < 0 || indexTo < 0) {
                throw new IndexOutOfBoundsException();
            }
            List<Purchase> forDeletion = purchases.stream()
                    .filter(purchase -> purchases.indexOf(purchase) >= indexFrom && purchases.indexOf(purchase) < indexTo)
                    .collect(Collectors.toList());
            forDeletion.forEach(purchase -> purchases.remove(purchase));
            isSorted = false;
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException();
        }
    }

    public void calculateTotalCost() {
        int total = 0;
        for (Purchase purchase : purchases) {
            total = total + purchase.getCost().getRubs() * 100 + purchase.getCost().getCoins();
        }
        System.out.println("Total cost: " + new Byn(total));
    }

    public void sort() {
        purchases.sort(comparator);
        isSorted = true;
    }

    public Purchase search(Purchase purchase) {
        if (!isSorted) {
            sort();
        }
        Purchase foundPurchase = null;
        int index = Collections.binarySearch(purchases, purchase, comparator);
        if (index > -1) {
            foundPurchase = purchases.get(index);
        }
        return foundPurchase;
    }

    public List<Purchase> getList() {
        return purchases.stream()
                .map(Purchase::getCopy)
                .collect(Collectors.toList());
    }
}
