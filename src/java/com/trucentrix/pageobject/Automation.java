package com.trucentrix.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.util.concurrent.TimeUnit;

public class Automation {

    public Login login;
    public Header header;
    public SubHeader subHeader;
    public DialogBox dialog;
    public PopupPanel popupPanel;
    public TabLayoutPanel tabLayout;
    public Table table;
    public ProjectsTable project;
    public AssignmentsTable assignments;
    public CompanyTable company;
    public DataSourcesTable datSources;
    public FavoriteItemsTable favorites;
    public FormsTable forms;
    public GlobalRolesTable globalRoles;
    public HomePagesTable homePages;
    public ObjectDefinitionsTable objectDefinitions;
    public RecentItemsTable recentItems;
    public ServerLogsTable serverLogs;
    public SubscriptionsTable subscriptions;
    public UIActionsTable uiActions;
    private WebDriver driver;

/**
 * Setup a FireFox instance where the selenium driver will start
 * the automation.
 *
 * @param baseUrl The base URL (e.g. https://www.google.com/) page where
 * the automation will take place.
 * @return A WebDriver object.
 * @throws Exception
 */
    public WebDriver setUpFirefox(String baseUrl) throws Exception{
        if(driver == null){
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        firefoxProfile.setPreference("browser.download.folderList",2);
        firefoxProfile.setPreference("browser.download.manager.showWhenStarting",false);
        firefoxProfile.setPreference("browser.download.dir","D:\\Automation_downloads");
        firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk","text/xml");
        driver = new FirefoxDriver(firefoxProfile);
        baseUrl = baseUrl + "nxps?action=defaultPage";
        driver.get(baseUrl);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        return driver;
        } else return driver;
    }

    public void setUpChrome(String url) throws Exception {
    }

    public void setUpInternetExplorer(String url) throws Exception {
    }

/**
 * Stops the web driver.
 */
    public void tearDown(){
        if(driver != null) {
            driver.close();
            driver.quit();
            driver = null;
        }
    }
    public String getTitle(){
        String title = driver.getTitle();
        return title;
    }

    public void getVariables(WebDriver driver){
        login = new Login(driver);
        header = new Header(driver);
        subHeader = new SubHeader(driver);
        dialog = new DialogBox(driver);
        popupPanel = new PopupPanel(driver);
        tabLayout = new TabLayoutPanel(driver);
        table = new Table(driver);
        project = new ProjectsTable(driver);
        assignments = new AssignmentsTable(driver);
        company = new CompanyTable(driver);
        datSources = new DataSourcesTable(driver);
        favorites = new FavoriteItemsTable(driver);
        forms = new FormsTable(driver);
        globalRoles = new GlobalRolesTable(driver);
        homePages = new HomePagesTable(driver);
        objectDefinitions = new ObjectDefinitionsTable(driver);
        recentItems = new RecentItemsTable(driver);
        serverLogs = new ServerLogsTable(driver);
        subscriptions = new SubscriptionsTable(driver);
        uiActions = new UIActionsTable(driver);
    }
}