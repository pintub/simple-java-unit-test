package com.p2.simpletest.singletestcase;

import com.p2.simpletest.runner.SimpleTestRunner;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;

/**
 * Created by I335831 on 4/15/2018.
 * IRONY- Simple unit onetest framework will be unit tested by Junit framework
 */
public class SimpleTestRunnerSingleUnitCaseTest {

    private static SimpleTestRunner testRunner;
    private static String packageName1 = "com.p2.simpletest.testcases.onetest";
    private static String packageName2 = "com.p2.simpletest.testcases.onefail";
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeClass
    public static void setUpClass(){
    }

    @Before
    public void setUpStreams(){
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(System.out);
    }
    @Test
    public void countNoOfMethodsZeroWithNoTestAnnotation(){
        testRunner = new SimpleTestRunner();
        Assert.assertEquals(0, testRunner.getUnitTestCount());
    }

    @Test
    public void countNoOfMethodsOneWithOneTestCaseAnnotation() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        testRunner = new SimpleTestRunner();
        testRunner.setPackageToBeScanned(packageName1);
        testRunner.runSimpleTest();
        Assert.assertEquals(1, testRunner.getUnitTestCount());
    }

    @Test
    public void testMethodInvokedWithOneTestCaseAnnotation() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        testRunner = new SimpleTestRunner();
        testRunner.setPackageToBeScanned(packageName1);
        testRunner.runSimpleTest();
        Assert.assertTrue(outContent.toString().contains("SimpleTest"));
    }

    @Test
    public void testAndAssertAdditionUsingSimpleTestPositiveScenario() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        testRunner = new SimpleTestRunner();
        testRunner.setPackageToBeScanned(packageName1);
        testRunner.runSimpleTest();
        Assert.assertEquals(1, testRunner.getPassedSimpleTestCases().size());
    }

    @Test
    public void testAndAssertAdditionUsingSimpleTestNegativeScenario() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        String exceptionPattern = "Expected Result .* But Found Actual Result .*";
        testRunner = new SimpleTestRunner();
        testRunner.setPackageToBeScanned(packageName2);
        testRunner.runSimpleTest();
        Assert.assertEquals(0, testRunner.getPassedSimpleTestCases().size());
        Assert.assertEquals(1, testRunner.getFailedSimpleTestCases().size());

        for(String value : testRunner.getFailedSimpleTestCases().values()){
            Assert.assertTrue(value.matches(exceptionPattern));
        }
    }

}
