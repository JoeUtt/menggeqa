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

package com.mengge.ios;

import com.mengge.MobileElement;
import com.mengge.remote.IOSMobileCapabilityType;
import com.mengge.remote.MobileCapabilityType;
import com.mengge.service.local.AppiumDriverLocalService;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;


/**
 * Created by joe.shi on 16/8/15.
 */
public class TestBase {
    private static AppiumDriverLocalService service;
    protected static IOSDriver<MobileElement> driver;

    /**
     * 初始化
     */
    @BeforeClass public static void beforeClass() throws Exception {
        service = AppiumDriverLocalService.buildDefaultService();
        service.start();

        if (service == null || !service.isRunning()) {
            throw new RuntimeException("Appium服务端未启动!");
        }

        File appDir = new File("src/test");
        File app = new File(appDir, "TestApp.app.zip");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9.3");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone Simulator");
        //sometimes environment has performance problems
        capabilities.setCapability(IOSMobileCapabilityType.LAUNCH_TIMEOUT, 500000);
        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        driver = new IOSDriver<>(service.getUrl(), capabilities);
    }


    /**
     * 结束
     */
    @AfterClass public static void afterClass() {
        if (driver != null) {
            driver.quit();
        }
        if (service != null) {
            service.stop();
        }
    }
}
