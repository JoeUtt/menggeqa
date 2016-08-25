/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mengge.iosTest;

import com.mengge.MobileBy;
import com.mengge.MobileElement;
import com.mengge.ios.IOSDriver;
import com.mengge.remote.IOSMobileCapabilityType;
import com.mengge.remote.MobileCapabilityType;
import com.mengge.service.local.AppiumDriverLocalService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

import static org.openqa.selenium.support.ui.ExpectedConditions.alertIsPresent;

/**
 * Created by joe.shi on 16/8/22.
 */
public class TestBase {
    private static AppiumDriverLocalService service;
    protected static IOSDriver<MobileElement> driver;

    @BeforeClass
    public static void beforeClass() throws Exception {
        service = AppiumDriverLocalService.buildDefaultService();
        service.start();

        if (service == null || !service.isRunning()) {
            throw new RuntimeException("Appium服务未启动");
        }

        //File appDir = new File("src/test");
        //File appName = new File(appDir, "TestApp.app.zip");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone Simulator");
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "safari");
        //capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9.3");

        //capabilities.setCapability(MobileCapabilityType.APP, "Safari");
        capabilities.setCapability(IOSMobileCapabilityType.LAUNCH_TIMEOUT, 500000);

        driver = new IOSDriver<>(service.getUrl(), capabilities);
    }

    @AfterClass
    public static void afterClass() {
        if (service != null) {
            service.stop();
        }

        /*if (driver != null) {
            driver.quit();
        }*/
    }
}
