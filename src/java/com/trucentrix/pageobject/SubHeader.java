package com.trucentrix.pageobject;

import org.openqa.selenium.*;
import org.junit.Assert;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.*;
import java.util.concurrent.TimeUnit;

/** This class is for handling the the elements under the TRUcentrix header.
 *
 * @author rcadima
 */
public class SubHeader {
    WebDriver driver;
    private WebDriverWait wait;
    public SubHeader(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, 90);
    }

    //MIGRATED   listSection.getHeaderSection();
    /**This method clicks on a header link found by its linklabel.
     *
     * @param linkLabel
     * @throws Exception
     */
    public void clickSubHeaderLink(String linkLabel) throws Exception {
        int flag=0;
        List<WebElement> links = new ArrayList<WebElement>();
        WebElement subHeader = getSubHeader();
        Assert.assertNotNull("Cant get the sub header", subHeader);
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        if(subHeader.findElements(By.xpath(".//div[@class='headerAction']")).size()>0){
            List<WebElement> elements = subHeader.findElements(By.xpath(".//div[@class='headerAction']"));
            for(WebElement element : elements){
                links.add(element);
            }
        }

        if(subHeader.findElements(By.xpath(".//div[@class='headerTitle headerTitle-link']")).size()>0){
            List<WebElement> elements = subHeader.findElements(By.xpath(".//div[@class='headerTitle headerTitle-link']"));
            for(WebElement element : elements){
                links.add(element);
            }
        }

        if(subHeader.findElements(By.xpath(".//div[@class='headerSubTitleLink']")).size()>0){
            List<WebElement> elements = subHeader.findElements(By.xpath(".//div[@class='headerSubTitleLink']"));
            for(WebElement element : elements){
                links.add(element);
            }
        }

        for(WebElement link : links){
            if(link.getText().contains(linkLabel)){
                link.click();
                flag=1;
                break;
            }
        }
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        if(flag==0){Assert.fail("Error: No links with "+ linkLabel +" label detected");}
    }

    /**This methods click on a button located in the subheader section found by its "buttonLabel"
     *
     * @param buttonLabel
     * @throws Exception
     */
    public void clickSubHeaderButton(String buttonLabel) throws Exception{
        WebElement subHeader = getSubHeader();
        Assert.assertNotNull("Cant get the sub header", subHeader);
        if(subHeader.findElements(By.xpath(".//button")).size()>0){
            List<WebElement> buttons = subHeader.findElements(By.xpath(".//button"));
            for(WebElement button : buttons){
                if(button.getText().contains(buttonLabel)){
                    button.click();
                    break;
                }
            }
        }else{
            Assert.fail("Error: No buttons detected");
        }
    }

    /**This methods sets a subheader Text located in the subheader section found by its "text
     *
     * @param text
     * @throws Exception
     */
    public void setSubHeaderText (String text) throws Exception{
        WebElement subHeader = getSubHeader();
        Assert.assertNotNull("Cant get the sub header", subHeader);
        if(subHeader.findElements(By.xpath(".//input[@type='text']")).size()>0){
            subHeader.findElement(By.xpath(".//input[@type='text']")).sendKeys(text);
        }else{
            Assert.fail("Error: No text field detected");
        }
    }

    //MIGRATED    CompanyListSection.getCompanyListTable()
    /** This methods gets a subheader section or a fail message is displayed when no subheaders were found.
     *
     * @return
     * @throws Exception
     */
    private WebElement getSubHeader() throws Exception{
        WebElement subHeader = null;
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//descendant::table[@class='header']")));
        if(driver.findElements(By.xpath("//table[@class='header']")).size()>0){
            subHeader = driver.findElement(By.xpath("//table[@class='header']"));
        }
        else{
            Assert.fail("Error: There is no sub header in the page");
        }
        return subHeader;
    }
}
