package com.trucentrix.test.page;

import com.thoughtworks.selenium.Selenium;
import com.trucentrix.test.ApplicationPage;
import com.trucentrix.test.CompanyListPage;
import com.trucentrix.test.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

//import org.openqa.selenium.support.FindBy;

/**
 * Created by IntelliJ IDEA.
 * User: falvarez
 * Date: 4/22/14
 * Time: 3:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class AdministrationPage extends ApplicationPage {

    @FindBy(how = How.XPATH, using = Constants.ICON_COMPANIES)
    private WebElement iconCompanies;

    public AdministrationPage(final Selenium selenium, final WebDriver driver) {
        super(selenium, driver, "Administration");
    }

    /*
    *
    * @return an object representing the section to be tested.
    */
    public CompanyListPage clickCompaniesIcon() throws Exception {
        iconCompanies.click();
        CompanyListPage companyListPage = new CompanyListPage(selenium, driver);
        PageFactory.initElements(driver, companyListPage);
        return companyListPage;
    }
}
