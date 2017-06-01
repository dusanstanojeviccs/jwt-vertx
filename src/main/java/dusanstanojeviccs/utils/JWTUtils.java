package dusanstanojeviccs.utils;

import io.vertx.core.json.Json;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;


public class JWTUtils {
    public static String secret = "DasansSuperCool123secr3t";

    public static String generateJWT(Object toSend) {
        String header = "{\"alg\":\"HS256\",\"typ\":\"JWT\"}";

        String payload = Json.encode(toSend);

        String header64 = Base64.getUrlEncoder().encodeToString(header.getBytes());
        String payload64 = Base64.getUrlEncoder().encodeToString(payload.getBytes());

        String verification = Base64.getUrlEncoder().encodeToString(encode(secret,  header64+ "." + payload64));

        return header64 + "." + payload64 + "." + verification;
    }

    public static byte[] encode(String key, String data) {
        try {
            Mac hmac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
            hmac.init(secret_key);
            return hmac.doFinal(data.getBytes("UTF-8"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean isValidJWT(String jwt) {
        String[] parts = jwt.split("\\.");

        if (parts.length == 3) {
            String encoded = Base64.getUrlEncoder().encodeToString(encode(secret, parts[0] + "." + parts[1]));

            return encoded.equals(parts[2]);
        }
        return false;
    }
}
