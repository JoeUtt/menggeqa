# 前提: 

- Android开发环境. 

- 安装appium服务端的桌面程序[Windows or MacOS](https://bitbucket.org/appium/appium.app/downloads/),或者使用 _npm_ 
_$ npm install -g appium_ or _$ npm install appium@required_version_

# 如何启动APP

```java
import java.io.File;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.MobileElement;
import java.net.URL;

...
File app  = new File("保存有*.apk文件的文件夹");
DesiredCapabilities capabilities = new DesiredCapabilities();
capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "模拟器名字");
capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
//还有其他的capability,请参考api文档 
AppiumDriver<MobileElement> driver = new AndroidDriver<>(
new URL("http://target_ip:used_port/wd/hub"), capabilities);
//若使用本地服务器target_ip可以设置为127.0.0.1 or 0.0.0.0
//默认端口是4723)
```

如果是启动浏览器: 

```java
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.remote.MobileBrowserType;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import java.net.URL;


...
DesiredCapabilities capabilities = new DesiredCapabilities();
capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "模拟器名字");
capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, MobileBrowserType.CHROME);
//使用MobileBrowserType.BROWSER
//来指定android系统浏览器
...
//还有其他的capability,请参考api文档 
AppiumDriver<MobileElement> driver = new AndroidDriver<>(
new URL("http://target_ip:used_port/wd/hub"), capabilities);
```
