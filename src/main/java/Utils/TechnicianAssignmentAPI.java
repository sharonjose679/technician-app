package Utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class TechnicianAssignmentAPI extends WorkOrderCreationAPI {
    private static String technicianAssignEndPoint = "/api/maintenance-v2/assign-technician";
    static String userName, password, bearerToken;
    static JSONObject approverCreds;

    public static String assignTechnician(String orderId) throws IOException, ParseException {
//        userName = JsonFileReader.getJsonData("approverEmail");
//        password = JsonFileReader.getJsonData("approverPassword");

        approverCreds = (JSONObject) JsonFileReader.getCredentials("approver2");
        userName = (String) approverCreds.get("email");
        password = (String) approverCreds.get("password");

        bearerToken = TokenManager.getToken(userName, password);

        String assignTechnicianRequestBody = """
                                
                                {
                                        "part_ids": [
                        252,5297
                    ],
                        "package_id": 9,
                                "order_id": "%s",
                                "technician_id": 1749
                }""";
        assignTechnicianRequestBody = String.format(assignTechnicianRequestBody, orderId);
//RestAssured.baseURI = BASE_URL;

        Response techAssignResponse = given()
                .header("authorization", "Bearer " + bearerToken)
                .header("content-type", "application/json")
                .header("timezone", "Asia/Dubai")
                .body(assignTechnicianRequestBody)
                .when()
                .post(technicianAssignEndPoint)
                .then()
                .statusCode(200)
                .extract().response();
        System.out.print("Technicians Assigned Successfully");
        return bearerToken;

    }
}
