package com.trucentrix.test;

import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;
import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;
import com.trucentrix.test.Section.HeaderPageSection;
import com.trucentrix.test.Section.TabLayoutPanelContentSection;
import com.trucentrix.test.Section.TabLayoutPanelTabsSection;
import com.trucentrix.test.Section.TopMenuSection;
import com.trucentrix.test.component.CompanyFormComponent;
import com.trucentrix.test.component.DialogBoxComponent;
import com.trucentrix.test.page.AdministrationCompanyPage;
import com.trucentrix.test.page.AdministrationPage;
import com.trucentrix.test.page.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


/**
 * Created by IntelliJ IDEA.
 * User: falvarez
 * Date: 4/22/14
 * Time: 8:06 AM
 * To change this template use File | Settings | File Templates.
 */

/**
 * TC64-476:Verify that it is possible to create a new Company
 */

@Listeners({ ATUReportsListener.class, ConfigurationListener.class,MethodListener.class })
public class CreateCompanyTest {
    {
        System.setProperty("atu.reporter.config", "C:\\Trubiquity\\automation\\trubiquity-qa\\WebApp_TestAutomation\\TRUcentrix\\ATUreports\\img\\atu.properties");
    }

    WebDriver driver;
    Selenium selenium;
    String companyName;
    TopMenuSection topMenu;

    @BeforeTest
    public void setUp() throws Exception {
        driver = Utils.setup(selenium, driver);
        selenium = new WebDriverBackedSelenium(driver, driver.getCurrentUrl());
        LoginPage loginPage = new LoginPage(selenium, driver);
        PageFactory.initElements(driver, loginPage);

        FileProperties fileProperties = FileProperties.getFileData();
        topMenu = loginPage.login(fileProperties.getUserName(), fileProperties.getPassword());
    }

    @Test
    public void createCompanyTest() throws Exception{
        AdministrationPage adminPage = topMenu.clickOpenAdministration();
        CompanyListPage companyListPage = adminPage.clickCompaniesIcon();

        HeaderPageSection headerPageSection = companyListPage.getHeaderPageSection();
        CompanyFormComponent companyFormComponent = headerPageSection.clickCreateCompanyLink();

        companyName = companyFormComponent.enterCompanyName();
        companyFormComponent.clickSaveButton();

        headerPageSection.enterCompanyNameToSearch(companyName);
        headerPageSection.clickSearchButton();

        String result = companyListPage.getCompanyNameTable(companyName);
        System.out.println("result = " + result);
        Assert.assertEquals(companyName, result, "ERROR: The company was not created.");

        TestLink testLink = new TestLink();
        testLink.updateTestCase("Verify that it is possible to create a new Company", companyName.equals(result));
    }

    @Test(dependsOnMethods = {"createCompanyTest"})
    public void editCompanyTest() throws Exception{
        AdministrationPage adminPage = topMenu.clickOpenAdministration();
        CompanyListPage companyListPage = adminPage.clickCompaniesIcon();
        HeaderPageSection headerPageSection = companyListPage.getHeaderPageSection();

        headerPageSection.enterCompanyNameToSearch(companyName);
        headerPageSection.clickSearchButton();
        AdministrationCompanyPage adminCompanyPage = companyListPage.clickCompanyNameLink();
        TabLayoutPanelTabsSection tabLayoutPanelTabsSection = adminCompanyPage.getTabLayoutPanelSection();
        TabLayoutPanelContentSection tabLayoutContent = tabLayoutPanelTabsSection.clickCompanyInfoTab();

        companyName = tabLayoutContent.enterNewCompanyName();
        tabLayoutContent.clickSaveButton();

        adminPage = topMenu.clickOpenAdministration();
        companyListPage = adminPage.clickCompaniesIcon();
        headerPageSection.enterCompanyNameToSearch(companyName);
        headerPageSection.clickSearchButton();

        String result = companyListPage.getCompanyNameTable(companyName);
        Assert.assertEquals(companyName, result, "ERROR: The company was not updated.");

        TestLink testLink = new TestLink();
        testLink.updateTestCase("Verify that is possible to update the information of the company", companyName.equals(result));
    }


    @Test(dependsOnMethods = {"editCompanyTest"})
    public void deleteCompanyTest() throws Exception{
        AdministrationPage adminPage = topMenu.clickOpenAdministration();

        CompanyListPage companyListPage = adminPage.clickCompaniesIcon();
        HeaderPageSection headerPageSection = companyListPage.getHeaderPageSection();
        headerPageSection.enterCompanyNameToSearch(companyName);
        headerPageSection.clickSearchButton();

        companyListPage.clickDeleteIcon();
        DialogBoxComponent dialogBoxComponent = companyListPage.getDialogBoxComponent();
        dialogBoxComponent.clickOKButton();

        headerPageSection.enterCompanyNameToSearch(companyName);
        headerPageSection.clickSearchButton();
        String result = companyListPage.getCompanyNameTable(companyName);
        Assert.assertTrue(result.isEmpty(), "ERROR: The company was not deleted.");

        TestLink testLink = new TestLink();
        testLink.updateTestCase("Verify that it is possible to delete a Company", result.isEmpty());
    }

    @AfterTest
    public void finish() throws Exception {
        Utils.tearDown(driver);
    }
}