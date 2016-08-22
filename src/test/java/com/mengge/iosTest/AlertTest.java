package com.mengge.iosTest;

import com.mengge.MobileBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import static org.openqa.selenium.support.ui.ExpectedConditions.alertIsPresent;

/**
 * Created by joe.shi on 16/8/22.
 */
public class AlertTest extends TestBase {
    @Test public static void acceptAlert() {
        driver.findElement(MobileBy
            .IosUIAutomation(".elements().withName(\"show alert\")")).click();
        WebDriverWait wating = new WebDriverWait(driver, 10000);
        wating.until(alertIsPresent()).accept();
    }

    @Test public void dismissAlertTest() {
        driver.findElement(MobileBy
            .IosUIAutomation(".elements().withName(\"show alert\")")).click();
        WebDriverWait wating = new WebDriverWait(driver, 10000);
        wating.until(alertIsPresent()).dismiss();
    }
}
