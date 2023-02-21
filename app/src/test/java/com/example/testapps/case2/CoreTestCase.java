package com.example.testapps.case2;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class CoreTestCase extends TestCase {

    protected AppiumDriver driver;
    private static String AppiumUrl = "http://localhost:4723/wd/hub";

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
        if (System.getProperty("user.name").equals("Sanek")) {
            capabilities.setCapability("app", "/home/sanek/documents/secondLession/apks/org.wikipedia.apk");
        } else if (System.getProperty("user.name").equals("Andrey")) {
            capabilities.setCapability("app", "C:\\home\\sanek\\LearnQA\\TestApps\\app\\apks\\org.wikipedia.apk");
        } else if (System.getProperty("user.name").equals("chel")) {
            capabilities.setCapability("app", "D:\\LearnQA\\AutoQA\\secondLession\\apks\\org.wikipedia.apk");
        }
            driver = new AppiumDriver(new URL(AppiumUrl), capabilities);

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

        protected void backgroundApp(int duration_in_seconds) {
            driver.runAppInBackground(Duration.ofSeconds(duration_in_seconds));
        }

    }
