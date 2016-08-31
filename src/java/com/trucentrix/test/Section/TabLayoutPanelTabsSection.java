package com.trucentrix.test.Section;

import com.thoughtworks.selenium.Selenium;
import com.trucentrix.test.AjaxWait;
import com.trucentrix.test.ApplicationSection;
import com.trucentrix.test.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by IntelliJ IDEA.
 * User: falvarez
 * Date: 5/28/14
 * Time: 11:56 AM
 * To change this template use File | Settings | File Templates.
 */
public class TabLayoutPanelTabsSection extends ApplicationSection {

    @FindBy(how = How.XPATH, using = Constants.TABLAYOUT_PANEL_TABS)
    private WebElement tabLayoutPanelTabs;

    public TabLayoutPanelTabsSection(final Selenium selenium, final WebDriver driver, String tabLayoutPanelTabs) {
        super(selenium, driver, tabLayoutPanelTabs);

        new AjaxWait("Couldn't detect Section") {
            @Override
            public boolean until() {
                return selenium.isElementPresent(Constants.TABLAYOUT_PANEL_TAB_INNER);
            }
        };
    }

    public TabLayoutPanelContentSection clickCompanyInfoTab(){
        selenium.click(Constants.TABLAYOUT_LABEL_COMPANY_INFO);
        TabLayoutPanelContentSection tabLayoutPanelContentSection = new TabLayoutPanelContentSection(selenium, driver, Constants.TABLAYOUT_PANEL_CONTENT);
        PageFactory.initElements(driver, tabLayoutPanelContentSection);
        return tabLayoutPanelContentSection;
    }

    public TabLayoutPanelContentSection clickUsersTab(){
        selenium.click(Constants.TABLAYOUT_LABEL_USERS);
        TabLayoutPanelContentSection tabLayoutPanelContentSection = new TabLayoutPanelContentSection(selenium, driver, Constants.TABLAYOUT_PANEL_CONTENT);
        PageFactory.initElements(driver, tabLayoutPanelContentSection);
        return tabLayoutPanelContentSection;
    }

    public TabLayoutPanelContentSection clickDepartmentTab(){
        selenium.click(Constants.TABLAYOUT_LABEL_DEPARTMENTS);
        TabLayoutPanelContentSection tabLayoutPanelContentSection = new TabLayoutPanelContentSection(selenium, driver, Constants.TABLAYOUT_PANEL_CONTENT);
        PageFactory.initElements(driver, tabLayoutPanelContentSection);
        return tabLayoutPanelContentSection;
    }
}
