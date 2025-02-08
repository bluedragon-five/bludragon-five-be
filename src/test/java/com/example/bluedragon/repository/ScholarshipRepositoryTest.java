package com.example.bluedragon.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.bluedragon.domain.Major;
import com.example.bluedragon.domain.Scholarship;
import com.example.bluedragon.domain.Type;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

@DataJpaTest
class ScholarshipRepositoryTest {

    @Autowired
    private ScholarshipRepository scholarshipRepository;


    @Test
    void searchTest() {
        Scholarship scholarship = new Scholarship(
                null,
                "장학1",
                20L,
                LocalDate.now().plusDays(1L),
                4,
                Type.LIVING,
                Major.NOTHING,
                true,
                0,
                "rediretUrl"
        );

        Scholarship scholarship2 = new Scholarship(
                null,
                "장학1",
                21L,
                LocalDate.now().plusDays(1L),
                4,
                Type.LIVING,
                Major.NOTHING,
                true,
                0,
                "rediretUrl"
        );

        scholarshipRepository.save(scholarship);
        scholarshipRepository.save(scholarship2);

        List<Scholarship> condition = scholarshipRepository.findByCondition(
                4,
                Type.LIVING,
                Major.NOTHING,
                true,
                2,
                Sort.by(Direction.DESC, "money")
        );
        assertThat(condition).containsExactly(scholarship2, scholarship);
    }

}
