package org.tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.pageObjects.HomePage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utils.DriverFactory;
import utils.PropertiesReader;

import java.time.Duration;

import static utils.AppiumUtils.startAppiumService;

public class BaseTest {

    public AppiumDriver driver;
    public AppiumDriverLocalService service;
    public HomePage homePage;

    @BeforeClass(alwaysRun = true)
    public void setup() throws Exception {
        PropertiesReader properties = new PropertiesReader();
        service = startAppiumService(properties);
        service.start();
        driver = DriverFactory.createDriver(properties);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        homePage = new HomePage(driver);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        if (service != null) {
            service.stop();
        }
    }
}
