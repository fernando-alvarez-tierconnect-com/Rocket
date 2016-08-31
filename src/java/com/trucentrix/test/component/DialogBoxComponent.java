package com.trucentrix.test.component;

import com.thoughtworks.selenium.Selenium;
import com.trucentrix.test.AjaxWait;
import com.trucentrix.test.ApplicationSection;
import com.trucentrix.test.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: falvarez
 * Date: 5/29/14
 * Time: 11:26 AM
 * To change this template use File | Settings | File Templates.
 */
public class DialogBoxComponent extends ApplicationSection {

    @FindBy(how = How.XPATH, using = Constants.DIALOGBOX_WARNING)
    private WebElement dialogBoxWarning;

    /**
     * Constructor for ApplicationSection
     *
     * @param selenium The driver driver
     * @param xPath    The path to reach the top level element of this GWT widget
     */
    public DialogBoxComponent(final Selenium selenium, WebDriver driver, String xPath) {
        super(selenium, driver, xPath);
        new AjaxWait("Couldn't detect Section") {
            @Override
            public boolean until() {
                return selenium.isElementPresent(Constants.COMPANY_FORM_COMPONENT_TITLE);
            }
        };
    }

    public void clickOKButton() {
        List<WebElement> linkList = dialogBoxWarning.findElements(By.xpath(Constants.BUTTON_ELEMENT));
        linkList.get(1).click();
//        selenium.click("//button[@class='gwt-Button' and contains(text(),'OK')]");
    }
}
