package by.epam.lab;

public abstract class AbstractPurchase implements Comparable<AbstractPurchase> {
    private Product product;
    private int number;

    public AbstractPurchase() {

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

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Byn getCost() {
        return new Byn(product.getPrice().mul(number));
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
