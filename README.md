# 蒙歌QA开放平台 #

### 说明 ###

- 蒙歌QA Mobile自动化框架JAVA版,符合[Mobile JSON Wire Protocol标准(原文)](https://github.com/SeleniumHQ/mobile-spec/blob/master/spec-draft.md)

- 基于Appium开源项目java-client-4.1.0编写

- [API 文档](http://appium.github.io/java-client/)

### 更新列表 ###

### 结构 ###

- `io.appium.java_client.AppiumDriver`类由Selenium Java Client的`org.openqa.selenium.remote.RemoteWebDriver`类扩展而来.

- `io.appium.java_client.AppiumDriver`类包含了所有iOS和Android共享使用的方法.

- `io.appium.java_client.ios.IOSDriver` and `io.appium.java_client.android.AndroidDriver`两个类扩展自`io.appium.java_client.AppiumDriver`. 并提供了更多的方法以及一些方法的具体实现.

- 同理`io.appium.java_client.ios.IOSElement` 和 `io.appium.java_client.android.AndroidElement` 两个类就是`io.appium.java_client.MobileElement`类的子类.

### 增加的方法 ###

- startActivity()
- resetApp()
- getAppStringMap()
- pressKeyCode()
- longPressKeyCode()
- longPressKey()
- currentActivity()
- getDeviceTime()
- pullFile()
- pushFile()
- pullFolder()
- replaceValue()
- hideKeyboard()
- runAppInBackground()
- performTouchAction()
- performMultiTouchAction()
- tap()
- swipe()
- pinch()
- zoom()
- isAppInstalled()
- installApp()
- removeApp()
- launchApp()
- closeApp()
- endTestCoverage()
- isLocked()
- shake()
- getSessionDetails()
- openNotifications()
- Context Switching: .context(), .getContextHandles(), getContext())
- setConnection(), getConnection()
- ignoreUnimportantViews(), getSettings()
- toggleLocationServices()
- lockDevice()
- unlockDevice()

### 增加的Locators ###

- findElementByAccessibilityId()
- findElementsByAccessibilityId()
- findElementByIosUIAutomation()
- findElementsByIosUIAutomation()
- findElementByAndroidUIAutomator()
- findElementsByAndroidUIAutomator()

