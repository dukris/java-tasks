package by.epam.lab;

import by.epam.lab.beans.Byn;
import by.epam.lab.beans.Purchase;
import by.epam.lab.beans.PurchaseList;

import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.List;

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

            System.out.println("Clone: ");
            List<Purchase> clone = list.getList();
            clone.forEach(System.out::println);

        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }
}