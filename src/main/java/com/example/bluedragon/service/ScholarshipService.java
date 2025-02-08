package com.example.bluedragon.service;

import com.example.bluedragon.domain.Major;
import com.example.bluedragon.domain.Scholarship;
import com.example.bluedragon.domain.SortCondition;
import com.example.bluedragon.domain.Type;
import com.example.bluedragon.dto.request.SearchRequest;
import com.example.bluedragon.dto.response.SearchResponses;
import com.example.bluedragon.repository.ScholarshipRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScholarshipService {

    private final ScholarshipRepository scholarshipRepository;

    public SearchResponses searchScholarships(SearchRequest request) {
        List<Scholarship> results = search(request.section(),
                request.type(),
                request.major(),
                request.attendance(),
                request.grade(),
                buildSort(request.sortCondition())
        );
        return SearchResponses.of(results);
    }

    private Sort buildSort(SortCondition sortCondition) {
        if (sortCondition == SortCondition.NOTHING || sortCondition == SortCondition.MONEY) {
            return Sort.by(Sort.Direction.ASC, "end_date");
        }
        return Sort.by(Sort.Direction.DESC, "money");
    }

    private List<Scholarship> search(
            long section,
            Type type,
            Major major,
            boolean attendance,
            long grade,
            Sort sort
    ) {
        return scholarshipRepository.findByCondition(section, type, major, attendance, grade, sort);
    }
}
