package com.trucentrix.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**This class contents the methods for handling the elements inside a dialog in a TRUcentrix page.
 *
 * @author rcadima
 */
public class DialogBox {
    WebDriver driver;
    public DialogBox(WebDriver driver){
        this.driver = driver;
    }


    //MIGRATED      companyform.getcompanyForm()
    /**This method gets the dialog by its xpath if there is an existing dialog in the page.
     *
     * @return
     * @throws Exception
     */
    public WebElement getDialogBox() throws Exception{
        WebElement dialog = null;
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        if(driver.findElements(By.xpath("//div[@class='gwt-DialogBox']")).size()>0){
            dialog = driver.findElement(By.xpath("//div[@class='gwt-DialogBox']"));
        }else{
            Assert.fail("There is no dialog box present");
        }
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        return dialog;
    }
/**This method gets the dialog by its xpath and name if there is an existing dialog in the page.
 *
 * @param name
 * @return
 * @throws Exception
 */
    public WebElement getSpecificDialogBox(String name) throws Exception{
        WebElement dialog = null;
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        if(driver.findElements(By.xpath("//div[@class='gwt-DialogBox']")).size()>0){
            List<WebElement> dialogs =  driver.findElements(By.xpath("//div[@class='gwt-DialogBox']"));
            for(WebElement dialogElement : dialogs){
                if(dialogElement.findElement(By.xpath(".//div[@class='Caption']")).getText().contains(name)){
                    dialog = dialogElement;
                }
            }
        }else{
            Assert.fail("There is no dialog box present");
        }
        if(dialog == null){
            Assert.fail(name + " dialog box is not present");
        }
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        return dialog;
    }

