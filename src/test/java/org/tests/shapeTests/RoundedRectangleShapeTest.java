package org.tests.shapeTests;

import org.testng.annotations.Test;
import org.tests.ShapeBaseTest;

@Test(groups = {"roundedRectangle", "shape"})
public class RoundedRectangleShapeTest extends ShapeBaseTest {

    @Override
    protected int getShapeIndex() {
        return 2; // Rounded rectangle shape index
    }
}
