package com.trucentrix.pageobject;

import org.openqa.selenium.*;
import java.util.*;
import org.testng.Assert;

/**
 * ObjectDefinitionsTable class is for handling the elements inside "Object Definitions" page in Administration.
 * @author rcadima
 */
public class ObjectDefinitionsTable extends Table{
    Miscellaneous numrandom = new Miscellaneous(driver);
    private boolean acceptNextAlert = true;
    public ObjectDefinitionsTable(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    /** 
     * selectObjectFromTable method clicks on a random Object Definition name from the Object Definitions table.
     * @throws Exception
     */
    public void selectObjectFromTable() throws Exception {
        boolean tableisnotempty = isTableNotEmpty();
        if(tableisnotempty == true)
        {
            int tableSize = getTableSize();
            int randomRow = numrandom.randomGenerator(tableSize);
            int columnNumber = findColumnFromTable("Name");

            WebElement cell = getCell(randomRow, columnNumber);
            List<WebElement> elements = cell.findElements(By.xpath(".//div"));
            int c=0;
            for(WebElement name : elements)
            {
                c++;
                if(c==2)
                {
                    name.click();
                    break;
                }
            }
        }
    }

    /**
     * deleteObjectsFromTable method deletes a random Object Definition from Object Definition table by clicking on its trash image.
     * @throws Exception
     */
    public void deleteObjectFromTable () throws Exception{
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
     * Select a object definitions by giving the name that you want to click from the Object Definitions table
     * @param objectName The Object Definition name.
     * @throws Exception
     */
    public void selectObjectFromTable(String objectName) throws Exception {
        int numberOfRow = 0;
        int numberOfColumn = 0;
        int flag = 0;

        List <WebElement> row = getRow(1);
        for (WebElement rowElement : row){
            List<WebElement> columns = rowElement.findElements(By.xpath("td"));
            for(WebElement colElement : columns){
                numberOfColumn ++;
                if(colElement.getText().equals("Name")) break;
            }
        }

        List <WebElement> columns = getColumn(numberOfColumn);
        for (WebElement columnElement : columns){
            numberOfRow ++;
            if (columnElement.getText().contentEquals(objectName)){
                flag = 1;
                break;
            }
        }

        if(flag == 1){
            WebElement cell = getCell(numberOfRow, numberOfColumn);
            List<WebElement> elements = cell.findElements(By.xpath(".//div"));
            for(WebElement name : elements){
                if(name.getText().contentEquals(objectName)){
                    name.click();
                    break;
                }
            }
        }
    }

    /**
     * Delete a object definitions by giving it the name of the item from the Object Definitions table
     * @param objectName The Object Definition name.
     * @throws Exception
     */
    public void deleteObjectFromTable (String objectName) throws Exception{
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
               if(colElement.getText().contentEquals("Name")) break;
           }
        }

        List<WebElement> column = getColumn(numberOfColumnName);
        for (WebElement colElement : column){
            numberOfRow ++;
            if (colElement.getText().contentEquals(objectName)) {
                flag=1;
                break;
            }
        }

        if(flag == 1){
            clickSpecificTableImage(numberOfRow, numberOfColumn, "Delete");
            Assert.assertTrue(closeAlertAndGetItsText().matches("^Are you sure you want to delete the selected object definitions[\\s\\S]$"));
        }
    }

    /**
     * A method tho handle alerts by getting it's text and closing it.
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