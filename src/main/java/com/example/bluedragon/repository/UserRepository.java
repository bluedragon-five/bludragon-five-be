package com.example.bluedragon.repository;

import com.example.bluedragon.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository<User, Long> {

  // signid와 password를 기준으로 사용자 찾기
  @Query("""
      select u
      from User u
      where u.loginId = :loginId
      and u.password = :password
      """)
  Optional<User> findByloginIdAndPassword(String loginId, String password);

  Optional<User> findByLoginId(String loginId);

}