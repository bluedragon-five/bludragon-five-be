package com.example.bluedragon.repository;

import com.example.bluedragon.domain.Major;
import com.example.bluedragon.domain.Scholarship;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.lang.reflect.Type;
import java.util.List;

public interface ScholarshipRepository extends JpaRepository<Scholarship, Long> {

    @Query("""
       select s
       from Scholarship as s
       where s.section >= :section
       and s.type = :type
       and s.major = :major
       and s.attendance = :attendance
       and s.end_date >= now()
      """)
    List<Scholarship> findByCondition(long section, Type type, Major major, boolean attendance, long grade, Sort sort);
}
