package by.epam.lab;

public abstract class AbstractPurchase implements Comparable<AbstractPurchase> {
    private final Product product;
    private int number;

    public AbstractPurchase() {
        this.product = new Product("-", new Byn(0));
        this.number = 0;
    }

    public AbstractPurchase(Product product, int number) {
        this.product = product;
        this.number = number;
    }

    public AbstractPurchase(String name, Byn price, int number) {
        this.product = new Product(name, price);
        this.number = number;
    }

    public Product getProduct() {
        return product;
    }


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    protected abstract Byn getFinalCost(Byn baseCost);

    public Byn getCost() {
        Byn baseCost = product.getPrice().mul(number);
        Byn finalCost = getFinalCost(baseCost);
        return finalCost.round(RoundMethod.FLOOR, 2);
    }

    protected String fieldsToString() {
        return product + ";" + number;
    }

    @Override
    public String toString() {
        return fieldsToString() + ";" + getCost();
    }

    @Override
    public int compareTo(AbstractPurchase o) {
        return getCost().compareTo(o.getCost());
    }
}
