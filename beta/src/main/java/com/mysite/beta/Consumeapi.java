package com.mysite.beta;

/*import org.springframework.web.client.RestTemplate;*/
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.Arrays;

/*importt org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;*/
import java.util.List;
import java.util.*;


public class Consumeapi {

    final String uri = "https://api.github.com/users/teachnologist";
    final String rate_uri = "https://api.github.com/rate_limit";

    public GithubUser getGithubJSON(){
        RestTemplate restTemplate = new RestTemplate();
        String jsonString = restTemplate.getForObject(uri, String.class);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        GithubUser result = null;
        try {
            System.out.println("Trying....");
            result = mapper.readValue(jsonString, GithubUser.class);
        } catch (Exception e) {
            System.out.println("t Invalid results");
            System.out.print(e);
            System.out.println("b Invalid results");
        }


        return result;
    }



    public GithubRate getGithubRateLimit(){
        RestTemplate restTemplate = new RestTemplate();
        String jsonString = restTemplate.getForObject(rate_uri, String.class);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        GithubRate result = null;
        try {
            System.out.println("Trying....");
            result = mapper.readValue(jsonString, GithubRate.class);
        } catch (Exception e) {
            System.out.println("t Invalid results");
            System.out.print(e);
            System.out.println("b Invalid results");
        }


        return result;
    }

    public Object parseNestedMap(Map json){
        System.out.println("parsing nested");


        Object result = null;
        try {
            String newjson = new ObjectMapper().writeValueAsString(json);

            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            System.out.println("parsing json");
            result = mapper.readValue(newjson, Object.class);
        } catch (Exception e) {
            System.out.println("t Invalid results");
            System.out.print(e);
            System.out.println("b Invalid results");
        }

        return result;
    }

    public List<GithubRepo> getGithubREPOS(String repo_url){
        RestTemplate restTemplate = new RestTemplate();
        String jsonString = restTemplate.getForObject(repo_url, String.class);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<GithubRepo> result = null;
        try {
            System.out.println("valid string");
            System.out.print(jsonString);
            System.out.println("b valid string");
            result = mapper.readValue(jsonString,  new TypeReference<List<GithubRepo>>(){});
        } catch (Exception e) {
            System.out.println("t Invalid results");
            System.out.print(e);
            System.out.println("b Invalid results");
        }


        return result;
    }

    public List<Array> getAnonymousJSONArray(String url){
        RestTemplate restTemplate = new RestTemplate();
        String jsonString = restTemplate.getForObject(url, String.class);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<Array> result = null;
        try {
            result = mapper.readValue(jsonString, new TypeReference<List<Array>>(){});
        } catch (Exception e) {
            System.out.println("t Invalid results");
            System.out.print(e);
            System.out.println("b Invalid results");
        }


        return result;
    }

}
