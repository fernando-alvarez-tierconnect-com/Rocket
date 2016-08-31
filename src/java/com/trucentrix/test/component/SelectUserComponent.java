package com.trucentrix.test.component;

import com.thoughtworks.selenium.Selenium;
import com.trucentrix.test.AjaxWait;
import com.trucentrix.test.ApplicationSection;
import com.trucentrix.test.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * Created by IntelliJ IDEA.
 * User: falvarez
 * Date: 7/9/14
 * Time: 9:00 AM
 * To change this template use File | Settings | File Templates.
 */
public class SelectUserComponent extends ApplicationSection{
    @FindBy(how = How.XPATH, using = Constants.START_NEW_MFT)
    private WebElement startNewMFT;

    @FindBy(how = How.XPATH, using = Constants.USER_SELECTED)
    private WebElement userSelected;

    @FindBy(how = How.XPATH, using = Constants.LINK_ADD)
    private WebElement linkAdd;

    @FindBy(how = How.XPATH, using = Constants.LINK_ADD_ALL)
    private WebElement linkAddAll;

    @FindBy(how = How.XPATH, using = Constants.BUTTON_OK)
    private WebElement buttonOk;

    public SelectUserComponent(final Selenium selenium, final WebDriver driver, String xPath) {
        super(selenium, driver, xPath);

        new AjaxWait("Couldn't detect Section") {
            @Override
            public boolean until() {
                return selenium.isTextPresent("Select User(s)");
            }
        };
    }

    public void selectUser() {
        userSelected.click();
    }
    
    public void clickLinkAdd() {
        linkAdd.click();
    }

    public void clickLinkAddAll() {
        linkAddAll.click();
    }

    public void clickButtonOk() {
        buttonOk.click();
    }

}
