package com.trucentrix.pageobject;

import org.openqa.selenium.*;
import java.util.*;

/**
 * FavoriteItemsTable class is for handling the elements inside My Favorite Items page.
 * @author acachi
 */
public class FavoriteItemsTable extends Table{
    Miscellaneous numrandom = new Miscellaneous(driver);

    public FavoriteItemsTable (WebDriver driver){
        super (driver);
        this.driver = driver;
    }

/**
 * selectFavoriteItemTable method clicks on a random Favorite item name from the My Favorite Items table.
 * @throws Exception
 */
    public void selectFavoriteItemTable () throws Exception{
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
 * deleteFavoriteItemFromTable method deletes a random item from the favoriteItems table by clicking on its trash image.
 * @throws Exception
 */
    public void deleteFavoriteItemFromTable ()throws Exception{
       boolean tableisnotempty = isTableNotEmpty();
        if(tableisnotempty == true)
        {
            int tableSize = getTableSize();
            int randomRow = numrandom.randomGenerator(tableSize);
            int columnNumber = findColumnFromTable("");
            clickSpecificTableImage(randomRow, columnNumber,"Delete");
        }
    }

    /**
     * Select a favorite item by giving the name that you want to click from the My Favorite Items table
     * @param favoriteItemName The favorite item's name.
     * @throws Exception
     */
    public void selectFavoriteItemTable (String favoriteItemName) throws Exception{
        int flag = 0;
        int numberOfRow = 0;
        List <WebElement> tableColumn = getColumn(1);
        for (WebElement rowElements : tableColumn){
            numberOfRow ++;
            if (rowElements.getText().contentEquals(favoriteItemName)){
                flag=1;
                break;
            }
        }

        if(flag==1) clickSpecificTableLink(numberOfRow, 1);
    }

    /**
     * Delete a favorite item by giving it the name of the item from the My Favorite Items table
     * @param favoriteItemName The favorite item's name.
     * @throws Exception
     */
    public void deleteFavoriteItemFromTable (String favoriteItemName)throws Exception{
        int flag = 0;
        int numberOfRow = 0;
        List <WebElement> tableColumn = getColumn(1);
        displayColumn (tableColumn);
        for (WebElement rowElements : tableColumn){
            numberOfRow ++;
            if (rowElements.getText().contentEquals(favoriteItemName)){
                flag=1;
                break;
            }
        }
        if(flag==1) clickSpecificTableImage(numberOfRow, 4, "Delete");
    }
}
