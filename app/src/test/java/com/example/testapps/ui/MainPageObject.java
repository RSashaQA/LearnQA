package com.example.testapps.ui;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class MainPageObject {

    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver) {
        this.driver = driver;
    }

    public void assertElementHasText(By by, String expected_value, String error_message) {
        WebElement element = waitForElementPresent(by, "Element " + by + " not found", 5);
        String actual_value = element.getText();
        Assert.assertEquals(error_message, expected_value, actual_value);
    }

    public void notWaitForElementPresent(By by, String error_message) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(0), Duration.ofSeconds(0));
        wait.withMessage(error_message + "\n");
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    public List<WebElement> waitForElementsPresents(By by, String error_message, long timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(by)
        );
    }

    public void waitForElementPresent(By by, String error_message) {
        waitForElementPresent(by, error_message, 15);
    }

    public void waitForElementAndClick(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
    }

    public void waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
    }

    public void waitForElementNotPresent(By by, String error_message, long timeoutInSeconds) {
        sleep();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.withMessage(error_message + "\n");
        wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    public void waitForElementAndClear(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
    }

    public void sleep() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void swipeUp(int timeOfSwipe) {
        TouchAction action = new TouchAction<AndroidTouchAction>((PerformsTouchActions) driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);
        action
                .press(PointOption.point(x, start_y))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(timeOfSwipe)))
                .moveTo(PointOption.point(x, end_y))
                .release()
                .perform();
    }

    public void swipeElementToLeft(By by, String error_message) {
        WebElement element = waitForElementPresent(
                by,
                error_message,
                10
        );
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction<AndroidTouchAction>((PerformsTouchActions) driver);
        action
                .press(PointOption.point(right_x, middle_y))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)))
                .moveTo(PointOption.point(left_x, middle_y))
                .release()
                .perform();
    }

    public void swipeUpQuick() {
        swipeUp(200);
    }


    public void swipeUpToFindElement(By by, String error_message, int max_swipes) {
        int already_swiped = 0;
        while (driver.findElements(by).size() == 0) {

            if (already_swiped > max_swipes) {
                waitForElementPresent(by, "Cannot find element by swiping  up. \n" + error_message, 0);
                return;
            }
            swipeUpQuick();
            ++already_swiped;
        }
    }

    public int getAmountOfElements(By by) {
        List<WebElement> elements = driver.findElements(by);
        return elements.size();
    }

    public void assertElementNotPresent(By by, String error_message) {
        int amount_of_elements = getAmountOfElements(by);
        if (amount_of_elements > 0) {
            String default_message = "An element '" + by.toString() + "' supposed to be present.";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

    public String waitForElementAndGetText(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        return element.getText();
    }
}
