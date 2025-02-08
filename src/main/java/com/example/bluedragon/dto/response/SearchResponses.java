package com.example.bluedragon.dto.response;

import com.example.bluedragon.domain.Scholarship;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

public record SearchResponses(
        @ArraySchema(schema = @Schema(description = "로그 목록", implementation = SearchResponse.class))
        List<SearchResponse> scholarships
) {
    public static SearchResponses of(List<Scholarship> scholarships) {
        List<SearchResponse> responses = scholarships.stream()
                .map(SearchResponse::from)
                .toList();

        return new SearchResponses(responses);
    }
}
