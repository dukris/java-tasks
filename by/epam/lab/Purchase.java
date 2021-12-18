package by.epam.lab;

public class Purchase <T extends Priceable>{
    private T item;
    private Number quantity;


    public Purchase() {

    }

    public Purchase(T item, Number quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public T getItem(){
        return item;
    }

    public Byn getCost() {
        return item.getPrice().mul(quantity.doubleValue(), RoundMethod.ROUND, 0);
    }

    @Override
    public String toString() {
        return item + ";" + quantity + ";" + getCost();
    }
}

