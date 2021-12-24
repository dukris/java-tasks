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
                Purchase purchase;
                String value = scanner.nextLine();
                String[] values = value.split(";");
                String name = values[0];
                Byn price = new Byn(Integer.parseInt(values[1]));
                int number = Integer.parseInt(values[2]);
                if (values.length == 3) {
                    purchase = new Purchase(name, price, number);
                } else {
                    Byn discount = new Byn(Integer.parseInt(values[3]));
                    purchase = new PricePurchase(name, price, number, discount);
                    pricePurchaseList.add((PricePurchase) purchase);
                }
                WeekDay day = WeekDay.valueOf(scanner.nextLine());
                lastPurchaseMap.put(purchase, day);
                if (enumeratedMap.containsKey(day)) {
                    purchases = enumeratedMap.get(day);
                }
                purchases.add(purchase);
                enumeratedMap.put(day, purchases);
                if (!firstPurchaseMap.containsKey(purchase)) {
                    firstPurchaseMap.put(purchase, day);
                }
            }
            System.out.println("First purchase map: ");
            printMap(firstPurchaseMap);

            System.out.println("Last purchase map: ");
            printMap(lastPurchaseMap);

            System.out.println("Enumerated map: ");
            printMap(enumeratedMap);

            System.out.println("The first weekday for bread with price 1.55: ");
            findValue(firstPurchaseMap, new Purchase("bread", new Byn(155), 0));
            System.out.println("The last weekday for bread with price 1.55: ");
            findValue(lastPurchaseMap, new Purchase("bread", new Byn(155), 0));
            System.out.println("The first weekday for bread with price 1.70: ");
            findValue(firstPurchaseMap, new Purchase("bread", new Byn(170), 0));

            removeEntries(lastPurchaseMap, new EntryChecker<>() {
                @Override
                public boolean check(Map.Entry<Purchase, WeekDay> entry) {
                    return "meat".equals(entry.getKey().getName());
                }
            });
            System.out.println("First purchase map after deletion: ");
            printMap(firstPurchaseMap);

            removeEntries(lastPurchaseMap, new EntryChecker<>() {
                @Override
                public boolean check(Map.Entry<Purchase, WeekDay> entry) {
                    return WeekDay.FRIDAY == entry.getValue();
                }
            });
            System.out.println("Last purchase map after deletion: ");
            printMap(lastPurchaseMap);

            System.out.println("Total cost of the purchases from list: ");
            getTotalCost(pricePurchaseList);

            for (Map.Entry<WeekDay, List<Purchase>> item : enumeratedMap.entrySet()) {
                List<Purchase> value = item.getValue();
                System.out.println("Total cost of all purchases on  " + item.getKey());
                getTotalCost(value);
            }

            System.out.println("All purchases on MONDAY: ");
            findValue(enumeratedMap, WeekDay.MONDAY);

        } catch (FileNotFoundException e) {
            System.err.println("Input file is not found");
        }
    }

    private static <K, V> void printMap(Map<K, V> map) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " => " + entry.getValue());
        }
    }

    private static <K, V> void findValue(Map<K, V> map, K key) {
        if (map.containsKey(key)) {
            System.out.println(map.get(key));
        } else {
            System.out.println("Not found");
        }
    }

    private static <K, V, T> void removeEntries(Map<K, V> map, EntryChecker<K, V> checker) {
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

