/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trucentrix.pageobject;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.junit.Assert;

/**AdministrationOptions contents all the existing options in Administration page.
 * Each option is found by an specific xpath and Assert is applied by Title.
 * @author mpanizzolo
 */
public class AdministrationOptions
{
    public WebDriver driver;
    private WebDriverWait wait;
    public WebElement table;

    public AdministrationOptions (WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, 90);
    }

    //MIGRATED  AdministrationPage.clickCompaniesIcon()
    public void clickCompanies () throws Exception
    {
        driver.findElement(By.xpath("//table/tbody/tr/td/table/tbody/tr[2]/td/div")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@class='sortableTable']")));
     
    }

    public void clickGlobalRoles () throws Exception
    {
        driver.findElement(By.xpath("//table/tbody/tr/td[2]/table/tbody/tr[2]/td/div")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@class='sortableTable']")));
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='headerTitle']")).getText(), "Global Roles");
    }
    public void clickHomePages () throws Exception{
        driver.findElement(By.xpath("//table/tbody/tr/td[3]/table/tbody/tr[2]/td/div")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@class='sortableTable']")));
        Assert.assertEquals(driver.getTitle(), "Home Pages");
    }
    public void clickReports () throws Exception{
        driver.findElement(By.xpath("//table/tbody/tr/td[4]/table/tbody/tr[2]/td/div")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div[2]/div/div[3]/div/div[3]")));
        Assert.assertEquals(driver.getTitle(), "Administration Reports");
    }
    public void clickObjectDefinitions () throws Exception{
        driver.findElement(By.xpath("//table/tbody/tr[2]/td/table/tbody/tr[2]/td/div")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@class='sortableTable']")));
        Assert.assertEquals(driver.getTitle(), "Object Definitions");

    }
    public void clickApplications () throws Exception{
        driver.findElement(By.xpath("//table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/div")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@class='sortableTable']")));
        Assert.assertEquals(driver.getTitle(), "Applications");
    }
    public void clickDataSources () throws Exception{
        driver.findElement(By.xpath("//table/tbody/tr[2]/td[3]/table/tbody/tr[2]/td/div")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@class='sortableTable']")));
        Assert.assertEquals(driver.getTitle(), "Data Sources");
    }
    public void clickUIActions () throws Exception{
        driver.findElement(By.xpath("//table/tbody/tr[2]/td[4]/table/tbody/tr[2]/td/div")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@class='sortableTable']")));
        Assert.assertEquals(driver.getTitle(), "UI Actions");
    }
    public void clickPreferences () throws Exception{
        driver.findElement(By.xpath("//table/tbody/tr[3]/td/table/tbody/tr[2]/td/div")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='gwt-TabLayoutPanelContent']")));
        Assert.assertEquals(driver.getTitle(), "Preferences");
    }
    public void clickServerLogs () throws Exception{
        driver.findElement(By.xpath("//table/tbody/tr[3]/td[2]/table/tbody/tr[2]/td/div")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@class='sortableTable']")));
        Assert.assertEquals(driver.getTitle(), "Server Logs");
    }
}
