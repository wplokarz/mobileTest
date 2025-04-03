package utils;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

public class Gestures {

    AppiumDriver driver;

    public Gestures(AppiumDriver driver) {
        this.driver = driver;
    }

    public void longPress(WebElement ele) {
        ((JavascriptExecutor)driver).executeScript("mobile: longClickGesture", ImmutableMap.of("elementId", ((RemoteWebElement)ele).getId(), "duration", 2000));
    }
}
