package org.example;

import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class BaseTest {
    protected static AppiumDriver<MobileElement> appiumDriver;
    protected static final Logger logger = LogManager.getLogger(BaseTest.class);

    private static final String deviceName = "TA91600CQ1";
    protected static final String packageName = "com.m.qr";
    private static final String platformName = "android";
    private static final String activityName = "com.m.qr.home.onboarding.ui.OnBoardingActivity";

    protected boolean localAndroid=true;
    @BeforeScenario
    public void startScenario() throws MalformedURLException {
        if(localAndroid){
            System.out.println("-----------------Localde Android Testi Başlıyor---------------");
            DesiredCapabilities desiredCapabilities=new DesiredCapabilities();
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
            desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"emulator--5554");
            desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,"com.m.qr");
            desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,"com.m.qr.home.main.ui.HomeActivity");
            desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,3000);
            URL url = new URL("http://127.0.0.2:4723/wd/hub");
            appiumDriver = new AndroidDriver(url,desiredCapabilities);

        }
        else{

            DesiredCapabilities desiredCapabilities=new DesiredCapabilities();
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,MobilePlatform.IOS);
            desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"XCUITest");
            desiredCapabilities.setCapability(MobileCapabilityType.UDID,"İod udid bilgisi girilecek");
            desiredCapabilities.setCapability(IOSMobileCapabilityType.BUNDLE_ID,"Ios uygulamasına ait bundle Id Girilecek");
            desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"Lokalde kullanılan cihazın ismi verilecek");
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,"Localde kullanılan cihazın versiyon bilgisi");
            desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,3000);
            URL url = new URL("http://127.0.0.2:4723/wd/hub");
            appiumDriver = new IOSDriver(url,desiredCapabilities);

        }

    }



    @AfterScenario

    public void afterScenario(){
        appiumDriver.quit();
    }

}
