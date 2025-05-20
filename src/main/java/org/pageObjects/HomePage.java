package org.pageObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.Gestures;

import java.util.List;

public class HomePage extends Gestures {
    AppiumDriver driver;

    public HomePage(AppiumDriver driver) {
        super();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.clickOnLaterButton();
    }

    @AndroidFindBy(id = "android:id/button3")
    private WebElement laterButton;

    @AndroidFindBy(id = "toplab18.app.simpleststopwatch2:id/tv_digits")
    private WebElement clockFace;

    @AndroidFindBy(id = "toplab18.app.simpleststopwatch2:id/tv_grab")
    private WebElement upperMenu;

    @AndroidFindBy(id = "toplab18.app.simpleststopwatch2:id/ll_BGColor")
    private WebElement backgroundColorParent;

    @AndroidFindBy(id = "toplab18.app.simpleststopwatch2:id/ll_shape")
    private WebElement watchfaceShapeParent;

    @AndroidFindBy(id= "toplab18.app.simpleststopwatch2:id/FLDayMode")
    private WebElement changeDarkLightModeButton;


    private void clickOnLaterButton() {
        // if popup window opens click on later
        try {
            laterButton.click();
        } catch (Exception e) {
        }
    }

    public void clickOnClockFace() {
        clockFace.click();
    }

    public String getClockTime() {
        return clockFace.getText();
    }

    public void longPressOnClockFace() {
        longPress(clockFace);
    }

    public void clickOnUpperMenu() {
        upperMenu.click();
    }

    public void selectBackgroundColor(int index) {
        List<WebElement> colorOptions = getBackgroundColorPicker(); // Refresh elements dynamically

        if (index >= 0 && index < colorOptions.size()) {
            colorOptions.get(index).click();
        } else {
            throw new IllegalArgumentException("Invalid color index: " + index);
        }
    }

    public void selectShape(int index) {
        List<WebElement> shapeOptions = watchfaceShapePicker();
        if (index >= 0 && index < shapeOptions.size()) {
            shapeOptions.get(index).click();
        } else {
            throw new IllegalArgumentException("Invalid shape index: " + index);
        }
    }

    public String getClockFaceBounds() {
        return clockFace.getAttribute("bounds");
    }

    public String getChangeModeButtonBounds() {
        return changeDarkLightModeButton.getAttribute("bounds");
    }

    public void clickChangeLightModeButton() {
        changeDarkLightModeButton.click();
    }

    private List<WebElement> getBackgroundColorPicker() {
        return backgroundColorParent.findElements(By.className("android.widget.LinearLayout"));
    }

    private List<WebElement> watchfaceShapePicker() {
        return watchfaceShapeParent.findElements(By.className("android.widget.LinearLayout"));
    }

    public void setWholeBackgroundInColor() {
        selectShape(3);
    }

    public boolean isDarkModeButtonVisible() {
        try {
            return changeDarkLightModeButton.isDisplayed();
        } catch (Exception e) {
            return false; // If not found, return false
        }
    }

}
