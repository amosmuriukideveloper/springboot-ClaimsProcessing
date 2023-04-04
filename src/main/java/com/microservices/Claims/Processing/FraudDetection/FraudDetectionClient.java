package com.microservices.Claims.Processing.FraudDetection;

import com.microservices.Claims.Processing.DTO.ClaimDTO;
import com.microservices.Claims.Processing.models.Claim;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FraudDetectionClient {
//    @Value("${fraudDetection.baseUrl}")
    private String baseUrl;

    private final RestTemplate restTemplate;

    public FraudDetectionClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public boolean detectFraud(ClaimDTO claimDto) {
        String url = baseUrl + "/fraud-detection";
        HttpEntity<ClaimDTO> requestEntity = new HttpEntity<>(claimDto);
        ResponseEntity<Boolean> responseEntity = restTemplate.exchange(
                url, HttpMethod.POST, requestEntity, Boolean.class);
        return responseEntity.getBody();
    }

    public Object isFraudulent(Claim claim) {
        return null;
    }
}
