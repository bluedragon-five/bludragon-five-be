package com.example.bluedragon.dto.request;

import com.example.bluedragon.domain.Major;
import com.example.bluedragon.domain.SortCondition;
import com.example.bluedragon.domain.Type;
import io.swagger.v3.oas.annotations.media.Schema;

public record SearchRequest(
        @Schema(description  = "장학금 지원구간", example= "4")
        long section,

        @Schema(description  = "장학금 유형", example= "LIVING")
        Type type,

        @Schema(description  = "장학금 전공 계열", example= "SCIENCE")
        Major major,

        @Schema(description  = "재학여부", example= "true")
        boolean attendance,

        @Schema(description  = "지원가능 학년", example= "4")
        long grade,

        @Schema(description  = "정렬 조건", example= "NOTHING")
        SortCondition sortCondition
) {

}
