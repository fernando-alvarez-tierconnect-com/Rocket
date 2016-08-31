/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.trucentrix.pageobject;

import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import java.util.List;
import static org.junit.Assert.*;

/**
 * Verify that the current page is a TRUcentrix page.
 * @author rcadima
 */
class Verification {
    private WebDriver driver;

    /**
     * The object is constructed confirming the page.
     * @param driver The web driver object.
     */
    public Verification(WebDriver driver) {
        this.driver=driver;
        String label = driver.getTitle();
        System.out.println("This is the page Title: "+label);
        if (!driver.getTitle().contains("TRUcentrix")) {
            throw new IllegalStateException("This is not TRUcentrix: " + driver.getCurrentUrl());
        }
    }

    public boolean isElementThere () throws Exception {

        return true;
    }

    /**Link found by text, if the link is found, a message is displayed.
     *
     * @param driver
     * @param text
     * @throws Exception
     */
    public static void assertLinkNotPresent (WebDriver driver, String text) throws Exception {
        List<WebElement> bob = driver.findElements(By.linkText(text));
        if (bob.isEmpty() == false) {
        throw new Exception (text + " (Link is present)");
    }
    }


    /**Link found by text, if the link is found, a message is displayed.
     *
     * @param driver
     * @param text
     */
    public static void assertLinkNotPresenta(WebDriver driver, String text) {
    try {
        driver.findElement(By.linkText(text));
        fail("Link with text <" + text + "> is present");
    } catch (NoSuchElementException ex) { 
        /* do nothing, link is not present, assert is passed */ 
    }
    }
}
