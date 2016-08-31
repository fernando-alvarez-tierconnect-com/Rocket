package com.trucentrix.test;

import com.thoughtworks.selenium.Selenium;
import org.openqa.selenium.WebDriver;

/**
 * Created by IntelliJ IDEA.
 * User: falvarez
 * Date: 4/22/14
 * Time: 8:10 AM
 * To change this template use File | Settings | File Templates.
 */

/**
 * Representation of a web page within an application initialised with the
 * selenium driver.
 */
public abstract class ApplicationPage {
    protected Selenium selenium;
    protected WebDriver driver;

    public ApplicationPage(final Selenium selenium, WebDriver driver, final String textPresent) {
        this.driver = driver;
        this.selenium = selenium;
        new AjaxWait("Couldn't detect Section") {
            @Override
            public boolean until() {
                return selenium.isTextPresent(textPresent);
            }
        };
    }
}
