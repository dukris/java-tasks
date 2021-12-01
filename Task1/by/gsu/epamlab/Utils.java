package by.gsu.epamlab;

import by.gsu.epamlab.classes1.BusinessTrip;
import by.gsu.epamlab.classes3.Purchase;
import by.gsu.epamlab.classes3.WeekDay;

public class Utils {
    public static String createDouble(int number) {
        return String.format("%d.%02d", number / 100, (number - (number / 100) * 100));
    }

    public static String getMaxTrip(BusinessTrip[] trips) {
        BusinessTrip maxTrip = trips[0];
        for (BusinessTrip trip : trips) {
            if (trip != null) {
                if (trip.getTotal() > maxTrip.getTotal()) {
                    maxTrip = trip;
                }
            }
        }
        return createDouble(maxTrip.getTotal());
    }

    public static WeekDay getCorrectDay(int number) {
        return switch (number) {
            case 0 -> WeekDay.SUNDAY;
            case 1 -> WeekDay.MONDAY;
            case 2 -> WeekDay.TUESDAY;
            case 3 -> WeekDay.WEDNESDAY;
            case 4 -> WeekDay.THURSDAY;
            case 5 -> WeekDay.FRIDAY;
            case 6 -> WeekDay.SATURDAY;
            default -> null;
        };
    }

    public static WeekDay getMaxCost(Purchase[] purchases) {
        if (purchases.length == 0) {
            return null;
        }
        Purchase maxCost = purchases[0];
            for (Purchase purchase : purchases) {
                if (purchase != null) {
                    if (purchase.getCost() > maxCost.getCost()) {
                        maxCost = purchase;
                    }
                }
            }
        return maxCost.getDay();
    }

    public static String getMeanCost(Purchase[] purchases) {
        int meanCost = 0;
        if (purchases.length != 0) {
            for (Purchase purchase : purchases) {
                if (purchase != null) {
                    meanCost = meanCost + purchase.getTotalCost();
                }
            }
            meanCost *= 10;
            meanCost /= purchases.length;
        }
        return String.format("%d.%03d", meanCost / 1000, (meanCost - (meanCost / 1000) * 1000));
    }

    public static void print(Purchase[] purchases) {
        System.out.println("Name of product: " + Purchase.NAME);
        System.out.println("Price of product: " + Utils.createDouble(Purchase.PRICE));
        if (purchases.length != 0) {
            for (Purchase purchase : purchases) {
                if (purchase != null) {
                    System.out.println(purchase);
                }
            }
        }

    }

    public static String getMondayCost(Purchase[] purchases) {
        int totalCost = 0;
        if(purchases.length != 0){
            for (Purchase purchase : purchases) {
                if (purchase != null && purchase.getDay().equals(WeekDay.MONDAY)) {
                    totalCost = totalCost + purchase.getCost() * purchase.getNumber();
                }
            }
        }
        return createDouble(totalCost);
    }
}
