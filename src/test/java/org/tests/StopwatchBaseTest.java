package org.tests;

import com.google.common.collect.ImmutableMap;
import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public abstract class StopwatchBaseTest extends BaseTest {

    protected abstract int getShapeIndex();

    @Step("Verify clock is 0:00.00 at start")
    @Test(priority = 1)
    public void zeroValueAtStartTest() {
        String actualValue = homePage.getClockTime();
        String expectedValue = "00:00.00";
        Assert.assertEquals(actualValue, expectedValue);
    }

    @Step("verify clock starts after clicking")
    @Test(priority = 3)
    public void startClock() throws InterruptedException {
        homePage.clickOnClockFace();
        TimeUnit.SECONDS.sleep(3);
        String actualValue = homePage.getClockTime();
        String defaultValue = "00:00.00";
        Assert.assertNotEquals(actualValue, defaultValue);
    }

    @Step("Start clock and stop after few seconds")
    @Test(priority = 4)
    public void startAndStopClock() throws InterruptedException {
        String startValue = homePage.getClockTime();
        homePage.clickOnClockFace();
        TimeUnit.SECONDS.sleep(3);
        homePage.clickOnClockFace();
        String currentValue = homePage.getClockTime();
        Assert.assertNotEquals(currentValue, startValue);
    }

    @Step("Start clock, stop and reset to zero")
    @Test(priority = 2)
    public void resetClock() throws InterruptedException {
        String actualValue = homePage.getClockTime();
        String defaultValue = "00:00.00";
        Assert.assertEquals(actualValue, defaultValue);
        homePage.clickOnClockFace();
        TimeUnit.SECONDS.sleep(3);
        homePage.clickOnClockFace();
        String currentValue = homePage.getClockTime();
        Assert.assertNotEquals(currentValue, defaultValue);
        homePage.longPressOnClockFace();
        String afterResetValue = homePage.getClockTime();
        Assert.assertEquals(afterResetValue, defaultValue);
    }

    @BeforeMethod(alwaysRun = true)
    public void setShapeAndState() {
        ((JavascriptExecutor)driver).executeScript("mobile: startActivity", ImmutableMap.of(
                "intent", "toplab18.app.simpleststopwatch2/.MainActivity"
        ));
        homePage.clickOnUpperMenu();
        homePage.selectShape(getShapeIndex()); // Select the shape dynamically
        homePage.clickOnUpperMenu();
    }

    @AfterMethod
    public void resetActivity() {
        driver.navigate().back();
    }
}
