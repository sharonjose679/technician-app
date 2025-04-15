package Utils;

import java.util.concurrent.ConcurrentHashMap;

public class TokenManager {
    private static final ConcurrentHashMap<String, String> tokenMap = new ConcurrentHashMap<>();

    public static String getToken(String email, String password) {

        //If token exists reuse it
        if (tokenMap.containsKey(email)) {
            return tokenMap.get(email);
        }

        //If not token exists generate new one
        String token = TPCLoginTokenGenerator.getToken(email, password);
        tokenMap.put(email, token);
        return token;
    }

}
