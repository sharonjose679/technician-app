package Pages;

import Utils.JsonFileReader;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

public class LoginPage {
    private AndroidDriver driver;
    private WebDriverWait wait;
    String actualEmail, email;
    static JSONObject techCreds = JsonFileReader.getCredentials("technician");
    static String expectedEmail = (String) techCreds.get("email");
    static String password = (String) techCreds.get("password");

    //Constructor - initializes Page factory elements
    public LoginPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        //AppiumFieldDecorator dynamically locate elements, avoid stale element exception,
        //AND provides implicit wait support
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    //Locators with @FindBy

    //    @FindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.widget.EditText[1]")
//    private WebElement emailTextBox;
    @FindBy(xpath = "//android.widget.EditText[1]")
    private WebElement emailTextBox;
    //    @FindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.widget.EditText[2]")
//    private WebElement passwordTextBox;
    @FindBy(xpath = "//android.widget.EditText[2]")
    private WebElement passwordTextBox;

    @FindBy(xpath = "//android.widget.Button[@content-desc=\"Login\"]")
    private WebElement loginButton;

    @FindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.view.View[1]")
    private WebElement technicianAppMenu;

    @FindBy(xpath = "//android.view.View[@content-desc=\"Settings\"]")
    private WebElement settings;

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.view.View\").instance(10)")
    private WebElement profileNavButton;

    @FindBy(xpath = "//android.view.View[7]")
    private WebElement emailLoggedinUser;

    @FindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.view.View[1]")
    private WebElement backButtonProfile;

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.view.View\").instance(4)")
    private WebElement settingsBackButton;


    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.view.View\").instance(5)")
    private WebElement closeButton;

    @FindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.view.View[1]")
    private WebElement dashboardMenu;

    @AndroidFindBy(accessibility = "Logout")
    private WebElement logOut;
    @AndroidFindBy(accessibility = "Welcome Back")
    private WebElement loginPageTitle;


    //Actions
    @Step("Enter Email")
    public void enterEmail() throws IOException, ParseException, InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(emailTextBox)).click();
        String emailId = emailTextBox.getText();
        Thread.sleep(2000);
        //String expectedEmail = JsonFileReader.getJsonData("email");
        if (!emailId.isEmpty()) {
            if (emailId.equals(expectedEmail)) {

                driver.pressKey(new KeyEvent(AndroidKey.TAB));
                System.out.print("Email id already there");
                return;
            } else {
                emailTextBox.clear();
                System.out.print("Existing email cleared");
            }
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        emailTextBox.sendKeys(expectedEmail);
        System.out.println("\nEmail entered " + expectedEmail);
    }

    @Step("Enter Password")
    public void enterPassword() throws IOException, ParseException, InterruptedException {
        Thread.sleep(3000);
        wait.until(ExpectedConditions.elementToBeClickable(passwordTextBox)).click();
        passwordTextBox.sendKeys(password);
        System.out.println("Password entered");
    }

    @Step("Click on Submit Button")
    public void submitLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
        //loginButton.click();
        System.out.println("Login Button clicked");
    }

    public String fetchActualEmail() {
        wait.until(ExpectedConditions.elementToBeClickable(technicianAppMenu)).click();
        // technicianAppMenu.click();
        System.out.println("Menu clicked");
        wait.until(ExpectedConditions.elementToBeClickable(settings)).click();
        System.out.println("Settings Clicked");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(profileNavButton)).click();
        System.out.println("Profile clicked");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        email = emailLoggedinUser.getDomAttribute("content-desc");
        return email;
    }

    public void techAppLogin() throws IOException, ParseException, InterruptedException {
        enterEmail();
        enterPassword();
        driver.pressKey(new KeyEvent(AndroidKey.TAB));
        submitLogin();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public boolean technicianLogin() throws IOException, ParseException, InterruptedException {
        techAppLogin();
        actualEmail = fetchActualEmail();
        //String expectedEmail = ;
        if (actualEmail.equals(expectedEmail)) {
            System.out.println("Email Matching: " + actualEmail);
            return true;
        } else {
            System.out.println("Wrong email: " + actualEmail);
            return false;
        }
    }

    public void goBackFromProfile() {
        wait.until(ExpectedConditions.elementToBeClickable(backButtonProfile)).click();
        wait.until(ExpectedConditions.elementToBeClickable(settingsBackButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(closeButton)).click();
    }

    public boolean logOut() {
        wait.until(ExpectedConditions.elementToBeClickable(dashboardMenu)).click();
        wait.until(ExpectedConditions.elementToBeClickable(logOut)).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        if (loginPageTitle.isDisplayed())
            return true;
        else
            return false;

    }
}
