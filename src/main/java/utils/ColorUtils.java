package utils;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class ColorUtils {
    private final ColorDetector colorDetector;

    public ColorUtils(WebDriver driver) {
        this.colorDetector = new ColorDetector((AppiumDriver) driver);
    }

    public float[] getColorAt(int x, int y) throws IOException, IOException {
        return colorDetector.getHSLFromScreen(x, y);
    }

    public float getHueAt(int x, int y) throws IOException {
        return getColorAt(x, y)[0];
    }

    public float getLuminanceAt(int x, int y) throws IOException {
        return getColorAt(x, y)[2];
    }
}

