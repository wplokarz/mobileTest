package org.tests.shapeTests;

import com.google.common.collect.ImmutableMap;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.tests.ShapeBaseTest;

@Test(groups = {"circle", "shape"})
public class CircleShapeTest extends ShapeBaseTest {

    @BeforeMethod(alwaysRun = true)
    public void changeToRectangleShapeFirst() {
        homePage.clickOnUpperMenu();
        homePage.selectShape(0);
        driver.navigate().back();
        ((JavascriptExecutor)driver).executeScript("mobile: startActivity", ImmutableMap.of(
                "intent", "toplab18.app.simpleststopwatch2/.MainActivity"
        ));
    }

    @Override
    protected int getShapeIndex() {
        return 1; // Circle shape index
    }
}
