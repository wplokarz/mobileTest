package utils;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ColorDetector {
    private AppiumDriver driver;

    public ColorDetector(AppiumDriver driver) {
        this.driver = driver;
    }

    public float[] getHSLFromScreen(int x, int y) throws IOException {
        // Capture screenshot
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        BufferedImage image = ImageIO.read(screenshot);

        // Get RGB color at the given coordinates
        Color color = new Color(image.getRGB(x, y));

        // Convert RGB to HSL
        return convertRGBtoHSL(color.getRed(), color.getGreen(), color.getBlue());
    }

    private float[] convertRGBtoHSL(int r, int g, int b) {
        float[] hsl = new float[3];
        Color.RGBtoHSB(r, g, b, hsl);
        hsl[0] *= 360; // Convert hue to degrees (0-360)
        hsl[1] *= 100; // Convert saturation to percentage (0-100)
        hsl[2] *= 100; // Convert brightness to percentage (0-100)
        return hsl;
    }
}
