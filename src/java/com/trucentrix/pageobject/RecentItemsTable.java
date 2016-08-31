package com.trucentrix.pageobject;

import org.openqa.selenium.*;
import java.util.*;


/**
 * RecentItemsTable class is for handling the elements inside My Recent Items page.
 * @author acachi
 */
public class RecentItemsTable extends Table{
 Miscellaneous numrandom = new Miscellaneous(driver);
    public RecentItemsTable (WebDriver driver){
        super (driver);
        this.driver = driver;
    }

    /** 
     * selectRecentItemTable method clicks on a random Recent Item name from the Recent Items table.
     * @throws Exception
     */
    public void selectRecentItemTable () throws Exception{
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
     * Select a recent item by giving the name that you want to click from the Recent Items table.
     * @param recentItemName The recent item name.
     * @throws Exception
     */
    public void selectRecentItemTable (String recentItemName) throws Exception{
        int flag = 0;
        int numberOfRow = 0;

        List <WebElement> tableColumn = getColumn(1);
        for (WebElement rowElements : tableColumn){
            numberOfRow ++;
            if (rowElements.getText().contentEquals(recentItemName)){
                flag= 1;
                break;
            }
        }
        if(flag==1) clickSpecificTableLink(numberOfRow, 1);
    }
}
