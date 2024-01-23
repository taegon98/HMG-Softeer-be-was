package controller;

import request.HttpRequest;
import response.HttpResponse;

import java.io.File;
import java.io.IOException;

import static util.Uri.*;
import static util.StatusCode.*;

public class HomeController implements Controller {
    private volatile static HomeController instance;

    private HomeController() {
    }

    public static HomeController getInstance() {
        if (instance == null) {
            instance = new HomeController();
        }
        return instance;
    }

    @Override
    public HttpResponse handleUserRequest(HttpRequest httpRequest) throws IOException {
        String uri = httpRequest.getUri();
        String filePath = httpRequest.getFilePath(uri);
        String method = httpRequest.getMethod();

        File file = new File(filePath);

        if (file.exists() && method.equals("GET")) {
            if (uri.equals(HOME.getUri())) {
                return new HttpResponse(FOUND, "text/html", "/index.html");
            }
            return new HttpResponse(OK, filePath);
        }

        return new HttpResponse(NOT_FOUND);
    }
}
