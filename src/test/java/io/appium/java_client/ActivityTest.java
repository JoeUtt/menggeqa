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

package io.appium.java_client;

import static org.junit.Assert.assertEquals;

import io.appium.java_client.android.AndroidKeyCode;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by joe.shi on 16/8/11.
 */
public class ActivityTest extends TestBase {

    @Before public void setUp() throws Exception {
        driver.startActivity("io.appium.android.apis", ".ApiDemos");
    }

    /**
     * 先打开第一个应用的Activity,断言是否正确打开,
     * 再打开第二个应用的Activity,断言是否正确打开,
     * 不关闭第二个被打开的应用,使用系统Back按钮,断言是否回到第一个被打开应用的Activity
     * @Link openTwoDifferentAppTestCase
     */
    @Test public void openTwoDifferentAppTestCase() {
        driver.startActivity("io.appium.android.apis",
            ".accessibility.AccessibilityNodeProviderActivity",
            "io.appium.android.apis",
            ".accessibility.AccessibilityNodeProviderActivity");
        assertEquals(driver.currentActivity(),
            ".accessibility.AccessibilityNodeProviderActivity");
        driver.startActivity("com.android.contacts",".ContactsListActivity",
            "com.android.contacts",".ContactsListActivity");
        assertEquals(driver.currentActivity(), ".ContactsListActivity");
        driver.pressKeyCode(AndroidKeyCode.BACK);
        assertEquals(driver.currentActivity(),
            ".accessibility.AccessibilityNodeProviderActivity");
    }
}
