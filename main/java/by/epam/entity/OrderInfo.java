package by.epam.entity;

import java.util.Objects;

public class OrderInfo extends Order{
    private String fromCity;
    private String toCity;
    private String date;

    public OrderInfo(String fromCity, String toCity, String date) {
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.date = date;
    }

    public OrderInfo(long userId, long flightId, int amount, int totalCost, String fromCity, String toCity, String date) {
        super(userId, flightId, amount, totalCost);
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.date = date;
    }

    public OrderInfo(Order order, Flight flight) {
        super(order);
        this.fromCity = flight.getFromCity();
        this.toCity = flight.getToCity();
        this.date = flight.getDate();
    }

    public OrderInfo(long id, long userId, long flightId, int amount, int totalCost, String fromCity, String toCity, String date) {
        super(id, userId, flightId, amount, totalCost);
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.date = date;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        OrderInfo orderInfo = (OrderInfo) o;
        return Objects.equals(fromCity, orderInfo.fromCity) && Objects.equals(toCity, orderInfo.toCity) && Objects.equals(date, orderInfo.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), fromCity, toCity, date);
    }
}
