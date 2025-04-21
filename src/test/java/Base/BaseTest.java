package Base;

import Managers.DriverManager;
import Utils.ScreenshotUtil;
import io.appium.java_client.android.AndroidDriver;
import org.json.simple.parser.ParseException;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import java.time.Duration;

@Listeners({io.qameta.allure.testng.AllureTestNg.class})
public class BaseTest {
    protected AndroidDriver driver;

    @Parameters("deviceName")
    @BeforeClass
    public void appiumDriverSetUp(@Optional("") String deviceName) throws IOException, ParseException {
        driver = DriverManager.getDriver(deviceName);
        System.out.println("Appium session has started successfully");
        // Wait for app to fully load (especially important for Jenkins/emulators)
        try {
            Thread.sleep(5000); // You can tweak this if needed
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterMethod
    public void captureScreenshotOnFailure(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            ScreenshotUtil.captureScreenshot(driver, "TestFailure");
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            System.out.println("Closing Appium driver session");
            driver.quit();
        }
    }
}
