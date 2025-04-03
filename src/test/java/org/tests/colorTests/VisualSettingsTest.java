package org.tests.colorTests;

import com.google.common.collect.ImmutableMap;
import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.tests.BaseTest;
import utils.ColorDetector;

import java.io.IOException;

import static utils.AppiumUtils.getBackgroundPoint;
import static utils.AppiumUtils.getPointInsideWatchfaceShape;

public class VisualSettingsTest extends BaseTest {

    @Step("Verify color of shape has changed")
    @Test(dataProvider = "getData", groups = "colorSchema")
    public void changeWatchFaceColor(int colorIndex) throws IOException {
        homePage.clickOnUpperMenu();
        ColorDetector colorDetector = new ColorDetector(driver);
        int[] coordinates = getPointInsideWatchfaceShape(homePage.getClockFaceBounds());
        float[] originalColors = colorDetector.getHSLFromScreen(coordinates[0], coordinates[1]);
        homePage.selectBackgroundColor(colorIndex);
        float[] newColor = colorDetector.getHSLFromScreen(coordinates[0], coordinates[1]);
        Assert.assertNotEquals(originalColors[0], newColor[0], "Hue value should change after selecting a new color.");
    }

    @Step("Verify dark mode is switched on")
    @Test(groups = "darkMode")
    public void switchToDarkMode() throws IOException {
        homePage.clickOnUpperMenu();
        ColorDetector colorDetector = new ColorDetector(driver);
        int[] coordinates = getBackgroundPoint(homePage.getClockFaceBounds(), homePage.getChangeModeButtonBounds());
        float[] originalColors = colorDetector.getHSLFromScreen(coordinates[0], coordinates[1]);
        homePage.clickChangeLightModeButton();
        float[] newColor = colorDetector.getHSLFromScreen(coordinates[0], coordinates[1]);
        Assert.assertNotEquals(originalColors[2], newColor[2], "Luminance should change when switching to dark mode.");
    }

    @BeforeMethod
    public void getDefaultState() {
        ((JavascriptExecutor)driver).executeScript("mobile: startActivity", ImmutableMap.of(
                "intent", "toplab18.app.simpleststopwatch2/.MainActivity"
        ));
    }

    @AfterMethod
    public void resetActivity() {
        driver.navigate().back();
    }

    @DataProvider
    public Object[][] getData() {
        return new Object[][] {
                {0}, {1} ,{2}, {3} ,{100}, {359}
        };
    }
}
