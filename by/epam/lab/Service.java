package by.epam.lab;

public class Service implements Priceable {
    private String name;
    private Byn totalCost;
    private int numberOfUsers;

    public Service() {

    }

    public Service(String name, Byn totalCost, int numberOfUsers) {
        this.name = name;
        this.totalCost = totalCost;
        this.numberOfUsers = numberOfUsers;
    }

    public Byn getPrice() {
        return totalCost.mul(1.0 / numberOfUsers, RoundMethod.CEIL, 0);
    }

    @Override
    public String toString() {
        return name + ";" + totalCost + ";" + numberOfUsers + ";" + getPrice();
    }
}

