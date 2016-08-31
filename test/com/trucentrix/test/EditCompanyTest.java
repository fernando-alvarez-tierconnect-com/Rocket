package com.trucentrix.test;

import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;
import com.trucentrix.test.CompanyListPage;
import com.trucentrix.test.Section.HeaderPageSection;
import com.trucentrix.test.Section.TopMenuSection;
import com.trucentrix.test.Utils;
import com.trucentrix.test.component.CompanyFormComponent;
import com.trucentrix.test.page.AdministrationPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Created by IntelliJ IDEA.
 * User: falvarez
 * Date: 5/28/14
 * Time: 7:43 AM
 * To change this template use File | Settings | File Templates.
 */
public class EditCompanyTest {
    WebDriver driver;
    Selenium selenium;

    @BeforeTest
    public void setUp() throws Exception {
        driver = Utils.setup(selenium, driver);
        selenium = new WebDriverBackedSelenium(driver, driver.getCurrentUrl());
    }

    @Test
    public void editCompanyTest() throws Exception{
        TopMenuSection topMenu = new TopMenuSection(selenium, driver);
        PageFactory.initElements(driver, topMenu);
        AdministrationPage adminPage = topMenu.clickOpenAdministration();

        CompanyListPage companyListPage = adminPage.clickCompaniesIcon();
        HeaderPageSection headerPageSection = companyListPage.getHeaderPageSection();
        CompanyFormComponent companyFormComponent = headerPageSection.clickCreateCompanyLink();
        String companyName = companyFormComponent.enterCompanyName();
        companyFormComponent.clickSaveButton();

        headerPageSection.enterCompanyNameToSearch(companyName);
        headerPageSection.clickSearchButton();

        String result = companyListPage.getCompanyNameTable(companyName);
        Assert.assertEquals(companyName, result, "ERROR The company was not created.");

    }

    @AfterTest
    public void finish() throws Exception{
        Utils.tearDown(driver);
    }

}
