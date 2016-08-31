package com.trucentrix.pageobject;

import org.openqa.selenium.*;
import org.testng.Assert;
import java.util.*;

/** DataSourcesTable class is for handling the elements inside "Data Sources" page in Administration.
 *
 * @author rcadima
 */
public class DataSourcesTable extends Table{
    private boolean acceptNextAlert = true;
    Miscellaneous numrandom = new Miscellaneous(driver);
    public DataSourcesTable(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    /**
     * selectDataSourceFromTable method clicks on a random Data Source name from the Data Sources table.
     * @throws Exception
     */
    public void selectDataSourceFromTable() throws Exception{

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
     * deleteDataSourcesFromTable method deletes a random Data Source from Data Sources table by clicking on its trash image.
     * @throws Exception
     */
    public void deleteDataSourceFromTable() throws Exception{
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
     * Selects a data source by giving the name that you want to click in the Data Source table.
     * @param dataName The data source's name.
     * @throws Exception
     */
    public void selectDataSourceFromTable(String dataName) throws Exception{
        int numberOfRow = 0;
        int numberOfColumn = 0;
        int flag = 0;

        List <WebElement> row = getRow(1);
        for (WebElement rowElement : row){
            List<WebElement> columns = rowElement.findElements(By.xpath("td"));
            for(WebElement colElement : columns){
                numberOfColumn ++;
                if(colElement.getText().equals("Name")){
                    break;
                }
            }
        }

        List <WebElement> columns = getColumn(numberOfColumn);
        for (WebElement columnElement : columns){
            numberOfRow ++;
            if (columnElement.getText().contentEquals(dataName)){
                flag = 1;
                break;
            }
        }

        if(flag == 1) clickSpecificTableLink(numberOfRow, numberOfColumn);
    }

    /**
     * Delete a data source by giving the name that you want to click in the Data Source table.
     * @param dataName The data source's name.
     * @throws Exception
     */
    public void deleteDataSourceFromTable(String dataName) throws Exception{
        int flag = 0;
        int numberOfColumn = 0;
        int numberOfColumnName = 0;
        int numberOfRow = 0;

        List<WebElement> row = getRow(1);
        for(WebElement rowElement : row){
           List<WebElement> columns = rowElement.findElements(By.xpath("td"));
           for(WebElement colElement : columns){
               numberOfColumn ++;
               if(colElement.getText().isEmpty()){
                   break;
               }
           }
        }

        for (WebElement rowElement : row){
           List<WebElement> columns = rowElement.findElements(By.xpath("td"));
           for(WebElement colElement : columns){
               numberOfColumnName ++;
               if(colElement.getText().contentEquals("Name")){
                   break;
               }
           }
        }

        List<WebElement> column = getColumn(numberOfColumnName);
        for (WebElement colElement : column){
            numberOfRow ++;
            if (colElement.getText().contentEquals(dataName)) {
                flag=1;
                break;
            }
        }

        if(flag == 1){
            clickSpecificTableImage(numberOfRow, numberOfColumn, "Delete");
            Assert.assertTrue(closeAlertAndGetItsText().matches("^Are you sure you want to delete the selected object definitions?[\\s\\S]$"));
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
