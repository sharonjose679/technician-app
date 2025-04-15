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

public class TasksListPage {
    private AndroidDriver driver;
    private WebDriverWait wait;

    public TasksListPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        //AppiumFieldDecorator dynamically locate elements, avoid stale element exception,
        //AND provides implicit wait support
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

//    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.view.View\").instance(17)")
//    private WebElement tasksNextButton;
    @FindBy(xpath = "//android.view.View[contains(@content-desc, \"Scheduled\")]/android.view.View[2]")
    private  WebElement tasksNextButton;

    public void clickTaskNextButton() {
        wait.until(ExpectedConditions.elementToBeClickable(tasksNextButton)).click();
    }

}
