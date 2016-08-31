package com.trucentrix.test.component;

import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.webdriven.commands.Click;
import com.trucentrix.test.AjaxWait;
import com.trucentrix.test.ApplicationSection;
import com.trucentrix.test.Constants;
import com.trucentrix.test.Utils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: falvarez
 * Date: 4/22/14
 * Time: 5:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class CompanyFormComponent extends ApplicationSection {

    @FindBy(how = How.XPATH, using = Constants.DIALOGBOX_COMPANY_XPATH)
    private WebElement dialogBoxCompany;

    @FindBy(how = How.XPATH, using = Constants.PASSWORD_TEXTBOX)
    private WebElement passwordTextBox;

    /**
     * Constructor for ApplicationSection
     *
     * @param selenium The driver driver
     * @param xPath    The path to reach the top level element of this GWT widget
     */
    public CompanyFormComponent(final Selenium selenium, WebDriver driver, String xPath) {
        super(selenium, driver, xPath);
//        final Selenium selenium = new WebDriverBackedSelenium(driver, driver.getCurrentUrl());
        new AjaxWait("Couldn't detect Section") {
            @Override
            public boolean until() {
                return selenium.isElementPresent(Constants.COMPANY_FORM_COMPONENT_TITLE);
            }
        };
    }

    /**
     * This method clicks on a button found by its "xpath" inside a dialog in the page.
     *
     * @return companyListObject
     */
    public void clickSaveButton() {
        List<WebElement> buttons = dialogBoxCompany.findElements(By.xpath(Constants.BUTTON_ELEMENT_));
        for (WebElement button : buttons) {
            if (button.getText().contains(Constants.BUTTON_LABEL)) {
                button.click();
                break;
            }
        }
        AjaxWait.ajaxWait(5000);
    }

    /**
     * This method sets a "dialogText" when the label is located at the left side.
     *
     * @return companyName
     */
    public String enterCompanyName() {
        final String companyName = getGroupName();
        return writeTextBox(companyName);
    }

    public String enterFirstName() {
        final String firstName = getGroupName();
        return writeFirstNameTextBox(firstName);
    }

    public String enterLastName() {
        final String lastName = getGroupName();
        return writeLastNameTextBox(lastName);
    }

    public String enterPassword() {
        final String newPassword = getGroupName();
        return writePasswordTextBox(newPassword);
    }

    public String enterEmail() {
        final String newEmail = generateEmail(15);
        return writeEmailTextBox(newEmail);
    }


    /**
     * This method sets a "dialogText" when the label is located at the left side.
     *
     * @return departmentName
     */
    public String enterDepartmentName() {
        final String departmentName = getGroupName();
        return writeTextBox(departmentName);
    }

    public String writeFirstNameTextBox(String firstName) {
        final WebElement textBox = dialogBoxCompany.findElements(By.xpath(Constants.TEXT_BOX_ELEMENT_)).get(0);
        textBox.clear();
        textBox.sendKeys(firstName);
        return firstName;
    }

    public String writeLastNameTextBox(String lastName) {
        final WebElement textBox = dialogBoxCompany.findElements(By.xpath(Constants.TEXT_BOX_ELEMENT_)).get(2);
        textBox.clear();
        textBox.sendKeys(lastName);
        return lastName;
    }

    public String writeLoginNameTextBox(String loginName) {
        final WebElement textBox = dialogBoxCompany.findElements(By.xpath(Constants.TEXT_BOX_ELEMENT_)).get(3);
        textBox.clear();
        textBox.sendKeys(loginName);
        return loginName;
    }

    public String writePasswordTextBox(String newPassword) {
        passwordTextBox.clear();
        passwordTextBox.sendKeys(newPassword);
        return newPassword;
    }

    public String writeEmailTextBox(String newEmailName) {
        final WebElement textBox = dialogBoxCompany.findElements(By.xpath(Constants.TEXT_BOX_ELEMENT_)).get(4);
        textBox.clear();
        textBox.sendKeys(newEmailName);
        return newEmailName;
    }

    public String writeDaysTextBox(String daysValue) {
        final WebElement textBox = dialogBoxCompany.findElements(By.xpath(Constants.TEXT_BOX_ELEMENT_)).get(6);
        textBox.clear();
        textBox.sendKeys(daysValue);
        return daysValue;
    }

    public String writeTextBox(String companyName) {
//        List<WebElement> textFields = dialogBoxCompany.findElements(By.xpath(Constants.TEXT_BOX_ELEMENT_));

        final WebElement textBox = dialogBoxCompany.findElements(By.xpath(Constants.TEXT_BOX_ELEMENT_)).get(0);
        textBox.clear();
        textBox.sendKeys(companyName);
//
//        for (WebElement textField : textFields) {
//            WebElement findLabel = textField;
//            findLabel = findLabel.findElement(By.xpath(".."));
//            findLabel = findLabel.findElement(By.xpath("./preceding-sibling::td[1]"));
//            if (findLabel.getText().contentEquals(Constants.TEXT_LABEL)) {
////                textField.clear();
//                textField.sendKeys(companyName);
//                break;
//            }
//        }

        return companyName;
    }

    public void selectOption() {
        Select dropdown = new Select(driver.findElement(By.xpath("//select[@class='gwt-ListBox']")));
        dropdown.selectByIndex(1);
    }

    public String getGroupName() {
        return RandomStringUtils.randomAlphanumeric(12).toUpperCase();
    }

    public String generateEmail(int length) {
        String allowedChars="abcdefghijklmnopqrstuvwxyz" +   //alphabets
                "1234567890" +   //numbers
                "_-.";   //special characters
        String email="";
        String temp=RandomStringUtils.random(length,allowedChars);
        email=temp.substring(0,temp.length()-9)+"@test.org";
        return email;
    }
}