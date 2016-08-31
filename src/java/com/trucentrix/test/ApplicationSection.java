package com.trucentrix.test;

import com.thoughtworks.selenium.Selenium;
import org.openqa.selenium.WebDriver;

/**
 * Created by IntelliJ IDEA.
 * User: falvarez
 * Date: 4/22/14
 * Time: 8:17 AM
 * To change this template use File | Settings | File Templates.
 */

/**
 * Representation of a discrete sub-section of a web page, likely a re-usable
 * GWT widget in this example.
 */
public abstract class ApplicationSection {
    public static final String DEFAULT_AJAX_LOAD_TIMEOUT = "5000";
    protected WebDriver driver;
    protected Selenium selenium;
    protected String xPath;
    /**
     * Constructor for ApplicationSection
     * @param selenium The driver driver
     * @param xPath The path to reach the top level element of this GWT widget
     */
    protected ApplicationSection(Selenium selenium, WebDriver driver, String xPath) {
        this.driver = driver;
        this.selenium = selenium;
        this.xPath = xPath;
    }

    public String getXPath() {
        return this.xPath;
    }
}