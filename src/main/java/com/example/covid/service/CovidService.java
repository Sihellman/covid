package com.example.covid.service;

import com.example.covid.model.TimeServerResponse;
import com.sun.jndi.toolkit.url.Uri;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.Time;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class CovidService {
    private final RestTemplate restTemplate;
    public String getQuarantineTime(int year, int month, int day){
        try{
            TimeServerResponse timeServerResponse =  restTemplate.exchange("localhost:8080/api/v1/getTime",
                    HttpMethod.GET, null, TimeServerResponse.class).getBody();
            return timeServerResponse.getLocalTime();
        }catch (RestClientException e){

        }
        return null;

    }
}
