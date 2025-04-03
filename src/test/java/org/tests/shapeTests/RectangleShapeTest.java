package org.tests.shapeTests;

import org.testng.annotations.Test;
import org.tests.ShapeBaseTest;

@Test(groups = {"rectangle", "shape"})
public class RectangleShapeTest extends ShapeBaseTest {

    @Override
    protected int getShapeIndex() {
        return 0; // Rectangle shape index
    }
}
