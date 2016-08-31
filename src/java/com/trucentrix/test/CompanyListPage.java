package com.trucentrix.test;

//import com.gargoylesoftware.htmlunit.util.StringUtils;
import com.thoughtworks.selenium.Selenium;
import com.trucentrix.test.Section.HeaderPageSection;
import com.trucentrix.test.component.DialogBoxComponent;
import com.trucentrix.test.page.AdministrationCompanyPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: falvarez
 * Date: 4/22/14
 * Time: 8:10 AM
 * To change this template use File | Settings | File Templates.
 */

/**
 * Concrete instance of an application page. As you can see when creating a new
 * DemoApplicationSection we not only pass in the selenium driver but also the
 * xPath to the instance of this section. By doing this the section only need
 * know about it's own content and can thus be re-used as every action within
 * that class will be relative to it's parent.
 */

public class CompanyListPage extends ApplicationPage {

    @FindBy(how = How.XPATH, using = Constants.SORTABLE_HEADER_TABLE)
    private WebElement table;

    public CompanyListPage(final Selenium selenium, WebDriver driver) {
        super(selenium, driver, "# of Licenses");
    }
    /*
    * The Constructor takes the xPath to the container within the page. If you
    * wanted to reuse the DemoApplicationSection in another page then you could
    * do so by passing in the appropriate xPath to the section relative to the
    * alternative host page.
    *
    * @return an object representing the section to be tested.
    */
    public HeaderPageSection getHeaderPageSection() {
        final HeaderPageSection headerPageSection = new HeaderPageSection(selenium, driver, Constants.TABLE_CONTAIN_HEADER_);
        PageFactory.initElements(driver, headerPageSection);
        return headerPageSection;
    }

    public String getCompanyNameTable(String name) {
        String nameFound = "";
//        WebElement table = driver.findElement(By.xpath(Constants.SORTABLE_HEADER_TABLE));
        List <WebElement> elementList = table.findElements(By.xpath(Constants.FIRST_ROW));
        for (WebElement webElement : elementList) {
            if (webElement.getText().equals(name)) {
                nameFound = webElement.getText();
                break;
            }
        }
//        WebElement companyName;
//        if (elementList.size() > 0) {
//            companyName = elementList.get(0);
//            return companyName.getText();
//        } else {
//            return "";
//        }
//        List<WebElement> row = table.findElements(By.xpath("./tbody/tr[" + 2 + "]/td["+1+"]"));
        System.out.println("THE NAME FOUND IS :" + nameFound);

        return nameFound;
    }

    public String getUserNameTable(String name) {
        String nameFound = "";
        List <WebElement> elementList = table.findElements(By.xpath(Constants.USERS_TABLE));
        for (WebElement webElement : elementList) {
            System.out.println("WebElementArray:" + webElement.getText()+  " /////");
            System.out.println("Name:" + name);
            if (webElement.getText().contains(name)); {
                    nameFound = webElement.getText();
                    System.out.println("WebElement:" + nameFound);
           }
       }

        return nameFound;
    }

    public AdministrationCompanyPage clickCompanyNameLink() {
//        WebElement table = driver.findElement(By.xpath(Constants.SORTABLE_HEADER_TABLE));
        List<WebElement> linkList = table.findElements(By.xpath(Constants.FIRST_ROW));
        linkList.get(0).click();

        AdministrationCompanyPage administrationCompanyPage = new AdministrationCompanyPage(selenium, driver);
        PageFactory.initElements(driver, administrationCompanyPage);
        return administrationCompanyPage;
    }

    public void clickDeleteIcon() {
        selenium.click(Constants.FIRST_ROW_XPATH+Constants.DELETE_ICON);
    }

    public DialogBoxComponent getDialogBoxComponent() {
        DialogBoxComponent dialogBoxComponent = new DialogBoxComponent(selenium, driver, Constants.SORTABLE_HEADER_TABLE);
        PageFactory.initElements(driver, dialogBoxComponent);
        return dialogBoxComponent;
    }
}