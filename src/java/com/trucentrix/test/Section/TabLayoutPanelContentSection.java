package com.trucentrix.test.Section;

import com.thoughtworks.selenium.Selenium;
import com.trucentrix.test.AjaxWait;
import com.trucentrix.test.ApplicationSection;
import com.trucentrix.test.Constants;
import com.trucentrix.test.Utils;
import com.trucentrix.test.component.CompanyFormComponent;
import org.apache.commons.lang3.RandomStringUtils;
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
 * Date: 5/28/14
 * Time: 4:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class TabLayoutPanelContentSection extends ApplicationSection {

    @FindBy(how = How.XPATH, using = Constants.TABLAYOUT_PANEL_CONTENT)
    private WebElement tabLayoutPanelContent;

    public TabLayoutPanelContentSection(final Selenium selenium, final WebDriver driver, String tabLayoutPanelTabs) {
        super(selenium, driver, tabLayoutPanelTabs);

        new AjaxWait("Couldn't detect Section") {
            @Override
            public boolean until() {
                return selenium.isElementPresent(Constants.TABLAYOUT_PANEL_CONTENT);
            }
        };
    }

    /**
     * This method clicks on a button found by its "xpath" inside a dialog in the page.
     *
     * @return companyListObject
     */
    public void clickSaveButton() {
        List<WebElement> buttons = tabLayoutPanelContent.findElements(By.xpath(Constants.BUTTON_ELEMENT));
        for (WebElement button : buttons) {
            if (button.getText().contains(Constants.BUTTON_LABEL)) {
                button.click();
                break;
            }
        }
        AjaxWait.ajaxWait(1000);
    }

    public CompanyFormComponent clickAddUserLink() {
        String linkText = "Add User";

        List<WebElement> elements = tabLayoutPanelContent.findElements(By.xpath(getXPath() + Constants.HEADER_ACTION_ELEMENT));
        for (WebElement element : elements) {
            if (element.getText().contains(linkText)) {
                element.click();
                break;
            }
        }

        CompanyFormComponent companyFormComponent = new CompanyFormComponent(selenium, driver, Constants.DIALOGBOX_COMPANY_XPATH);
        PageFactory.initElements(driver, companyFormComponent);
        return companyFormComponent;
    }

    /**
     * This method sets a "dialogText" when the label is located at the left side.
     *
     * @return companyName
     */
    public String enterNewCompanyName() {

        final String companyName = getCompanyName();
        List<WebElement> textFields = tabLayoutPanelContent.findElements(By.xpath(Constants.TEXT_BOX_ELEMENT));

        WebElement textField = textFields.get(0).findElement(By.xpath("../preceding-sibling::td[1]"));
//        WebElement textField = textFields.get(1).findElement(By.xpath("/html/body/div[2]/div[2]/div/div[3]/div/div[3]/div/div[2]/div/div[3]/div/div[3]/div/div[2]/div/div[3]/div/div/table/tbody/tr[3]/td[1]"));
        if (textField.getText().contentEquals(Constants.TEXT_LABEL)) {
            textFields.get(0).clear();
            textFields.get(0).sendKeys(companyName);
        }

        return companyName;
    }

    public String getCompanyName() {
        return RandomStringUtils.randomAlphanumeric(12).toUpperCase();
    }

    /*
   * Department methods
   * */
    public CompanyFormComponent clickAddDepartment() {
        selenium.click(getXPath() + Constants.TABLAYOUT_LABEL_ADD_DEPARTMENT);
        CompanyFormComponent companyFormComponent = new CompanyFormComponent(selenium, driver, Constants.DIALOGBOX_COMPANY_XPATH);
        PageFactory.initElements(driver, companyFormComponent);
        return companyFormComponent;
    }

    public CompanyFormComponent clickDepartmentNameLink(String name) {
        List <WebElement> elementList = tabLayoutPanelContent.findElements(By.xpath(Constants.FIRST_ROW));
        for (WebElement webElement : elementList) {
            if (webElement.getText().equals(name)) {
                webElement.click();
                break;
            }
        }
        
//        List<WebElement> linkList = tabLayoutPanelContent.findElements(By.xpath(Constants.FIRST_ROW));
//        linkList.get(0).click();

        CompanyFormComponent companyFormComponent = new CompanyFormComponent(selenium, driver, Constants.DIALOGBOX_COMPANY_XPATH);
        PageFactory.initElements(driver, companyFormComponent);
        return companyFormComponent;
    }
}
