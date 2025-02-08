package com.example.bluedragon.controller;

import com.example.bluedragon.dto.request.SearchRequest;
import com.example.bluedragon.dto.response.SearchResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "ScholarShip API")
public interface ScholarShipSwagger {

    @Operation(
            summary = "맞춤 장학금 검색하기",
            requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = SearchRequest.class))),
            responses = {@ApiResponse(responseCode = "200", description = "장학금 조회 성공 성공")}
    )
    ResponseEntity<SearchResponses> searchScholarShip(SearchRequest searchRequest);

}
