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
import utils.ColorUtils;

import java.io.IOException;

import static utils.AppiumUtils.getBackgroundPoint;
import static utils.AppiumUtils.getPointInsideWatchfaceShape;

public class VisualSettingsTest extends BaseTest {

    private ColorUtils colorUtils;

    @Step("Verify color of shape has changed")
    @Test(dataProvider = "getData", groups = "colorSchema")
    public void changeWatchFaceColor(int colorIndex) throws IOException {
        homePage.clickOnUpperMenu();
        int[] cords = getPointInsideWatchfaceShape(homePage.getClockFaceBounds());

        float hueBefore = colorUtils.getHueAt(cords[0], cords[1]);
        homePage.selectBackgroundColor(colorIndex);
        float hueAfter = colorUtils.getHueAt(cords[0], cords[1]);

        Assert.assertNotEquals(hueBefore, hueAfter, "Hue value should change after selecting a new color.");
    }

    @Step("Verify dark mode is switched on")
    @Test(groups = "darkMode")
    public void switchToDarkMode() throws IOException {
        homePage.clickOnUpperMenu();
        int[] cords = getBackgroundPoint(homePage.getClockFaceBounds(), homePage.getChangeModeButtonBounds());

        float luminanceBefore = colorUtils.getLuminanceAt(cords[0], cords[1]);
        homePage.clickChangeLightModeButton();
        float luminanceAfter = colorUtils.getLuminanceAt(cords[0], cords[1]);

        Assert.assertNotEquals(luminanceBefore, luminanceAfter, "Luminance should change when switching to dark mode.");
    }

    @BeforeMethod
    public void getDefaultStateAndInitColor() {
        colorUtils = new ColorUtils();
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
