package by.epam.entity;

import java.sql.Date;
import java.util.Objects;

public class Flight extends Entity {
    private String fromCity;
    private String toCity;
    private String date;
    private int amount;
    private int price;

    public Flight() {

    }

    public Flight(String from, String to, String date, int amount, int price) {
        this.fromCity = from;
        this.toCity = to;
        this.date = date;
        this.amount = amount;
        this.price = price;
    }

    public Flight(long id, String from, String to, String date, int amount, int price) {
        super(id);
        this.fromCity = from;
        this.toCity = to;
        this.date = date;
        this.amount = amount;
        this.price = price;
    }

    public String getFromCity() {
        return fromCity;
    }

    public void setFromCity(String fromCity) {
        this.fromCity = fromCity;
    }

    public String getToCity() {
        return toCity;
    }

    public void setToCity(String toCity) {
        this.toCity = toCity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return price == flight.price && Objects.equals(fromCity, flight.fromCity) && Objects.equals(toCity, flight.toCity) && Objects.equals(date, flight.date);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((fromCity == null) ? 0 : fromCity.hashCode());
        result = prime * result + ((toCity == null) ? 0 : toCity.hashCode());
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + amount;
        result = prime * result + price;
        return result;
    }

    @Override
    public String toString() {
        return fromCity + ";" + toCity + ";" + date + ";" + amount + ";" + price;
    }
}
