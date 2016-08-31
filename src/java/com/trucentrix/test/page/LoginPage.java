package com.trucentrix.test.page;

import com.thoughtworks.selenium.Selenium;
import com.trucentrix.test.ApplicationPage;
import com.trucentrix.test.Section.TopMenuSection;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by IntelliJ IDEA.
 * User: falvarez
 * Date: 4/24/14
 * Time: 8:39 AM
 * To change this template use File | Settings | File Templates.
 */
public class LoginPage extends ApplicationPage{

    @FindBy(how = How.NAME, using = "j_username")
    private WebElement userName;

    @FindBy(how = How.NAME, using = "j_password")
    private WebElement password; // password

    @FindBy(how = How.LINK_TEXT, using = "Login")
    private WebElement loginButton; // login

    public LoginPage(Selenium selenium, WebDriver driver) {
        super(selenium, driver, "Username");
    }

    public TopMenuSection login(String UserName, String Password)
    {
        userName.sendKeys(UserName);
        System.out.println("UserName = " + UserName);
        password.sendKeys(Password);
        loginButton.click();

        TopMenuSection topMenu = new TopMenuSection(selenium, driver);
        PageFactory.initElements(driver, topMenu);
        return topMenu;
    }

}
