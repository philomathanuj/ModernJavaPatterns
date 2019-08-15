package com.java.patterns;

import com.java.patterns.fixtures.TestDataFixture;
import com.java.patterns.model.EcomData;

import java.util.List;

public abstract class Playground {


    private List<EcomData> ecomDataList;

    private EcomData ecomData;

    public Playground(){
        ecomDataList = TestDataFixture.cookEcomData();
        ecomData = ecomDataList.stream().findFirst().get();
    }

    public List<EcomData> getEcomDataList() {
        return ecomDataList;
    }

    public EcomData getEcomData() {
        return ecomData;
    }
}
