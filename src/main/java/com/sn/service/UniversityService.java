package com.sn.service;

import com.sn.dto.UniversityDTO;

import java.util.List;

public interface UniversityService {

    List<UniversityDTO> getUniversitiesForCountry(String inputCountry);

}
