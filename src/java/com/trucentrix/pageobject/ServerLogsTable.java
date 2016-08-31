package com.trucentrix.pageobject;

import org.openqa.selenium.*;

/**
 * ServerLogsTable class is for handling the elements inside "Server Logs" page in Administration.
 * @author rcadima
 */
public class ServerLogsTable extends Table {
 Miscellaneous numrandom = new Miscellaneous(driver);
    public ServerLogsTable (WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    /**
     * selectServerLogFromTable method clicks on a random Server Log name from the Server Logs table.
     * @throws Exception
     */
    public void selectServerLogFromTable () throws Exception
    {
        boolean tableisnotempty = isTableNotEmpty();
        if(tableisnotempty == true)
        {
            int tableSize = getTableSize();
            int randomRow = numrandom.randomGenerator(tableSize);
            int column = findColumnFromTable("Name");
            clickSpecificTableLink(randomRow, column);
        }
    }
}
