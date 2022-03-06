package by.epam.entity;

import java.util.Objects;

public class Order extends Entity {
    private long userId;
    private long flightId;
    private int amount;
    private int totalCost;

    public Order() {

    }

    public Order(long userId, long flightId, int amount, int totalCost) {
        this.userId = userId;
        this.flightId = flightId;
        this.amount = amount;
        this.totalCost = totalCost;
    }

    public Order(Order order) {
        this.userId = order.userId;
        this.flightId = order.flightId;
        this.amount = order.amount;
        this.totalCost = order.totalCost;
    }

    public Order(long id, long userId, long flightId, int amount, int totalCost) {
        super(id);
        this.userId = userId;
        this.flightId = flightId;
        this.amount = amount;
        this.totalCost = totalCost;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getFlightId() {
        return flightId;
    }

    public void setFlightId(long flightId) {
        this.flightId = flightId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return userId == order.userId && flightId == order.flightId && amount == order.amount && totalCost == order.totalCost;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (int) (prime * result + userId);
        result = (int) (prime * result + flightId);
        result = prime * result + amount;
        result = prime * result + totalCost;
        return result;
    }

    @Override
    public String toString() {
        return userId + ";" + flightId + ";" + amount + ";" + totalCost;
    }
}
