package by.epam.lab;

import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRunner {
    private static final String RESULT_HEAD = "sum = ";
    private static final String NEW_LINE = "\n";
    private static final String ERROR_LINES = "error-lines = ";
    private static final String INDEX = "index";
    private static final String VALUE = "value";
    private static final int INDEX_LENGTH = INDEX.length();
    private static final int VALUE_LENGTH = VALUE.length();

    public static int getResult(String fileName, StringBuilder strResult) throws FileNotFoundException {
        double number;
        double result = 0;
        int errorLines = 0;
        Pattern indexPattern = Pattern.compile(INDEX + "\\d{1,}$");
        Pattern totalValuePattern = Pattern.compile("\\d{1,}$");
        Pattern valuePattern = Pattern.compile(VALUE + "\\d{1,}$");
        ResourceBundle rb = ResourceBundle.getBundle(fileName, Locale.ENGLISH);
        Enumeration<String> keys = rb.getKeys();
        String key;
        while (keys.hasMoreElements()) {
            key = keys.nextElement().trim();
            Matcher matcher = indexPattern.matcher(key);
            if (matcher.find()) {
                matcher = totalValuePattern.matcher(rb.getString(key).trim());
                if (matcher.find()) {
                    int i = Integer.parseInt(key.substring(INDEX_LENGTH));
                    int j = Integer.parseInt(matcher.group(0).trim());
                    String ij = "" + i + j;
                    //System.out.println(key + " - " + rb.getString(key));
                    //matcher.group(0).trim() - получение значения
                    matcher = valuePattern.matcher(rb.getString(key).trim());
                    if(matcher.find()){
                        System.out.println(rb.getString(key));
                    }
//                        if(key.substring(VALUE_LENGTH).equals(ij)){
//                            System.out.println(matcher.group(0).trim());
//                        }
//                    }
                }
            }
        }


        strResult.insert(0, RESULT_HEAD).append(result).append(NEW_LINE).append(ERROR_LINES).append(errorLines);
        return errorLines;
    }
}

//    @Test
//    public void testFirstScenario() throws FileNotFoundException {
//        StringBuilder result = new StringBuilder();
//        int errorLines = getResult("src/in1.csv", result);
//        Assert.assertEquals(3, errorLines);
//        Assert.assertEquals("result(5.2 - 3.14 + 0.0) = 2.06\nerror-lines = 3", result.toString());
//    }
//
//    @Test
//    public void testSecondScenario() throws FileNotFoundException {
//        StringBuilder result = new StringBuilder();
//        int errorLines = getResult("src/in2.csv", result);
//        Assert.assertEquals(0, errorLines);
//        Assert.assertEquals("result(-3.1 - 1.0) = -4.1\nerror-lines = 0", result.toString());
//    }
//
//    @Test
//    public void testThirdScenario() throws FileNotFoundException {
//        StringBuilder result = new StringBuilder();
//        int errorLines = getResult("src/in3.csv", result);
//        Assert.assertEquals(0, errorLines);
//        Assert.assertEquals("result(0.75) = 0.75\nerror-lines = 0", result.toString());
//    }
//
//    @Test
//    public void testForthScenario() throws FileNotFoundException {
//        StringBuilder result = new StringBuilder();
//        int errorLines = getResult("src/in4.csv", result);
//        Assert.assertEquals(0, errorLines);
//        Assert.assertEquals("result(0.0) = 0.0\nerror-lines = 0", result.toString());
//    }
//
//    @Test
//    public void testFifthScenario() throws FileNotFoundException {
//        StringBuilder result = new StringBuilder();
//        int errorLines = getResult("src/in5.csv", result);
//        Assert.assertEquals(1, errorLines);
//        Assert.assertEquals("result() = 0.0\nerror-lines = 1", result.toString());
//    }
//
//    @Test(expected = FileNotFoundException.class)
//    public void testWrongCsvName() throws FileNotFoundException {
//        getResult("src/1.csv", new StringBuilder());
//    }
//}