package com.example.bluedragon.dto.response;

import com.example.bluedragon.domain.Scholarship;
import java.util.List;

public record SearchResponses(
        List<SearchResponse> scholarships
) {
    public static SearchResponses of(List<Scholarship> scholarships) {
        List<SearchResponse> responses = scholarships.stream()
                .map(SearchResponse::from)
                .toList();

        return new SearchResponses(responses);
    }
}
