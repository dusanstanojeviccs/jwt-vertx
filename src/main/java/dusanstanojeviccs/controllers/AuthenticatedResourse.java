package dusanstanojeviccs.controllers;

import dusanstanojeviccs.utils.JWTUtils;
import dusanstanojeviccs.utils.ResponseUtils;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.RoutingContext;

public class AuthenticatedResourse {

    public void authenticatedResourse(RoutingContext routingContext) {
        HttpServerRequest req = routingContext.request();

        if (req.headers().contains("Authorization")) {
            String authorizationHeader = req.headers().get("Authorization");

            if (authorizationHeader.startsWith("Bearer ")) {
                String jwt = authorizationHeader.replace("Bearer ", "");

                if (JWTUtils.isValidJWT(jwt)) {
                    req.response().end("{date: \"Some awesome data\"}");
                } else {
                    ResponseUtils.unathorized(req);
                }
            } else {
                ResponseUtils.unathorized(req);
            }
        } else {
            ResponseUtils.unathorized(req);
        }
    }
}
