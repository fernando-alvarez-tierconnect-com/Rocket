package com.trucentrix.test;

import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;
import com.trucentrix.test.CompanyListPage;
import com.trucentrix.test.FileProperties;
import com.trucentrix.test.Section.TabLayoutPanelContentSection;
import com.trucentrix.test.Section.TabLayoutPanelTabsSection;
import com.trucentrix.test.Section.TopMenuSection;
import com.trucentrix.test.TestLink;
import com.trucentrix.test.Utils;
import com.trucentrix.test.component.CompanyFormComponent;
import com.trucentrix.test.page.AdministrationCompanyPage;
import com.trucentrix.test.page.AdministrationPage;
import com.trucentrix.test.page.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: falvarez
 * Date: 5/28/14
 * Time: 7:50 AM
 * To change this template use File | Settings | File Templates.
 */
public class CreateDepartmentTest {
    WebDriver driver;
    Selenium selenium;
    String departmentName;
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
    public void createDepartmentTest() throws Exception{
        AdministrationPage adminPage = topMenu.clickOpenAdministration();
        CompanyListPage companyListPage = adminPage.clickCompaniesIcon();
        AdministrationCompanyPage adminCompanyPage = companyListPage.clickCompanyNameLink();
        TabLayoutPanelTabsSection tabLayoutPanelTabsSection = adminCompanyPage.getTabLayoutPanelSection();
        TabLayoutPanelContentSection tabLayoutContent = tabLayoutPanelTabsSection.clickDepartmentTab();
        CompanyFormComponent companyFormComponent = tabLayoutContent.clickAddDepartment();

        companyFormComponent.selectOption();
        departmentName = companyFormComponent.enterDepartmentName();
        companyFormComponent.clickSaveButton();

        String result = companyListPage.getCompanyNameTable(departmentName);
        System.out.println("result = " + result);
        Assert.assertEquals(departmentName, result, "ERROR: The company was not created.");

        TestLink testLink = new TestLink();
        testLink.updateTestCase("Verify that is possible to Add a new Department", departmentName.equals(result));

    }

    @Test(dependsOnMethods = {"createDepartmentTest"})
    public void editDepartmentTest() throws Exception{
        AdministrationPage adminPage = topMenu.clickOpenAdministration();
        CompanyListPage companyListPage = adminPage.clickCompaniesIcon();
        AdministrationCompanyPage adminCompanyPage = companyListPage.clickCompanyNameLink();
        TabLayoutPanelTabsSection tabLayoutPanelTabsSection = adminCompanyPage.getTabLayoutPanelSection();
        TabLayoutPanelContentSection tabLayoutContent = tabLayoutPanelTabsSection.clickDepartmentTab();
        CompanyFormComponent companyFormComponent = tabLayoutContent.clickDepartmentNameLink(departmentName);

        companyFormComponent.selectOption();
        departmentName = companyFormComponent.enterDepartmentName();
        companyFormComponent.clickSaveButton();

        String result = companyListPage.getCompanyNameTable(departmentName);
        System.out.println("result = " + result);
        Assert.assertEquals(departmentName, result, "ERROR: The company was not created.");

        TestLink testLink = new TestLink();
        testLink.updateTestCase("Verify that is possible to edit a Department", departmentName.equals(result));
    }


    @Test(dependsOnMethods = {"editDepartmentTest"})
    public void deleteDepartmentTest() throws Exception{
        AdministrationPage adminPage = topMenu.clickOpenAdministration();
        CompanyListPage companyListPage = adminPage.clickCompaniesIcon();
        AdministrationCompanyPage adminCompanyPage = companyListPage.clickCompanyNameLink();
        TabLayoutPanelTabsSection tabLayoutPanelTabsSection = adminCompanyPage.getTabLayoutPanelSection();
        tabLayoutPanelTabsSection.clickDepartmentTab();
        companyListPage.clickDeleteIcon();

        String result = companyListPage.getCompanyNameTable(departmentName);
        Assert.assertTrue(result.isEmpty(), "ERROR: The company was not deleted.");

        TestLink testLink = new TestLink();
        testLink.updateTestCase("Verify that is possible to delete a Department", departmentName.equals(result));
    }

    @AfterTest
    public void finish() throws Exception{
        Utils.tearDown(driver);
    }
}
