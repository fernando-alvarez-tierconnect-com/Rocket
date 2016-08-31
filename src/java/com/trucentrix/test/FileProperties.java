package com.trucentrix.test;

import java.io.*;
import java.util.Properties;

/**
 * Created by falvarez on 3/11/2016.
 */

public class FileProperties {

    private String userName = "";
    private String password = "";
    private String urlApp = "";
    private String baseUrlApp = "";

    private String devKey = "";
    private String urlTestLink = "";
    private String testProject = "";
    private String testPlan = "";
    private String buildTestPlan = "";

    public static FileProperties fileProperties;


    public static FileProperties getFileData() {
        Properties properties = new Properties();

        try {
            properties.load(new FileInputStream("Configuration.properties"));
            fileProperties =  new FileProperties(properties);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileProperties;
    }

    private FileProperties(Properties properties) {
        this.userName = properties.getProperty("username");
        this.password = properties.getProperty("password");
        this.urlApp = properties.getProperty("urlapp");
        this.baseUrlApp = properties.getProperty("baseUrl");
        this.devKey = properties.getProperty("devkey");
        this.urlTestLink = properties.getProperty("url_testlink");
        this.testProject = properties.getProperty("testproject");
        this.testPlan = properties.getProperty("testplan");
        this.buildTestPlan = properties.getProperty("buildtestplan");
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getUrlApp() {
        return urlApp;
    }

    public String getBaseUrlApp() {
        return baseUrlApp;
    }

    public String getDevKey() {
        return devKey;
    }

    public String getUrlTestLink() {
        return urlTestLink;
    }

    public String getTestProject() {
        return testProject;
    }

    public String getTestPlan() {
        return testPlan;
    }

    public String getBuildTestPlan() {
        return buildTestPlan;
    }

}
