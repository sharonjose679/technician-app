package Utils;

import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class WorkshopInboxListAPI extends WorkOrderCreationAPI {
    //private static final String workshopListEndpoint = "/api/maintenance-v2/workshop-inbox?limit=10&page=1&search=";
    private static final String workshopListEndpoint = "/api/maintenance-v2/workshop-inbox";
    static JSONObject approverCreds;

    public String getWorkOrderInboxId() throws IOException, ParseException {
        approverCreds = (JSONObject) JsonFileReader.getCredentials("approver2");
        String userName = (String) approverCreds.get("email");
        String password = (String) approverCreds.get("password");

//        String userName = JsonFileReader.getJsonData("approverEmail");
//        String password = JsonFileReader.getJsonData("approverPassword");


        String bearerToken = TokenManager.getToken(userName, password);
        // System.out.println("Workshop List Entered");
        //  String token = TechnicianAssignmentAPI.assignTechnician(orderId)
        Response workshopInboxListResponse = given()
                .header("authorization", "Bearer " + bearerToken)
                .header("accept", "application/json, text/plain, */*")
                .header("accept-language", "en-US,en;q=0.9,ml-IN;q=0.8,ml;q=0.7")
                .header("access-control-allow-origin", "*")
                .header("priority", "u=1, i")
                .header("referer", "https://saas-stage.wiot360.com/tpc-portal/workshop-inbox")
                .header("sec-ch-ua", "\"Chromium\";v=\"134\", \"Not:A-Brand\";v=\"24\", \"Google Chrome\";v=\"134\"")
                .header("sec-ch-ua-mobile", "?0")
                .header("sec-ch-ua-platform", "\"Windows\"")
                .header("sec-fetch-dest", "empty")
                .header("sec-fetch-mode", "cors")
                .header("sec-fetch-site", "same-origin")
                .header("timezone", "Asia/Dubai")
                .queryParam("limit", 10)
                .queryParam("page", 1)
                .queryParam("search", "")
                .when()
                .get(workshopListEndpoint)
                .then()
                .statusCode(200)
                .extract().response();
        // System.out.print("Status Code: "+workshopInboxListResponse.getStatusCode());
        // System.out.println("Workshop List Response: "+workshopInboxListResponse);
        String workshopListOrderId = workshopInboxListResponse.jsonPath().getString("data[0]._id");
        System.out.println("Workshop List order Id: " + workshopListOrderId);
        return workshopListOrderId;
    }
}
