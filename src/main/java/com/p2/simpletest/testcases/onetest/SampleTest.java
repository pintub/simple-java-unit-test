package com.p2.simpletest.testcases.onetest;

import com.p2.simpletest.Test;
import com.p2.simpletest.assertion.Assert;
import com.p2.simpletest.exception.SimpleTestFailureException;
import com.p2.simpletest.testedclasses.Addition;

import java.util.Arrays;
import java.util.List;

/**
 * Created by I335831 on 4/15/2018.
 */
public class SampleTest {

    @Test
    public void methodWithCustomTestAnnotation(){

        System.out.println("SimpleTest Called , YIPEE....");

        Addition addition = new Addition();
        List<Integer> integerList = Arrays.asList(1, 3, 5, 7);
        addition.setIntegerList(integerList);

        int expectedResult = 16;
        int actualResult = addition.getSum();

        Assert.assertTrue(expectedResult == actualResult);
    }
}
