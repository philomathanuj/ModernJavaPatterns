package com.java.patterns.fundamentals;

import com.java.patterns.Playground;
import com.java.patterns.model.EcomData;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.function.*;

public class FIPlayground extends Playground {

    public boolean matchingStockCode_Predicate(EcomData ecomData, String stockCodeKey){
        Predicate<EcomData> matchingStockCode_Predicate = ecomRecord -> ecomRecord.getStockCode().equals(stockCodeKey);
        return matchingStockCode_Predicate.test(ecomData);
    }

    public int countNumberOfWordsInDescription_Function(EcomData ecomData){
        Function<EcomData,Integer> numberOfWordsInDescription_Function = ecomRecord -> ecomRecord.getDescription().split(" ").length;
        return numberOfWordsInDescription_Function.apply(ecomData);
    }

    public void printCustomerIdAndQty_Consumer(EcomData ecomData){
        Consumer<EcomData> printCustomerIdAndQty = ecomRecord -> System.out.println(ecomRecord.getCustomerId()+"-"+ecomRecord.getQuantity());
        printCustomerIdAndQty.accept(ecomData);
    }

    public EcomData buildEcomData_Supplier(){
        Supplier<EcomData> buildEcomData = () -> new EcomData.EcomDataBuilder()
                .customerId(123456L)
                .country("United Kingdom")
                .unitPrice(new BigDecimal("20.2"))
                .invoiceDate(LocalDateTime.now())
                .stockCode("SK1234")
                .invoiceNumber("121141")
                .description("Demo Supplier Function")
                .quantity(5).build();
        return buildEcomData.get();
    }

    public Double divideTwoNumbers_BiFunction(int x, int y){
        BiFunction<Integer,Integer,Double> divideTwoNumbers = (num1, num2) -> (double)num1/num2;
        return divideTwoNumbers.apply(x,y);
    }

    public void addTwoNumbersAndPrintThem_BiConsumer(int x, int y){
        BiConsumer<Integer,Integer> addTwoNumbersAndPrintThem = (num1,num2) -> System.out.println(num1+num2);
         addTwoNumbersAndPrintThem.accept(x,y);
    }

    public int multiplyTwoNumbers_BinaryOperator(int x, int y){
        BinaryOperator<Integer> multiplyTwoNumbers = (num1,num2) -> num1*num2;
        return multiplyTwoNumbers.apply(x,y);
    }

    public int incrementByOne_UnaryOperator(int x){
        UnaryOperator<Integer> incrementByOne = (num) -> num+1;
        return incrementByOne.apply(x);
    }
}
