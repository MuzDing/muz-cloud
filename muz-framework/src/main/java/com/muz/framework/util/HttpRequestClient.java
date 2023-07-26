package com.muz.framework.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Component
public class HttpRequestClient {
    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<String> request(String postUrl, String postData, HttpHeaders httpHeaders) {
        HttpEntity<String> requestData = new HttpEntity<>(postData, httpHeaders);
        ResponseEntity<String> postResult = restTemplate.exchange(postUrl, HttpMethod.POST, requestData, String.class);
        return postResult;
    }

    public ResponseEntity<String> requestJson(String postUrl, String postData){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestData = new HttpEntity<>(postData, headers);
        ResponseEntity<String> postResult = restTemplate.exchange(postUrl, HttpMethod.POST, requestData, String.class);
        return postResult;
    }

    public ResponseEntity<String> requestForm(String postUrl, String postData) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<String> requestData = new HttpEntity<>(postData, headers);
        ResponseEntity<String> postResult = restTemplate.exchange(postUrl, HttpMethod.POST, requestData, String.class);
        return postResult;
    }

}
