package com.p2.simpletest.asserttest;

import com.p2.simpletest.assertion.Assert;
import com.p2.simpletest.exception.SimpleTestFailureException;
import org.junit.Test;

/**
 * Created by I335831 on 4/16/2018.
 */
public class AssertTest {

    @Test(expected = SimpleTestFailureException.class)
    public void assertEqualsPassFail(){
        Assert.assertEquals("TestString1", "TestString2");
    }

    @Test
    public void assertEqualsPassTest(){
        Assert.assertEquals("TestString", "TestString");
    }

    @Test
    public void assertTruePassTest(){
        Assert.assertTrue("TestString".equals("TestString"));
    }

    @Test(expected = SimpleTestFailureException.class)
    public void assertTrueFailTest(){
        Assert.assertTrue("TestString1".equals("TestString2"));
    }

    @Test(expected = SimpleTestFailureException.class)
    public void assertFalseFailTest(){
        Assert.assertFalse("TestString1".equals("TestString1"));
    }
}
