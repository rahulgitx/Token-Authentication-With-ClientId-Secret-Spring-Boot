package com.example.TokenAuthentication.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.Objects;

@RestController
@RequestMapping("/auth/")
public class Controller {

    @GetMapping("testing")
    public @ResponseBody String test() {
        return"Working fine";
    }

    @GetMapping("getToken")
    public @ResponseBody String getToken(){
        String clientId = "Insert_here";
        String clientSecret = "Insert_here";
        String tokenUrl = "insert_here";

        // creating a restTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        // create the request body with the client ID, client secret and grant type parameters
        JSONObject requestBody = new JSONObject();
        requestBody.put("clientId", clientId);
        requestBody.put("secret", clientSecret);

        System.out.println("RequestBody created");


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));


        // Create the HTTP entity with the request body and headers
        HttpEntity<String> entity = new HttpEntity<>(requestBody.toString(), headers);
        System.out.println(requestBody.toString());


        // send the POST request to the token URL with the HTTP entity and get the access token
        ResponseEntity<String> response = restTemplate.postForEntity(tokenUrl, entity, String.class);
        String accessToken="empty";
        if (response.getStatusCode() == HttpStatus.OK) {
            String responseBody = response.getBody();
            // process the response body to get the access token

            accessToken = responseBody;
        }

        System.out.println(accessToken);

        return accessToken;

    }

    private static class AccessTokenResponse {
        private String access_token;

        public String getAccessToken() {
            return access_token;
        }

        public void setAccessToken(String access_token) {
            this.access_token = access_token;
        }
    }
}
