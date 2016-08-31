package com.trucentrix.pageobject;

import java.util.concurrent.TimeUnit;
import java.util.*;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.*;
import org.junit.Assert;

/**This class contents the methods for handling the elements inside a Pop-Up in a TRUcentrix page.
 *
 * @author rcadima
 */
public class PopupPanel {
    WebDriver driver;
    public PopupPanel(WebDriver driver){
        this.driver = driver;
    }

     /**This method gets the dialog by its xpath if there is an existing Pop-Up in the page.
     *
     * @return
     * @throws Exception
     */
    public WebElement getPopupPanel(){
        WebElement panel = null;
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        if(driver.findElements(By.xpath("//div[@class='gwt-PopupPanel']")).size()>0){
            panel = driver.findElement(By.xpath("//div[@class='gwt-PopupPanel']"));
        }else{
            Assert.fail("There is no popup panel present");
        }
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        return panel;
    }

    /**This method clicks on a button found by its "buttonName" inside a Pop-Up in the page.
     *
     * @param panel
     * @param buttonName
     * @throws Exception
     */
    public void clickPanelButton(WebElement panel, String buttonName) throws Exception{
        if(panel == null){
            Assert.fail("Error: No popup panel founded");
        }else{
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            if(panel.findElements(By.xpath(".//button")).size()>0){
                List<WebElement> buttons = panel.findElements(By.xpath(".//button"));
                for (WebElement button : buttons){
                    if(button.getText().contains(buttonName)){
                        button.click();
                        break;
                    }
                }
            }
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        }
    }

    /**This method click on a tab inside a panel and found by its "tabName"
     *
     * @param panel
     * @param tabName
     * @throws Exception
     */
    public void clickPanelTab(WebElement panel, String tabName) throws Exception{
        if(panel == null){
            Assert.fail("Error: No popup panel founded");
        }else{
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            if(panel.findElements(By.xpath(".//td[@class='tabMiddleCenter']")).size()>0){
                List<WebElement> tabs = panel.findElements(By.xpath(".//td[@class='tabMiddleCenter']"));
                for(WebElement tab:tabs){
                    if(tab.getText().contains(tabName)){
                        tab.click();
                        break;
                    }
                }
            }
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        }
    }

    /**This method clicks on a specific link found by its linkName inside a Pop-Up in the page.
     *
     * @param panel
     * @param linkName
     * @throws Exception
     */
    public void clickPanelLink(WebElement panel, String linkName) throws Exception{
        List<WebElement> links = new ArrayList<WebElement>();
        if(panel == null){
            Assert.fail("Error: No popup panel founded");
        }else{
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            if(panel.findElements(By.xpath(".//div[@class='td_text_hover']")).size()>0){
                List<WebElement> linkElements = panel.findElements(By.xpath(".//div[@class='td_text_hover']"));
                for(WebElement element:linkElements){
                    links.add(element);
                }
            }
            if(panel.findElements(By.xpath(".//div[@class='hyperlink_text']")).size()>0){
                List<WebElement> linkElements = panel.findElements(By.xpath(".//div[@class='hyperlink_text']"));
                for(WebElement element:linkElements){
                    links.add(element);
                }
            }
            for(WebElement link:links){
                if(link.getText().contains(linkName)){
                    link.click();
                }
            }
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        }
    }

    /**This method clicks on a specific image found by its imageName inside a Pop-Up in the page.
     *
     * @param panel
     * @param imageName
     * @throws Exception
     */
    public void clickPanelImage(WebElement panel, String imageName) throws Exception{
        int flag = 0;
        if(panel == null){
            Assert.fail("Error: No popup panel founded");
        }else{
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            if(panel.findElements(By.xpath(".//td/img")).size()>0){
                List<WebElement> imageElements = panel.findElements(By.xpath(".//td/img"));
                for(WebElement element:imageElements){
                    if(element.getAttribute("title").contains(imageName)){
                        flag=1;
                        element.click();
                        break;
                    }
                }
            }

            if(panel.findElements(By.xpath(".//div[@class='command_button']/img")).size()>0 && flag == 0){
                List<WebElement> imageElements = panel.findElements(By.xpath(".//div[@class='command_button']"));
                for(WebElement element:imageElements){
                    if(element.getAttribute("title").contains(imageName)){
                        element.click();
                        break;
                    }
                }
            }
            if(imageName.contains("Settings")){
                panel.findElement(By.xpath(".//div[@class='td_text_hover_hand']")).click();
            }
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        }
    }

