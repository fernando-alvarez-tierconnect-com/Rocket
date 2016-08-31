package com.trucentrix.pageobject;

import org.openqa.selenium.*;
import org.testng.Assert;
import java.util.*;

/** UIActionsTable class is for handling the elements inside "UI Actions" page in Administration.
 *
 * @author rcadima
 */
public class UIActionsTable extends Table{
     Miscellaneous numrandom = new Miscellaneous(driver);
    private boolean acceptNextAlert = true;
    public UIActionsTable(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    /** selectUIActionFromTable method clicks on a random UI Action from the UI Action table.
     *
     * @throws Exception
     */
    public void selectUIActionFromTable () throws Exception {
        boolean tableisnotempty = isTableNotEmpty();
        if(tableisnotempty == true)
        {
            int tableSize = getTableSize();
            int randomRow = numrandom.randomGenerator(tableSize);
            int columnNumber = findColumnFromTable("Label");
            clickSpecificTableLink(randomRow, columnNumber);
        }
    }

    /** deleteUIActionsFromTable method deletes a random UI Action from UI Actions table by clicking on its trash image.
     *
     * @throws Exception
     */
    public void deleteUIActionFromTable() throws Exception {
       boolean tableisnotempty = isTableNotEmpty();
        if(tableisnotempty == true)
        {
            int tableSize = getTableSize();
            int randomRow = numrandom.randomGenerator(tableSize);
            int columnNumber = findColumnFromTable("");

            clickSpecificTableImage(randomRow, columnNumber,"Delete");
            Assert.assertTrue(closeAlertAndGetItsText().matches("^Are you sure you want to DELETE this Role[\\s\\S]$"));
        }
    }

    /**
     * Select a UI Action item by giving the name that you want to click from the UI actions Items table
     * @param actionName
     * @throws Exception
     */
    public void selectUIActionFromTable (String actionName) throws Exception {
        int numberOfRow = 0;
        int numberOfColumn = 0;
        int flag = 0;

        List <WebElement> row = getRow(1);
        for (WebElement rowElement : row){
            List<WebElement> columns = rowElement.findElements(By.xpath("td"));
            for(WebElement colElement : columns){
                numberOfColumn ++;
                if(colElement.getText().equals("Label")) break;
            }
        }

        List <WebElement> columns = getColumn(numberOfColumn);
        for (WebElement columnElement : columns){
            numberOfRow ++;
            if (columnElement.getText().contentEquals(actionName)){
                flag = 1;
                break;
            }
        }
        if(flag == 1) clickSpecificTableLink(numberOfRow, numberOfColumn);
    }

    /**
     * Delete a UI Action item by giving the name that you want to click from the UI actions Items table
     * @param actionName
     * @throws Exception
     */
    public void deleteUIActionFromTable(String actionName) throws Exception {
        int flag = 0;
        int numberOfColumn = 0;
        int numberOfColumnName = 0;
        int numberOfRow = 0;

        List<WebElement> row = getRow(1);
        for(WebElement rowElement : row){
           List<WebElement> columns = rowElement.findElements(By.xpath("td"));
           for(WebElement colElement : columns){
               numberOfColumn ++;
               if(colElement.getText().isEmpty()) break;
           }
        }

        for (WebElement rowElement : row){
           List<WebElement> columns = rowElement.findElements(By.xpath("td"));
           for(WebElement colElement : columns){
               numberOfColumnName ++;
               if(colElement.getText().contentEquals("Label")) break;
           }
        }

        List<WebElement> column = getColumn(numberOfColumnName);
        for (WebElement colElement : column){
            numberOfRow ++;
            if (colElement.getText().contentEquals(actionName)) {
                flag=1;
                break;
            }
        }

        if(flag == 1){
            clickSpecificTableImage(numberOfRow, numberOfColumn, "Delete");
            Assert.assertTrue(closeAlertAndGetItsText().matches("^Are you sure you want to delete the selected UI Action?[\\s\\S]$"));
        }
    }

    /**
     *
     * @return
     */
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
