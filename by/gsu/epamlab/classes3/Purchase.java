package by.gsu.epamlab.classes3;

import by.gsu.epamlab.Utils;

public class Purchase implements Comparable<Purchase> {
    private final String name;
    private final int price;
    private Integer number;
    private int percent;
    private WeekDay day;

    public Purchase(){
        this.name = null;
        this.price = 0;
    }

    public Purchase(String name, String price, int number, int percent, WeekDay day) {
        this.name = name;
        this.price = Integer.parseInt(price.replace(".", ""));
        this.number = number;
        this.percent = percent;
        this.day = day;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public WeekDay getDay() {
        return day;
    }

    public void setDay(WeekDay day) {
        this.day = day;
    }

    public int getCost() {
        return price * (100 - percent) / 100;
    }

    public int getTotalCost() {
        int cost = getCost() * number;
        int part = cost / 100;
        if ((cost - part * 100) >= 50) {
            part++;
        }
        return part * 100;
    }

    @Override
    public String toString() {
        return number + ";" + percent + ";" + day + ";" + Utils.createDouble(getTotalCost());
    }

    @Override
    public int compareTo(Purchase purchase) {
        return number.compareTo(purchase.number);
    }
}
