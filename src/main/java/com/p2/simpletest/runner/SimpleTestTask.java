package com.p2.simpletest.runner;

import com.p2.simpletest.helper.SimpleTestHelper;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * Created by I335831 on 4/16/2018.
 */
public class SimpleTestTask implements Runnable {

    private Class<?> klass;
    private Method method;
    private Map<String, String> failedTestCases;
    private List<String> passedTestCases;

    public SimpleTestTask(Class<?> klass,
                          Method method, Map<String, String> failedTestCases, List<String> passedTestCases){
        this.klass = klass;
        this.method = method;
        this.failedTestCases = failedTestCases;
        this.passedTestCases = passedTestCases;
    }

    @Override
    public void run() {

        String methodFullyQualifiedName = SimpleTestHelper.getMethodName(klass, method);
        System.out.println("Running Simple Test Unit Case : " + methodFullyQualifiedName);
        try {
            method.invoke(klass.newInstance());//Run @Test Annotated Method

            synchronized(passedTestCases){
                passedTestCases.add(methodFullyQualifiedName);
            }
        } catch (Exception e) {
            synchronized(failedTestCases) {
                failedTestCases.put(methodFullyQualifiedName, e.getCause().toString());
            }
        }
    }
}
