package com.trucentrix.test.Section;

import com.thoughtworks.selenium.Selenium;
import com.trucentrix.test.AjaxWait;
import com.trucentrix.test.ApplicationSection;
import com.trucentrix.test.Constants;
import com.trucentrix.test.component.CompanyFormComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static org.openqa.selenium.support.How.XPATH;

/**
 * Created by IntelliJ IDEA.
 * User: falvarez
 * Date: 4/24/14
 * Time: 11:45 AM
 * To change this template use File | Settings | File Templates.
 */
public class HeaderPageSection extends ApplicationSection {

    final String xpathList[] = {
            Constants.HEADER_ACTION_ELEMENT,
            Constants.HEADER_TITLE_ELEMENT,
            Constants.HEADER_SUBTITLE_ELEMENT
    };

    @FindBy(how = XPATH, using = Constants.TABLE_CONTAIN_HEADER_)
    private WebElement headerTable;

    @FindBy(how = XPATH, using = Constants.USERS_TAB_SECTION)
    private WebElement usersTableHeader;

    @FindBy(how = XPATH, using = Constants.TABLE_CONTAIN_HEADER_ + Constants.TEXT_BOX_ELEMENT)
    private WebElement textBoxFind;

    @FindBy(how = XPATH, using = Constants.TABLE_CONTAIN_HEADER_ + Constants.BUTTON_ELEMENT)
    private WebElement buttonFind;

    @FindBy(how = XPATH, using = Constants.USERS_TAB_SECTION + Constants.TEXT_BOX_ELEMENT)
    private WebElement usersTextBoxFind;

    @FindBy(how = XPATH, using = Constants.USERS_TAB_SECTION + Constants.USERS_BUTTON_ELEMENT)
    private WebElement usersButtonFind;

    @FindBy(how = How.XPATH, using = Constants.USERS_TABLE)
    private WebElement table;

    /**
     * Constructor for ApplicationSection
     *  @param selenium The driver driver
     * @param xPath    The path to reach the top level element of this GWT widget
     */
    public HeaderPageSection(final Selenium selenium, WebDriver driver, String xPath) {
        super(selenium, driver, xPath);

        new AjaxWait("Couldn't detect Section") {
            @Override
            public boolean until() {
                return selenium.isElementPresent(Constants.TABLE_CONTAIN_HEADER_);
            }
        };
    }

    /**
     * This method clicks on a header link found by its linklabel.
     *
     * @return CompanyFormComponent object
     */
    public CompanyFormComponent clickCreateCompanyLink() {
        String linkText = "Create Company";

        List<WebElement> elements = headerTable.findElements(By.xpath(getXPath() + Constants.HEADER_ACTION_ELEMENT));
        for (WebElement element : elements) {
            if (element.getText().contains(linkText)) {
                element.click();
                break;
            }
        }

        CompanyFormComponent companyFormComponent = new CompanyFormComponent(selenium, driver, Constants.DIALOGBOX_COMPANY_XPATH);
        PageFactory.initElements(driver, companyFormComponent);
        return companyFormComponent;
    }

    public void enterCompanyNameToSearch(String companyName) {
        textBoxFind.sendKeys(companyName);
    }

    public void clickSearchButton() {
        buttonFind.click();
    }

    public void enterUserNameToSearch(String firstName) {
        usersTextBoxFind.sendKeys(firstName);
    }

    public void clickUsersSearchButton() {
        usersButtonFind.click();
    }

  /*  public String getUserNameTable(String name) {
        String nameFound = "";
        List <WebElement> elementList = table.findElements(By.xpath(Constants.FIRST_ROW));
        for (WebElement webElement : elementList) {
            System.out.println("WEBELEMENT :" + webElement.getText());
            if (webElement.getText().equals(name)) {
                nameFound = webElement.getText();

                break;
            }
        }
        System.out.println("THE NAME FOUND IS :" + nameFound);
        return nameFound;
    }
*/
}
