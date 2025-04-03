package org.tests;

import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ColorDetector;

import java.io.IOException;

import static utils.AppiumUtils.getCoordinates;

public abstract class ShapeBaseTest extends BaseTest {

    protected abstract int getShapeIndex(); // Each subclass must define its shape index

    @Step("Verify correct shape is displayed")
    @Test(groups = "shape")
    public void shapeTest() throws IOException {
        homePage.clickOnUpperMenu();
        ColorDetector colorDetector = new ColorDetector(driver);

        // Define coordinates where color will be checked
        int[][] coordinatesOfElement = getCoordinates(homePage.getClockFaceBounds());
        int x1 = coordinatesOfElement[0][0], x2 = coordinatesOfElement[1][0];
        int y1 = coordinatesOfElement[0][1], y2 = coordinatesOfElement[1][1];

        // Point which is outside of default circle shape, but inside any other available shape
        int[] coordinatesToCheck = {x1 + (int) x2/10 , y1 + (int) y2/20};
        float[] originalColors = colorDetector.getHSLFromScreen(coordinatesToCheck[0], coordinatesToCheck[1]);

        homePage.selectShape(getShapeIndex()); // Call specific shape index from subclass

        float[] newColor = colorDetector.getHSLFromScreen(coordinatesToCheck[0], coordinatesToCheck[1]);

        Assert.assertNotEquals(originalColors[0], newColor[0], "Hue value should change after selecting a different shape.");
    }
}
