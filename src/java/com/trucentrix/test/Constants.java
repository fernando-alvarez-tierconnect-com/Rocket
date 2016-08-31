package com.trucentrix.test;

/**
 * Created by IntelliJ IDEA.
 * User: falvarez
 * Date: 4/25/14
 * Time: 11:00 AM
 * To change this template use File | Settings | File Templates.
 */
public final class Constants {
    //Top Menu
    public static final String TOP_MENU_SECTION = "//table[@class='topMenuMain']/tbody/tr/td[3]/table/tbody/tr/td[2]/div";
    public static final String TOP_MENU_SECTION_IE = "/HTML/BODY/DIV[2]/DIV[2]/DIV/DIV[2]/TABLE/TBODY/TR/TD[3]/TABLE/TBODY/TR/TD[2]/DIV";
    public static final String VIEW = "//div[@class='topMenuBar topMenuBar-horizontal']/table/tbody/tr/td";
    public static final String MYFORMS_OPTION = "//td[@id='gwt-uid-15']";

    //Administration Page
    public static final String ICON_COMPANIES = "//table/tbody/tr/td/table/tbody/tr[2]/td/div";

    //Header Page
//  public static final String HEADER_PAGE_SECTION_CONTAINER = "//*/div[contains(@id,'GWTCreativePanel')]";
    public static final String HEADER_ACTION_ELEMENT = "//div[@class='headerAction']";
    public static final String HEADER_TITLE_ELEMENT = "//div[@class='headerTitle headerTitle-link']";
    public static final String HEADER_SUBTITLE_ELEMENT = "//div[@class='headerSubTitleLink']";
    public static final String TABLE_CONTAIN_HEADER = "//descendant::table[@class='header']";
    //headerTable.findElements(By.xpath("//table[@class='header']"+"//div[@class='headerAction']"))
    public static final String TABLE_CONTAIN_HEADER_ = "//table[@class='header']";


    //CompanyListPage
    public static final String SORTABLE_HEADER_TABLE_OLD = "//*/div[contains(@class,'sortableHeaderText sortableHeaderText-selected')]";
    public static final String SORTABLE_HEADER_TABLE = "//table[@class='sortableTable']";
    public static final String FIRST_ROW = "//div[@class='hyperlink_text']";
    public static final String DELETE_ICON = "//img[@class='command_button']";
    public static final String FIRST_ROW_XPATH = "//tr[@class='table_cell_alt']";

    //Company form Component
    public static final String DIALOGBOX_COMPANY_XPATH = "//div[@class='gwt-DialogBox']";
    public static final String DIALOGBOX_WARNING = "//div[@class='gwt-DialogBox']";
    public static final String COMPANY_FORM_COMPONENT_TITLE = "//div[@class='Caption']";
    public static final String TEXT_LABEL = "Company Name*:";
    public static final String TEXT_BOX_ELEMENT_ = ".//input[@class='gwt-TextBox']";
    public static final String BUTTON_ELEMENT_ = ".//button";
    public static final String BUTTON_LABEL = "Save";

    //Users Component
    public static final String PASSWORD_TEXTBOX = "//input[@class='gwt-PasswordTextBox']";

    //UsersListPage
    public static final String USERS_TAB_SECTION = "//div[@class='gwt-TabLayoutPanelContent']";
    //public static final String USERS_FIRST_ROW = "//div[@class='hyperlink_text']";
    public static final String USERS_TABLE = "//tr[@class='table_cell_alt']";


    //TabLayoutPanelTabsSection
    public static final String TABLAYOUT_PANEL_TABS = "//div[@class='gwt-TabLayoutPanelTabs']";
    public static final String TABLAYOUT_PANEL_TAB_INNER = "//div[@class='gwt-TabLayoutPanelTabInner']";
    public static final String TABLAYOUT_LABEL_COMPANY_INFO = "//div[@class='gwt-Label' and contains(text(),'Company Info')]";
    public static final String TABLAYOUT_LABEL_DEPARTMENTS = "//div[@class='gwt-Label' and contains(text(),'Departments')]";
    public static final String TABLAYOUT_LABEL_ADD_DEPARTMENT = "//div[@class='headerAction' and contains(text(),'Add Department')]";
    public static final String TABLAYOUT_LABEL_USERS = "//div[@class='gwt-Label' and contains(text(),'Users')]";
    public static final String TABLAYOUT_PANEL_CONTENT = "//div[@class='gwt-TabLayoutPanelContent']";
    public static final String TEXT_BOX_ELEMENT = "//input[@class='gwt-TextBox']";
    public static final String BUTTON_ELEMENT = "//button";
    public static final String USERS_BUTTON_ELEMENT = "//button[@type='button' and contains(text(),'Search')]";

    //Dialog Box Component
//    public static final String DIALOGBOX_COMPANY_XPATH = "//div[@class='gwt-DialogBox']";
    public static final String DIALOGBOX_BUTTON_OK = "//button[@class='gwt-Button' and contains(text(),'OK')]";

    //Dialog Select Users Component
    public static final String SELECT_USERS_COMPONENT = "//div[@class='Caption']";
    public static final String USER_SELECTED = "//option[contains(.,'Alvarez, Fernando (falvarez@coderoad.com)')]";
    public static final String LINK_ADD = "//div[contains(.,'Add')]";
    public static final String LINK_ADD_ALL = "//div[@class='gwt-Hyperlink' and contains(text(), 'Add All')]";
    public static final String BUTTON_OK = "//button[contains(.,'OK')]";

    //Form List Page
    public static final String START_NEW_MFT = "//div[@class='hyperlink_text']";


    //Form MFT Page
    public static final String BUTTON_BROWSE = "//button[contains(.,'Browse')]";
    public static final String UPLOAD_LINK = "//div[@class='hyperlink_text' and contains(text(), 'Upload')]";




}
