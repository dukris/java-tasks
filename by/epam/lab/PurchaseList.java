package by.epam.lab;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

public class PurchaseList {
    private List<Purchase> purchases;
    private final Comparator<Purchase> comparator;

    public PurchaseList(String csvName, Comparator<Purchase> comparator) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new FileReader(csvName))) {
            scanner.useLocale(Locale.ENGLISH);
            purchases = new ArrayList<>();
            while (scanner.hasNext()) {
                Purchase purchase = PurchaseFactory.getPurchaseFromFactory(scanner);
                if (purchase != null) {
                    purchases.add(purchase);
                }
            }
            this.comparator = comparator;
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
    }

    public Purchase search(Purchase purchase) {
        Purchase foundPurchase = null;
        int index = Collections.binarySearch(purchases, purchase, comparator);
        if (index > -1) {
            foundPurchase = purchases.get(index);
        }
        return foundPurchase;
    }
}
