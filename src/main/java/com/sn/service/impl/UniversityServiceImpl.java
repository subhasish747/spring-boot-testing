package com.sn.service.impl;

import com.sn.dto.UniversityDTO;
import com.sn.service.UniversityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
@Slf4j
public class UniversityServiceImpl implements UniversityService {

    private final RestTemplate restTemplate;


    private final String universityBaseURL;

    public UniversityServiceImpl(RestTemplate restTemplate, @Value("${universitiesBaseURL}") String universityBaseURL) {
        this.restTemplate = restTemplate;
        this.universityBaseURL = universityBaseURL;
    }
    @Override
    public List<UniversityDTO> getUniversitiesForCountry(String inputCountry) {
        String urlTemplate = UriComponentsBuilder.fromHttpUrl(universityBaseURL + "/search").queryParam("country", inputCountry).encode().toUriString();
        ResponseEntity<List<UniversityDTO>> rateResponse =
                restTemplate.exchange(urlTemplate,
                        HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                        }, inputCountry);
        return rateResponse.getBody();
    }
}
