package com.trucentrix.pageobject;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.junit.Assert;

/**This class contents the methods to access to reports inside "Reports" icon located in TRUcentrix top Header.
 * Each element is found by ID and an error is displayed if the respective assert value is False.
 *
 * @author rcadima
 */
public class ReportsOptions {
    WebDriver driver;
    private WebDriverWait wait;

    public ReportsOptions(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 90);
    }

    public void openFindDocumentQuickReport() throws Exception{
        driver.findElement(By.id("gwt-uid-10")).click();
        Assert.assertTrue("Error: The quick reports menu is not displayed", driver.findElement(By.xpath("//div[@class='gwt-MenuBarPopup topMenuBarPopup']")).isDisplayed());
        driver.findElement(By.id("gwt-uid-5")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='gwt-DialogBox']")));
        Assert.assertTrue("Error: The quick reports dialog is not displayed", driver.findElement(By.xpath("//div[@class='gwt-DialogBox']")).isDisplayed());
    }

    public void openFindFormQuickReport() throws Exception{
        driver.findElement(By.id("gwt-uid-10")).click();
        Assert.assertTrue("Error: The quick reports menu is not displayed", driver.findElement(By.xpath("//div[@class='gwt-MenuBarPopup topMenuBarPopup']")).isDisplayed());
        driver.findElement(By.id("gwt-uid-6")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='gwt-DialogBox']")));
        Assert.assertTrue("Error: The quick reports dialog is not displayed", driver.findElement(By.xpath("//div[@class='gwt-DialogBox']")).isDisplayed());
    }

    public void openFindProcessQuickReport() throws Exception{
        driver.findElement(By.id("gwt-uid-10")).click();
        Assert.assertTrue("Error: The quick reports menu is not displayed", driver.findElement(By.xpath("//div[@class='gwt-MenuBarPopup topMenuBarPopup']")).isDisplayed());
        driver.findElement(By.id("gwt-uid-7")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='gwt-DialogBox']")));
        Assert.assertTrue("Error: The quick reports dialog is not displayed", driver.findElement(By.xpath("//div[@class='gwt-DialogBox']")).isDisplayed());
    }

    public void openFindAssignmentsQuickReport() throws Exception{
        driver.findElement(By.id("gwt-uid-10")).click();
        Assert.assertTrue("Error: The quick reports menu is not displayed", driver.findElement(By.xpath("//div[@class='gwt-MenuBarPopup topMenuBarPopup']")).isDisplayed());
        driver.findElement(By.id("gwt-uid-8")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='gwt-DialogBox']")));
        Assert.assertTrue("Error: The quick reports dialog is not displayed", driver.findElement(By.xpath("//div[@class='gwt-DialogBox']")).isDisplayed());
    }

    public void openWebPageQuickReport() throws Exception{
        driver.findElement(By.id("gwt-uid-10")).click();
        Assert.assertTrue("Error: The quick reports menu is not displayed", driver.findElement(By.xpath("//div[@class='gwt-MenuBarPopup topMenuBarPopup']")).isDisplayed());
        driver.findElement(By.id("gwt-uid-9")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='gwt-DialogBox']")));
        Assert.assertTrue("Error: The quick reports dialog is not displayed", driver.findElement(By.xpath("//div[@class='gwt-DialogBox']")).isDisplayed());
    }
    
    public void openViewReports() throws Exception{
        driver.findElement(By.id("gwt-uid-11")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@class='sortableTable']")));
        Assert.assertTrue("Error: No new report page founded", driver.findElement(By.xpath("//div[@class='headerTitle']")).getText().contains("Reports"));
    }
    
    public void openNewReport() throws Exception{
        driver.findElement(By.id("gwt-uid-12")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@class='sumLabBor']")));
        Assert.assertTrue("Error: No new report page founded", driver.findElement(By.xpath("//div[@class='headerTitle headerTitle-link']")).getText().contains("View Reports"));
    }
}
