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
        trials.sort((o1, o2) -> o1.getMark1() + o1.getMark2() - o2.getMark1() - o2.getMark2());

        System.out.println("After sorting: ");
        trials.forEach(System.out::println);

        System.out.println("Sums of first and second marks: ");
        int[] sumOfMarks = trials.stream()
                .mapToInt(trial->trial.getMark1()+trial.getMark2()).toArray();
        System.out.println(IntStream.of(sumOfMarks)
                .mapToObj(Integer::toString).collect(Collectors.joining(",")));

        System.out.println("A new collection from unpassed trials: ");
        List<Trial> unpassedTrials = trials.stream()
                .filter(trial -> !trial.isPassed())
                .collect(Collectors.toList());
        unpassedTrials.forEach(trial -> {
            trial.clearBadMarks();
            System.out.println(trial);
        });
    }
}
