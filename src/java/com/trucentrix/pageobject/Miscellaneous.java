package com.trucentrix.pageobject;

import org.openqa.selenium.*;

import java.io.*;
import java.util.Random;

/**
 * This class contents all the additional methods to use in another method.
 * These methods are a complement for others.
 * 
 * @author rcadima
 */

public class Miscellaneous{
    private WebDriver driver;
    private String user, password;
    BufferedReader incoming = new BufferedReader (new InputStreamReader(System.in));

    public Miscellaneous(WebDriver driver){
        this.driver=driver;
    }

    /**This class generates a Random number based in the max number received as parameter.
     *
     * @param maxNumberOfTheRange
     * @return
     * @throws Exception
     */
     public int randomGenerator (int maxNumberOfTheRange) throws Exception
     {
        int randomNumber = 0;
        
        if(maxNumberOfTheRange > 1)
        {
        Random generator = new Random();
        randomNumber = generator.nextInt(maxNumberOfTheRange-1) + 2;
        }
        return randomNumber;
    }
}





