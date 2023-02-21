package com.example.testapps.case2;

import junit.framework.TestCase;

import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;
import java.util.Objects;

import io.appium.java_client.android.AndroidDriver;

public class CoreTestCase extends TestCase {

    protected AndroidDriver driver;
    private static final String AppiumUrl = "http://0.0.0.0:4723/wd/hub";

    @Override
    public void setUp() throws Exception {
        super.setUp();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Pixel");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        if (Objects.requireNonNull(System.getProperty("user.name")).equals("Sanek")) {
            capabilities.setCapability("app", "/home/sanek/documents/secondLession/apks/org.wikipedia.apk");
        } else if (Objects.requireNonNull(System.getProperty("user.name")).equals("Andrey")) {
            capabilities.setCapability("app", "C:\\home\\sanek\\LearnQA\\TestApps\\app\\apks\\org.wikipedia.apk");
        } else if (Objects.requireNonNull(System.getProperty("user.name")).equals("chel")) {
            capabilities.setCapability("app", "D:\\LearnQA\\AutoQA\\LearnQA\\app\\apks\\org.wikipedia.apk");
        }
        driver = new AndroidDriver(new URL(AppiumUrl), capabilities);
        if (driver.getOrientation().name().equals("LANDSCAPE"))
            driver.rotate(ScreenOrientation.PORTRAIT);
    }

    @Override
    public void tearDown() throws Exception {
        driver.quit();
        super.tearDown();
    }

    protected void rotateScreenPortrait() {
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    protected void rotateScreenLandscape() {
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    protected void backgroundApp() {
        driver.runAppInBackground(Duration.ofSeconds(2));
    }
}