package by.epam.lab;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Runner {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new FileReader("src/in.csv"))) {
            scanner.useLocale(Locale.ENGLISH);

            Map<Purchase, WeekDay> firstPurchaseMap = new HashMap<>();
            Map<Purchase, WeekDay> lastPurchaseMap = new HashMap<>();
            Map<WeekDay, List<Purchase>> enumeratedMap = new EnumMap<>(WeekDay.class);
            List<PricePurchase> pricePurchaseList = new ArrayList<>();

            while (scanner.hasNext()) {
                List<Purchase> purchases = new ArrayList<>();

                Purchase purchase = PurchaseFactory.getPurchaseFromFactory(scanner);
                WeekDay day = WeekDay.valueOf(scanner.nextLine());
                lastPurchaseMap.put(purchase, day);

                if (purchase.getClass().getSimpleName().equals("PricePurchase")) {
                    pricePurchaseList.add((PricePurchase) purchase);
                }

                if (enumeratedMap.containsKey(day)) {
                    purchases = enumeratedMap.get(day);
                }
                purchases.add(purchase);
                enumeratedMap.put(day, purchases);

                if (!firstPurchaseMap.containsKey(purchase)) {
                    firstPurchaseMap.put(purchase, day);
                }
            }

            printMap(firstPurchaseMap, "First purchase map: ");

            printMap(lastPurchaseMap, "Last purchase map: ");

            printMap(enumeratedMap, "Enumerated map: ");

            findValue(firstPurchaseMap, new Purchase("bread", new Byn(155), 0), "The first weekday for bread with price 1.55: ");
            findValue(lastPurchaseMap, new Purchase("bread", new Byn(155), 0), "The last weekday for bread with price 1.55: ");
            findValue(firstPurchaseMap, new Purchase("bread", new Byn(170), 0), "The first weekday for bread with price 1.70: ");

            removeEntries(lastPurchaseMap, new EntryChecker<>() {
                @Override
                public boolean check(Map.Entry<Purchase, WeekDay> entry) {
                    return "meat".equals(entry.getKey().getName());
                }
            });

            printMap(firstPurchaseMap, "First purchase map after deletion: ");

            removeEntries(lastPurchaseMap, new EntryChecker<>() {
                @Override
                public boolean check(Map.Entry<Purchase, WeekDay> entry) {
                    return WeekDay.FRIDAY == entry.getValue();
                }
            });

            printMap(lastPurchaseMap,"Last purchase map after deletion: ");

            System.out.println("Total cost of the purchases from list: ");
            getTotalCost(pricePurchaseList);

            for (Map.Entry<WeekDay, List<Purchase>> item : enumeratedMap.entrySet()) {
                List<Purchase> value = item.getValue();
                System.out.println("Total cost of all purchases on  " + item.getKey());
                getTotalCost(value);
            }

            findValue(enumeratedMap, WeekDay.MONDAY, "All purchases on MONDAY: ");


            removeEntries(enumeratedMap, new EntryChecker<>() {
                @Override
                public boolean check(Map.Entry<WeekDay, List<Purchase>> entry) {
                    boolean found = false;
                    for (Purchase purchase : entry.getValue()) {
                        if (purchase.getName().equals("milk")) {
                            found = true;
                            break;
                        }
                    }
                    return found;
                }
            });

            printMap(lastPurchaseMap, "Enumerated map after deletion: ");
        } catch (FileNotFoundException e) {
            System.err.println("Input file is not found");
        }
    }

    private static <K, V> void printMap(Map<K, V> map, String message) {
        System.out.println(message);
        for (Map.Entry<K, V> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " => " + entry.getValue());
        }
    }

    private static <K, V> void findValue(Map<K, V> map, K key,  String message) {
        System.out.println(message);
        if (map.containsKey(key)) {
            System.out.println(map.get(key));
        } else {
            System.out.println("Not found");
        }
    }

    private static <K, V> void removeEntries(Map<K, V> map, EntryChecker<K, V> checker) {
        Iterator<Map.Entry<K, V>> iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<K, V> entry = iter.next();
            if (checker.check(entry)) {
                iter.remove();
            }
        }
    }

    private static <T extends Purchase> void getTotalCost(List<T> list) {
        Byn totalCost = new Byn(0);
        if (list != null) {
            for (T purchase : list) {
                totalCost = totalCost.add(purchase.getCost());
            }
            System.out.println(totalCost);
        }
    }
}