    /** This method checks a check-box found by "checkboxLabel" inside a Pop-up in the page.
     *
     * @param panel
     * @param checkboxLabel
     * @throws Exception
     */
    public void clickPanelCheckbox(WebElement panel, String checkboxLabel) throws Exception{
        if(panel==null){
            Assert.fail("Error: No popup panel founded");
        }else{
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            if(panel.findElements(By.xpath(".//span")).size()>0){
                List<WebElement> checkboxes = panel.findElements(By.xpath(".//span"));
                for(WebElement checkbox:checkboxes){
                    if(checkbox.getText().contains(checkboxLabel)){
                        if(checkbox.findElement(By.xpath(".//input")).isSelected()){
                            System.out.println(checkboxLabel + " is already checked");
                            break;
                        }else{
                            checkbox.findElement(By.xpath(".//input")).click();
                            break;
                        }
                    }else{
                        if(panel.findElements(By.xpath(".//tr/td[10]/span")).size()>0){
                            WebElement findLabel = checkbox;
                            findLabel = findLabel.findElement(By.xpath(".."));
                            findLabel = findLabel.findElement(By.xpath("./following-sibling::td[1]"));
                            if(findLabel.getText().contains(checkboxLabel)){
                                if(checkbox.findElement(By.xpath(".//input")).isSelected()){
                                    System.out.println(checkboxLabel + " is already checked");
                                    break;
                                }else{
                                    checkbox.findElement(By.xpath(".//input")).click();
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        }
    }

    /**This method unchecks a check-box found by "checkboxLabel" inside a Pop-Up in the page.
     *
     * @param panel
     * @param checkboxLabel
     * @throws Exception
     */
    public void clickPanelUncheckbox(WebElement panel, String checkboxLabel) throws Exception{
        if(panel==null){
            Assert.fail("Error: No popup panel founded");
        }else{
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            if(panel.findElements(By.xpath(".//span")).size()>0){
                List<WebElement> checkboxes = panel.findElements(By.xpath(".//span"));
                for(WebElement checkbox:checkboxes){
                    if(checkbox.getText().contains(checkboxLabel)){
                        if(checkbox.findElement(By.xpath(".//input")).isSelected()){
                            checkbox.findElement(By.xpath(".//input")).click();
                            break;
                        }else{
                            System.out.println(checkboxLabel + " is already unchecked");
                            break;
                        }
                    }else{
                        if(panel.findElements(By.xpath(".//tr/td[10]/span")).size()>0){
                            WebElement findLabel = checkbox;
                            findLabel = findLabel.findElement(By.xpath(".."));
                            findLabel = findLabel.findElement(By.xpath("./following-sibling::td[1]"));
                            if(findLabel.getText().contains(checkboxLabel)){
                                if(checkbox.findElement(By.xpath(".//input")).isSelected()){
                                    checkbox.findElement(By.xpath(".//input")).click();
                                    break;
                                }else{
                                    System.out.println(checkboxLabel + " is already unchecked");
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        }
    }
    
/**This method sets a PanelText found by "textLabel" and text inside a Pop-Up in the page when the label is located at the left side.
 *
 * @param panel
 * @param textLabel
 * @param text
 * @throws Exception
 */
    public void setPanelTextLeftLabel(WebElement panel, String textLabel, String text) throws Exception{
        if(panel == null){
            Assert.fail("Error: No popup panel founded");
        }else{
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            if(panel.findElements(By.xpath(".//input[@class='gwt-TextBox']")).size()>0){
                List<WebElement> textFields = panel.findElements(By.xpath(".//input[@class='gwt-TextBox']"));
                for(WebElement textField:textFields){
                    WebElement findLabel = textField;
                    findLabel=findLabel.findElement(By.xpath(".."));
                    findLabel=findLabel.findElement(By.xpath("./preceding-sibling::td[1]"));
                    if(findLabel.getText().contains(textLabel)){
                        textField.clear();
                        textField.sendKeys(text);
                        break;
                    }
                }
            }
            if(panel.findElements(By.xpath(".//input[@class='td_big_label_text']")).size()>0){
                List<WebElement> textFields = panel.findElements(By.xpath(".//input[@class='td_big_label_text']"));
                for(WebElement textField:textFields){
                    WebElement findLabel = textField;
                    findLabel=findLabel.findElement(By.xpath(".."));
                    findLabel=findLabel.findElement(By.xpath("./preceding-sibling::td[1]"));
                    if(findLabel.getText().contains(textLabel)){
                        textField.clear();
                        textField.sendKeys(text);
                        break;
                    }
                }
            }
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        }
    }

    /**This method sets a PanelTextArea found by "textLabel" and text inside a Pop-Up in the page when the label is located at the left side.
     *
     * @param panel
     * @param textLabel
     * @param text
     * @throws Exception
     */
    public void setPanelTextAreaLeftLabel(WebElement panel, String textLabel, String text) throws Exception{
        if(panel == null){
            Assert.fail("Error: No popup panel founded");
        }else{
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            if(panel.findElements(By.xpath(".//textarea")).size()>0){
                List<WebElement> textAreas = panel.findElements(By.xpath(".//textarea"));
                for(WebElement textArea:textAreas){
                    WebElement findLabel = textArea;
                    findLabel=findLabel.findElement(By.xpath(".."));
                    findLabel=findLabel.findElement(By.xpath("./preceding-sibling::td[1]"));
                    if(findLabel.getText().contains(textLabel)){
                        textArea.clear();
                        textArea.sendKeys(text);
                        break;
                    }
                }
            }
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        }
    }

    /**This method select an item from an Item List.
     *
     * @param panel
     * @param dropdownLabel
     * @param item
     * @throws Exception
     */
    public void selectPanelListItem(WebElement panel, String dropdownLabel, String item) throws Exception{
        int times;
        int flag=0;
        if(panel==null){
            Assert.fail("Error: No popup panel founded");
        }else{
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            if(panel.findElements(By.xpath(".//select")).size()>0){
                List<WebElement> lists = panel.findElements(By.xpath(".//select"));
                for(WebElement list : lists){
                    times=0;
                    WebElement findLabel = list;
                    findLabel=findLabel.findElement(By.xpath(".."));
                    do{
                        if(findLabel.findElements(By.xpath("./preceding-sibling::td[1]")).size()>0){
                            findLabel=findLabel.findElement(By.xpath("./preceding-sibling::td[1]"));
                            if(findLabel.getText().contains(dropdownLabel)){
                                new Select(list).selectByVisibleText(item);
                                flag=1;
                                break;
                            }else{times++;}
                        }else{times++;}
                    }while(times<2);
                    if(flag==1){break;}
                }
            }
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        }
    }

        public void clickPanelItem(WebElement panel, String itemLabel){
        if(panel==null){
            Assert.fail("No thin panel selected");
        }else{
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            if(panel.findElements(By.xpath(".//td[@class='gwt-MenuItem']")).size()>0){
                List<WebElement> items = panel.findElements(By.xpath(".//td[@class='gwt-MenuItem']"));
                for(WebElement item : items){
                    if(item.getText().contains(itemLabel)){
                        item.click();
                        break;
                    }
                }
            }
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        }
    }
}