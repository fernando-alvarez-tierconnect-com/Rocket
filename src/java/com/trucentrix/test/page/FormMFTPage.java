package com.trucentrix.test.page;

import com.thoughtworks.selenium.Selenium;
import com.trucentrix.test.ApplicationPage;
import com.trucentrix.test.Constants;
import com.trucentrix.test.component.SelectUserComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by IntelliJ IDEA.
 * User: falvarez
 * Date: 7/9/14
 * Time: 8:37 AM
 * To change this template use File | Settings | File Templates.
 */
public class FormMFTPage extends ApplicationPage{
    @FindBy(how = How.XPATH, using = Constants.BUTTON_BROWSE)
    private WebElement buttonBrowse;

    @FindBy(how = How.XPATH, using = Constants.UPLOAD_LINK)
    private WebElement uploadLink;

    public FormMFTPage(final Selenium selenium, final WebDriver driver) {
        super(selenium, driver, "Managed File Transfer Form");
    }

    public SelectUserComponent clickBrowseButton() {
        buttonBrowse.click();
        SelectUserComponent selectUserComponent = new SelectUserComponent(selenium, driver, Constants.SELECT_USERS_COMPONENT);
        PageFactory.initElements(driver, selectUserComponent);
        return selectUserComponent;
    }

    public void clickUploadLink() {
        uploadLink.click();
    }
}
