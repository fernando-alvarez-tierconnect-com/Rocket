package com.trucentrix.test;

import com.thoughtworks.selenium.Selenium;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LocalLogs;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


import java.io.File;
import java.net.URL;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: falvarez
 * Date: 5/27/14
 * Time: 6:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class Utils {

    public static WebDriver setup(Selenium selenium, WebDriver driver) throws Exception {
        FileProperties fileProperties = FileProperties.getFileData();
        driver = setUpFirefox(driver, fileProperties.getUrlApp());
        return driver;
    }

    public static WebDriver setUpFirefox(WebDriver driver, String baseUrl) throws Exception{
        FileProperties fileProperties = FileProperties.getFileData();

        if(driver == null){
//            FirefoxBinary firefoxBinary = new FirefoxBinary();
//            firefoxBinary.setEnvironmentProperty("DISPLAY",":77");
//            firefoxBinary.setTimeout(20000l);
//            FirefoxProfile profile = new FirefoxProfile();
//            driver = new FirefoxDriver(firefoxBinary, profile);

//            FirefoxProfile firefoxProfile = new FirefoxProfile();
//            firefoxProfile.setPreference("webdriver_firefox_port", 6954);
//            firefoxProfile.setPreference("browser.download.folderList",2);
//            firefoxProfile.setPreference("browser.download.manager.showWhenStarting",false);
//            firefoxProfile.setPreference("browser.download.dir","D:\\Automation_downloads");
//            firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk","text/xml");

//            File pathToBinary = new File("C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
//            FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
//            FirefoxProfile firefoxProfile = new FirefoxProfile();
//            driver = new FirefoxDriver(ffBinary,firefoxProfile);
//            driver = new FirefoxDriver(firefoxProfile);

//            System.setProperty("webdriver.chrome.driver", "C:\\Trubiquity\\automation\\trubiquity-qa\\WebApp_TestAutomation\\TRUcentrix\\src\\drivers\\Chrome_2.21\\chromedriver.exe");
//            driver = new ChromeDriver();


            DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
            capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, false);
            capabilities.setCapability("requireWindowFocus", true);
//            System.setProperty("webdriver.ie.driver", "C:\\Trubiquity\\automation\\trubiquity-qa\\WebApp_TestAutomation\\Trucentrix_Clean\\drivers\\2.52_64\\IEDriverServer.exe");
//            driver = new InternetExplorerDriver(capabilities);

            //Remote Server
            System.setProperty("webdriver.ie.driver", "\\\\10.100.0.23\\share\\drivers\\2.52_64\\IEDriverServer.exe");
//            driver = new RemoteWebDriver(new URL("http://10.100.0.253:4444/wd/hub"), capabilities);
            driver = new RemoteWebDriver(new URL("http://10.100.0.253:5566/wd/hub"), capabilities);
//            driver = new RemoteWebDriver(new URL("http://10.100.1.176:4444/wd/hub"), DesiredCapabilities.internetExplorer());
//            driver = new RemoteWebDriver(new URL("http://10.100.1.24:5555/wd/hub"), DesiredCapabilities.chrome());


            baseUrl = baseUrl + fileProperties.getBaseUrlApp();
            driver.get(baseUrl);
            return driver;
        } else return driver;
    }

    public static void tearDown(WebDriver driver){
        if(driver != null) {
            driver.close();
            driver.quit();
        }
    }
}
