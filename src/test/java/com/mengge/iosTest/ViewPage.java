package com.mengge.iosTest;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

/**
 * Created by joe.shi on 16/8/22.
 */
public class ViewPage extends TestBase {
    @Test
    public static void openApage() {
        driver.get("http://www.jd.com");
        WebDriverWait wati = new WebDriverWait(driver, 10000);
    }
}
