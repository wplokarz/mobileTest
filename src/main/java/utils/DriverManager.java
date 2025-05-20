package utils;


import io.appium.java_client.AppiumDriver;

public class DriverManager {
    private static final ThreadLocal<AppiumDriver> driverHolder = new ThreadLocal<>();

    public static void setDriver(AppiumDriver driver) {
        driverHolder.set(driver);
    }

    public static AppiumDriver getDriver() {
        return driverHolder.get();
    }
}
