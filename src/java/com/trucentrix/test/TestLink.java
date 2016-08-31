package com.trucentrix.test;

import testlink.api.java.client.TestLinkAPIClient;
import testlink.api.java.client.TestLinkAPIException;
import testlink.api.java.client.TestLinkAPIResults;

import java.util.Date;

/**
 * Created by falvarez on 3/11/2016.
 */
public class TestLink {

    private String DEVKEY;
    private String URL_TESTLINK;
    private String testProject;
    private String testPlan;
    private String buildTestPlan;

    public static FileProperties fileProperties;

    public TestLink () {
        FileProperties fileProperties = FileProperties.getFileData();
        DEVKEY = fileProperties.getDevKey();
        URL_TESTLINK = fileProperties.getUrlTestLink();
        testProject = fileProperties.getTestProject();
        testPlan = fileProperties.getTestPlan();
        buildTestPlan = fileProperties.getBuildTestPlan();
    }

    public void updateTestCase(String testCase, boolean result) throws TestLinkAPIException {
        String statusTestCase = "";
        String notesTestCase = "";

        if (result) {
            statusTestCase = TestLinkAPIResults.TEST_PASSED;
            notesTestCase = "Executed successfully "+ new Date();
            System.out.println("Testlink test case --> Success");
        } else {
            statusTestCase=TestLinkAPIResults.TEST_FAILED;
            notesTestCase="Execution failed "+ new Date();
            System.out.println("Testlink test case --> failed");
        }

        TestLinkAPIClient api = new TestLinkAPIClient(DEVKEY, URL_TESTLINK);
        api.reportTestCaseResult(testProject, testPlan, testCase, buildTestPlan, notesTestCase, statusTestCase);
    }
}
