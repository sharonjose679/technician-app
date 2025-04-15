package Base;

import Managers.DriverManager;
import io.appium.java_client.android.AndroidDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.IOException;
import java.time.Duration;

@Listeners({io.qameta.allure.testng.AllureTestNg.class})
public class BaseTest {
    protected AndroidDriver driver;
    @Parameters("deviceName")
    @BeforeClass
    public void appiumDriverSetUp(@Optional("")String deviceName) throws IOException, ParseException {
        driver = DriverManager.getDriver(deviceName);
        System.out.println("Appium session has started successfully");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
}
