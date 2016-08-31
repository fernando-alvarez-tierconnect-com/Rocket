package com.trucentrix.test;

import com.nemo.festselenium.fixture.AppletFixture;
import com.nemo.festselenium.selenium.DefaultJavaSelenium;
import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;
import com.trucentrix.test.Section.TopMenuSection;
import com.trucentrix.test.Utils;
import com.trucentrix.test.component.SelectUserComponent;
import com.trucentrix.test.page.FormListPage;
import com.trucentrix.test.page.FormMFTPage;
import com.trucentrix.test.page.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
* Created by IntelliJ IDEA.
* User: falvarez
* Date: 7/8/14
* Time: 5:06 PM
* To change this template use File | Settings | File Templates.
*/
public class CreateFormTest {

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
        topMenu = loginPage.login("falvarez", "12345678");
    }

    @Test
    public void createCompanyTest() throws Exception{
        topMenu.clickView();
        FormListPage formListPage = topMenu.clickMyForms();
        FormMFTPage formMFTPage = formListPage.clickNewFormMFT();
        SelectUserComponent selectUserComponent = formMFTPage.clickBrowseButton();
        selectUserComponent.selectUser();
//        selectUserComponent.clickLinkAdd();
        selectUserComponent.clickLinkAddAll();
        selectUserComponent.clickButtonOk();
        formMFTPage.clickUploadLink();

//        selenium setup
        DefaultJavaSelenium selenium1 = new DefaultJavaSelenium("10.100.1.162",8080, "firefox" , "http://10.100.1.162");
        selenium1.start();
        selenium1.open("/nxps/nxps?action=gwtDispatch#module:form:contextId:311:objectId:416:formLayoutId:133");
//        selenium1.open("/nxps/nxps?action=defaultPage");

// get the appletfixure to control fest JAppletFixture
        AppletFixture dialog = selenium1.applet("transferApplet");

// fest similar API for autmation testing
//        dialog.comboBox("domain").select("Users");
//        dialog.textBox("username").enterText("alex.ruiz");
        dialog.button("Choose Files...").click();
    }

    @AfterTest
    public void finish() throws Exception {
        Utils.tearDown(driver);
    }
}
