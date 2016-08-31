package com.trucentrix.test.Section;

import com.thoughtworks.selenium.Selenium;
import com.trucentrix.test.AjaxWait;
import com.trucentrix.test.ApplicationPage;
import com.trucentrix.test.Constants;
import com.trucentrix.test.page.AdministrationPage;
import com.trucentrix.test.page.FormListPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by IntelliJ IDEA.
 * User: falvarez
 * Date: 4/22/14
 * Time: 2:43 PM
 * To change this template use File | Settings | File Templates.
 */

public class TopMenuSection extends ApplicationPage {

//    @FindBy(how = How.XPATH, using = Constants.TOP_MENU_SECTION)
    @FindBy(how = How.XPATH, using = Constants.TOP_MENU_SECTION_IE)
    private WebElement linkAdmin;

    @FindBy(how = How.XPATH, using = Constants.VIEW)
    private WebElement viewOption;

    @FindBy(how = How.XPATH, using = Constants.MYFORMS_OPTION)
    private WebElement myForms;

    public TopMenuSection(final Selenium selenium, final WebDriver driver) {
        super(selenium, driver, "Administration"); // "Administration"
        new AjaxWait("Couldn't detect Section") {
            @Override
            public boolean until() {
                return selenium.isTextPresent("Administration") && !selenium.isTextPresent("Please wait");
            }
        };
    }

    public AdministrationPage clickOpenAdministration() {
        linkAdmin.click();
        AdministrationPage page = new AdministrationPage(selenium, driver);
        PageFactory.initElements(driver, page);
        return page;
    }

    public void clickView() {
        viewOption.click();
    }

    public FormListPage clickMyForms() {
        myForms.click();
        FormListPage formListPage = new FormListPage(selenium, driver);
        PageFactory.initElements(driver, formListPage);
        return formListPage;
    }

    /*
    * Another simple method that gives the client a handle on section content.
    *
    * @return WebComponent
    */
    public String getSectionContent() {
//        return selenium.getValue(this.getXPath() + RELATIVE_SECTION_CONTENT_XPATH);
        return driver.getCurrentUrl();
    }
}
