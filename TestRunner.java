package by.gsu.epamlab;

import by.gsu.epamlab.classes3.Purchase;
import junit.framework.Assert;
import org.junit.Test;

public class TestRunner extends Assert {

    @Test
    public void testConstructors(){
        boolean result = true;
        try {
            Purchase firstPurchase = new Purchase();
            Purchase secondPurchase = new Purchase(4, 20, Utils.getCorrectDay(0));
            Purchase thirdPurchase = new Purchase(2, 15, Utils.getCorrectDay(1));
        } catch (RuntimeException exception) {
            result = false;
        }
        assertTrue(result);
    }

    @Test
    public void testGetCost(){
        int result1 = 400;
        int result2 = new Purchase(4,20, Utils.getCorrectDay(0)).getCost();
        assertEquals(result1, result2);
    }

    @Test
    public void testCreateDouble(){
        String result1 = "4.00";
        String result2 = Utils.createDouble(new Purchase(4,20, Utils.getCorrectDay(0)).getCost());
        assertEquals(result1, result2);
    }
}
