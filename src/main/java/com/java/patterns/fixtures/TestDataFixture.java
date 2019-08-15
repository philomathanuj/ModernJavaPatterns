package com.java.patterns.fixtures;


import com.java.patterns.model.EcomData;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;


public class TestDataFixture {
    public static List<EcomData> cookEcomData(){
        List<EcomData> ecomData = new ArrayList<>();
        String fileName = "C:\\Users\\Anuj Sharma\\IdeaProjects\\comjavapatterns\\modern-java\\src\\main\\resources\\ecommerce-data\\data.csv";
        List<List<String>> grid = processAndCollectCSV(fileName);
        List<String> headers = grid.get(0);

        ecomData = grid.stream().skip(1).map(row ->
            new EcomData.EcomDataBuilder()
                .invoiceNumber(row.get(0).trim())
                .stockCode(row.get(1).trim())
                .description(row.get(2).trim())
                .quantity(parseLong(row.get(3)))
                .invoiceDate(clenseDate(row.get(4).trim()))
                .unitPrice(toBigDecimal(row.get(5)))
                .customerId(parseLong(row.get(6)))
                .country(row.get(7).trim()).build()).collect(Collectors.toList());

        return ecomData;
    }

    private static BigDecimal toBigDecimal(String s) {
        try{
            return  new BigDecimal(s);
        }
        catch (RuntimeException e){
        }
        return new BigDecimal(0.0);
    }

    private static long parseLong(String str) {
        try {
            Long.parseLong(str);
        }
        catch(RuntimeException e){
        }
        return 0;
    }

    private static LocalDateTime clenseDate(String date) {
        // 12/1/2016 12:45
        //String[] splitValues = date.split("[/ | :]");

        StringBuilder builder = new StringBuilder();
        Queue<Character> values = new LinkedList<>();
        for (int i = 0; i < date.length(); i++) {

            if(date.charAt(i) != '/' && date.charAt(i) != ':' && date.charAt(i) != ' '){
                values.add(date.charAt(i));
            }
            else{

                if(values.size() == 1){
                    builder.append("0");
                }
                while(!values.isEmpty()){
                    builder.append(values.poll());
                }
                builder.append(date.charAt(i));
            }
        }
        if(values.size() == 1){
            builder.append("0");
        }
        while(!values.isEmpty()){
            builder.append(values.poll());
        }
        String cleansedString = builder.toString();
        String formatWithSlash = "MM/dd/yyyy HH:mm";
        String formatWithHyphen = "MM-dd-yyyy HH:mm";
        String effectiveFormat = cleansedString.contains("/") ? formatWithSlash: formatWithHyphen;
        LocalDateTime resultDate = null;
        try{
            resultDate = LocalDateTime.parse(cleansedString, DateTimeFormatter.ofPattern(effectiveFormat));
        }
        catch (Exception e){
        }
        return resultDate;
    }

    private static List<List<String>> processAndCollectCSV(String fileName){
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                records.add(Arrays.asList(values));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }
        return records;
    }
}
