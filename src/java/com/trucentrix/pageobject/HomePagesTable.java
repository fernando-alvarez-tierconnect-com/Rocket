package com.trucentrix.pageobject;

import org.openqa.selenium.*;
import java.util.*;

/**
 * HomePagesTable class is for handling the elements inside "Home Pages" page in Administration.
 * @author rcadima
 */
public class HomePagesTable extends Table {
 Miscellaneous numrandom = new Miscellaneous(driver);
    public HomePagesTable (WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    /** 
     * selectHomePageFromTable method clicks on a random Home Page name from the Home Pages table.
     * @throws Exception
     */
    public void selectHomePageFromTable() throws Exception {
        boolean tableisnotempty = isTableNotEmpty();
        if(tableisnotempty == true)
        {
            int tableSize = getTableSize();
            int randomRow = numrandom.randomGenerator(tableSize);
            int column = findColumnFromTable("Name");
            clickSpecificTableLink(randomRow, column);
        }
    }

    /**
     * exportHomePageFromTable method clicks on the Save image of a random Home Page to export a home Page in a xml format.
     * @throws Exception
     */
    public void exportHomePageFromTable() throws Exception
    {
        boolean tableisnotempty = isTableNotEmpty();
        if(tableisnotempty == true)
        {
            int tableSize = getTableSize();
            int randomRow = numrandom.randomGenerator(tableSize);
            int column = findColumnFromTable("");

            clickSpecificTableImage(randomRow, column,"Save");
        }
    }

    /**
     * deleteHomePageFromTable method deletes a random Home Page from Home Pages table by clicking on its trash image.
     * @throws Exception
     */
    public void deleteHomePageFromTable() throws Exception{
       boolean tableisnotempty = isTableNotEmpty();
        if(tableisnotempty == true){
            int tableSize = getTableSize();
            int randomRow = numrandom.randomGenerator(tableSize);
            int column = findColumnFromTable("");
            
            clickSpecificTableImage(randomRow, column,"Delete");

                    if(driver.findElements(By.xpath("//div[@class='gwt-DialogBox']")).size()>0){
                        List<WebElement> buttons = driver.findElements(By.xpath("//div[@class='gwt-DialogBox']//button"));
                        for(WebElement button : buttons){
                            if(button.getText().contentEquals("OK")){
                                button.click();
                                break;
                            }
                        }
                    }            
        }                            
    }

    /**
     * Select a checkbox that belongs to the row specified in the Home Page table.
     * @param numberOfRow The number of the row that where is the checkbox to be selected.
     * @throws Exception
     */
    public void checkHomePage (int numberOfRow) throws Exception {
        List<WebElement> row = getRow(numberOfRow);
        for (WebElement rowElement : row){
            if(rowElement.findElements(By.xpath(".//input")).size()>0){
                if(rowElement.findElement(By.xpath(".//input")).isSelected()){
                    System.out.println("The checkbox in row NÂ°"+ numberOfRow +" is already checked");
                    break;
                }
                rowElement.findElement(By.xpath(".//input")).click();
                break;
            }
        }
    }

    /**
     * Unselect a checkbox that belongs to the row specified in the Home Page table.
     * @param numberOfRow The number of the row that where is the checkbox to be unselected.
     * @throws Exception
     */
    public void uncheckHomePage (int numberOfRow) throws Exception {
        List<WebElement> row = getRow(numberOfRow);
        for (WebElement rowElement : row){
            if(rowElement.findElements(By.xpath(".//input")).size()>0){
                if(rowElement.findElement(By.xpath(".//input")).isSelected()){
                    rowElement.findElement(By.xpath(".//input")).click();
                    break;
                }
                System.out.println("The checkbox in row NÂ°"+ numberOfRow +" is already unchecked");
                break;
            }
        }
    }

    /**
     * Select a home page by giving the name that you want to click from the Home Page table
     * @param homePageName The home page name.
     * @throws Exception
     */
    public void selectHomePageFromTable(String homePageName) throws Exception {
        int flag = 0;
        int numberOfRow = 0;
        int numberOfColumn = 0;

        List<WebElement> row = getRow(1);
        for(WebElement rowElement:row){
            List<WebElement> columns=rowElement.findElements(By.xpath("td"));
            for(WebElement columnElement : columns){
                numberOfColumn++;
                if(columnElement.getText().contains("Name")){
                    break;
                }
            }
        }

        List<WebElement> column = getColumn(numberOfColumn);
        for (WebElement columnElement : column){
            numberOfRow++;
            if(columnElement.getText().contentEquals(homePageName)){
                flag = 1;
                break;
            }
        }

        if(flag == 1) clickSpecificTableLink(numberOfRow, numberOfColumn);
    }

    /**
     * Export a home page by giving it the name of the item from the Home Page table
     * @param homePageName The home page name.
     * @throws Exception
     */
    public void exportHomePageFromTable(String homePageName) throws Exception{
        int flag = 0;
        int numberOfRow = 0;
        int numberOfColumn = 0;
        int numberOfColumnName = 0;

        List<WebElement> row = getRow(1);
        for(WebElement rowElement:row){
            List<WebElement> columns=rowElement.findElements(By.xpath("td"));
            for(WebElement columnElement : columns){
                numberOfColumn++;
                if(columnElement.getText().isEmpty()) break;
            }
        }

        for(WebElement rowElement:row){
            List<WebElement> columns=rowElement.findElements(By.xpath("td"));
            for(WebElement columnElement : columns){
                numberOfColumnName++;
                if(columnElement.getText().contains("Name")) break;
            }
        }

        List<WebElement> column = getColumn(numberOfColumnName);
        for (WebElement columnElement : column){
            numberOfRow++;
            if(columnElement.getText().contentEquals(homePageName)){
                flag = 1;
                break;
            }
        }

        if(flag == 1){
            WebElement cell = getCell(numberOfRow, numberOfColumn);
            List<WebElement> images = cell.findElements(By.xpath(".//img"));
            for (WebElement imageElement : images){
                if(imageElement.getAttribute("title").contentEquals("Save")) imageElement.click();
            }
        }
    }

    /**
     * Delete a home page by giving it the name of the item from the Home Page table
     * @param homePageName The home page name.
     * @throws Exception
     */
    public void deleteHomePageFromTable(String homePageName) throws Exception{
        int flag = 0;
        int numberOfRow = 0;
        int numberOfColumn = 0;
        int numberOfColumnName = 0;

        List<WebElement> row = getRow(1);
        for(WebElement rowElement:row){
            List<WebElement> columns=rowElement.findElements(By.xpath("td"));
            for(WebElement columnElement : columns){
                numberOfColumn++;
                if(columnElement.getText().isEmpty()) break;
            }
        }

        for(WebElement rowElement:row){
            List<WebElement> columns=rowElement.findElements(By.xpath("td"));
            for(WebElement columnElement : columns){
                numberOfColumnName++;
                if(columnElement.getText().contains("Name")) break;
            }
        }

        List<WebElement> column = getColumn(numberOfColumnName);
        for (WebElement columnElement : column){
            numberOfRow++;
            if(columnElement.getText().contentEquals(homePageName)){
                flag = 1;
                break;
            }
        }

        if(flag == 1){
            WebElement cell = getCell(numberOfRow, numberOfColumn);
            List<WebElement> images = cell.findElements(By.xpath(".//img"));
            for (WebElement imageElement : images){
                if(imageElement.getAttribute("title").contentEquals("Delete")){
                    imageElement.click();
                    if(driver.findElements(By.xpath("//div[@class='gwt-DialogBox']")).size()>0){
                        List<WebElement> buttons = driver.findElements(By.xpath("//div[@class='gwt-DialogBox']//button"));
                        for(WebElement button : buttons){
                            if(button.getText().contentEquals("OK")){
                                button.click();
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
}
