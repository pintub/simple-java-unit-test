package com.p2.simpletest.exception;

/**
 * Created by I335831 on 4/15/2018.
 */
public class SimpleTestFailureException extends RuntimeException {

    private Object expected;
    private Object actual;

    public Object getExpected() {
        return expected;
    }

    public Object getActual() {
        return actual;
    }

    public SimpleTestFailureException(){
        //TODO
    }

    public SimpleTestFailureException(Object expected, Object actual){
        this.expected = expected;
        this.actual = actual;
    }

    @Override
    public String toString() {
        return  new StringBuilder().append("Expected Result : ").append(expected).append(", But Found Actual Result : ")
                .append(actual).toString();
    }
}
