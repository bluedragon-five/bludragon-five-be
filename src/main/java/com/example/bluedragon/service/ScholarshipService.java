package com.example.bluedragon.service;

import com.example.bluedragon.domain.Major;
import com.example.bluedragon.domain.Scholarship;
import com.example.bluedragon.domain.SortCondition;
import com.example.bluedragon.domain.Type;
import com.example.bluedragon.domain.User;
import com.example.bluedragon.dto.request.SearchRequest;
import com.example.bluedragon.dto.response.SearchResponses;
import com.example.bluedragon.repository.ScholarshipRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ScholarshipService {

    private final ScholarshipRepository scholarshipRepository;

    @Transactional(readOnly = true)
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

    @Transactional(readOnly = true)
    public SearchResponses searchByUser(User user) {
        List<Scholarship> userResults = search(user.getGrade(),
                user.getType(),
                user.getMajor(),
                user.isAttendance(),
                user.getGrade(),
                buildSort(SortCondition.NOTHING)
        );
        return SearchResponses.of(userResults);
    }

    private Sort buildSort(SortCondition sortCondition) {
        if (sortCondition == SortCondition.NOTHING || sortCondition == SortCondition.MONEY) {
            return Sort.by(Sort.Direction.ASC, "endDate");
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
        if(type==Type.NOTHING){
            return scholarshipRepository.findByConditionNothing(section, major, attendance, grade, sort);
        }
        return scholarshipRepository.findByCondition(section, type, major, attendance, grade, sort);
    }
}
