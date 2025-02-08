package com.example.bluedragon.domain;

import com.example.bluedragon.dto.UserRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.lang.Long;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "member")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "login_id", nullable = false, length = 20)
  private String loginId;

  @Column(nullable = false, length = 8)
  private String password;

  @Column(nullable = true, length = 255)
  private String email;

  @Column(nullable = false, length = 255)
  private Long section;

  @Enumerated(value= EnumType.STRING)
  @Column(nullable = false, length = 255)
  private Type type; //이넘

  @Enumerated(value= EnumType.STRING)
  @Column(nullable = false)
  private Major major;

  @Column(nullable = false)
  private boolean attendance;

  @Column(nullable = false)
  private long grade;


  public User(String loginId, String password) { // 생성자 오버로딩
    // 매개값 이용 필드변수 초기화
    this.loginId = loginId;
    this.password = password;
    this.type = Type.NOTHING;
    this.major = Major.NOTHING;
    this.section = 0L;
    this.attendance = true;
    this.grade = 1;
  }


  public void update(UserRequest.InfoDTO infoDTO) {
    this.email = infoDTO.getEmail();
    this.type = infoDTO.getType();
    this.major = infoDTO.getMajor();
    this.grade = infoDTO.getGrade();
    this.section = infoDTO.getSection();
    this.attendance = infoDTO.isAttendance();
  }
}
