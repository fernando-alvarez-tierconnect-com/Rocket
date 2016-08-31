package com.trucentrix.pageobject;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import java.util.*;

/**
 * The table class is for handling any table in the TRUcentrix application.
 *
 * @author rcadima
 */
public class Table {
    WebDriver driver;
    WebDriverWait wait;
    WebElement table;
    private boolean acceptNextAlert = true;
    public Table (WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 90);
    }
/**
 * Obtain the table that is being displayed in the TRUcentrix application.
 *
 * @return A Web element that contains the table.
 * @throws Exception
 */
    private WebElement getTable() throws Exception {
        table = null;
        if(driver.findElements(By.xpath("//table[@class='sortableTable']")).size()>0)
            table = driver.findElement(By.xpath("//table[@class='sortableTable']"));
        else Assert.fail("Error: There is no table present");
        return table;
    }

 /**
 * Obtain a specific row of a table that is being displayed in the TRUcentrix application.
 *
 * @param table A table Web element where is the row wanted.
 * @param numberOfRow The number of row that is needed (the table header counts like a row).
 * @return A web element list containing the columns of the row.
 * @throws Exception
 */
    public List<WebElement> getRow(int numberOfRow) throws Exception{
        table = getTable();
        Assert.assertNotNull(table, "Error: Table not found");
        Assert.assertTrue(isTableNotEmpty(), "Error: The Table is empty");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//table[@class='sortableTable']/tbody/tr["+ numberOfRow+"]")));
        List<WebElement> row=table.findElements(By.xpath("./tbody/tr["+ numberOfRow+"]"));
        return row;
    }

/**
 * Obtain a specific column of a table that is being displayed in the TRUcentrix application.
 *
 * @param table A table Web element where is the column wanted.
 * @param numberOfRow The number of row that is needed (the table header counts like a row).
 * @return A web element list containing the columns of the row.
 * @throws Exception
 */
    public List<WebElement> getColumn(int numberOfColumn) throws Exception{
        table = getTable();
        Assert.assertNotNull(table, "Error: Table not found");
        Assert.assertTrue(isTableNotEmpty(), "Error: The Table is empty");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//table[@class='sortableTable']/tbody/tr/td["+ numberOfColumn +"]")));
        List<WebElement> column=table.findElements(By.xpath("./tbody/tr/td["+ numberOfColumn +"]"));
        return column;
    }

