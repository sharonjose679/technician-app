package Utils;

import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.json.Json;

import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.given;

public class WorkOrderScheduling extends TPCLoginTokenGenerator {
    private static final String SCHEDULING_ENDPOINT = "/api/maintenance-v2/reschedule-work-order/";
    static JSONObject approverCreds;

    public static void scheduleWorkOrder(String workOrderID) throws IOException, ParseException {
//        String userName = JsonFileReader.getJsonData("approverEmail");
//        String password = JsonFileReader.getJsonData("approverPassword");
        approverCreds = (JSONObject) JsonFileReader.getCredentials("approver2");
        String userName = (String) approverCreds.get("email");
        String password = (String) approverCreds.get("password");

        String bearerToken = TokenManager.getToken(userName, password);

        //To Get Current Time in Dubai timezone
        ZonedDateTime now = ZonedDateTime.now(java.time.ZoneId.of("Asia/Dubai"));
        ZonedDateTime futureStart = now.plusHours(2);
        ZonedDateTime futureEnd = now.plusDays(1).plusHours(4);

        //Format As ISO 8601 wit Offset
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
        String startTime = futureStart.format(formatter);
        String endTime = futureEnd.format(formatter);

        //Create JSON request body dynamically
        String scheduleRequestBody = """
                 {
                                        "note": "",
                                "scheduled_start_time": "%s",
                                "scheduled_end_time": "%s"
                }
                """.formatted(startTime, endTime);

        Response scheduleResponse = given()
                .header("authorization", "Bearer " + bearerToken)
                .header("accept", "application/json, text/plain, */*")
                .header("content-type", "application/json")
                .header("timezone", "Asia/Dubai")
                .body(scheduleRequestBody)
                .when()
                .post(SCHEDULING_ENDPOINT + workOrderID)
                .then()
                .statusCode(200)
                .extract().response();

        System.out.println("Response Body: " + scheduleResponse.getBody().asString());


    }
}
