package dusanstanojeviccs.utils;

import io.vertx.core.http.HttpServerRequest;

public class ResponseUtils {
    public static void unathorized(HttpServerRequest req) {
        req.response().setStatusCode(403).end();
    }
    public static void badRequest(HttpServerRequest req) {
        req.response().setStatusCode(400).end();
    }
}
