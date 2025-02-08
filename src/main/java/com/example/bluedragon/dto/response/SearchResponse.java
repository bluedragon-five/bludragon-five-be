package com.example.bluedragon.dto.response;

import com.example.bluedragon.domain.Scholarship;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public record SearchResponse(
        @Schema(description  = "장학금 이름", example= "새로운 장학금 제도")
        String name,

        @Schema(description  = "지원금액(만 원)", example= "100")
        long money,

        @Schema(description  = "남을 디데이", example = "3")
        long remainDays,

        @Schema(description  = "리다이렉트 url", example= "http://redirect_url")
        String redirectUrl
) {

    public static SearchResponse from(Scholarship scholarship) {
        return new SearchResponse(scholarship.getName(), scholarship.getMoney(), calculateRemainDay(scholarship), scholarship.getRedirectUrl());
    }

    private static long calculateRemainDay(Scholarship scholarship) {
        LocalDate today = LocalDate.now();
        LocalDate endDate = scholarship.getEndDate();
        return ChronoUnit.DAYS.between(today, endDate);
    }
}
