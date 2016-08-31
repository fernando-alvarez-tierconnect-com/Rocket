package com.trucentrix.pageobject;

import org.openqa.selenium.*;


/**
 * SubscriptionTable class is for handling the elements inside  My Subscriptions table.
 * @author acachi
 */
public class SubscriptionsTable extends Table{
    Miscellaneous numrandom = new Miscellaneous(driver);

    public SubscriptionsTable (WebDriver driver){
        super (driver);
        this.driver = driver;
    }

     /**
     * selectSubscriptionFromTAble method clicks on a random subscription name from the My Subscriptions table.
     * @throws Exception
     */
    public void selectSubscriptionFromTable () throws Exception
    {
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
     * selectSubscriptionOwnerFromTable method clicks on a random item owner from Subscriptions table.
     * @throws Exception
     */
        public void selectSubscriptionOwnerFromTable () throws Exception{
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
       * selectSubscriptionLocationFromTable method clicks on the location link of a random subscription.
       * @param table
       * @throws Exception
       */
    public void selectSubscriptionLocationFromTable () throws Exception{
        boolean tableisnotempty = isTableNotEmpty();
        if(tableisnotempty == true)
        {
            int tableSize = getTableSize();
            int randomRow = numrandom.randomGenerator(tableSize);
            int columnNumber = findColumnFromTable("Location");
            clickSpecificTableLink(randomRow, columnNumber);
        }
    }

    /**
    * deleteSubscriptionItemFromTable method deletes a random subscription from My Subscriptions table by clicking on its trash image.
    * @param table
    * @throws Exception
    */
    public void deleteSubscriptionItemFromTable () throws Exception
    {
        boolean tableisnotempty = isTableNotEmpty();
        if(tableisnotempty == true)
        {
            int tableSize = getTableSize();
            int randomRow = numrandom.randomGenerator(tableSize);
            int columnNumber = findColumnFromTable("");
            clickSpecificTableImage(randomRow, columnNumber,"Unsubscribe");
        }
           
       
    }
}