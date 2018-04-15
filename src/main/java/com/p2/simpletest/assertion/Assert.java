package com.p2.simpletest.assertion;

import com.p2.simpletest.exception.SimpleTestFailureException;

/**
 * Created by I335831 on 4/16/2018.
 */
public class Assert {

    public static void assertEquals(Object expected, Object actual){
        if(expected != null && actual != null & !expected.equals(actual)){
            throw new SimpleTestFailureException(expected, actual);
        }
    }

    public static void assertTrue(boolean condition) {
        if(!condition){
            throw new SimpleTestFailureException(Boolean.TYPE, Boolean.FALSE);
        }
    }

    public static void assertFalse(boolean condition) {
        if(condition){
            throw new SimpleTestFailureException(Boolean.FALSE, Boolean.TRUE);
        }
    }
}
