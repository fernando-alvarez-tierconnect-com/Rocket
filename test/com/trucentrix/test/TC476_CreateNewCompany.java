package com.trucentrix.test;

import com.trucentrix.pageobject.Automation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.util.List;

public class TC476_CreateNewCompany extends Automation{
    WebDriver driver;
    String cause = "";
    WebElement t, box;
    int ok;
    String companyName = "_123TestCompany";

    @Test
    public void step1() throws Exception{
        System.out.println("Verify that it is possible to create a new Company");
        System.out.println("Step 1");
        driver = setUpFirefox("http://qa-trubiquity2k12.tierconnect.com/nxps/");
        getVariables(driver);
        login.loginAss("falvarez", "123456");
    }

    @Test(dependsOnMethods = {"step1"})
    public void step2() throws Exception{
        System.out.println("Step 2");
        try{
            header.openAdministration().clickCompanies();
        }catch(Exception e){
            cause = " -- Cause:  " + e.toString();
        }
        Assert.assertTrue(company.isTableNotEmpty(),"Error in step 2: There is no Companies in the list. " + cause);
    }

    @Test(dependsOnMethods = {"step2"})
    public void step3() throws Exception{
        System.out.println("Step 3");
        try{
            subHeader.clickSubHeaderLink("Create Company");
            box = dialog.getDialogBox();
        }catch(Exception e){
            cause = " -- Cause:  " + e.toString();
        }
        Assert.assertNotNull(box,"Error in step 3: There is no Companies in the list. " + cause);
    }

    @Test(dependsOnMethods = {"step3"})
    public void step4() throws Exception{
        System.out.println("Step 4");
        ok = 0;
        try{
            dialog.setDialogText(box, "Company Name*:", companyName);
            dialog.clickDialogButton(box, "Save");
            List<WebElement> column = company.getColumn(1);
            for(WebElement element : column){
                if(element.getText().contentEquals(companyName)){
                    ok++;
                    break;
                }
            }
        }catch (Exception e){
            cause = " -- Cause:  " + e.toString();
        }
        Assert.assertEquals(ok, 1, "Error in step 4: The company was not created. " + cause);
    }

    @AfterTest
    public void finish(){
        tearDown();
    }
}