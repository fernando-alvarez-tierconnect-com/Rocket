package com.trucentrix.pageobject;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**HelpOption is for handling all the options inside Help icon.
 *
 * @author rcadima
 */
public class HelpOptions {

    private WebDriver driver;
    private String principalWindow;
    private WebDriverWait wait;

    public HelpOptions(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 90);
    }

    /**whatsNew is a class for access to Whats New notes related to the application
     *  After the windows was opened the driver switches to the new opened window
     * @throws Exception
     */
    public void whatsNew() throws Exception {
        principalWindow = driver.getWindowHandle();
        driver.findElement(By.id("gwt-uid-1")).click();

        for(String popUpWindow : driver.getWindowHandles()){
          driver.switchTo().window(popUpWindow);
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Changes in Previous Releases")));
        driver.findElement(By.linkText("Changes in Previous Releases")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body")));
        driver.close();
        driver.switchTo().window(principalWindow);
    }

    /**onlineHelp is a class for access to the online Help for common users related to the application
     *  After the windows was opened the driver switches to the new opened window
     *
     * @throws Exception
     */
    public void onlineHelp() throws Exception {
        principalWindow = driver.getWindowHandle();
        driver.findElement(By.id("gwt-uid-2")).click();

        for(String popUpWindow : driver.getWindowHandles()){
            driver.switchTo().window(popUpWindow);
        }
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("/html")));
        driver.close();
        driver.switchTo().window(principalWindow);
    }

    /** adminGuide is a class for access to the Admin Guide related to the application
     *  After the windows was opened the driver switches to the new opened window
     *
     * @throws Exception
     */
    public void adminGuide() throws Exception {
        principalWindow = driver.getWindowHandle();
        driver.findElement(By.id("gwt-uid-3")).click();

        for(String popUpWindow : driver.getWindowHandles()){
            driver.switchTo().window(popUpWindow);
        }
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("/html")));
        driver.close();
        driver.switchTo().window(principalWindow);
    }

    /** aboutTrucentrix is a class to see the general info related to TRUcentrix application
     * Notice that the driver is not changed to the dialog.
     * @throws Exception
     */
    public void aboutTrucentrix() throws Exception {
        driver.findElement(By.id("gwt-uid-4")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='gwt-DialogBox']")));
        driver.findElement(By.xpath("//div[@class='gwt-DialogBox']//button")).click();
    }
}
