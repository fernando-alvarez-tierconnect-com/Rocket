package com.trucentrix.test.page;

import com.thoughtworks.selenium.Selenium;
import com.trucentrix.test.ApplicationPage;
import com.trucentrix.test.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by IntelliJ IDEA.
 * User: falvarez
 * Date: 7/8/14
 * Time: 5:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class FormListPage extends ApplicationPage{
    @FindBy(how = How.XPATH, using = Constants.START_NEW_MFT)
    private WebElement startNewMFT;

    public FormListPage(final Selenium selenium, final WebDriver driver) {
        super(selenium, driver, "Forms");
    }


    public FormMFTPage clickNewFormMFT() throws Exception {
        startNewMFT.click();
        FormMFTPage formMFTPage = new FormMFTPage(selenium, driver);
        PageFactory.initElements(driver, formMFTPage);
        return formMFTPage;
    }

}
