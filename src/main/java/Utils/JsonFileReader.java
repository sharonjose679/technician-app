package Utils;

//import org.json.JSONArray;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONArray;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JsonFileReader {
    private static final String FILE_PATH = "resources/TestData/config.json";
    private static JSONObject jsonObject;

//    public static String getJsonData(String key) throws IOException, ParseException {
//        File file = new File(System.getProperty("user.dir") + "/" + FILE_PATH);
//
//        if (!file.exists()) {
//            throw new IOException("JSON file not found at: " + file.getAbsolutePath());
//        }
//
//        JSONParser parser = new JSONParser();
//        JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(file));
//        return (String) jsonObject.get(key);
//    }

    static {
        try {
            File file = new File(System.getProperty("user.dir") + "/" + FILE_PATH);
            JSONParser parser = new JSONParser();
            jsonObject = (JSONObject) parser.parse(new FileReader(file));

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public static JSONObject getDeviceConfig(int index) {
        JSONArray devices = (JSONArray) jsonObject.get("devices");

        return (JSONObject) devices.get(index);
    }

    public static JSONObject getCredentials(String role) {
        JSONObject credentials = (JSONObject) jsonObject.get("credentials");
        return (JSONObject) credentials.get(role);
    }


}
