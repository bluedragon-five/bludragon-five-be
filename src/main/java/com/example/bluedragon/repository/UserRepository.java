package com.example.bluedragon.repository;

import com.example.bluedragon.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByloginIdAndPassword(String loginId, String password);

  Optional<User> findByLoginId(String loginId);

}
