package com.example.bluedragon.service;

import com.example.bluedragon.domain.Major;
import com.example.bluedragon.domain.Scholarship;
import com.example.bluedragon.domain.SortCondition;
import com.example.bluedragon.repository.ScholarshipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScholarshipService {

    private final ScholarshipRepository scholarshipRepository;

    public List<Scholarship> searchScholarship(long section, Type type, Major major, boolean attendance, long grade, Sort sort) {
        //1. 유저의 소득 분위보다 적어야 함
        //2.오늘보다 마감기한이 늦어야 함.

        return scholarshipRepository.findByCondition(section, type, major, attendance, grade, sort);
    }
}
