package com.example.bluedragon.controller;

import com.example.bluedragon.dto.request.SearchRequest;
import com.example.bluedragon.dto.response.SearchResponses;
import com.example.bluedragon.service.ScholarshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ScholarShipController implements ScholarShipSwagger {

    private final ScholarshipService scholarshipService;

    @Override
    @PostMapping("/api/scholarship")
    public ResponseEntity<SearchResponses> searchScholarShip(@RequestBody SearchRequest searchRequest) {
        SearchResponses responses = scholarshipService.searchScholarships(searchRequest);
        return ResponseEntity.ok(responses);
    }
}
