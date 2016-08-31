package com.trucentrix.pageobject;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.io.*;

/**
 * This class is for the login process in TRUcentrix application.
 * There are two types of logging in the application:
 *      -Login with an user for the first time, this is represented
 *       with the method firstTimeLogin().
 *      -Login with an user already logged in before, this is
 *       represented with the method commonLogin().
 * @author rcadima
 */

public class Login{
    private WebDriver driver;
    private String user, password;
    private WebDriverWait wait;
    BufferedReader incoming = new BufferedReader (new InputStreamReader(System.in));

    public Login(WebDriver driver){
        this.driver=driver;
        wait = new WebDriverWait(driver,300);
    }

    /**
     * Login with a user for the first time.
     * @throws Exception
     */
    public void loginFirstTime() throws Exception {
        do{
            System.out.println("Introduce the user name: ");
            user = incoming.readLine();
            System.out.println("Introduce the password: ");
            password = incoming.readLine();
            driver.findElement(By.name("j_username")).clear();
            driver.findElement(By.name("j_username")).sendKeys(user);
            driver.findElement(By.name("j_password")).clear();
            driver.findElement(By.name("j_password")).sendKeys(password);
            driver.findElement(By.linkText("Login")).click();
          }while((driver.getTitle().contains("Login")));

        driver.findElement(By.xpath("//input[@value='I Accept']")).click();
        driver.findElement(By.name("newpassword")).clear();
        driver.findElement(By.name("newpassword")).sendKeys(password);
        driver.findElement(By.name("confirmpassword")).clear();
        driver.findElement(By.name("confirmpassword")).sendKeys(password);
        new Select(driver.findElement(By.id("passwdHint"))).selectByVisibleText("What is your pet's name?");
        driver.findElement(By.id("passwdHintAnswer")).clear();
        driver.findElement(By.id("passwdHintAnswer")).sendKeys("aaa");
        driver.findElement(By.cssSelector("input.login")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]")));
    }

    /**
     * Login with a user that is not his first time.
     * @throws Exception
     */
    public void commonLogin() throws Exception {
        do{
            System.out.println("Introduce the user name: ");
            user = incoming.readLine();
            System.out.println("Introduce the password: ");
            password = incoming.readLine();
            driver.findElement(By.name("j_username")).clear();
            driver.findElement(By.name("j_username")).sendKeys(user);
            driver.findElement(By.name("j_password")).clear();
            driver.findElement(By.name("j_password")).sendKeys(password);
            driver.findElement(By.linkText("Login")).click();
        }while((driver.getTitle().contains("Login")));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]")));
    }
    /**
     * Login with a specified account
     * @param user The username
     * @param password The password
     * @throws Exception
     */
    public void loginAss(String user, String password) throws Exception {
        do{
            driver.findElement(By.name("j_username")).clear();
            driver.findElement(By.name("j_username")).sendKeys(user);
            driver.findElement(By.name("j_password")).clear();
            driver.findElement(By.name("j_password")).sendKeys(password);
            driver.findElement(By.linkText("Login")).click();
        }while((driver.getTitle().contains("Login")));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]")));
    }
}





