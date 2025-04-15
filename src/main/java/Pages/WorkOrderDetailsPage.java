package Pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WorkOrderDetailsPage {
    private AndroidDriver driver;
    private WebDriverWait wait;

    public WorkOrderDetailsPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @FindBy(xpath = "//android.widget.Button[@content-desc=\"Start Work order\"]")
    private WebElement startWorkOrderButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.view.View\").instance(6)")
    private WebElement yesButtonConfirmationWindow;

    public void clickStartWorkOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(startWorkOrderButton)).click();
    }

    public void clickConfirmationYes() {
        wait.until(ExpectedConditions.elementToBeClickable(yesButtonConfirmationWindow)).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
}
