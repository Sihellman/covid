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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.net.URI;
import java.util.Date;
import java.time.LocalDate;
import java.time.Month;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

import static java.time.temporal.ChronoUnit.DAYS;


@Service
@RequiredArgsConstructor
public class CovidService {
    private final RestTemplate restTemplate;
    public  String getQuarantineTime(int year, int month, int day){
        TimeServerResponse timeServerResponse;
        try{

             timeServerResponse =  restTemplate.exchange("http://localhost:8080/api/v1/getTime",
                    HttpMethod.GET, null, TimeServerResponse.class).getBody();

            LocalDate exposure = LocalDate.of(year, month, day);
            LocalDate free = exposure.plus(15, DAYS);

            assert timeServerResponse != null;
            LocalDate now = LocalDate.parse(timeServerResponse.getLocalTime());
            long noOfDaysBetween = now.until(free, DAYS);
            String answer = noOfDaysBetween +"";

            if (noOfDaysBetween < 0){
                answer = "you were able to leave on " + free;
            }
            if (noOfDaysBetween == 0){
                answer = "today you are free";
            }
            if (exposure.compareTo(now) > 0){
                answer = "this date has not occurred";
            }

            return answer;

        }catch (RestClientException e){

        }
        return null;

    }

    public static String getTime(){
        Date date = new Date();
        return date.toString();
    }
}
