package Utils;

import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class FleetOfficerApprovalAPI extends TPCLoginTokenGenerator {
    private static final String fleetOfficerApprovalEndPoint = "/api/maintenance-v2/approve-workshop-inbox/";
    static JSONObject approverCreds;

    public static void approveAsFleetOfficer() throws IOException, ParseException {
//        String userName = JsonFileReader.getJsonData("approverEmail");
//        String password = JsonFileReader.getJsonData("approverPassword");
        approverCreds = (JSONObject) JsonFileReader.getCredentials("approver2");
        String userName = (String) approverCreds.get("email");
        String password = (String) approverCreds.get("password");

        String bearerToken = TokenManager.getToken(userName, password);
        WorkshopInboxListAPI list = new WorkshopInboxListAPI();
        String orderListId = list.getWorkOrderInboxId();

        String fleetOfficerApprovalRequestbody = """
                {"approval":true}
                """;
        Response fleetOfficerApprovalresponse = given()
                .header("authorization", "Bearer " + bearerToken)
                .header("content-type", "application/json")
                .header("accept", "application/json, text/plain, */*")
                .header("timezone", "Asia/Dubai")
                .body(fleetOfficerApprovalRequestbody)
                .when()
                .post(fleetOfficerApprovalEndPoint + orderListId)
                .then()
                .statusCode(200)
                .extract().response();


    }
}
