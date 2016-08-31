package com.trucentrix.test.page;

import com.thoughtworks.selenium.Selenium;
import com.trucentrix.test.ApplicationPage;
import com.trucentrix.test.Constants;
import com.trucentrix.test.Section.TabLayoutPanelTabsSection;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by IntelliJ IDEA.
 * User: falvarez
 * Date: 5/28/14
 * Time: 11:45 AM
 * To change this template use File | Settings | File Templates.
 */
public class AdministrationCompanyPage extends ApplicationPage {

    @FindBy(how = How.XPATH, using = Constants.ICON_COMPANIES)
    private WebElement iconCompanies;

    public AdministrationCompanyPage(final Selenium selenium, final WebDriver driver) {
        super(selenium, driver, "Company Administration");
    }

    public TabLayoutPanelTabsSection getTabLayoutPanelSection(){
        final TabLayoutPanelTabsSection tabLayoutPanelTabsSection = new TabLayoutPanelTabsSection(selenium, driver, Constants.TABLAYOUT_PANEL_TABS);
        PageFactory.initElements(driver, tabLayoutPanelTabsSection);
        return tabLayoutPanelTabsSection;
    }
}
