package com.trucentrix.pageobject;

import org.openqa.selenium.*;
import java.util.*;

/**
 * FormsTable class is for handling the elements inside My Forms page.
 * @author acachi
 */
public class FormsTable extends Table{
    Miscellaneous numrandom = new Miscellaneous(driver);
    public FormsTable (WebDriver driver){
        super (driver);
        this.driver = driver;
    }

    /**
    * startNewFormFromTable method opens a random form page.
    * @throws Exception
    */
    public void startNewFormFromTable () throws Exception{
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
     * Start a new form by giving the name of the one you want from the Forms table
     * @param formItemName The form item name
     * @throws Exception
     */
    public void startNewFormFromTable (String formItemName) throws Exception{
        int flag = 0;
        int numberOfRow = 0;

        List <WebElement> tableColumn = getColumn(1);
        for (WebElement rowElements : tableColumn){
            numberOfRow ++;
            if (rowElements.getText().contentEquals(formItemName)){
                flag=1;
                break;
            }
        }

        if(flag==1) clickSpecificTableLink(numberOfRow, 3);
    }
}
