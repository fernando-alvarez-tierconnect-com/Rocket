
package com.trucentrix.pageobject;

import org.openqa.selenium.*;
import java.util.*;

/**
 * ProjectsTable class is for handling the elements inside My Projects page.
 * @author acachi
 */
public class ProjectsTable extends Table{
     Miscellaneous numrandom = new Miscellaneous(driver);
    String content = "//table[@class='sortableTable']";
    public ProjectsTable (WebDriver driver){
        super (driver);
        this.driver = driver;
    }

    /**
     * selectProjectsFromTable method clicks on a Project name from the My Projects table.
     * @throws Exception
     */
    public void selectProjectFromTable ()throws Exception{
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
     * selectAdminProjectFromTable method clicks on the project Administrator from the Projects table.
     * @throws Exception
     */
    public void selectAdminProjectFromTable ()throws Exception{
        boolean tableisnotempty = isTableNotEmpty();
        if(tableisnotempty == true)
        {
            int tableSize = getTableSize();
            int randomRow = numrandom.randomGenerator(tableSize);
            int columnNumber = findColumnFromTable("");
            clickSpecificTableLink(randomRow, columnNumber);
        }
    }

    /**
     * Select a Project by giving the name that you want to click from the My Projects table.
     * @param projectName The project's name.
     * @throws Exception
     */
    public void selectProjectFromTable (String projectName)throws Exception{
        int flag = 0;
        int numberOfRow = 0;

        List <WebElement> columnProject = getColumn(1);
        for (WebElement columnElement : columnProject){
            numberOfRow ++;
            if(columnElement.getText().contentEquals(projectName)){
                flag=1;
                break;
            }
        }
        if(flag==1) clickSpecificTableLink(numberOfRow, 1);
    }

    /**
     * Select a Project's administrator by giving the project name that you want to click from the My Projects table.
     * @param projectName The project's name.
     * @throws Exception
     */
    public void selectAdminProjectFromTable (String projectName)throws Exception{
        int flag = 0;
        int numberOfRow = 0;
        int numberOfColumn = 0;

        List <WebElement> tableColumn = getColumn(1);
        for (WebElement rowElements : tableColumn){
            numberOfRow ++;
            if (rowElements.getText().contentEquals(projectName)) break;
        }

        List<WebElement> rowNumber = getRow(numberOfRow);
        for (WebElement rowElement : rowNumber){
            List<WebElement> columns=rowElement.findElements(By.xpath("td"));
            for(WebElement colElement : columns){
                numberOfColumn ++;
                if(colElement.getText().contentEquals("Administration")){
                    flag = 1;
                    break;
                }
            }
        }
        if(flag==1) clickSpecificTableLink(numberOfRow, numberOfColumn);
    }
}