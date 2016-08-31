
package com.trucentrix.pageobject;

import org.openqa.selenium.*;
import java.util.*;

/** 
 * AssignmentsTable class is for handling the elements inside "My Assignments" list.
 * @author acachi
 */
public class AssignmentsTable extends Table {
 Miscellaneous numrandom = new Miscellaneous(driver);
    public AssignmentsTable (WebDriver driver){
        super (driver);
        this.driver = driver;
    }

    /**
     * selectAssignmentsFromTable method clicks on a random assignment name from the Assignments table.
     * 
     * @throws Exception
     */
    public void selectAssignmentFromTable () throws Exception{
         boolean tableisnotempty = isTableNotEmpty();
        if(tableisnotempty == true)
        {
            int tableSize = getTableSize();
            int randomRow = numrandom.randomGenerator(tableSize);
            int columnNumber = findColumnFromTable("Title");
            clickSpecificTableLink(randomRow, columnNumber);
        }
    }

    /** 
     * selectAssignmentsOwnerFromTable method clicks on the owner name of a
     * random assignment from My Assignments table.
     * 
     * @throws Exception
     */
    public void selectAssignmentOwnerFromTable () throws Exception{
         boolean tableisnotempty = isTableNotEmpty();
        if(tableisnotempty == true)
        {
            int tableSize = getTableSize();
            int randomRow = numrandom.randomGenerator(tableSize);
            int columnNumber = findColumnFromTable("Owner");
            clickSpecificTableLink(randomRow, columnNumber);
        }
    }

    /**
     * Selects an assignment by giving the name that you want to click in My Assignments table
     * @param AssignmentItemName The assignment's name
     * @throws Exception
     */
    public void selectAssignmentFromTable (String AssignmentItemName) throws Exception{
        int numberOfRow = 0;

        List <WebElement> tableColumn = getColumn(1);
        for (WebElement rowElements : tableColumn){
            numberOfRow ++;
            if (rowElements.getText().contentEquals(AssignmentItemName)) break;
        }
        clickSpecificTableLink(numberOfRow, 1);
    }

    /**
     * Click to the assignment's owner by giving the name of the assignment that you want to click in My Assignments table
     * @param AssignmentItemName The assignment's name
     * @throws Exception
     */
    public void selectAssignmentOwnerFromTable (String AssignmentItemName) throws Exception{
        int numberOfRow = 0;

        List <WebElement> tableColumn = getColumn(4);
        for (WebElement rowElements : tableColumn){
            numberOfRow ++;
            if (rowElements.getText().contentEquals(AssignmentItemName)) break;
        }
        clickSpecificTableLink( numberOfRow, 4);
    }
}
