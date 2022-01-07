package by.epam.lab;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Runner {

    public static void main(String[] args) {
        List<Trial> trials = new ArrayList<>(Arrays.asList(
                new Trial("John", 20, 51),
                new Trial("Hanna", 29, 95),
                new Trial("Liz", 35, 68),
                new LiteTrial("Olivia", 87, 33),
                new LiteTrial("Alex", 36, 58),
                new StrongTrial("James", 90, 57),
                new StrongTrial("Mia", 98, 88),
                new ExtraTrial("Emma", 45, 93, 85),
                new ExtraTrial("Charlotte", 55, 48, 21)));

        trials.forEach(System.out::println);

        System.out.println("The number of passed trials: " + trials.stream()
                .filter(Trial::isPassed)
                .count());

        System.out.println("After sorting: ");
        trials.sort(Comparator.comparingInt(Trial::getSum));
        trials.forEach(System.out::println);

        System.out.println("Sums of first and second marks: ");
        trials.stream()
                .map(Trial::getSum)
                .forEach(System.out::println);

        System.out.println("A new collection from failed trials: ");
        List<Trial> failedTrials = trials.stream()
                .filter(trial -> !trial.isPassed())
                .map(Trial::getCopy)
                .peek(Trial::clearMarks)
                .peek(System.out::println)
                .collect(Collectors.toList());
        System.out.println(failedTrials.stream()
                .allMatch(Trial::isPassed));

        int[] sumOfMarks = trials.stream()
                .mapToInt(Trial::getSum)
                .toArray();
        System.out.println(IntStream.of(sumOfMarks)
                .mapToObj(Integer::toString)
                .collect(Collectors.joining(", ")));
    }
}