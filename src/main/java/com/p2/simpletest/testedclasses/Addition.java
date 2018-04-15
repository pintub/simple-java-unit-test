package com.p2.simpletest.testedclasses;

import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by I335831 on 4/15/2018.
 */
public class Addition {
    private List<Integer> integerList = new ArrayList<>();

    public List<Integer> getIntegerList() {
        return integerList;
    }

    public void setIntegerList(List<Integer> integerList) {
        this.integerList = integerList;
    }

    public int getSum(){
        int sum = 0;
        if(CollectionUtils.isNotEmpty(integerList)){
            for(int i : integerList){
                sum = sum + i;
            }
        }

        return sum;
    }
}
