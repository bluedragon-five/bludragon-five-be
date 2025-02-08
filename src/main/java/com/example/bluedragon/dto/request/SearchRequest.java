package com.example.bluedragon.dto.request;

import com.example.bluedragon.domain.Major;
import com.example.bluedragon.domain.SortCondition;
import com.example.bluedragon.domain.Type;

public record SearchRequest(
        long section,
        Type type,
        Major major,
        boolean attendance,
        long grade,
        SortCondition sortCondition
) {

}
