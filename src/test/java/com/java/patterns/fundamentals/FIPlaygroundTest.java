package com.java.patterns.fundamentals;

import com.java.patterns.model.EcomData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class FIPlaygroundTest {

    private FIPlayground fiPlayground = new FIPlayground();


    @Test
    public void whenStockCodeMatches_shouldReturnTrue(){
        assertTrue(fiPlayground.matchingStockCode_Predicate(fiPlayground.getEcomData(),"85123A"));
    }

    @Test
    public void whenEcomDataIsGiven_shouldReturnTheNumberOfWordsInDescription(){
        assertEquals(5,fiPlayground.countNumberOfWordsInDescription_Function(fiPlayground.getEcomData()));
    }

    @Test
    public void whenEcomDataIsGiven_shouldPrintCustomerIdAndQty(){
         final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
         final PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        fiPlayground.printCustomerIdAndQty_Consumer(fiPlayground.getEcomData());
        assertEquals("0-0"+ System.lineSeparator(),outContent.toString());
        System.setOut(originalOut);
    }

    @Test
    public void whenBuildEcomData_shouldReturnEcomData(){
        EcomData ecomData = fiPlayground.buildEcomData_Supplier();
        assertEquals(123456,ecomData.getCustomerId().longValue());
        assertEquals("United Kingdom",ecomData.getCountry());
        assertEquals("SK1234",ecomData.getStockCode());
        assertEquals("121141",ecomData.getInvoiceNumber());
        assertEquals("Demo Supplier Function",ecomData.getDescription());
    }

    @Test
    public void shouldDivideTwoNumbers(){
        double division = fiPlayground.divideTwoNumbers_BiFunction(10,5);
        assertEquals(division,2.0,0);
    }

    @Test
    public void shouldAddTwoNumbersAndPrintThem(){
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        final PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        fiPlayground.addTwoNumbersAndPrintThem_BiConsumer(5,10);
        assertEquals(15+System.lineSeparator(), outContent.toString());
        System.setOut(originalOut);
    }

    @Test
    public void shouldMultiplyTwoNumbers(){
        int result = fiPlayground.multiplyTwoNumbers_BinaryOperator(5,10);
        assertEquals(50,result);
    }

    @Test
    public void shouldIncrementANumberByOne(){
        int result = fiPlayground.incrementByOne_UnaryOperator(5);
        assertEquals(6,result);
    }



}
