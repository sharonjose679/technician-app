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

public class DashBoardPage {
    private AndroidDriver driver;
    private WebDriverWait wait;

    public DashBoardPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        //AppiumFieldDecorator dynamically locate elements, avoid stale element exception,
        //AND provides implicit wait support
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @FindBy(xpath = "//android.view.View[@content-desc=\"1\n" +
            "Scheduled\"]")
    private WebElement scheduledStatus;

//    @AndroidFindBy(xpath = "//android.view.View[translate(substring-before(@content-desc, ' Scheduled'), '0123456789', '') = '']")
//    private WebElement scheduledStatusCount;

    public void clickScheduledStatus() {
        wait.until(ExpectedConditions.elementToBeClickable(scheduledStatus)).click();
    }

}
