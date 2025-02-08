package com.example.bluedragon.repository;

import com.example.bluedragon.domain.Scholarship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScholarshipRepository extends JpaRepository<Scholarship, Long> {
    
}
