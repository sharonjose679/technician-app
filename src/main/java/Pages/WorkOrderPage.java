package Pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class WorkOrderPage {
    private AndroidDriver driver;
    private WebDriverWait wait;


    public WorkOrderPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath = "new UiSelector().description(\"KA Keran ALi Technician Senior Engine Pending: 1 m Manufacture Nissan Model Oil Seal Qty Needed 1 Unit Service Tag Service Part Status --- Request\")\n")
    private WebElement firstPartRequest;

    @FindBy(xpath = "(//android.widget.Button[@content-desc=\"Start Task\"])[1]")
    private WebElement firstStartTask;

//    @FindBy(xpath = "//android.widget.ImageView[@content-desc=\"Are you sure, You want to start the task - Engine? No Yes\"]/android.view.View[3]\n")
//    private WebElement startConfirmYes;

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.view.View\").instance(5)")
    private WebElement confirmYes;

    @FindBy(xpath = "//android.widget.Button[@content-desc=\"Start Task\"]")
    private WebElement secondStartTask;

    @AndroidFindBy(accessibility = "Start Task")
    private WebElement startTask;

    @FindBy(xpath = "(//android.widget.Button[@content-desc=\"Complete Task\"])[1]\n")
    private WebElement firstCompleteTask;

    @FindBy(xpath = "//android.widget.Button[@content-desc=\"Complete Task\"]\n")
    private WebElement secondCompleteTask;

    @AndroidFindBy(accessibility = "Complete Task")
    private WebElement completeTask;
    @FindBy(xpath = "//android.widget.Button[contains(@content-desc, 'COMPLETE')]")
    private WebElement completeButton;

    public void requestPart() {
        wait.until(ExpectedConditions.elementToBeClickable(firstPartRequest)).click();
    }

    public void startTask() {
        wait.until(ExpectedConditions.elementToBeClickable(startTask)).click();
        // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(confirmYes)).click();

        //To scroll down Method 1
      /*  JavascriptExecutor js = (JavascriptExecutor) driver;
        HashMap<String, Object> params = new HashMap<>();
        params.put("left", 0);  // Change strategy as needed
        params.put("top", 0);
        params.put("width", 1200);
        params.put("height", 1920);
        params.put("direction", "down");
        params.put("percent", 1);
        js.executeScript("mobile: scrollGesture", params);
        System.out.println("Scroll down");

       */

        //Scroll method 2
        WebElement scrollable = driver.findElement(
                AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))"));

        Rectangle rect = scrollable.getRect();

// Scroll gesture
        /*Map<String, Object> params = new HashMap<>();
        params.put("left", rect.getX());
        params.put("top", rect.getY());
        params.put("width", rect.getWidth());
        params.put("height", rect.getHeight());
        params.put("direction", "down");
        params.put("percent", 1.0);

        ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", params);
        System.out.println("Scroll down");

         */

        //Scroll method 3
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().descriptionContains(\"Start Task\"))"));

        wait.until(ExpectedConditions.elementToBeClickable(startTask)).click();
        wait.until(ExpectedConditions.elementToBeClickable(confirmYes)).click();
    }

    public void completeTask() {
        wait.until(ExpectedConditions.elementToBeClickable(completeTask)).click();
        wait.until(ExpectedConditions.elementToBeClickable(confirmYes)).click();

        /*
        JavascriptExecutor js = (JavascriptExecutor) driver;
        HashMap<String, Object> params = new HashMap<>();
        params.put("left", 0);  // Change strategy as needed
        params.put("top", 0);
        params.put("width", 1200);
        params.put("height", 1920);
        params.put("direction", "down");
        params.put("percent", 1);
        js.executeScript("mobile: scrollGesture", params);
        System.out.println("Scroll down");

         */
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().descriptionContains(\"Complete Task\"))"));

        wait.until(ExpectedConditions.elementToBeClickable(completeTask)).click();
        wait.until(ExpectedConditions.elementToBeClickable(confirmYes)).click();
        wait.until(ExpectedConditions.elementToBeClickable(completeButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(confirmYes)).click();
    }

}
