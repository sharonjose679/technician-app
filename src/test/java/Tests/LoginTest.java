package Tests;

import Base.BaseTest;
import Pages.LoginPage;
import io.qameta.allure.*;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest extends BaseTest {
    @Epic("Maintenance")
    @Feature("Technician APP")
    @Story("Technician Login")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify the technician is able to login to technician APP")
    @Test(enabled = true)
    public void technicianAppLoginTest() throws IOException, ParseException, InterruptedException {
        LoginPage login = new LoginPage(driver);
        boolean flag = login.technicianLogin();
        Assert.assertTrue(flag, "Logged in user email is not matching");
        login.goBackFromProfile();
    }

    @Test
    public void verifyTechnicianLogout() {
        LoginPage login = new LoginPage(driver);
        boolean flag = login.logOut();
        Assert.assertTrue(flag, "Logout not successful");
    }

}
