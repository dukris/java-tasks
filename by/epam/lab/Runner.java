package by.epam.lab;

import java.io.FileNotFoundException;
import java.util.Comparator;

public class Runner {

    public static void main(String[] args){
        try {
            PurchaseList list = new PurchaseList("src/in.csv", Comparator.comparing(purchase -> purchase.getCost().getRubs() * 100 + purchase.getCost().getCoins()));

            list.insert(1, new Purchase("name", new Byn(100), 2));
            list.print();
            list.calculateTotalCost();

            System.out.println("After sorting: ");
            list.sort();
            list.print();

            System.out.println("After deletion: ");
            list.delete(2, 4);
            list.print();

            System.out.println("Search result: " + list.search(new Purchase("Orange", new Byn(50), 4)));
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }

    }
}