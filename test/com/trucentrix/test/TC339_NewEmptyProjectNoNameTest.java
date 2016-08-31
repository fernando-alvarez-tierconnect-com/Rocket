
package com.trucentrix.test;

import org.testng.annotations.*;
import org.testng.Assert;
import org.openqa.selenium.*;
import com.trucentrix.pageobject.*;
/**
 *
 * @author rcadima
 */
public class TC339_NewEmptyProjectNoNameTest extends Automation{
    WebDriver driver;
    String cause="";
    WebElement box;
    int ok = 0;

    @Test
    public void step1() throws Exception{
        System.out.println("Verify that is not possible to create a new project without name");
        System.out.println("Step 1");
        driver = setUpFirefox("http://qa-tcentrix-2.tierconnect.com/nxps/");
        getVariables(driver);
        login.loginAss("aquiles", "123456");
    }

    @Test(dependsOnMethods = {"step1"})
    public void step2() throws Exception{
        System.out.println("Step 2");
        try{
            header.openViewMenu().openMyProjects();
        }catch(Exception e){
            cause = " -- Cause:  " + e.toString();
        }
        Assert.assertEquals(getTitle(), "Projects", "Error in step 1: Couldn't open My projects"+cause);
    }

    @Test(dependsOnMethods = {"step2"})
    public void step3() throws Exception{
        System.out.println("Step 3");
        try{
            subHeader.clickSubHeaderLink("New Project");
            WebElement panel = popupPanel.getPopupPanel();
            popupPanel.clickPanelItem(panel, "Empty Project");
            box = dialog.getDialogBox();
        }catch(Exception e){
            cause = " -- Cause:  " + e.toString();
        }
        Assert.assertTrue(box.getText().contains("Create"),"Error in step 2: Couldn't open the Create project dialog"+cause);
    }

    @Test(dependsOnMethods = {"step3"})
    public void step4() throws Exception{
        System.out.println("Step 4");
        try{
            dialog.clickDialogButton(box, "Create");
            WebElement box1 = dialog.getSpecificDialogBox("Validation Error");
            if(box1.getText().contains("Attribute 'Name' is a required attribute! Please specify a value.")) ok++;
            dialog.clickDialogButton(box1, "Close");
            dialog.clickDialogButton(box, "Cancel");
        }catch(Exception e){
            cause = " -- Cause:  " + e.toString();
        }
        Assert.assertEquals(ok, 1,"Error: No warning was displayed creating a project without name"+cause);
    }
    
    @AfterTest
    public void finish(){
        tearDown();
    }
}
