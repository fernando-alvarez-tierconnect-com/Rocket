package com.trucentrix.test;

import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;
import com.trucentrix.test.AjaxWait;
import com.trucentrix.test.CompanyListPage;
import com.trucentrix.test.Section.HeaderPageSection;
import com.trucentrix.test.Section.TabLayoutPanelContentSection;
import com.trucentrix.test.Section.TabLayoutPanelTabsSection;
import com.trucentrix.test.Section.TopMenuSection;
import com.trucentrix.test.Utils;
import com.trucentrix.test.component.CompanyFormComponent;
import com.trucentrix.test.page.AdministrationCompanyPage;
import com.trucentrix.test.page.AdministrationPage;
import com.trucentrix.test.page.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Created by user on 8/8/2014.
 */
public class UsersTest {

    WebDriver driver;
    Selenium selenium;
    String companyName;
    String firstName;
    String lastName;
    String newPassword;
    String newEmail;
    TopMenuSection topMenu;

    @BeforeTest
    public void setUp() throws Exception {
        driver = Utils.setup(selenium, driver);
        selenium = new WebDriverBackedSelenium(driver, driver.getCurrentUrl());
        LoginPage loginPage = new LoginPage(selenium, driver);
        PageFactory.initElements(driver, loginPage);
        topMenu = loginPage.login("admin", "manager");
    }

    @Test
    public void CreateUserTest() throws Exception {
        AdministrationPage adminPage = topMenu.clickOpenAdministration();
        CompanyListPage companyListPage = adminPage.clickCompaniesIcon();

        HeaderPageSection headerPageSection = companyListPage.getHeaderPageSection();
        CompanyFormComponent companyFormComponent = headerPageSection.clickCreateCompanyLink();

        companyName = companyFormComponent.enterCompanyName();
        companyFormComponent.clickSaveButton();

        headerPageSection.enterCompanyNameToSearch(companyName);
        headerPageSection.clickSearchButton();
        AdministrationCompanyPage adminCompanyPage = companyListPage.clickCompanyNameLink();
        TabLayoutPanelTabsSection tabLayoutPanelTabsSection = adminCompanyPage.getTabLayoutPanelSection();
        TabLayoutPanelContentSection tabLayoutContent = tabLayoutPanelTabsSection.clickUsersTab();

        CompanyFormComponent userFormComponent = tabLayoutContent.clickAddUserLink();
        firstName = userFormComponent.enterFirstName();
        lastName = userFormComponent.enterLastName();
        newPassword = userFormComponent.enterPassword();
        newEmail = userFormComponent.enterEmail();
        companyFormComponent.clickSaveButton();

        AjaxWait.ajaxWait(10000);
        headerPageSection = companyListPage.getHeaderPageSection();
        headerPageSection.enterUserNameToSearch(firstName);
        headerPageSection.clickUsersSearchButton();

        String result = companyListPage.getUserNameTable(firstName);
        System.out.println("result = " + result);
        Assert.assertEquals(firstName, result, "ERROR: The user was not created.");
    }
}