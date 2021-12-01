package by.epam.lab;

import java.util.Scanner;

public class Runner {

    public static void main(String[] args) {
        Trial[] trials = {
                new Trial("John", 20, 51),
                new Trial("Hanna", 29, 95),
                new Trial("Liz", 35, 68),
                new LiteTrial("Olivia", 87, 33),
                new LiteTrial("Alex", 36, 58),
                new StrongTrial("James", 90, 57),
                new StrongTrial("Mia", 98, 88),
                new ExtraTrial("Emma", 45, 93, 85),
                new ExtraTrial("Charlotte", 55, 48, 21)
        };
        Utils.printTrials(trials);
        System.out.println("Trials with the average value of all marks for a trial less than a half of the PASS_MARK: ");
        for (Trial trial : trials) {
            if (trial.getAverageMark() < Trial.PASS_MARK / 2) {
                System.out.println(trial);
            }
        }
        System.out.println("Array after clearing failed trials: ");
        Utils.clearFailedTrials(trials);
        Utils.printTrials(trials);
        System.out.println("Enter an account: ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        System.out.println("Superclass trials for an account: ");
        for (Trial trial : trials) {
            if(name.equals(trial.getName())){
                System.out.println(new Trial(trial.getName(), trial.getMark1(), trial.getMark2()));
            }
        }
    }
}
