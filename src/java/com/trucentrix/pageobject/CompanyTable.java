package com.trucentrix.pageobject;
import org.openqa.selenium.*;
import java.util.*;

/**
 * CompanyTable class is for handling the elements inside Companies page in Administration.
 * @author acachi
 */
public class CompanyTable extends Table{
    Miscellaneous numrandom = new Miscellaneous(driver);

    public CompanyTable (WebDriver driver){
        super (driver);
        this.driver = driver;
    }

    /**
     * selectcompanyFromTAble method clicks on a company name from the companies table.
     * 
     * @throws Exception
     */
    public void selectCompanyFromTable() throws Exception
    {
        boolean tableisnotempty = isTableNotEmpty();
        if(tableisnotempty == true)
        {
            int tableSize = getTableSize();
            int randomRow = numrandom.randomGenerator(tableSize);
            int column = findColumnFromTable("Company");
            clickSpecificTableLink(randomRow, column);
        }

    }

    /**
     * selectcompanyFromTable method clicks on the several company administrator from the companies table.
     * 
     * @throws Exception
     */
    public void selectAdministratorFromTable ()throws Exception
    {
        int randomRow = 0;
        int column = 0;

       boolean tableisnotempty = isTableNotEmpty();
        if(tableisnotempty == true)
        {
            int tableSize = getTableSize();
            randomRow = numrandom.randomGenerator(tableSize);
            column = findColumnFromTable("Administrator(s)");

            WebElement cell = getCell(randomRow, column);
            List<WebElement> names = cell.findElements(By.xpath(".//div"));

            clickSeveralLinksFromTable(names);
       }
     
    }

    /**
     * deleteCompanyFromTable method deletes a company from the companies table by clicking on its trash image.
     * 
     * @throws Exception
     */
   public void deleteCompanyFromTable() throws Exception
   {
      int randomRow = 0;
        int column = 0;

       boolean tableisnotempty = isTableNotEmpty();
        if(tableisnotempty == true)
        {
            int tableSize = getTableSize();
            randomRow = numrandom.randomGenerator(tableSize);
            column = findColumnFromTable("");

            clickSpecificTableImage(randomRow, column,"Delete");
            confirmDialogAlertMessage();
         /*   if(driver.findElements(By.xpath("//div[@class='gwt-DialogBox']")).size()>0)
             {
               List<WebElement> buttons = driver.findElements(By.xpath("//div[@class='gwt-DialogBox']//button"));
               for(WebElement button : buttons)
               {
                   if(button.getText().contentEquals("OK"))
                   {
                       button.click();
                       break;
                   }
               }
             }*/


       }
                     
   }

   /**
    * Selects a company by giving the name that you want to click in the Companies table
    * @param companyName The company's name.
    * @throws Exception
    */
   public void selectCompanyFromTable(String companyName) throws Exception{
       int numberOfRow = 0;
       int numberOfColumn = 0;
       int flag = 0;

       List <WebElement> row = getRow(1);
       for (WebElement rowElement : row){
           List<WebElement> columns = rowElement.findElements(By.xpath("td"));
           for(WebElement colElement : columns){
               numberOfColumn ++;
               if(colElement.getText().equals("Company")) break;
           }
       }

       List <WebElement> columns = getColumn(numberOfColumn);
       for (WebElement columnElement : columns){
           numberOfRow ++;
           if (columnElement.getText().contentEquals(companyName)){
               flag = 1;
               break;
           }
       }
       if(flag == 1) clickSpecificTableLink (numberOfRow, numberOfColumn);
   }

   /**
    * Selects a company's administrator by giving the name of the company in the Companies table.
    * @param companyName The company's name.
    * @throws Exception
    */
   public void selectAdministratorFromTable (String companyName)throws Exception{
       int numberOfRow = 0;
       int numberOfColumn = 0;
       int numberOfColumnName = 0;
       int flag = 0;

       List <WebElement> row = getRow(1);
       for (WebElement rowElement : row){
           List<WebElement> columns =rowElement.findElements(By.xpath("td"));
           for(WebElement colElement : columns){
               numberOfColumn ++;
               if(colElement.getText().equals("Administrator(s)")) break;
           }
       }

       for (WebElement rowElement : row){
           List<WebElement> columns =rowElement.findElements(By.xpath("td"));
           for(WebElement colElement : columns){
               numberOfColumnName ++;
               if(colElement.getText().equals("Company")) break;
           }
       }
       
       List <WebElement> columns = getColumn(numberOfColumnName);
       for (WebElement columnElement : columns){
           numberOfRow ++;
           if (columnElement.getText().contentEquals(companyName)){
               flag = 1;
               break;
           }
       }

       if (flag==1){
           WebElement cell = getCell(numberOfRow, numberOfColumn);
           List<WebElement> names = cell.findElements(By.xpath(".//div"));
           for(WebElement name : names){
               name.click();
               if(driver.findElements(By.xpath("//div[@class='gwt-DialogBox']")).size()>0){
                   List<WebElement> buttons = driver.findElements(By.xpath("//div[@class='gwt-DialogBox']//button"));
                   for(WebElement button : buttons){
                       if(button.getText().contentEquals("Cancel")){
                           button.click();
                           break;
                       }
                   }
               }
           }
       }
   }

   /**
    * Delete a company by giving the name from the Companies table.
    * @param companyName The company's name.
    * @throws Exception
    */
   public void deleteCompanyFromTable(String companyName) throws Exception{
       int numberOfRow = 0;
       int numberOfColumn = 0;
       int numberOfColumnName = 0;
       int flag = 0;

       List<WebElement> row = getRow(1);
       for (WebElement rowElement : row){
           List<WebElement> columns = rowElement.findElements(By.xpath("td"));
           for(WebElement colElement : columns){
               numberOfColumn ++;
               if(colElement.getText().isEmpty()) break;
           }
       }
       for (WebElement rowElement : row){
           List<WebElement> columns = rowElement.findElements(By.xpath("td"));
           for(WebElement colElement : columns){
               numberOfColumnName ++;
               if(colElement.getText().contentEquals("Company")) break;
           }
       }

       List <WebElement> columns = getColumn(numberOfColumnName);
       for (WebElement columnElement : columns){
           numberOfRow ++;
           if (columnElement.getText().contentEquals(companyName)){
               flag = 1;
               break;
           }
       }

       if(flag == 1){
           clickSpecificTableImage(numberOfRow, numberOfColumn, "Delete");
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