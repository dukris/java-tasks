package by.epam.lab;

import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Locale;
import java.util.Scanner;

public class TestRunner {
    private static final String RESULT_HEAD = "result(";
    private static final String RESULT_TAIL = ") = ";
    private static final String PLUS = " + ";
    private static final String MINUS = " - ";
    private static final String NEW_LINE = "\n";
    private static final String ERROR_LINES = "error-lines = ";
    private static final int SYMBOL_LENGTH = PLUS.length();
    private static final int LAST_POSITION = SYMBOL_LENGTH - 1;

    private static int getResult(String csvName, StringBuilder strResult) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new FileReader(csvName))) {
            scanner.useLocale(Locale.ENGLISH);
            double number;
            double result = 0;
            int errorLines = 0;
            while (scanner.hasNext()) {
                String[] values = scanner.nextLine().split(";");
                try {
                    int index = Integer.parseInt(values[0]);
                    if (index >= 0) {
                        number = Double.parseDouble(values[index]);
                        if (number >= 0) {
                            strResult.append(PLUS).append(number);
                        } else if (number < 0) {
                            strResult.append(MINUS).append(Math.abs(number));
                        }
                        result += number;
                    }
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    errorLines++;
                }
            }
            if (strResult.toString().startsWith(PLUS)) {
                strResult.delete(0, SYMBOL_LENGTH);
            } else if (strResult.toString().startsWith(MINUS)) {
                strResult.deleteCharAt(LAST_POSITION);
                strResult.deleteCharAt(0);
            }
            strResult.insert(0, RESULT_HEAD).append(RESULT_TAIL).append(result).append(NEW_LINE).append(ERROR_LINES).append(errorLines);
            return errorLines;
        }
    }

    @Test
    public void testFirstScenario() throws FileNotFoundException {
        StringBuilder result = new StringBuilder();
        int errorLines = getResult("src/in1.csv", result);
        Assert.assertEquals(3, errorLines);
        Assert.assertEquals("result(5.2 - 3.14 + 0.0) = 2.06\nerror-lines = 3", result.toString());
    }

    @Test
    public void testSecondScenario() throws FileNotFoundException {
        StringBuilder result = new StringBuilder();
        int errorLines = getResult("src/in2.csv", result);
        Assert.assertEquals(0, errorLines);
        Assert.assertEquals("result(-3.1 - 1.0) = -4.1\nerror-lines = 0", result.toString());
    }

    @Test
    public void testThirdScenario() throws FileNotFoundException {
        StringBuilder result = new StringBuilder();
        int errorLines = getResult("src/in3.csv", result);
        Assert.assertEquals(0, errorLines);
        Assert.assertEquals("result(0.75) = 0.75\nerror-lines = 0", result.toString());
    }

    @Test
    public void testForthScenario() throws FileNotFoundException {
        StringBuilder result = new StringBuilder();
        int errorLines = getResult("src/in4.csv", result);
        Assert.assertEquals(0, errorLines);
        Assert.assertEquals("result(0.0) = 0.0\nerror-lines = 0", result.toString());
    }

    @Test
    public void testFifthScenario() throws FileNotFoundException {
        StringBuilder result = new StringBuilder();
        int errorLines = getResult("src/in5.csv", result);
        Assert.assertEquals(1, errorLines);
        Assert.assertEquals("result() = 0.0\nerror-lines = 1", result.toString());
    }

    @Test(expected = FileNotFoundException.class)
    public void testWrongCsvName() throws FileNotFoundException {
        getResult("src/1.csv", new StringBuilder());
    }
}