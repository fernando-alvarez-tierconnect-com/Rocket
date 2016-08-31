package com.trucentrix.test;

import com.trucentrix.pageobject.Automation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.util.List;

public class TC338_NewEmptyProjectTest extends Automation{
    WebDriver driver;
    int row = 0;
    String cause = "";
    WebElement box;
    String projectName = "NewEmptyProject";
    
    @Test
    public void step1() throws Exception{
        System.out.println("Verify that is possible to create an empty project");
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
        box = dialog.getDialogBox();
        dialog.setDialogTextUpperLabel(box, "Name", projectName);
        dialog.setDialogTextUpperLabel(box, "Description", "This was automated");
        dialog.clickDialogButton(box, "Create");
    }

    @Test(dependsOnMethods = {"step4"})
    public void step5() throws Exception{
        int ok = 0;
        row = 0;
        System.out.println("Step 5");
        box = dialog.getSpecificDialogBox("Project Created");
        dialog.clickDialogButton(box, "Done");

        try{

            List<WebElement> column = project.getColumn(1);
            for(WebElement element : column){
                row++;
                if(element.getText().contentEquals(projectName)){
                    ok++;
                    break;
                }
            }
        }catch(Exception e){
            cause = " -- Cause:  " + e.toString();
        }
        Assert.assertEquals(ok, 1,"Error in step 5: There is "+ ok +" project with "+ projectName +" name." + cause);
        
        try{
            project.clickSpecificTableLink(row, 1);
        }catch(Exception e){
            cause = " -- Cause:  " + e.toString();
        }
        Assert.assertTrue(getTitle().contains("Content"),"Error in step 6: The project's link doesn't open the project"+cause);
    }

    @AfterTest
    public void finish(){
        tearDown();
    }
}