/**
 * Go to a specific cell based in a row and a column. The cell is returned as a WebElement.
 *
 * @param table
 * @param numberOfRow
 * @param numberOfColumn
 * @return
 * @throws Exception
 */
    public WebElement getCell(int numberOfRow, int numberOfColumn) throws Exception {
        table = getTable();
        Assert.assertNotNull(table, "Error: Table not founded");
        Assert.assertTrue(isTableNotEmpty(), "Error: The Table is empty");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//table[@class='sortableTable']/tbody/tr["+ numberOfRow +"]/td["+ numberOfColumn +"]")));
        WebElement cell = table.findElement(By.xpath("./tbody/tr["+ numberOfRow +"]/td["+ numberOfColumn +"]"));
        return cell;
    }

    /**This method counts the number of rows and columns of a specific table.
     *
     * @param table
     */
    public void displayTable() throws Exception{
        table = getTable();
        Assert.assertNotNull(table, "Error: Table not found");
        List<WebElement> rows=table.findElements(By.xpath("./tbody/tr[position()>1]"));

        System.out.println("NUMBER OF ROWS IN THIS TABLE = "+rows.size());
        int rowNumber,colNumber;
        rowNumber=1;
        for(WebElement rowElement : rows)
        {
            List<WebElement> columns=rowElement.findElements(By.xpath("td"));
            System.out.println("NUMBER OF COLUMNS="+columns.size());
            colNumber=1;
            for(WebElement colElement : columns)
            {
                System.out.println("row # "+rowNumber+", col # "+colNumber+ " text= "+colElement.getText());
                colNumber++;
            }
            rowNumber++;
        }
    }

    /**This method counts the number of rows of a specific table.
     *
     * @param row
     */

    public void displayRow(List<WebElement> row){
        for (WebElement rowElement : row){
            List<WebElement> columns=rowElement.findElements(By.xpath("td"));
            for(WebElement colElement : columns){
                System.out.print(colElement.getText()+ " | ");
            }
            System.out.println(" ");
        }
    }


    /**This method counts the number of columns of a specific table.
    *
    * @param column
    */
    public void displayColumn(List<WebElement> column){
        for (WebElement colElement : column){
            System.out.println(colElement.getText());
        }
    }

    /**Click a column header based in the headerName introduced as parameter.
     *
     * @param table
     * @param headerName
     * @return
     * @throws Exception
     */
    public int clickColumnHeader (String headerName) throws Exception{
        table = getTable();
        int numberOfColumn = 0;
        Assert.assertNotNull(table, "Error: Table not found");
        Assert.assertTrue(isTableNotEmpty(), "Error: The Table is empty");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//table[@class='sortableTable']/tbody/tr[1]")));
        List<WebElement> header = table.findElements(By.xpath("./tbody/tr[1]"));
        for(WebElement rowHeader : header){
            List<WebElement> columnHeader = rowHeader.findElements(By.xpath("td"));
            for (WebElement colHeader : columnHeader){
                numberOfColumn++;
                if(colHeader.getText().contentEquals(headerName)){
                    colHeader.findElement(By.xpath("./table/tbody/tr/td/div")).click();
                    break;
                }
            }
        }
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//table[@class='sortableTable']/tbody/tr[1]")));
        return numberOfColumn;
    }

    /**
     * Click a specific link located in a Table Cell based in a number of Row and column.
     * @param table
     * @param numberOfRow
     * @param numberOfColumn
     * @throws Exception
     */
    public void clickSpecificTableLink (int numberOfRow, int numberOfColumn) throws Exception{
        table = getTable();
        Assert.assertNotNull(table, "Error: Table not found");
        WebElement cell = getCell(numberOfRow, numberOfColumn);
        WebElement clicking = cell.findElement(By.xpath(".//div"));
        clicking.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]")));
    }

    /**Click a specific image located in a Table Cell based a number of Row and column.
     *
     * @param table
     * @param numberOfRow
     * @param numberOfColumn
     * @param deleteTitle
     * @throws Exception
     */
    public void clickSpecificTableImage (int numberOfRow, int numberOfColumn, String deleteTitle) throws Exception{
            table = getTable();
            WebElement cell = getCell(numberOfRow, numberOfColumn);
            List<WebElement> images = cell.findElements(By.xpath(".//img"));
            for (WebElement imageElement : images)
            {
                if(imageElement.getAttribute("title").contentEquals(deleteTitle)) imageElement.click();
            }
    }

    /**The expected sorting for a column in ascending order is returned based in a specific column.
     *
     * @param table
     * @param column
     * @return
     * @throws Exception
     */
    public ArrayList expectedSortColumnAsc (int column)throws Exception{
        table = getTable();
        Assert.assertNotNull(table, "Error: Table not found");
        ArrayList<String> listSort = new ArrayList<String>();
        List<WebElement> sortColumnAsc = getColumn (column);
        sortColumnAsc.remove(0);
        for (WebElement columnElement : sortColumnAsc){
            listSort.add(columnElement.getText());
        }
        Collections.sort(listSort);
        return listSort;
    }

    /**The expected sorting for a column in descending order is returned based in a specific column.
     *
     * @param table
     * @param column
     * @return
     * @throws Exception
     */
    public ArrayList expectedSortColumnDesc  (int column)throws Exception{
        table = getTable();
        Assert.assertNotNull(table, "Error: Table not found");
        ArrayList<String> listSort = new ArrayList<String>();
        List<WebElement> sortColumnDesc = getColumn (column);
        sortColumnDesc.remove(0);
        for (WebElement columnElement : sortColumnDesc){
            listSort.add(columnElement.getText());
        }
        Collections.sort(listSort, Collections.reverseOrder());
        return listSort;
    }

    /** The column sorting after clicking the column header is returned using "getSortedColumn" method
     *
     * @param table
     * @param headerName
     * @return
     * @throws Exception
     */
    public List<WebElement> getSortedColumn (String headerName) throws Exception {
        table = getTable();
        Assert.assertNotNull(table, "Error: Table not found");
        int numberOfColumn = clickColumnHeader(headerName);
        List<WebElement> column = getColumn(numberOfColumn);
        return column;
    }

    /**Compare two columns with the ascending or descending order of the same column, finally false or true value is returned.
     *
     * @param appColumn
     * @param expectedAsc
     * @param expectedDesc
     * @return
     * @throws Exception
     */
    public boolean compareColumns (List<WebElement> appColumn, ArrayList<String> expectedAsc, ArrayList<String> expectedDesc) throws Exception {
        int row = 0;
        appColumn.remove(0);
        for (WebElement compare : appColumn){
            if(!compare.getText().contentEquals(expectedAsc.get(row))){
                if(!compare.getText().contentEquals(expectedDesc.get(row))){
                    return false;
                }
            }
            row++;
        }
        return true;
    }

    /**The method compares if there is at least one item in the table.
     *
     * @param table
     * @return
     * @throws Exception
     */
    public boolean isTableNotEmpty () throws Exception {
        table = getTable();
        Assert.assertNotNull(table, "Error: Table not found");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//table[@class='sortableTable']")));
        List<WebElement> rows=table.findElements(By.xpath("./tbody/tr[position()>1]"));
        if(rows.size()>=1) return true;
        else return false;
    }

    /**The number of items in a table is returned using "GetTableSize" method.
     *
     * @param table
     * @return
     * @throws Exception
     */
    public int getTableSize () throws Exception{
        table = getTable();
        List<WebElement> rows=table.findElements(By.xpath("./tbody/tr[position()>1]"));
        return rows.size();
    }

    /**A specific column is found based in its value finally the number of column is returned.
     *
     * @param table
     * @param headerValue
     * @return
     * @throws Exception
     */
    public int findColumnFromTable (String headerValue) throws Exception{
        int numberOfColumn = 0;
        int rowHeader = 1;
        table = getTable();
        Assert.assertNotNull(table, "Error: Table not found");
        List<WebElement> rowNumber = getRow(rowHeader);
        for (WebElement rowElement : rowNumber){
            List<WebElement> columns=rowElement.findElements(By.xpath("td"));
            for(WebElement colElement : columns){
                numberOfColumn ++;
                if(colElement.getText().contentEquals(headerValue)){
                    break;
                }
            }
        }
        return numberOfColumn;
    }

       /** Several links inside a specific cell are clicked. "names" contents all the links found previously inside a cell.
        *
        * @param names
        */
        public void clickSeveralLinksFromTable(List<WebElement> names)
        {
            for(WebElement name : names)
            {
                name.click();
                if(driver.findElements(By.xpath("//div[@class='gwt-DialogBox']")).size()>0)
                {
                    List<WebElement> buttons = driver.findElements(By.xpath("//div[@class='gwt-DialogBox']//button"));
                    for(WebElement button : buttons)
                    {
                        if(button.getText().contentEquals("Cancel"))
                        {
                            button.click();
                            break;
                        }
                    }
                }
            }
        }

    /** Check an element from a Table based in its number of column.
     *
     * @param table
     * @param numberOfRow
     * @throws Exception
     */
    public void checkElementFromTable (int numberOfRow) throws Exception {
        table = getTable();
        List<WebElement> row = getRow(numberOfRow);
        for (WebElement rowElement : row){
            if(rowElement.findElements(By.xpath(".//input")).size()>0){
                if(rowElement.findElement(By.xpath(".//input")).isSelected()){
                    System.out.println("The checkbox in row N°"+ numberOfRow +" is already checked");
                    break;
                }
                rowElement.findElement(By.xpath(".//input")).click();
                break;
            }
        }
    }

    /** Uncheck an element from a Table based in its number of column.
     *
     * @param table
     * @param numberOfRow
     * @throws Exception
     */
    public void uncheckElementFromTable (int numberOfRow) throws Exception {
        table = getTable();
        List<WebElement> row = getRow(numberOfRow);
        for (WebElement rowElement : row){
            if(rowElement.findElements(By.xpath(".//input")).size()>0){
                if(rowElement.findElement(By.xpath(".//input")).isSelected()){
                    rowElement.findElement(By.xpath(".//input")).click();
                    break;
                }
                System.out.println("The checkbox in row N°"+ numberOfRow +" is already unchecked");
                break;
            }
        }
    }

    /** This class confirms a dialog or alert message.
     *
     */
    public void confirmDialogAlertMessage()
    {
                 if(driver.findElements(By.xpath("//div[@class='gwt-DialogBox']")).size()>0){
                        List<WebElement> buttons = driver.findElements(By.xpath("//div[@class='gwt-DialogBox']//button"));
                        for(WebElement button : buttons){
                            if(button.getText().contentEquals("OK")){
                                button.click();
                                break;
                            }
                        }
                 }
                 else
                 {
                     //Assert.assertTrue(closeAlertAndGetItsText().matches("^Are you sure you want to DELETE this Role[\\s\\S]$"));
                     closeAlertAndGetItsText();
                 }
    }


   /** This class closes the alert message and returns the alert text.
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