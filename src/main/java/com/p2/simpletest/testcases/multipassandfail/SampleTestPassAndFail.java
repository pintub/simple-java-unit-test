package com.p2.simpletest.testcases.multipassandfail;

import com.p2.simpletest.Test;
import com.p2.simpletest.exception.SimpleTestFailureException;
import com.p2.simpletest.testedclasses.Addition;

import java.util.Arrays;
import java.util.List;

/**
 * Created by I335831 on 4/15/2018.
 */
public class SampleTestPassAndFail {

    @Test
    public void methodWithCustomTestAnnotationFail(){

        Addition addition = new Addition();
        List<Integer> integerList = Arrays.asList(1, 3, 5, 7);
        addition.setIntegerList(integerList);

        int expectedResult = 15;
        int actualResult = addition.getSum();
        if(addition.getSum() != expectedResult){
            throw new SimpleTestFailureException(expectedResult, actualResult);
        }
    }

    @Test
    public void methodWithCustomTestAnnotationPass(){

        Addition addition = new Addition();
        List<Integer> integerList = Arrays.asList(1, 3, 5, 7);
        addition.setIntegerList(integerList);

        int expectedResult = 16;
        int actualResult = addition.getSum();
        if(addition.getSum() != expectedResult){
            throw new SimpleTestFailureException(expectedResult, actualResult);
        }
    }
}
