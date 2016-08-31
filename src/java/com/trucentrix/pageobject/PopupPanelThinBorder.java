package com.trucentrix.pageobject;

import java.util.concurrent.TimeUnit;
import java.util.List;
import org.openqa.selenium.*;
import org.testng.Assert;

/**
 *
 * @author rcadima
 */
public class PopupPanelThinBorder extends PopupPanel{
    public PopupPanelThinBorder(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    public WebElement getPanelThinBorder(){
        WebElement thinPanel = null;
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        if(driver.findElements(By.xpath("//div[@class='popupPanelThinBorder']")).size()>0){
            thinPanel = driver.findElement(By.xpath("//div[@class='popupPanelThinBorder']"));
        }else{
            Assert.fail("There is no popup panel present");
        }
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        return thinPanel;
    }

    @Override
    public void clickPanelItem(WebElement thinPanel, String itemLabel){
        if(thinPanel==null){
            Assert.fail("No thin panel selected");
        }else{
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            if(thinPanel.findElements(By.xpath(".//td[@class='gwt-MenuItem']")).size()>0){
                List<WebElement> items = thinPanel.findElements(By.xpath(".//td[@class='gwt-MenuItem']"));
                for(WebElement item : items){
                    if(item.getText().contains(itemLabel)){
                        item.click();
                        break;
                    }
                }
            }
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        }
    }
}
