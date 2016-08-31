package com.trucentrix.pageobject;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.junit.Assert;

/** This class contents all the options inside "View" option located on TRUcentirx header.
 * The elements are found by the respective xpath.
 * @author acachi
 */
public class ViewMenuOption {

    WebDriver driver;
    private WebDriverWait wait;

    public ViewMenuOption (WebDriver driver){
        this.driver=driver;
        wait = new WebDriverWait(driver, 90);
    }

    public void openMyProjects() throws Exception {
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='gwt-MenuBarPopup topMenuBarPopup']")).isDisplayed());
        driver.findElement(By.xpath("//*[@id='gwt-uid-13']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//table[@class='sortableTable']")));
        Assert.assertEquals(driver.getTitle(), "Projects");
    }

    public void openMyForms() throws Exception {
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='gwt-MenuBarPopup topMenuBarPopup']")).isDisplayed());
        driver.findElement(By.xpath("//*[@id='gwt-uid-14']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//table[@class='sortableTable']")));
        Assert.assertEquals(driver.getTitle(), "Forms");
        
    }

    public void openMyAssignments() throws Exception {
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='gwt-MenuBarPopup topMenuBarPopup']")).isDisplayed());
        driver.findElement(By.xpath("//*[@id='gwt-uid-15']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//table[@class='sortableTable']")));
        Assert.assertEquals(driver.getTitle(), "My Assignments");

    }

    public void openMyRecentItems() throws Exception {
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='gwt-MenuBarPopup topMenuBarPopup']")).isDisplayed());
        driver.findElement(By.xpath("//*[@id='gwt-uid-16']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//table[@class='sortableTable']")));
        Assert.assertEquals(driver.getTitle(), "Recent Items");
    }

    public void openMyFavoriteItems() throws Exception {
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='gwt-MenuBarPopup topMenuBarPopup']")).isDisplayed());
        driver.findElement(By.xpath("//*[@id='gwt-uid-17']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//table[@class='sortableTable']")));
        Assert.assertEquals(driver.getTitle(), "Favorites");
    }

    public void openMySubscriptions() throws Exception {
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='gwt-MenuBarPopup topMenuBarPopup']")).isDisplayed());
        driver.findElement(By.xpath("//*[@id='gwt-uid-18']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//table[@class='sortableTable']")));
        Assert.assertEquals(driver.getTitle(), "My Subscriptions");
        
    }

    public void openUserDirectory() throws Exception {
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='gwt-MenuBarPopup topMenuBarPopup']")).isDisplayed());
        driver.findElement(By.xpath("//*[@id='gwt-uid-19']")).click();
        Assert.assertEquals(driver.getTitle(), "User Directory");
    }

    public void openHomePage() throws Exception {
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='gwt-MenuBarPopup topMenuBarPopup']")).isDisplayed());
        driver.findElement(By.xpath("//*[@id='gwt-uid-20']")).click();
        Assert.assertEquals(driver.getTitle(), "TRUcentrix Home Page");
    }
}