package Tests;

import Base.BaseTest;
import Pages.*;
import Utils.*;
import io.qameta.allure.*;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;

public class WorkOrderFlowTests extends BaseTest {
    @Epic("Maintenance")
    @Feature("Technician APP")
    @Story("Technician Completes work order")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify the work order is able to complete from technician APP")
    @Test(enabled = true)
    public void verifyScheduledWorkOrderLists() throws IOException, ParseException, InterruptedException {
        String orderId = WorkOrderCreationAPI.createMaintenanceOrder();
        TechnicianAssignmentAPI.assignTechnician(orderId);
        FleetOfficerApprovalAPI.approveAsFleetOfficer();
        WorkOrderScheduling.scheduleWorkOrder(orderId);
        LoginPage login = new LoginPage(driver);
        login.techAppLogin();
        DashBoardPage dashboard = new DashBoardPage(driver);
        dashboard.clickScheduledStatus();
        TasksListPage tasks = new TasksListPage(driver);
        tasks.clickTaskNextButton();
        WorkOrderDetailsPage details = new WorkOrderDetailsPage(driver);
        details.clickStartWorkOrder();
        details.clickConfirmationYes();
        AssetInspectionPage inspection = new AssetInspectionPage(driver);
        inspection.enterBasicDetails();
        inspection.enterInspectionDetails();
        inspection.submitInspection();
        boolean flag = inspection.checkStatusChangeToInProgress();
        Assert.assertTrue(flag);
        WorkOrderPage woPage = new WorkOrderPage(driver);
        //woPage.requestPart();
        woPage.startTask();
        woPage.completeTask();
        inspection.enterBasicDetails();
        inspection.enterInspectionDetails();
        inspection.updateMaintenance();

    }

}
