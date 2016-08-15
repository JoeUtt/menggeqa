package com.mengge.ios;

import com.mengge.MobileBy;
import org.junit.Test;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.alertIsPresent;

/**
 * Created by joe.shi on 16/8/15.
 */
public class AlertTest extends TestBase {

    @Test public void acceptAlertTest() {
        driver.findElement(MobileBy
            .IosUIAutomation(".elements().withName(\"show alert\")")).click();
        WebDriverWait wating = new WebDriverWait(driver, 10000);
        wating.until(alertIsPresent()).accept();

    }

    @Test public void cancelAlertTest() {
        driver.findElement(MobileBy
            .IosUIAutomation(".elements().withName(\"show alert\")")).click();
        WebDriverWait wating = new WebDriverWait(driver, 10000);
        wating.until(alertIsPresent()).dismiss();
    }
}
