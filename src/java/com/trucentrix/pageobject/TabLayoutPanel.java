package com.trucentrix.pageobject;

import org.openqa.selenium.*;
import org.testng.Assert;
import org.openqa.selenium.support.ui.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * A class where is the operations that can be done in a TabLayoutPanel
 * object in the TRUcentrix application
 * @author rcadima
 */
public class TabLayoutPanel {
    WebDriver driver;
    WebDriverWait wait;
    WebElement tabLayout;
    public TabLayoutPanel(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver,90);
    }

    /**
    * Click a tab from a TabLayoutPanel element of TRUcentrix application
    * @param tabLabel Introduce the label of the tab  to be clicked.
    * @throws Exception
    */
    public void clickTabLayoutTab(String tabLabel) throws Exception{
        int flag = 0;
        tabLayout = getTabLayoutPanel();
        if(tabLabel.contentEquals("Contents")|tabLabel.contentEquals("Permissions")|tabLabel.contentEquals("Discussions")|tabLabel.contentEquals("Subscriptions")|tabLabel.contentEquals("Properties")){
            tabLayout = tabLayout.findElement(By.xpath(".//div[@class='gwt-TabLayoutPanel']"));
        }
        Assert.assertNotNull(tabLayout, "Error: Tab layout panel not found");
        List<WebElement> tabs = tabLayout.findElements(By.xpath(".//div[@class='gwt-TabLayoutPanelTabInner']"));
        for(WebElement tab : tabs){
            if(tab.getText().contains(tabLabel)){
                tab.click();
            if(tabLabel.contentEquals("Contents")|tabLabel.contentEquals("Permissions")|tabLabel.contentEquals("Discussions")|tabLabel.contentEquals("Subscriptions")|tabLabel.contentEquals("Properties")){
                Thread.sleep(4000);
            }
                flag = 1;
                break;
            }
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='gwt-TabLayoutPanelContentContainer']")));
        if(flag==0) Assert.fail("Error: "+ tabLabel + " tab not founded");
    }

    /**
     * Click a button from a TabLayoutPanel element of TRUcentrix application
     * @param buttonLabel Introduce the label of the button to be clicked.
     * @throws Exception
     */
    public void clickTabLayoutButton(String buttonLabel) throws Exception{
        int flag = 0;
        tabLayout = getTabLayoutPanel();
        Assert.assertNotNull(tabLayout, "Error: Tab layout panel not found");
        List<WebElement> buttons = tabLayout.findElements(By.xpath(".//button"));
        for(WebElement button : buttons){
            if(button.getText().contains(buttonLabel)){
                button.click();
                flag = 1;
                break;
            }
        }
        if(flag == 0) Assert.fail("Error: "+ buttonLabel + " button not founded");
    }

    /**
     * Click a radio button from a TabLayoutPanel element of TRUcentrix application
     * @param radioButtonLabel Introduce the label of the radio button to be clicked.
     * @throws Exception
     */
    public void clickTabLayoutRadioButton(String radioButtonLabel) throws Exception{
        int flag = 0;
        tabLayout = getTabLayoutPanel();
        Assert.assertNotNull(tabLayout, "Error: Tab layout panel not found");
        List <WebElement> radioButtons = tabLayout.findElements(By.xpath(".//span[@class='gwt-RadioButton']"));
        for(WebElement radioButton : radioButtons){
            if(radioButton.getText().contains(radioButtonLabel)){
                radioButton.click();
                flag = 1;
                break;
            }
        }
        if(flag == 0) Assert.fail("Error: "+ radioButtonLabel + " button not founded");
    }
    /**
     * Set/Introduce a value in a text field with the specified label
     * @param textLabel Introduce the label of the text field concerned
     * @param text The text that you want to introduce in the text field
     * @throws Exception
     */
    public void setTabLayoutText(String textLabel, String text) throws Exception{
        int flag = 0;
        List<WebElement> textFields = new ArrayList<WebElement>();
        tabLayout = getTabLayoutPanel();
        Assert.assertNotNull(tabLayout, "Error: Tab layout panel not found");
        textFields = tabLayout.findElements(By.xpath(".//input[@class='gwt-TextBox']"));
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        if(tabLayout.findElements(By.xpath(".//input[@class='gwt-PasswordTextBox']")).size()>0){
            List<WebElement> aux = tabLayout.findElements(By.xpath(".//input[@class='gwt-PasswordTextBox']"));
            textFields.addAll(aux);
        }

        if(tabLayout.findElements(By.xpath(".//input[@class='gwt-DateBox']")).size()>0){
            List<WebElement> aux = tabLayout.findElements(By.xpath(".//input[@class='gwt-DateBox']"));
            textFields.addAll(aux);
        }

        if(tabLayout.findElements(By.xpath(".//input[@class='gwt-SuggestBox']")).size()>0){
            List<WebElement> aux = tabLayout.findElements(By.xpath(".//input[@class='gwt-SuggestBox']"));
            textFields.addAll(aux);
        }
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        if(textLabel.contentEquals("Type user's first or last name")|textLabel.contentEquals("Contact *")){
            tabLayout.findElement(By.xpath(".//input[@class='gwt-SuggestBox']")).sendKeys(text);
            flag = 1;
        }else{
            for(WebElement textField : textFields){
                WebElement findLabel = textField;
                findLabel=findLabel.findElement(By.xpath(".."));
                findLabel=findLabel.findElement(By.xpath("./preceding-sibling::td[1]"));
                if(findLabel.getText().contentEquals(textLabel)){
                    textField.clear();
                    textField.sendKeys(text);
                    flag = 1;
                    break;
                }
            }
        }
        if(flag==0) Assert.fail("Error: "+ textLabel + " text field not founded");
    }

    /**
     * Get all links displayed in a TRUcentrix TabLayout panel and click the one that
     * match with the introduced label
     * @param linkLabel The label of the link to be clicked.
     * @throws Exception
     */
    public void clickTabLayoutLink (String linkLabel) throws Exception{
        int flag=0;
        List<WebElement> links = new ArrayList<WebElement>();
        List<WebElement> elements = new ArrayList<WebElement>();
        tabLayout = getTabLayoutPanel();
        Assert.assertNotNull(tabLayout, "Error: Tab layout panel not found");
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        if(tabLayout.findElements(By.xpath(".//div[@class='gwt-Hyperlink']")).size()>0){
            elements = tabLayout.findElements(By.xpath(".//div[@class='gwt-Hyperlink']"));
            links.addAll(elements);
        }

        if(tabLayout.findElements(By.xpath(".//div[@class='hyperlink_text']")).size()>0){
            elements = tabLayout.findElements(By.xpath(".//div[@class='hyperlink_text']"));
            links.addAll(elements);
        }

        if(tabLayout.findElements(By.xpath(".//div[@class='gwt-Label']")).size()>0){
            elements = tabLayout.findElements(By.xpath(".//div[@class='gwt-Label']"));
            links.addAll(elements);
        }

        if(tabLayout.findElements(By.xpath(".//div[@class='headerAction']")).size()>0){
            elements = tabLayout.findElements(By.xpath(".//div[@class='headerAction']"));
            links.addAll(elements);
        }

        if(tabLayout.findElements(By.xpath(".//div[@class='doc_link_label']")).size()>0){
            elements = tabLayout.findElements(By.xpath(".//div[@class='doc_link_label']"));
            links.addAll(elements);
        }

        for(WebElement link : links){
            if(link.getText().contentEquals(linkLabel)){
                link.click();
                flag=1;
                break;
            }
        }

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        if(flag==0) Assert.fail("Error: No links with "+ linkLabel +" label detected");
    }

    /**
     * Get all check boxes displayed in a TRUcentrix TabLayout panel and check the one that
     * match with the introduced label
     * @param checkboxLabel The label of the checkbox to be clicked.
     * @throws Exception
     */
    public void clickTabLayoutCheckbox(String checkboxLabel) throws Exception{
        int flag = 0;
        List<WebElement> checkboxes = new ArrayList<WebElement>();
        tabLayout = getTabLayoutPanel();
        Assert.assertNotNull(tabLayout, "Error: Tab layout panel not found");
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        if(tabLayout.findElements(By.xpath(".//span")).size()>0){
            checkboxes = tabLayout.findElements(By.xpath(".//span"));
        }

        for(WebElement checkbox:checkboxes){
            if(checkbox.getText().contains(checkboxLabel)){
                if(checkbox.findElement(By.xpath(".//input")).isSelected()){
                    System.out.println(checkboxLabel + " is already checked");
                    flag = 1;
                    break;
                }else{
                    checkbox.findElement(By.xpath(".//input")).click();
                    flag = 1;
                    break;
                }
            }else{
                WebElement findLabel = checkbox;
                findLabel = findLabel.findElement(By.xpath(".."));
                if(checkbox.findElements(By.xpath("./preceding-sibling::td[1]")).size()>0){
                    findLabel = findLabel.findElement(By.xpath("./preceding-sibling::td[1]"));
                    if(findLabel.getText().contains(checkboxLabel)){
                        if(checkbox.findElement(By.xpath(".//input")).isSelected()){
                            System.out.println(checkboxLabel + " is already checked");
                            flag=1;
                            break;
                        }else{
                            checkbox.findElement(By.xpath(".//input")).click();
                            flag=1;
                            break;
                        }
                    }
                }
            }
        }
        if(flag==0){
            checkboxes = tabLayout.findElements(By.xpath(".//*[@class='form-task-headTable']//span[@class='gwt-CheckBox']"));
            for(WebElement checkbox:checkboxes){
                WebElement findLabel = checkbox.findElement(By.xpath(".."));
                findLabel = findLabel.findElement(By.xpath(".."));
                    if(findLabel.getText().contains(checkboxLabel)){
                        if(checkbox.findElement(By.xpath(".//input")).isSelected()){
                            System.out.println(checkboxLabel + " is already checked");
                            flag=1;
                            break;
                        }else{
                            checkbox.findElement(By.xpath(".//input")).click();
                            flag=1;
                            break;
                        }
                    }
            }
        }
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        if(flag==0) Assert.fail("Error: No checkboxes with "+ checkboxLabel +" label detected");
    }

    /**
     * Get all check boxes displayed in a TRUcentrix TabLayout panel and uncheck the one that
     * match with the introduced label
     * @param checkboxLabel The label of the checkbox to be clicked.
     * @throws Exception
     */
    public void clickTabLayoutUncheckbox(String checkboxLabel) throws Exception{
        int flag = 0;
        List<WebElement> checkboxes = new ArrayList<WebElement>();
        tabLayout = getTabLayoutPanel();
        Assert.assertNotNull(tabLayout, "Error: Tab layout panel not found");
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        if(tabLayout.findElements(By.xpath(".//span")).size()>0){
            checkboxes = tabLayout.findElements(By.xpath(".//span"));
        }

        for(WebElement checkbox:checkboxes){
            if(checkbox.getText().contains(checkboxLabel)){
                if(checkbox.findElement(By.xpath(".//input")).isSelected()){
                    checkbox.findElement(By.xpath(".//input")).click();
                    flag = 1;
                    break;
                }else{
                    System.out.println(checkboxLabel + " is already unchecked");
                    flag = 1;
                    break;
                }
            }else{
                WebElement findLabel = checkbox;
                findLabel = findLabel.findElement(By.xpath(".."));
                if(checkbox.findElements(By.xpath("./preceding-sibling::td[1]")).size()>0){
                    findLabel = findLabel.findElement(By.xpath("./preceding-sibling::td[1]"));
                    if(findLabel.getText().contains(checkboxLabel)){
                        if(checkbox.findElement(By.xpath(".//input")).isSelected()){
                            checkbox.findElement(By.xpath(".//input")).click();
                            flag=1;
                            break;
                        }else{
                            System.out.println(checkboxLabel + " is already unchecked");
                            flag=1;
                            break;
                        }
                    }
                }
            }
        }

        if(flag==0){
            checkboxes = tabLayout.findElements(By.xpath(".//*[@class='form-task-headTable']//span[@class='gwt-CheckBox']"));
            for(WebElement checkbox:checkboxes){
                WebElement findLabel = checkbox.findElement(By.xpath(".."));
                findLabel = findLabel.findElement(By.xpath(".."));
                    if(findLabel.getText().contains(checkboxLabel)){
                        if(checkbox.findElement(By.xpath(".//input")).isSelected()){
                            checkbox.findElement(By.xpath(".//input")).click();
                            flag=1;
                            break;
                        }else{
                            System.out.println(checkboxLabel + " is already unchecked");
                            flag=1;
                            break;
                        }
                    }
            }
        }
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        if(flag==0) Assert.fail("Error: No checkboxes with "+ checkboxLabel +" label detected");
    }

    /**
     * Get all images displayed in a TRUcentrix TabLayout panel and click the one that
     * match with the introduced label
     * @param imageLabel The label of the image to be clicked.
     * @throws Exception
     */
    public void clickTabLayoutImage(String imageLabel) throws Exception{
        int flag = 0;
        List<WebElement> images = new ArrayList<WebElement>();
        List<WebElement> imagesWithoutText = new ArrayList<WebElement>();
        List<WebElement> elements = new ArrayList<WebElement>();
        tabLayout = getTabLayoutPanel();
        Assert.assertNotNull(tabLayout, "Error: Tab layout panel not found");
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        if(tabLayout.findElements(By.xpath(".//*[@class='explorerToolbar']")).size()>0){
            elements = tabLayout.findElements(By.xpath(".//*[@class='explorerToolbar']//div[@class='html-face']"));
            images.addAll(elements);
        }

        if(tabLayout.findElements(By.xpath(".//*[@class='explorerToolbar']//div/img")).size()>0){
            elements = tabLayout.findElements(By.xpath(".//*[@class='explorerToolbar']//div/img"));
            imagesWithoutText.addAll(elements);
        }

        for(WebElement image:images){
            if(image.getText().contentEquals(imageLabel)){
                image.click();
                flag = 1;
                break;
            }
        }
        if(flag==0){
            for(WebElement image:imagesWithoutText){
                WebElement findTitle = image.findElement(By.xpath(".."));
                if(findTitle.getAttribute("title").isEmpty()) findTitle = findTitle.findElement(By.xpath(".."));
                if(findTitle.getAttribute("title").contentEquals(imageLabel)){
                    image.click();
                    flag = 1;
                    break;
                }
            }
        }
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        if(flag==0) Assert.fail("Error:"+ imageLabel +" image not founded");
    }

    /**
     * Get all list objects of the TabLayout Panel page that is being displayed, then
     * it select an item that is contained in the dropdown or list.
     * @param label The label of the dropdown or list that contain the item.
     * @param item The text of the item that you want to click.
     * @throws Exception
     */
    public void selectTabLayoutListItem(String label, String item) throws Exception{
        int flag = 0;
        tabLayout = getTabLayoutPanel();
        Assert.assertNotNull(tabLayout, "Error: Tab layout panel not found");
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        if(tabLayout.findElements(By.xpath(".//select")).size()>0){
            List<WebElement> lists = tabLayout.findElements(By.xpath(".//select"));
            for(WebElement list : lists){
                WebElement findLabel = list;
                findLabel=findLabel.findElement(By.xpath(".."));
                if(findLabel.findElements(By.xpath("./preceding-sibling::td[1]")).size()>0){
                    findLabel=findLabel.findElement(By.xpath("./preceding-sibling::td[1]"));
                    if(findLabel.getText().contains(label)){
                        new Select(list).selectByVisibleText(item);
                        flag=1;
                        break;
                    }
                }
                if(flag==0){
                    findLabel=findLabel.findElement(By.xpath(".."));
                    if(findLabel.findElements(By.xpath("./preceding-sibling::tr[1]")).size()>0){
                        findLabel=findLabel.findElement(By.xpath("./preceding-sibling::tr[1]"));
                        if(findLabel.getText().contains(label)){
                            new Select(list).selectByVisibleText(item);
                            flag=1;
                            break;
                        }
                    }
                }
            }
        }
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        if(flag==0) Assert.fail("Error:"+ label +" dropdown/list not founded");
    }

    /**
     * Set/Introduce a value in a text area with the specified label
     * @param textLabel Introduce the label of the text field concerned
     * @param text The text that you want to introduce in the text area
     * @throws Exception
     */
    public void setTabLayoutTextArea(String textLabel, String text) throws Exception{
        int flag = 0;
        int times = 0;
        List<WebElement> textAreas = new ArrayList<WebElement>();
        tabLayout = getTabLayoutPanel();
        Assert.assertNotNull(tabLayout, "Error: Tab layout panel not found");
        textAreas = tabLayout.findElements(By.xpath(".//textarea[@class='gwt-TextArea']"));
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        for(WebElement textArea : textAreas){
            WebElement findLabel = textArea;
            do{
                findLabel=findLabel.findElement(By.xpath(".."));
                if(findLabel.getTagName().contains("td")){
                    if(findLabel.findElements(By.xpath("./preceding-sibling::td[1]")).size()>0){
                        findLabel=findLabel.findElement(By.xpath("./preceding-sibling::td[1]"));
                        break;
                    }else times++;
                }
            }while(times < 2);
            if(findLabel.getText().contentEquals(textLabel)){
                textArea.clear();
                textArea.sendKeys(text);
                flag = 1;
                break;
            }
        }
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        if(flag==0) Assert.fail("Error:"+ textLabel +" text area not founded");
    }

    /**
    * Get the TabLayoutPanel element that is being displayed in the TRUcentrix application
    * @return A web element that contain the tab layout panel element.
    * @throws Exception
    */
    private WebElement getTabLayoutPanel() throws Exception{
        tabLayout = null;
        if(driver.findElements(By.xpath("//div[@class='gwt-TabLayoutPanel']")).size()>0)
            tabLayout = driver.findElement(By.xpath("//div[@class='gwt-TabLayoutPanel']"));
        else Assert.fail("Error: There is no tab layout panel int he page");

        return tabLayout;
    }
}
