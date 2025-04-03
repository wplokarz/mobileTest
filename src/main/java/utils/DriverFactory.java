package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;

import java.net.URI;
import java.net.URL;
import java.time.Duration;

public class DriverFactory {
    public static AppiumDriver createDriver(PropertiesReader properties) throws Exception {
        String platform = properties.getProperty("platform"); // android or ios
        String ipAddress = properties.getProperty("ipAddress");
        String port = properties.getProperty("port");

        URL appiumServerUrl = new URI(String.format("http://%s:%s", ipAddress, port)).toURL();

        if (platform.equalsIgnoreCase("android")) {
            return new AndroidDriver(appiumServerUrl, getAndroidCapabilities(properties));
        } else if (platform.equalsIgnoreCase("ios")) {
            return new IOSDriver(appiumServerUrl, getIOSCapabilities(properties));
        } else {
            throw new IllegalArgumentException("Unsupported platform: " + platform);
        }
    }

    private static UiAutomator2Options getAndroidCapabilities(PropertiesReader properties) {
        UiAutomator2Options options = new UiAutomator2Options();
        //        options.setDeviceName(deviceName);
        options.setCapability("avd", properties.getProperty("deviceName"));
        options.setApp(properties.getProperty("appPath"));
        options.setAvdLaunchTimeout(Duration.ofSeconds(30));
        return options;
    }

    private static XCUITestOptions getIOSCapabilities(PropertiesReader properties) {
        XCUITestOptions options = new XCUITestOptions();
        options.setDeviceName(properties.getProperty("deviceName"));
        options.setApp(properties.getProperty("appPath"));
        options.setPlatformVersion(properties.getProperty("platformVersion"));
        return options;
    }
}
