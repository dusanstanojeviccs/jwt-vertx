package dusanstanojeviccs;

import dusanstanojeviccs.controllers.AuthenticatedResourse;
import dusanstanojeviccs.controllers.AuthorizationRequest;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

public class Server extends AbstractVerticle {

    public AuthorizationRequest authorizationRequest;
    public AuthenticatedResourse authenticatedResourse;

    public Server() {
        authorizationRequest = new AuthorizationRequest();
        authenticatedResourse = new AuthenticatedResourse();
    }

    public void start() {
        HttpServer server = vertx.createHttpServer();

        Router router = Router.router(vertx);

        router.route("/request_token").handler(BodyHandler.create());
        router.post("/request_token").handler(authorizationRequest::authorizationRequest);

        router.route("/authenticated/*").handler(authenticatedResourse::authenticatedResourse);

        server.requestHandler(router::accept).listen(8090);
    }
}