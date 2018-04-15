package com.p2.simpletest.simpletesthelper;

import com.p2.simpletest.helper.SimpleTestHelper;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by I335831 on 4/15/2018.
 */
public class SimpleTestHelperTest {

    private static String packageName1 = "com.p2.simpletest.testcases.onetest";
    private static String packageName2 = "com.p2.simpletest.testcases.test2";
    private static String packageName3 = "com.p2.simpletest.testcases.test3";

    @Test
    public void countNumberOfClassesIsOneForGivenPackageHasOneClass(){
        Assert.assertEquals(1, SimpleTestHelper.getClasses(packageName1).size());
    }

    @Test
    public void countNumberOfClassesIs2ForGivenPackageHas2Class(){
        Assert.assertEquals(2, SimpleTestHelper.getClasses(packageName2).size());
    }

    @Test
    public void countNumberOfClasses2ForGivenPackageHasOneInnerOneOuterClass(){
        Assert.assertEquals(2, SimpleTestHelper.getClasses(packageName3).size());
    }
}
