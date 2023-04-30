package com.example.TokenAuthentication.service;

import com.example.TokenAuthentication.models.Credentials;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

public class GenerateToken {
    public String generateToken(Credentials credentials, String url){

        RestTemplate restTemplate = new RestTemplate();


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        ObjectMapper objectMapper = new ObjectMapper();
        String requestJson = null;
        try {
            requestJson = objectMapper.writeValueAsString(credentials);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(requestJson);

        HttpEntity<String> httpEntity = new HttpEntity<>(requestJson, headers);

        // Send the POST request with the HttpEntity and receive the response as a ResponseEntity
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, httpEntity, String.class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            String accessToken = responseEntity.getBody();
            return accessToken;
        }
        return null;
    }
}
