package com.p2.simpletest.runner;

import java.util.List;
import java.util.Map;

/**
 * Created by I335831 on 4/16/2018.
 */
public interface ITestRunner {

    int THREAD_POOL_LIMIT = 10;

    List<String> getTestCases();

    int getTestCount();

    List<String> getPassedTestCases();

    Map<String,String> getFailedTestCases();

    void runTest() throws Exception;
}