    //MIGRATED CompanyFormComponent
    /**This method clicks on a button found by its "xpath" inside a dialog in the page.
     *
     * @param dialog
     * @param buttonName
     * @throws Exception
     */
    public void clickDialogButton(WebElement dialog, String buttonName) throws Exception{
        if(dialog==null){
            Assert.fail("No dialog box selected");
        }else{
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            if(dialog.findElements(By.xpath(".//button")).size()>0){
                List<WebElement> buttons = dialog.findElements(By.xpath(".//button"));
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

    /**This method clicks on a specific button found by its name and number of button inside a dialog in the page.
     *
     * @param dialog
     * @param buttonName
     * @param numberOfButton
     * @throws Exception
     */
    public void clickSpecificDialogButton(WebElement dialog, String buttonName, int numberOfButton) throws Exception{
        int number = 1;
        if(dialog==null){
            Assert.fail("No dialog box selected");
        }else{
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            if(dialog.findElements(By.xpath(".//button")).size()>0){
                List<WebElement> buttons = dialog.findElements(By.xpath(".//button"));
                for (WebElement button : buttons){
                    if(button.getText().contains(buttonName)){
                        if(number == numberOfButton){
                            button.click();
                            break;
                        }else number++;
                    }
                }
            }
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        }
    }

    /**This method clicks on a specific link found by its linkText inside a dialog in the page.
     *
     * @param dialog
     * @param linkText
     * @throws Exception
     */
    public void clickDialogLink(WebElement dialog, String linkText) throws Exception{
        List<WebElement> links = new ArrayList<WebElement>();
        List<WebElement> elements = new ArrayList<WebElement>();
        if(dialog==null){
            Assert.fail("No dialog box selected");
        }else{
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            if(dialog.findElements(By.xpath(".//div[@class='hyperlink_text']")).size()>0){
                elements = dialog.findElements(By.xpath(".//div[@class='hyperlink_text']"));
                links.addAll(elements);
            }
            
            if(dialog.findElements(By.xpath(".//div[@class='doc_link_label']")).size()>0){
                elements = dialog.findElements(By.xpath(".//div[@class='doc_link_label']"));
                links.addAll(elements);
            }
                for (WebElement link : links){
                    if(link.getText().contains(linkText)){
                        link.click();
                        break;
                    }
                }
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        }
    }

    public void clickSpecificDialogLink(WebElement dialog, String linkText,int numberOfLink) throws Exception{
        int number = 1;
        List<WebElement> links = new ArrayList<WebElement>();
        List<WebElement> elements = new ArrayList<WebElement>();
        if(dialog==null){
            Assert.fail("No dialog box selected");
        }else{
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            if(dialog.findElements(By.xpath(".//div[@class='hyperlink_text']")).size()>0){
                elements = dialog.findElements(By.xpath(".//div[@class='hyperlink_text']"));
                links.addAll(elements);
            }
            if(dialog.findElements(By.xpath(".//div[@class='doc_link_label']")).size()>0){
                elements = dialog.findElements(By.xpath(".//div[@class='doc_link_label']"));
                links.addAll(elements);
            }
                for (WebElement link : links){
                    if(link.getText().contentEquals(linkText)){
                        if(number == numberOfLink){
                            link.click();
                            break;
                        }else number++;
                    }
                }
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        }
    }

    /** This method checks a check-box found by "checkboxLabel" inside a dialog in the page.
     *
     * @param dialog
     * @param checkBoxLabel
     * @throws Exception
     */
    public void clickDialogCheckBox(WebElement dialog, String checkBoxLabel) throws Exception{
        if(dialog==null){
            Assert.fail("No dialog box selected");
        }else{
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            if(dialog.findElements(By.xpath(".//span[@class='gwt-CheckBox']")).size()>0){
                List<WebElement> checkboxes = dialog.findElements(By.xpath(".//span[@class='gwt-CheckBox']"));
                for(WebElement checkbox : checkboxes){
                    if(checkbox.getText().contains(checkBoxLabel)){
                        if(checkbox.findElement(By.xpath(".//input")).isSelected()){
                            System.out.println(checkBoxLabel + " is already checked");
                            break;
                        }else{
                            checkbox.findElement(By.xpath(".//input")).click();
                            break;
                        }
                    }else{
                        int flag = 0;
                        int times = 0;
                        WebElement findLabel = checkbox;
                        do{
                            findLabel = findLabel.findElement(By.xpath(".."));
                            if(findLabel.findElements(By.xpath(".//div")).size()>0){
                                flag=1;
                            }else{times++;}
                        }while(flag==0 && times <2);
                        if(findLabel.getText().contains(checkBoxLabel)){
                            if(checkbox.findElement(By.xpath(".//input")).isSelected()){
                                System.out.println(checkBoxLabel + " is already checked");
                                break;
                            }else{
                                checkbox.findElement(By.xpath(".//input")).click();
                                break;
                            }
                        }
                    }
                }
            }
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        }
    }

    /** This method unchecks a check-box found by "checkboxLabel" inside a dialog in the page.
     *
     * @param dialog
     * @param checkBoxLabel
     * @throws Exception
     */
    public void clickDialogUncheckBox(WebElement dialog, String checkBoxLabel) throws Exception{
        if(dialog==null){
            Assert.fail("No dialog box selected");
        }else{
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            if(dialog.findElements(By.xpath(".//span[@class='gwt-CheckBox']")).size()>0){
                List<WebElement> checkboxes = dialog.findElements(By.xpath(".//span[@class='gwt-CheckBox']"));
                for(WebElement checkbox : checkboxes){
                    if(checkbox.getText().contains(checkBoxLabel)){
                        if(checkbox.findElement(By.xpath(".//input")).isSelected()){
                            checkbox.findElement(By.xpath(".//input")).click();
                            break;
                        }else{
                            System.out.println(checkBoxLabel + " is already unchecked");
                            break;
                        }
                    }else{
                        int flag = 0;
                        int times = 0;
                        WebElement findLabel = checkbox;
                        do{
                            findLabel = findLabel.findElement(By.xpath(".."));
                            if(findLabel.findElements(By.xpath(".//div")).size()>0){
                                flag=1;
                            }else{times++;}
                        }while(flag==0 && times <2);
                        if(findLabel.getText().contains(checkBoxLabel)){
                            if(checkbox.findElement(By.xpath(".//input")).isSelected()){
                                checkbox.findElement(By.xpath(".//input")).click();
                                break;
                            }else{
                                System.out.println(checkBoxLabel + " is already unchecked");
                                break;
                            }
                        }
                    }
                }
            }
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        }
    }

    /** This method checks a check-box found by "checkboxLabel" when its label is located in the left side.
     *
     * @param dialog
     * @param checkBoxLabel
     * @throws Exception
     */
    public void clickDialogCheckBoxLeftLabel(WebElement dialog, String checkBoxLabel) throws Exception{
        int flag;
        int times;
        
        if(dialog==null){
            Assert.fail("No dialog box selected");
        }else{
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            if(dialog.findElements(By.xpath(".//span")).size()>0){
                List<WebElement> checkboxes = dialog.findElements(By.xpath(".//span"));
            for(WebElement checkbox : checkboxes){
                    flag = 0;
                    times = 0;
                    WebElement findLabel = checkbox;
                    do{
                        findLabel = findLabel.findElement(By.xpath(".."));
                        if(findLabel.findElements(By.xpath(".//div")).size()>0){
                            flag=1;
                        }else{times++;}
                    }while(flag==0 && times <2);
                    if(findLabel.getText().contains(checkBoxLabel)){
                        if(checkbox.findElement(By.xpath(".//input")).isSelected()){
                            System.out.println(checkBoxLabel + " is already checked");
                            break;
                        }else{
                            checkbox.findElement(By.xpath(".//input")).click();
                            break;
                        }
                    }
                }
            }
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        }
    }

    /**This method unchecks a check-box found by "checkboxLabel" when its label is located in the left side.
     *
     * @param dialog
     * @param checkBoxLabel
     * @throws Exception
     */
    public void clickDialogUncheckBoxLeftLabel(WebElement dialog, String checkBoxLabel) throws Exception{
        int flag;
        int times;
        
        if(dialog==null){
            Assert.fail("No dialog box selected");
        }else{
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            if(dialog.findElements(By.xpath(".//span")).size()>0){
                List<WebElement> checkboxes = dialog.findElements(By.xpath(".//span"));
                for(WebElement checkbox : checkboxes){
                    flag = 0;
                    times = 0;
                    WebElement findLabel = checkbox;
                    do{
                        findLabel = findLabel.findElement(By.xpath(".."));
                        if(findLabel.findElements(By.xpath(".//div")).size()>0){
                            flag=1;
                        }else{times++;}
                    }while(flag==0 && times <2);
                    if(findLabel.getText().contains(checkBoxLabel)){
                        if(checkbox.findElement(By.xpath(".//input")).isSelected()){
                            checkbox.findElement(By.xpath(".//input")).click();
                            break;
                        }else{
                            System.out.println(checkBoxLabel + " is already unchecked");
                            break;
                        }
                    }
                }
            }
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        }
    }

    /**This method selects a radiobutton found by "radioLabel" inside a dialogBox.
     *
     * @param dialog
     * @param radioLabel
     * @throws Exception
     */
    public void clickDialogRadioButton(WebElement dialog, String radioLabel) throws Exception{
        if(dialog==null){
            Assert.fail("No dialog box selected");
        }else{
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            if(dialog.findElements(By.xpath(".//span")).size()>0){
                List<WebElement> radioButtons = dialog.findElements(By.xpath(".//span"));
                for(WebElement radioButton : radioButtons){
                    if(radioButton.getText().contains(radioLabel)){
                        radioButton.findElement(By.xpath(".//input")).click();
                        break;
                    }
                }
            }
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        }
    }

    //MIGRATED
    /** This method sets a "dialogText" when the label is located at the left side.
     *  The element is found by textLabel and text.
     * @param dialog
     * @param textLabel
     * @param text
     * @throws Exception
     */
    public void setDialogText(WebElement dialog, String textLabel, String text) throws Exception{
        int flag = 0;
        List<WebElement> textFields = new ArrayList<WebElement>();
        List<WebElement> aux = new ArrayList<WebElement>();
        Assert.assertNotNull(dialog,"Error: No dialog box selected (Null)");
        textFields = dialog.findElements(By.xpath(".//input[@class='gwt-TextBox']"));
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        if(dialog.findElements(By.xpath(".//input[@class='gwt-PasswordTextBox']")).size()>0){
            aux = dialog.findElements(By.xpath(".//input[@class='gwt-PasswordTextBox']"));
            textFields.addAll(aux);
        }

        for(WebElement textField : textFields){
            WebElement findLabel = textField;
            findLabel=findLabel.findElement(By.xpath(".."));
            if(findLabel.findElements(By.xpath("./preceding-sibling::td[1]")).size()>0){
                findLabel=findLabel.findElement(By.xpath("./preceding-sibling::td[1]"));
                if(findLabel.getText().contentEquals(textLabel)){
                    textField.clear();
                    textField.sendKeys(text);
                    flag = 1;
                    break;
                }
            }
        }
        if(flag==0){
            for(WebElement textField : textFields){
                WebElement findLabel = textField;
                findLabel=findLabel.findElement(By.xpath(".."));
                findLabel=findLabel.findElement(By.xpath(".."));
                if(findLabel.findElements(By.xpath("./preceding-sibling::tr[1]")).size()>0){
                findLabel=findLabel.findElement(By.xpath("./preceding-sibling::tr[1]"));
                    if(findLabel.getText().contentEquals(textLabel)){
                        textField.clear();
                        textField.sendKeys(text);
                        flag = 1;
                        break;
                    }
                }
            }
        }
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    /**This method sets a "dialogText" when the label is located at the upper side.
     *  The element is found by textLabel and text.
     *
     * @param dialog
     * @param textLabel
     * @param text
     * @throws Exception
     */
    public void setDialogTextUpperLabel(WebElement dialog, String textLabel, String text) throws Exception{
        int flag;

        if(dialog==null){
            Assert.fail("No dialog box selected");
        }else{
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            if(dialog.findElements(By.xpath(".//input[@class='gwt-TextBox']")).size()>0){
                List<WebElement> textFields = dialog.findElements(By.xpath(".//input[@class='gwt-TextBox']"));
                for(WebElement textField : textFields){
                    flag = 0;
                    WebElement findLabel = textField;
                    do{
                        findLabel = findLabel.findElement(By.xpath(".."));
                        if(findLabel.getTagName().contentEquals("tr")){
                            findLabel = findLabel.findElement(By.xpath("./preceding-sibling::tr[1]"));
                            if(findLabel.findElements(By.xpath(".//div")).size()>0){
                                flag=1;
                            }
                        }
                    }while(flag==0);
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

    /**This method sets a "dialogTextArea" when the label is located at the left side.
     *  The element is found by textLabel and text.
     *
     * @param dialog
     * @param textLabel
     * @param text
     */
    public void setDialogTextArea(WebElement dialog, String textLabel, String text){
        int flag = 0;
        List<WebElement> textAreas = new ArrayList<WebElement>();
        Assert.assertNotNull(dialog,"Error: No dialog box selected (Null)");
        textAreas = dialog.findElements(By.xpath(".//input[@class='gwt-TextArea']"));

        for(WebElement textField : textAreas){
            WebElement findLabel = textField;
            findLabel=findLabel.findElement(By.xpath(".."));
            if(findLabel.findElements(By.xpath("./preceding-sibling::td[1]")).size()>0){
                findLabel=findLabel.findElement(By.xpath("./preceding-sibling::td[1]"));
                if(findLabel.getText().contentEquals(textLabel)){
                    textField.clear();
                    textField.sendKeys(text);
                    flag = 1;
                    break;
                }
            }
        }
        if(flag==0){
            for(WebElement textField : textAreas){
                WebElement findLabel = textField;
                findLabel=findLabel.findElement(By.xpath(".."));
                findLabel=findLabel.findElement(By.xpath(".."));
                if(findLabel.findElements(By.xpath("./preceding-sibling::tr[1]")).size()>0){
                findLabel=findLabel.findElement(By.xpath("./preceding-sibling::tr[1]"));
                    if(findLabel.getText().contentEquals(textLabel)){
                        textField.clear();
                        textField.sendKeys(text);
                        flag = 1;
                        break;
                    }
                }
            }
        }
    }


    /**This method sets a "dialogTextArea" when the label is located at the upper side.
     *
     * @param dialog
     * @param textLabel
     * @param text
     * @throws Exception
     */
    public void setDialogTextAreaUpperLabel(WebElement dialog, String textLabel, String text) throws Exception{
        int flag;
        
        if(dialog==null){
            Assert.fail("No dialog box selected");
        }else{
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            if(dialog.findElements(By.xpath(".//textarea[@class='gwt-TextArea']")).size()>0){
                List<WebElement> textFields = dialog.findElements(By.xpath(".//textarea[@class='gwt-TextArea']"));
                for(WebElement textField : textFields){
                    flag = 0;
                    WebElement findLabel = textField;
                    do{
                        findLabel = findLabel.findElement(By.xpath(".."));
                        if(findLabel.getTagName().contentEquals("tr")){
                            findLabel = findLabel.findElement(By.xpath("./preceding-sibling::tr[1]"));
                            if(findLabel.findElements(By.xpath(".//div")).size()>0){
                                flag=1;
                            }
                        }
                    }while(flag==0);
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

    /** This method selects a "dialogListItem" and it is found by dropdownlabel and item.
     *
     * @param dialog
     * @param dropdownLabel
     * @param item
     * @throws Exception
     */
    public void selectDialogListItem(WebElement dialog, String dropdownLabel, String item) throws Exception{
        int flag;
        int times;
        
        if(dialog==null){
            Assert.fail("No dialog box selected");
        }else{
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            if(dialog.findElements(By.xpath(".//select")).size()>0){
                List<WebElement> lists = dialog.findElements(By.xpath(".//select"));
                for(WebElement list : lists){
                    flag = 0;
                    times = 0;
                    WebElement findLabel = list;
                    do{
                        findLabel = findLabel.findElement(By.xpath(".."));
                        if(findLabel.findElements(By.xpath(".//div")).size()>0){
                            flag=1;
                        }else{times++;}
                    }while(flag==0 && times < 2);
                    if(findLabel.getText().contains(dropdownLabel)){
                        new Select(list).selectByVisibleText(item);
                        break;
                    }else{
                        findLabel = list.findElement(By.xpath(".."));
                        findLabel = findLabel.findElement(By.xpath(".."));
                        if(findLabel.findElements(By.xpath("./preceding-sibling::tr[1]")).size()>0){
                        findLabel=findLabel.findElement(By.xpath("./preceding-sibling::tr[1]"));
                        if(findLabel.getText().contentEquals(dropdownLabel)){
                            new Select(list).selectByVisibleText(item);
                            break;
                        }
                    }
                    }
                }
            }
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        }
    }

    /** This method is for handling the uploading file process inside a dialog.
     *
     * @param dialog
     * @param fileDirectory
     * @throws Exception
     */
    public void uploadFileInDialog(WebElement dialog, String fileDirectory) throws Exception{
        if(dialog==null){
            Assert.fail("No dialog box selected");
        }else{
            if(dialog.findElements(By.xpath(".//input[@name='File_0']")).size()>0){
                WebElement upload = dialog.findElement(By.xpath(".//input[@name='File_0']"));
                LocalFileDetector detector = new LocalFileDetector();
                File file = detector.getLocalFile(fileDirectory);
                upload.sendKeys(file.getAbsolutePath());
            }else{
                Assert.fail("There's no button to search the file to upload");
            }
        }
    }
}
