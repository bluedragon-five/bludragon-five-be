package com.example.bluedragon.dto.response;

import com.example.bluedragon.domain.Scholarship;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public record SearchResponse(
        String name,
        long money,
        long remainDays,
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
