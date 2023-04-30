package com.example.TokenAuthentication.service;

import com.example.TokenAuthentication.models.Request;
import com.example.TokenAuthentication.models.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

public class AuthWithToken {
    public Response authenticate(Request request, String accessToken){
        RestTemplate restTemplate = new RestTemplate();
        // Set the headers to include the access token for authentication
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        // this step might not be used. But can be used if the request is supposed to be turned into an array as in case of generating token.
        // creating request object
        ObjectMapper objectMapper = new ObjectMapper();
        String requestJson = null;
        try {
            requestJson = objectMapper.writeValueAsString(request);
        } catch (Exception e) {
            e.printStackTrace();
        }


// Create the HTTP entity with the headers
        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);
        // or you can directly insert response in it. You'll have to try that.

// Send the GET request to the API endpoint and get the response
        ResponseEntity<Response> response = restTemplate.exchange("https://api.example.com/data", HttpMethod.GET, entity, Response.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            Response responseBody = response.getBody();
            return responseBody;
            // process the JSON response data as needed
        }
        return null;
    }
}
