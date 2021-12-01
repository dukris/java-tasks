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
                if (sc.hasNextLine()) {
                    purchases[i] = new Purchase(sc.nextInt(), sc.nextInt(), Utils.getCorrectDay(sc.nextInt()));
                } else {
                    purchases[i] = new Purchase(0, 0, Utils.getCorrectDay(0));
                }
            }
            Utils.print(purchases);
            System.out.println("The mean cost of all purchases: " + Utils.getMeanCost(purchases));
            System.out.println("The total cost of all purchases on Monday: " + Utils.getMondayCost(purchases));
            System.out.println("The day with the maximum purchase cost: " + Utils.getMaxCost(purchases));
            Arrays.sort(purchases);
            Utils.print(purchases);
            int index = Arrays.binarySearch(purchases, new Purchase(5, 15, WeekDay.FRIDAY));
            if (index > -1) {
                System.out.println("Some purchase with number equalled to 5: " + purchases[index]);
            } else {
                System.out.println("Instance not found");
            }

        } catch (FileNotFoundException e) {
            System.err.println("Input file is not found");
        }


    }
}
