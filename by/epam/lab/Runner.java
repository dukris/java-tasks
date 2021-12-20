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
            List<Purchase> pricePurchaseList = new ArrayList<>();
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
                    pricePurchaseList.add(purchase);
                }
                WeekDay day = WeekDay.valueOf(scanner.nextLine());
                lastPurchaseMap.put(purchase, day);
                if (enumeratedMap.containsKey(day)) {
                    purchases = enumeratedMap.get(day);
                    purchases.add(purchase);
                } else {
                    purchases.add(purchase);
                }
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
            findPurchase(firstPurchaseMap, new Purchase("bread", new Byn(155), 0), null);
            System.out.println("The last weekday for bread with price 1.55: ");
            findPurchase(lastPurchaseMap, new Purchase("bread", new Byn(155), 0), null);
            System.out.println("The first weekday for bread with price 1.70: ");
            findPurchase(firstPurchaseMap, new Purchase("bread", new Byn(170), 0), null);

            removePurchase(firstPurchaseMap, new Purchase("meat", new Byn(0), 0), null);
            System.out.println("First purchase map after deletion: ");
            printMap(firstPurchaseMap);

            removePurchase(lastPurchaseMap, null, WeekDay.FRIDAY);
            System.out.println("Last purchase map after deletion: ");
            printMap(lastPurchaseMap);

            findTotalCost(pricePurchaseList, null);

            findTotalCost(null, enumeratedMap);

            findPurchase(enumeratedMap, null, WeekDay.MONDAY);

        } catch (FileNotFoundException e) {
            System.err.println("Input file is not found");
        }
    }

    private static void printMap(Map<?, ?> map) {
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " => " + entry.getValue());
        }
    }

    private static void findPurchase(Map<?, ?> map, Purchase purchase, WeekDay weekDay) {
        if (purchase != null) {
            if (map.containsKey(purchase)) {
                System.out.println(map.get(purchase).toString());
            } else {
                System.out.println("Not found");
            }
        } else if (weekDay != null) {
            if (map.containsKey(weekDay)) {
                System.out.println("All purchases on " + weekDay + ": " + map.get(weekDay).toString());
            }
        }
    }

    private static void removePurchase(Map<Purchase, WeekDay> map, Purchase purchase, WeekDay weekDay) {
        Iterator<Map.Entry<Purchase, WeekDay>> iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<Purchase, WeekDay> entry = iter.next();
            if (purchase != null && purchase.getName().equals(entry.getKey().getName())) {
                iter.remove();
            } else if (weekDay != null && weekDay.equals(entry.getValue())) {
                iter.remove();
            }
        }
    }

    private static void findTotalCost(List<Purchase> list, Map<WeekDay, List<Purchase>> map) {
        Byn totalCost = new Byn(0);
        if (list != null) {
            for (Purchase purchase : list) {
                totalCost = totalCost.add(purchase.getCost());
            }
            System.out.println("Total cost of the purchases from list: " + totalCost);
        } else if (map != null) {
            for (Map.Entry<WeekDay, List<Purchase>> entry : map.entrySet()) {
                totalCost = new Byn(0);
                for (Purchase purchase : entry.getValue()) {
                    totalCost = totalCost.add(purchase.getCost());
                }
                System.out.println("Total cost of all purchases on " + entry.getKey() + ": " + totalCost);
            }
        }
    }

}
