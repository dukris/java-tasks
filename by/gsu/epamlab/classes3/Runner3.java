package by.gsu.epamlab.classes3;

import by.gsu.epamlab.Utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class Runner3 {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(new FileReader("src/in.txt"))) {
            sc.useLocale(Locale.ENGLISH);
            final int PURCHASES_NUMBER = sc.nextInt();
            Purchase[] purchases = new Purchase[PURCHASES_NUMBER];
            for (int i = 0; i < PURCHASES_NUMBER; i++) {
                purchases[i] = new Purchase(sc.next(), sc.next(), sc.nextInt(), sc.nextInt(), Utils.getCorrectDay(sc.nextInt()));
            }
            Utils.print(purchases);
            System.out.println("The mean cost of all purchases: " + Utils.getMeanCost(purchases));
            System.out.println("The total cost of all purchases on Monday: " + Utils.getMondayCost(purchases));
            System.out.println("The day with the maximum purchase cost: " + Utils.getMaxCost(purchases));
            Arrays.sort(purchases);
            Utils.print(purchases);
            System.out.println("Some purchase with number equalled to 5: " + Arrays.binarySearch(purchases, new Purchase("tea", "8.50", 5, 15, WeekDay.FRIDAY)));
        } catch (FileNotFoundException e) {
            System.err.println("Input file is not found");
        }


    }
}
