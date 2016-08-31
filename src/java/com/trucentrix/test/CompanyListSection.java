package com.trucentrix.test;

import com.thoughtworks.selenium.Selenium;
import com.trucentrix.test.component.CompanyFormComponent;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ IDEA.
 * User: falvarez
 * Date: 4/22/14
 * Time: 8:12 AM
 * To change this template use File | Settings | File Templates.
 */

/**
 * Concrete instance of an ApplicationSection ideal for re-use as with GWT
 * widgets themselves
 */
public class CompanyListSection extends ApplicationSection {
    private static final String RELATIVE_INPUT_BUTTON_XPATH = "/descendant::*/input[contains(@id,'myButton')]";
    private static final String RELATIVE_SECTION_CONTENT_XPATH = "/descendant::*/div[contains(@id,'myContent')]";
    private WebDriverWait wait;

    /**
     * Constructor for DemoApplicationSection
     *
     * @param selenium
     * @param xPath
     *          the full xPath to this widget/section
     */
    public CompanyListSection(final Selenium selenium, WebDriver driver, final String xPath) {
        super(selenium, driver, xPath);
        wait = new WebDriverWait(driver, 90);
        /*
        * Since this could be an section that is loaded in via a secondary XHR call
        * I will use the AjaxWait class documented in this post.
        * This is designed to fail fast if the section is not found.
        */
//        new AjaxWait("Couldn't detect DemoApplicationSection") {
//            @Override
//            public boolean until() {
//                return selenium.isElementPresent(xPath);
//            }
//        };
    }

    /*
    * Simple real "user" type commands that illustrate actions that can be taken
    * on the DemoApplicationSection
    */
    public void clickSomeButton() {
        driver.get(getXPath() + RELATIVE_INPUT_BUTTON_XPATH);
    }

    /*
    * Another simple method that gives the client a handle on section content.
    *
    * @return String value of
    */
    public String getSectionContent() {
//        return selenium.getValue(this.getXPath() + RELATIVE_SECTION_CONTENT_XPATH);
        return driver.getCurrentUrl();
    }

    /**
     *  getHeaderSection
     *
     * @return
     * @throws Exception
     */
    public WebElement getHeaderSection() throws Exception{
        WebElement headerSectionCompanyList = null;
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//descendant::table[@class='header']")));
        if(driver.findElements(By.xpath("//table[@class='header']")).size()>0) {
            headerSectionCompanyList = driver.findElement(By.xpath("//table[@class='header']"));
        } else {
            Assert.fail("Error: There is no sub header in the page");
        }
        return headerSectionCompanyList;
    }

    /**This method clicks on a header link found by its linklabel.
     *
     * @param createCompanyLink
     * @throws Exception
     */
    public CompanyFormComponent getCompanyForm(String createCompanyLink, WebElement headerSection) throws Exception {
        int flag=0;
        List<WebElement> links = new ArrayList<WebElement>();
        Assert.assertNotNull("Cant get the sub header", headerSection);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        if(headerSection.findElements(By.xpath(".//div[@class='headerAction']")).size()>0){
            List<WebElement> elements = headerSection.findElements(By.xpath(".//div[@class='headerAction']"));
            for(WebElement element : elements){
                links.add(element);
            }
        }

        if(headerSection.findElements(By.xpath(".//div[@class='headerTitle headerTitle-link']")).size()>0){
            List<WebElement> elements = headerSection.findElements(By.xpath(".//div[@class='headerTitle headerTitle-link']"));
            for(WebElement element : elements){
                links.add(element);
            }
        }

        if(headerSection.findElements(By.xpath(".//div[@class='headerSubTitleLink']")).size()>0){
            List<WebElement> elements = headerSection.findElements(By.xpath(".//div[@class='headerSubTitleLink']"));
            for(WebElement element : elements){
                links.add(element);
            }
        }

        for(WebElement link : links){
            if(link.getText().contains(createCompanyLink)){
                link.click();
                flag=1;
                break;
            }
        }
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        if(flag==0){
            Assert.fail("Error: No links with "+ createCompanyLink +" label detected");
        }

        return new CompanyFormComponent(selenium, driver, "");
    }
}

/**
 * Despite the javadoc documentation claims, you can't use the Wait class as an
 * anonymous class by default so I have created a separate version that
 * facilitates the use in the documented way.
 */
//public abstract class AjaxWait extends Wait {
//    public AjaxWait(String onFailText) {
//        this.wait(onFailText);
//    }
//}