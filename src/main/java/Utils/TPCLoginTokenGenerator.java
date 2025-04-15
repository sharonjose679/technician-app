package Utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TPCLoginTokenGenerator {
    public static String getToken(String username, String password) {


        // Replace with your authentication endpoint URL
        String authUrl = "https://saas-stage.wiot360.com/api/auth/external/sign-in";
        // Replace with the necessary body or credentials for authentication
        String requestBody = "{ \"email\": \"" + username + "\", \"password\": \"" + password + "\", \"portal\": \"tpc\", \"timezone\": \"Asia/Dubai\"}";

        // Send a POST request to get the JWT token
        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post(authUrl);

        // Print the response to understand the structure
        //System.out.println("Auth Response: " + response.asString());

        // Extract the token (assuming it's returned in a field named "token")
        String token = response.jsonPath().getString("access_token"); // Adjust based on the actual response
        System.out.println("JWT Token: " + token);
        //fetchOtpWithRestAssured(token);
        return token;

    }

}
