package org.tests.features;

import org.testng.annotations.Test;
import org.tests.StopwatchBaseTest;

@Test(groups = {"default", "clockFeatures", "circle"})
public class DefaultStopwatchTest extends StopwatchBaseTest {

    @Override
    protected int getShapeIndex() {
        return 1;
    }

}
