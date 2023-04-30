package com.example.TokenAuthentication.controllers;

import com.example.TokenAuthentication.models.Credentials;
import com.example.TokenAuthentication.service.GenerateToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping ("getToken")
    public @ResponseBody String getToken(){
        Credentials credentials = new Credentials();

        credentials.setClientId("");
        credentials.setSecret("");
        String url = "";

        System.out.println("Calling GenerateToken");
        GenerateToken gen = new GenerateToken();
        return gen.generateToken(credentials, url);

    }


}
