package com.trucentrix.pageobject;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/** Click on all the options displayed in the TRUcentrix header.
 * All the options are found by "xpath" excepting "Applications" link that is found by ID.
 * also an error is displayed if the driver.GetTitle is not the right title of the page.
 * @author rcadima
 */
public class Header {
    WebDriver driver;
    private WebDriverWait wait;
    BufferedReader incoming = new BufferedReader (new InputStreamReader(System.in));
    public Header(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, 90);
    }

    public void homeTrucentrixLink() throws Exception {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]")));
        driver.findElement(By.xpath("//img[@title='Default Page']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@title='Default Page']")));
        Assert.assertTrue("Error: Is not the Home Page", driver.getTitle().contains("TRUcentrix Home Page"));
    }

    public void openUserProfile() throws Exception {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]")));
        driver.findElement(By.xpath("//img[@title='User Profile']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='gwt-TabLayoutPanel']")));
        Assert.assertTrue("Error: Is not the User Profile", driver.getTitle().contains("General"));
    }

    public ReportsOptions openReportsMenu() throws Exception{
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]")));
        driver.findElement(By.xpath("//img[@title='Click to get Reports Menu']")).click();
        Assert.assertTrue("Error: The reports menu wasn't displayed", driver.findElement(By.xpath("//div[@class='topMenuPopup']")).isDisplayed());
        return new ReportsOptions(driver);
    }

    public void openCalendar() throws Exception {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]")));
        driver.findElement(By.xpath("//img[@title='Calendar']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='calendarInFrame']")));
        Assert.assertTrue("Error: The Calendar wasn't displayed", driver.getTitle().contains("Calendar"));
    }

    public HelpOptions openHelpMenu() throws Exception {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]")));
        driver.findElement(By.xpath("//img[@title='Help']")).click();
        Assert.assertTrue("Error: The help menu wasn't displayed", driver.findElement(By.xpath("//div[@class='topMenuPopup']")).isDisplayed());
        return new HelpOptions(driver);
    }

    public ViewMenuOption openViewMenu() throws Exception {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]")));
        driver.findElement(By.xpath("//table[@class='topMenuMain']/tbody/tr/td[3]/table/tbody/tr/td/div/table/tbody/tr/td")).click();
        Assert.assertTrue("Error: The view menu wasn't displayed", driver.findElement(By.xpath("//div[@class='gwt-MenuBarPopup topMenuBarPopup']")).isDisplayed());
        return new ViewMenuOption(driver);
    }

    public void openApplications() throws Exception{
        wait.until(ExpectedConditions.elementToBeClickable(By.id("gwt-uid-26")));
        driver.findElement(By.id("gwt-uid-26")).click();
        Assert.assertTrue("Error: The application menu wasn't displayed", driver.findElement(By.xpath("//div[@class='gwt-MenuBarPopup topMenuBarPopup']")).isDisplayed());
    }

    //MIGRATED   TopMenuSection.clickOpenAdministration()
    public AdministrationOptions openAdministration() throws Exception {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]")));
        driver.findElement(By.xpath("//table[@class='topMenuMain']/tbody/tr/td[3]/table/tbody/tr/td[2]/div")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//table[@class='adminIconsBg']")));
        Assert.assertTrue("Error: This is not the Administration page", driver.getTitle().contains("Administration"));
        return new AdministrationOptions(driver);
    }
    
    public void search() throws Exception{
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@title='Search']")));
        System.out.println("Introduce the search criteria");
        driver.findElement(By.xpath("//input[@class='gwt-TextBox']")).clear();
        driver.findElement(By.xpath("//input[@class='gwt-TextBox']")).sendKeys(incoming.readLine());
        driver.findElement(By.xpath("//img[@title='Search']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div[2]/div/div[3]/div/div[4]/div/div[3]/div/div/table")));
    }

    public void advancedSearch() throws Exception{
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='topMenuLinkSmall']")));
        driver.findElement(By.xpath("//div[@class='topMenuLinkSmall']")).click();
        Assert.assertTrue("Error: The advance search dialog wasn't displayed",driver.findElement(By.xpath("//div[@class='gwt-DialogBox']")).isDisplayed());
    }

    public void logoutApp() throws Exception{
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]")));
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div[2]/table/tbody/tr/td[3]/table/tbody/tr/td[4]/div")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("header")));
        Assert.assertTrue("Error: Was not logged out", driver.getTitle().contains("Login"));
    }
}
