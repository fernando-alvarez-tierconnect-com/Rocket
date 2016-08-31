package com.trucentrix.test;

import org.testng.annotations.*;
import org.testng.Assert;
import org.openqa.selenium.*;
import com.trucentrix.pageobject.*;

public class TC475_SeeAllCompaniesTest extends Automation{
    WebDriver driver;
    String cause = "";
    WebElement t;

    @Test
    public void step1() throws Exception{
        System.out.println("Verify that it is possible to see all the Companies from Administration page");
        System.out.println("Step 1");
        driver = setUpFirefox("http://qa-tcentrix-2.tierconnect.com/nxps/");
        getVariables(driver);
        login.loginAss("aquiles", "123456");
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
    
    @AfterTest
    public void finish(){
        tearDown();
    }
}