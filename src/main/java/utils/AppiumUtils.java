package utils;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AppiumUtils {

    public static AppiumDriverLocalService startAppiumService(PropertiesReader properties) {
        int port = Integer.parseInt(properties.getProperty("port"));
        String ipAddress = properties.getProperty("ipAddress");
        String pathToAppium = properties.getProperty("appiumPath");
        return new AppiumServiceBuilder().withAppiumJS(new File(pathToAppium)).withIPAddress(ipAddress).usingPort(port).build();
    }

    public static int[][] getCoordinates(String bounds) {
        Pattern pattern = Pattern.compile("(\\d+),(\\d+)");
        Matcher matcher = pattern.matcher(bounds);
        int[][] coordinates = new int[2][2];
        int i = 0;

        while (matcher.find() && i < 2) {
            coordinates[i][0] = Integer.parseInt(matcher.group(1)); // X coordinate
            coordinates[i][1] = Integer.parseInt(matcher.group(2)); // Y coordinate
            i++;
        }
        return coordinates;
    }

    public static int[] getPointInsideWatchfaceShape(String bounds) {
        int[][] coordinates = getCoordinates(bounds);
        return new int[] {
                getCenter(coordinates[0][0], coordinates[0][1]),  // Center X
                getCenter(coordinates[1][0], coordinates[1][1])   // Center Y
        };
    }

    public static int[] getBackgroundPoint(String watchfaceBounds, String buttonBounds) {
        // returns array with point X as center of screen and point Y as point between watchface and button to switch dark/light modes
        int[][] watchfaceCoordinates = getCoordinates(watchfaceBounds);
        int[][] buttonCoordinates = getCoordinates(buttonBounds);
        int centerX = (watchfaceCoordinates[0][1] + watchfaceCoordinates[0][0])/2;
        int downY = watchfaceCoordinates[1][1];
        int topYButton = buttonCoordinates[0][1];
        return new int[] {centerX, (topYButton + downY)/2};
    }

    private static int getCenter(int a, int b) {
        return (a + b) / 2;
    }

}
