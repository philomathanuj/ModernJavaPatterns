package com.java.patterns.fundamentals;

import com.java.patterns.model.EcomData;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class StreamsPlaygroundTest {

    private StreamsPlayground streamsPlayground = new StreamsPlayground();

    @Test
    public void whenSearchedByExistentSearchCode_thenShouldReturnEcomData(){
        EcomData ecomData = streamsPlayground.searchByStockCode("22633");
        Assert.assertEquals(ecomData.getStockCode(),"22633");
    }

    @Test
    public void whenSearchedByNonExistantSearchCode_thenShouldReturnNull(){
        EcomData ecomData = streamsPlayground.searchByStockCode("ZYCG");
        Assert.assertEquals(null,ecomData);
    }
}
