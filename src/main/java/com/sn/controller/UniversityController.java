package com.sn.controller;

import com.sn.dto.UniversityDTO;
import com.sn.service.UniversityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/university")
@RequiredArgsConstructor
public class UniversityController {

    private final UniversityService universityService;

    @GetMapping
    @Operation(summary = "Get the universities for a given country")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Universities for the given country",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UniversityDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Universities not found for the given country",
                    content = @Content)})
    public List<UniversityDTO> getUniversitiesForCountry(@RequestParam String country) {
        return universityService.getUniversitiesForCountry(country);
    }
}