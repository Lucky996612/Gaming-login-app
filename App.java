package com.gaming;

import static spark.Spark.*;
import com.google.gson.*;

import java.util.HashMap;
import java.util.Map;

public class App {
    static class LoginRequest {
        String username;
        String password;
    }

    public static void main(String[] args) {
        port(8080);
        post("/login", (req, res) -> {
            Gson gson = new Gson();
            LoginRequest login = gson.fromJson(req.body(), LoginRequest.class);

            Map<String, String> response = new HashMap<>();
            if ("player".equals(login.username) && "secret".equals(login.password)) {
                response.put("status", "success");
                response.put("message", "Login successful!");
            } else {
                response.put("status", "fail");
                response.put("message", "Invalid credentials");
            }
            res.type("application/json");
            return gson.toJson(response);
        });
    }
}
