package Managers;

import Utils.JsonFileReader;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

public class DriverManager {

    private static AndroidDriver driver;
    static JSONObject device;
    static String platformName, platformVersion, deviceName, automationName, server;

    public static AndroidDriver getDriver(String name) throws IOException, ParseException {
        String appPath = System.getProperty("user.dir") + "/Resources/App/1.0.0+5-stage.apk";
        File appFile = new File(appPath);
        if (!appFile.exists()) {
            throw new IOException("APK File not found at: " + appFile.getAbsolutePath());
        }
//        if (name.equalsIgnoreCase("Samsung Tab")) {
//            device = JsonFileReader.getDeviceConfig(0);
//        } else if (name.equalsIgnoreCase("Samsung Galaxy S23")) {
//            device = JsonFileReader.getDeviceConfig(1);
//
//        }
        device = JsonFileReader.getDeviceConfigByName(name);
        platformName = (String) device.get("platformName");
        platformVersion = (String) device.get("platformVersion");
        deviceName = (String) device.get("deviceName");
        automationName = (String) device.get("automationName");
        server = (String) device.get("appiumServer");


        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName(platformName)
                .setPlatformVersion(platformVersion)
                .setDeviceName(deviceName)
                .setAutomationName(automationName)
                .setApp(appFile.getAbsolutePath())
                .setNoReset(true)
                .setAutoGrantPermissions(true)
                .setDisableWindowAnimation(true)
                .setSystemPort(generateUniqueSystemPort());


        //String serverUrl = JsonFileReader.getJsonData("appiumServer");
        return driver = new AndroidDriver(new URL(server), options);
    }

    private static int generateUniqueSystemPort() {
        return 8200 + new Random().nextInt(100); // Random port between 8200–8299
    }

}
