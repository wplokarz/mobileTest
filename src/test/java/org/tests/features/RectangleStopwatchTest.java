package org.tests.features;

import org.testng.annotations.Test;

import org.tests.StopwatchBaseTest;

@Test(groups = {"rectangle", "clockFeatures"})
public class RectangleStopwatchTest extends StopwatchBaseTest {

    @Override
    protected int getShapeIndex() {
        return 0; // Rectangle shape index
    }
}
