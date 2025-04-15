package Pages;

import Utils.CommonUtilities;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AndroidFindBySet;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AssetInspectionPage {
    private AndroidDriver driver;
    private WebDriverWait wait;

    private String imagePath;

    CommonUtilities utils;

    public AssetInspectionPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @FindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.widget.EditText[1]")
    private WebElement serviceBay;


    @FindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.widget.EditText[2]")
    private WebElement odometer;

    @FindBy(xpath = "//android.widget.Button[@content-desc=\"Inside\"]")
    private WebElement insideInspectionNextButton;

//    @FindBy(xpath = "//android.view.View[@content-desc=\"Upload Image\"]")
//    private WebElement imageUpload;

    @AndroidFindBy(accessibility = "Upload Image")
    private WebElement  imageUpload;

//    @FindBy(xpath = "//android.widget.ImageView[@content-desc=\"Camera\n" +
//            "Media Picker\"]/android.view.View[3]")
//    private WebElement mediaPicker;

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.view.View\").instance(5)\n")
    private WebElement mediaPicker;


//    @FindBy(xpath = "//android.widget.ImageView[@resource-id='com.google.android.documentsui:id/icon_thumb']")
//    private List<WebElement> imageThumbNail;
    @FindBy(xpath = "//android.widget.ImageView[contains(@resource-id,'com.google.android') and contains(@resource-id, 'icon_thumb')]")
    private List<WebElement> imageThumbNail;

    @FindBy(xpath = "//android.view.View[@content-desc=\"Right\n" +
            "Tab 2 of 4\"]")
    private WebElement inspectionRightTab;

    @FindBy(xpath = "//android.view.View[@content-desc=\"Left\n" +
            "Tab 3 of 4\"]")
    private WebElement inspectionLeftTab;

    @FindBy(xpath = "//android.view.View[@content-desc=\"Back\n" +
            "Tab 4 of 4\"]")
    private WebElement inspectionBackTab;

    @FindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]")
    private WebElement assetInspectionBackButton;

    @FindBy(xpath = "//android.widget.Button[@content-desc=\"Outside\"]")
    private WebElement outsideInspectionNextButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"Submit\")\n")
    private WebElement inspectionSubmitButton;

    @FindBy(xpath = "//android.view.View[@content-desc=\"In Progress\"]")
    private WebElement inProgress;

    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"Update Maintenance\")\n")
    private WebElement updateMaintenanceNextButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Switch\").instance(0)\n")
    private WebElement distanceToggleButton;

    @FindBy(className = "android.widget.EditText")
    private WebElement distanceTextBox;

    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"Update Maintenance\")\n")
    private WebElement updateMaintenanceButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.view.View\").instance(4)\n")
    private WebElement inspectionBackNav;

    public void enterBasicDetails() {
        wait.until(ExpectedConditions.elementToBeClickable(serviceBay)).click();
        serviceBay.sendKeys("10B");
        wait.until(ExpectedConditions.elementToBeClickable(odometer)).click();
        odometer.sendKeys("5000");
        System.out.println("Inspection Basic Details entered");
    }

    public void enterInspectionDetails() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(insideInspectionNextButton)).click();
        //Thread.sleep(3000);
        uploadImage();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(outsideInspectionNextButton)).click();
        Thread.sleep(3000);
        uploadImage();
        System.out.println("Inspection Details entered");
    }

    public void uploadImage() throws InterruptedException {
        //Upload images for Inside section
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(imageUpload)).click();
       // Thread.sleep(3000);
        // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(mediaPicker)).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        if (!imageThumbNail.isEmpty()) {
            imageThumbNail.get(0).click();
        } else
            System.out.println("No images found");

        System.out.println("First image uploaded");
        wait.until(ExpectedConditions.elementToBeClickable(inspectionRightTab)).click();
        upload();
        System.out.println("Second image uploaded");
        wait.until(ExpectedConditions.elementToBeClickable(inspectionLeftTab)).click();
        upload();
        System.out.println("Third image uploaded");
        wait.until(ExpectedConditions.elementToBeClickable(inspectionBackTab)).click();
        upload();
        System.out.println("Fourth image uploaded");
        wait.until(ExpectedConditions.elementToBeClickable(assetInspectionBackButton)).click();
        System.out.println("All Images uploaded");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    public void upload() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(imageUpload)).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(mediaPicker)).click();
        if (!imageThumbNail.isEmpty()) {
            imageThumbNail.get(0).click();
        } else
            System.out.println("No images found");
    }

    public void submitInspection() {
        driver.pressKey(new KeyEvent(AndroidKey.TAB));
        wait.until(ExpectedConditions.elementToBeClickable(inspectionSubmitButton)).click();
        System.out.println("Inspection details submitted");
    }

    public boolean checkStatusChangeToInProgress() {
        if (inProgress.isDisplayed())
            return true;
        else
            return false;
    }

    public void updateMaintenance() {
        wait.until(ExpectedConditions.elementToBeClickable(updateMaintenanceNextButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(distanceToggleButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(distanceTextBox)).click();
        String distanceValue = distanceTextBox.getText();
        if (!distanceValue.isEmpty()) {
            distanceTextBox.clear();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        distanceTextBox.sendKeys("10");
        wait.until(ExpectedConditions.elementToBeClickable(updateMaintenanceButton)).click();
        driver.pressKey(new KeyEvent(AndroidKey.TAB));
        wait.until(ExpectedConditions.elementToBeClickable(inspectionSubmitButton)).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
}
