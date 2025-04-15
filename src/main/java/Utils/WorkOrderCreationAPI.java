package Utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static io.restassured.path.json.JsonPath.from;

public class WorkOrderCreationAPI {
    protected static final String BASE_URL = "https://saas-stage.wiot360.com/";
    private static final String WorkOrderCreationEndPoint = "/api/maintenance-v2/orders";

    static JSONObject tpcCreds;
    static int totalItemsCount;

    public static String createMaintenanceOrder() throws IOException, ParseException {
//        String userName = JsonFileReader.getJsonData("tpcEmail");
//        String password = JsonFileReader.getJsonData("tpcPassword");
        tpcCreds = (JSONObject) JsonFileReader.getCredentials("tpc");
        String userName = (String) tpcCreds.get("email");
        String password = (String) tpcCreds.get("password");

        String bearerToken = TPCLoginTokenGenerator.getToken(userName, password);

        //Use text blocks """  """ for java language level 15+
        String maintenaceRequestBody = """
                                {
                                        "prefix": "",
                            "unique_id": "VU5BWAXG",
                            "request_type": "work_order",
                            "when": "asap",
                            "priority": "urgent",
                            "garage": "in_house",
                            "approvals": [],
                        "distance": 0,
                            "duration": 0,
                            "packages": [
                        {
                            "name": "General Inspection",
                                "package_id": 9,
                                "package_item_types": [
                            {
                                "compartment_type": "Oil Seal",
                                    "compartment_type_id": 176,
                                    "service_tag": "Service",
                                    "service_tag_id": 61,
                                    "quantity": 1,
                                    "part_type": "part",
                                    "compartment_id": 11,
                                    "compartment": "Engine",
                                    "compartment_category": "Oil",
                                    "compartment_category_id": 89,
                                    "part_id": 252,
                                    "part_name": "781h-77",
                                    "sub_category_id": 227,
                                    "sub_category": "Nissan",
                                    "unit_size": 1,
                                    "unit_cost": 22,
                                    "unit_type": "Unit",
                                    "cost": 22
                            },
                            {
                                "compartment_type": "Steering Oil",
                                    "compartment_type_id": 177,
                                    "service_tag": "Service",
                                    "service_tag_id": 61,
                                    "quantity": 1,
                                    "part_type": "part",
                                    "compartment_id": 11,
                                    "compartment": "Engine",
                                    "compartment_category": "Oil",
                                    "compartment_category_id": 89,
                                    "part_id": 5297,
                                    "part_name": "oil sub part 2",
                                    "sub_category_id": 233,
                                    "sub_category": "oil sub",
                                    "unit_size": 2,
                                    "unit_cost": 1,
                                    "unit_type": "Unit",
                                    "cost": 1
                            }
                            ],
                            "extra_package_item_types": []
                        }
                        ],
                        "notifications": [],
                        "notify_departments": [],
                        "assets": [
                        {
                            "vehicle_id": 1332,
                                "vehicle_uuid": "8L6L5MFEJBW",
                                "vehicle_make_id": 112,
                                "vehicle_make": "Toyota",
                                "vehicle_model_id": 202,
                                "vehicle_model": "Corolla",
                                "year": 2024,
                                "image": "",
                                "vehicle_plate": "AD-7-968947",
                                "vehicle_num": "VEHC-00000968947",
                                "capacity": 5,
                                "color": "#000000FF",
                                "plate_color": "#970606FF",
                                "asset_type_id": 4589,
                                "asset_type": "Sedan",
                                "asset_name_id": 325,
                                "asset_name": "Vehicle",
                                "vehicle_numbers": [
                            "VEHC-00000968947"
                            ],
                            "odometer": 15000000,
                                "status": true,
                                "viewable_branches": [
                            "67b46611218be61d93aead52",
                                    "67b45b79218be61d93aeac8e"
                            ],
                            "viewable_departments": [
                            "67b46611218be61d93aead51",
                                    "67b45b79218be61d93aeac8f",
                                    "67d1391493657908d23054e4",
                                    "67b471ae218be61d93aeaed4",
                                    "67b471aa218be61d93aeaed2"
                            ]
                        }
                        ]
                }""";
        //Extract package array size
        int packageCount = from(maintenaceRequestBody).getList("packages").size();
        int itemsCount = 0;
        for (int i = 0; i < packageCount; i++) {
            itemsCount += from(maintenaceRequestBody).getList("packages[" + i + "].package_item_types").size();
            //int extraItemCount = from(maintenaceRequestBody).getList("packages["+i+"].extra_package_item_types").size();
            //totalItemsCount = itemsCount+extraItemCount;
        }

        RestAssured.baseURI = BASE_URL;
        Response woResponse = given()
                .header("Authorization", "Bearer " + bearerToken)
                .header("Content-Type", "application/json")
                .header("timezone", "Asia/Dubai")
                .body(maintenaceRequestBody)
                .when()
                .post(WorkOrderCreationEndPoint)
                .then()
                .statusCode(200)
                .extract().response();
        String workOrderId = woResponse.jsonPath().getString("data[0].id");
        return workOrderId;
        // return new WorkOrderResponseCustomClass(workOrderId, itemsCount);
    }

}
