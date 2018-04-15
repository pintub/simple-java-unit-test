package com.p2.simpletest.runner;

import com.p2.simpletest.Test;
import com.p2.simpletest.helper.SimpleTestHelper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by I335831 on 4/15/2018.
 */
public class SimpleTestRunner implements ITestRunner {
    private int testCount;
    private String packageToBeScanned;
    private List<String> testCases = new ArrayList<>();
    private List<String> passedTestCases = new ArrayList<>();
    private Map<String, String> failedTestCases = new HashMap<>();
    private ThreadPoolExecutor executor ;

    @Override
    public List<String> getTestCases() {
        return testCases;
    }

    @Override
    public List<String> getPassedTestCases() {
        synchronized (passedTestCases) {
            return passedTestCases;
        }
    }

    public void setPackageToBeScanned(String packageToBeScanned) {
        this.packageToBeScanned = packageToBeScanned;
    }

    @Override
    public int getTestCount() {
        return testCount;
    }

    @Override
    public Map<String,String> getFailedTestCases() {
        synchronized (failedTestCases) {
            return failedTestCases;
        }
    }

    /**
     * For UnitTesting Purpose -- To Mock ThreadPoolExecutor
     * @param executor
     */
    public SimpleTestRunner(ThreadPoolExecutor executor){
        if(executor == null) {
            this.executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(ITestRunner.THREAD_POOL_LIMIT);
        } else {
            this.executor = executor;
        }
    }

    public SimpleTestRunner(){
        this.executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(ITestRunner.THREAD_POOL_LIMIT);
    }

    public void runTest() throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Set<Class<?>> classes = SimpleTestHelper.getClasses(packageToBeScanned);
        for(Class<?> klass : classes){
            Method[] methods = klass.getDeclaredMethods();
            for(Method method : methods){
                String methodFullyQualifiedName = SimpleTestHelper.getMethodName(klass, method);
               if(method.isAnnotationPresent(Test.class)){
                    testCount++;
                    testCases.add(methodFullyQualifiedName);
                    SimpleTestTask simpleTestTask = getSimpleTask(klass, method , failedTestCases, passedTestCases);
                    executor.execute(simpleTestTask);
                }
            }
        }

        executor.shutdown();

        try {
            if (executor.awaitTermination(5, TimeUnit.SECONDS)) {
                System.out.println("Test Cases Executed Successfully!!");
            } else {
                System.err.println("Test Cases Not Executed in 5 Seconds");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public SimpleTestTask getSimpleTask(Class<?> klass,
                                        Method method, Map<String, String> failedTestCases, List<String> passedTestCases) {
        return new SimpleTestTask(klass, method , failedTestCases, passedTestCases);
    }
}
