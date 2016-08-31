package com.trucentrix.pageobject;

import org.openqa.selenium.*;
import org.testng.Assert;
import java.util.*;

/** 
 * GlobalRolesTable class is for handling the elements inside Global Roles page in Administration.
 * @author acachi
 */
public class GlobalRolesTable extends Table{

  Miscellaneous numrandom = new Miscellaneous(driver);
  private boolean acceptNextAlert = true;
    public GlobalRolesTable(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    /** 
     * selectGlobalRoleFromTable method clicks on a random Global Role name from the Global Roles table.
     * @param table
     * @throws Exception
     */
    public void selectGlobalRoleFromTable () throws Exception
    {
        boolean tableisnotempty = isTableNotEmpty();
        if(tableisnotempty == true)
        {
            int tableSize = getTableSize();
            int randomRow = numrandom.randomGenerator(tableSize);
            int columnNumber = findColumnFromTable("Name");
            clickSpecificTableLink(randomRow, columnNumber);
        }
    }

    /** 
     * deleteGlobalRolesFromTable method deletes a random Global Role from the companies table by clicking on its trash image.
     * @throws Exception
     */
    public void deleteGlobalRoleFromTable () throws Exception
    {
        boolean tableisnotempty = isTableNotEmpty();
        if(tableisnotempty == true)
        {
            int tableSize = getTableSize();
            int randomRow = numrandom.randomGenerator(tableSize);
            int columnNumber = findColumnFromTable("");

            clickSpecificTableImage(randomRow, columnNumber,"Delete");
            //Assert.assertTrue(closeAlertAndGetItsText().matches("^Are you sure you want to DELETE this Role[\\s\\S]$"));
            //closeAlertAndGetItsText();
            confirmDialogAlertMessage();
        }
        
    }

    /**
     * Selects a global role by giving the name that you want to click from the Global Role table.
     * @param role The name's role.
     * @throws Exception
     */
    public void selectGlobalRoleFromTable (String role) throws Exception {
        int flag = 0;
        int numberOfRow = 0;

        List<WebElement> column = getColumn(1);
        for (WebElement colElement : column){
            numberOfRow ++;
            if (colElement.getText().contentEquals(role)) {
                flag=1;
                break;
            }
        }
        if(flag == 1) clickSpecificTableLink(numberOfRow, 1);
    }

    /**
     * Delete a global role by giving the name from the Global Role table.
     * @param role
     * @throws Exception
     */
    public void deleteGlobalRoleFromTable (String role) throws Exception{
        int flag = 0;
        int numberOfRow = 0;

        List<WebElement> column = getColumn(1);
        for (WebElement colElement : column){
            numberOfRow ++;
            if (colElement.getText().contentEquals(role)) {
                flag=1;
                break;
            }
        }
        if(flag == 1) {
            clickSpecificTableImage(numberOfRow, 4, "Delete");
            Assert.assertTrue(closeAlertAndGetItsText().matches("^Are you sure you want to DELETE this Role[\\s\\S]$"));
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alert.getText();
            } finally {
                acceptNextAlert = true;
            }
    }
}
