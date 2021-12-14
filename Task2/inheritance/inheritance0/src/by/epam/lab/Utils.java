package by.epam.lab;

public class Utils {

    public static void printTrials(Trial[] trials) {
        for (Trial trial : trials) {
            System.out.println(trial);
        }
    }

    public static void clearFailedTrials(Trial[] trials) {
        for (Trial trial : trials) {
            if (!trial.isPassed()) {
                trial.clearBadMarks();
            }
        }
    }
}

