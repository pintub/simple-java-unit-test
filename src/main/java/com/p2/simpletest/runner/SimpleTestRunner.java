package com.p2.simpletest.runner;

import com.p2.simpletest.Test;
import com.p2.simpletest.exception.SimpleTestFailureException;
import com.p2.simpletest.helper.SimpleTestHelper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Matcher;

/**
 * Created by I335831 on 4/15/2018.
 */
public class SimpleTestRunner {
    private int unitTestCount;
    private String packageToBeScanned;
    private List<String> simpleTestCases = new ArrayList<>();
    private List<String> passedSimpleTestCases = new ArrayList<>();
    private Map<String, String> failedSimpleTestCases = new HashMap<>();

    public List<String> getSimpleTestCases() {
        return simpleTestCases;
    }

    public List<String> getPassedSimpleTestCases() {
        return passedSimpleTestCases;
    }

    public void setPackageToBeScanned(String packageToBeScanned) {
        this.packageToBeScanned = packageToBeScanned;
    }

    public int getUnitTestCount() {
        return unitTestCount;
    }

    public void runSimpleTest() throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Set<Class<?>> classes = SimpleTestHelper.getClasses(packageToBeScanned);
        for(Class klass : classes){
            Method[] methods = klass.getDeclaredMethods();
            for(Method method : methods){
                String methodFullyQualifiedName = new StringBuilder().append(klass.getName()).append("#")
                        .append(method.getName()).toString();
               if(method.isAnnotationPresent(Test.class)){
                    unitTestCount++;
                    simpleTestCases.add(methodFullyQualifiedName);
                    try {
                        method.invoke(klass.newInstance());//Run @Test Annotated Method
                        passedSimpleTestCases.add(methodFullyQualifiedName);
                    } catch (Exception e) {
                        failedSimpleTestCases.put(methodFullyQualifiedName, e.getCause().toString());
                    }
                }
            }
        }
    }

    public Map<String,String> getFailedSimpleTestCases() {
        return failedSimpleTestCases;
    }
}
