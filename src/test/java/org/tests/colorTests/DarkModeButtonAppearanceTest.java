package org.tests.colorTests;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.tests.BaseTest;

public class DarkModeButtonAppearanceTest extends BaseTest {

    @Step("Verify visibility of dark/light mode button")
    @Test(groups = "darkMode")
    public void darkModeButtonVisibilityTest() {
        homePage.clickOnUpperMenu();
        Assert.assertTrue(homePage.isDarkModeButtonVisible());
        homePage.setWholeBackgroundInColor();
        Assert.assertFalse(homePage.isDarkModeButtonVisible());
        homePage.selectShape(1);
        Assert.assertTrue(homePage.isDarkModeButtonVisible());
    }
}
