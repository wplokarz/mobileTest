package org.tests.features;

import org.testng.annotations.Test;
import org.tests.StopwatchBaseTest;

@Test(groups = {"roundedRectangle", "clockFeatures"})
public class RoundedRectangleStopwatchTest extends StopwatchBaseTest {

    @Override
    protected int getShapeIndex() {
        return 2;
    }
}
