# Mobile Test Automation Project

## Requirements
- **Android Emulator** with API **not higher than 29**
- **Appium** installed and configured
- **Java & Maven** installed on the system

## Setup
1. Navigate to `src/main/resources`.
2. Open or create `config.properties` and add the necessary details. Below is an example configuration:

   ```properties
   ipAddress=IPADDRESS
   port=PORT
   deviceName=DEVICE_NAME
   appiumPath=PATH_TO_APPIUM
   appPath=PATH_TO_APK_FILE
   platform=PLATFORM
   ```

   **User-specific values to be added:**
    - `deviceName`: Name of the connected emulator or physical device.
    - `appiumPath`: Path to the Appium executable.
    - `appPath`: Full path to the APK file.

   **Default recommended values:**
    - `ipAddress=0.0.0.0`
    - `port=4723`
    - `platform=android`

   **Typical path to appium:**
    - `appiumPath=/Users/your-username/.nvm/versions/node/v22.14.0/bin/appium`

## Running Tests
### Run All Tests
```sh
mvn clean test
```

### Run Specific Test Groups
To run tests for a specific group, use:
```sh
mvn clean test -Dgroups=<groupName>
```

### Available Groups
| Group Name       | Description |
|-----------------|-------------|
| `circle`        | Tests related to circle shape |
| `rectangle`     | Tests related to rectangle shape |
| `roundedRectangle` | Tests related to rounded rectangle shape |
| `clockFeatures`  | Tests related to stopwatch features |
| `shape`         | General shape-related tests |
| `default`       | Default functionality tests |
| `colorSchema`   | Tests for changing color settings |
| `darkMode`      | Tests for enabling/disabling dark mode |

---
### Example: Run Only `rectangle` Tests
```sh
mvn clean test -Dgroups=rectangle
```

### Generating Allure Report
After running tests, generate an Allure report using:
```sh
allure serve
```

This will open the test report in your browser.
