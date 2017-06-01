package dusanstanojeviccs.controllers;

import dusanstanojeviccs.models.LoginRequest;
import dusanstanojeviccs.models.User;
import dusanstanojeviccs.utils.JWTUtils;
import dusanstanojeviccs.utils.ResponseUtils;
import dusanstanojeviccs.utils.UserUtils;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;

public class AuthorizationRequest {
    public void authorizationRequest(RoutingContext routingContext) {
        HttpServerRequest req = routingContext.request();

        LoginRequest loginRequest = Json.decodeValue(routingContext.getBodyAsString(), LoginRequest.class);

        String name = loginRequest.name;
        String password = loginRequest.password;

        for (User user : UserUtils.users) {
            if (user.name.equals(name) && user.password.equals(password)) {
                req.response().putHeader("content-type", "application/json").end(JWTUtils.generateJWT(user));
                break;
            }
        }

        if (!req.response().ended()) {
            ResponseUtils.badRequest(req);
        }
    }
}
