package com.example.bluedragon.service;

import com.example.bluedragon.DTO.UserRequest;
import com.example.bluedragon.DTO.UserRequest.SignDTO;
import com.example.bluedragon.domain.User;
import com.example.bluedragon.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  public Optional<User> getUserById(String loginId) {
    return userRepository.findByLoginId(loginId);
  }


  //회원가입
  public User createUser(SignDTO signDTO) {
    // SignDTO에서 User로 변환
    String loginId = signDTO.getLoginId();
    String password = signDTO.getPassword();

    User user = new User(loginId, password);
    // User 객체 저장
    return userRepository.save(user);  // userRepository는 JpaRepository를 상속받는 리포지토리
  }


  public User updateUser(String loginId, UserRequest.InfoFixDTO userDetails) {
    User user = userRepository.findByLoginId(loginId)
        .orElseThrow(() -> new RuntimeException("User not found with id: " + loginId));

    // 기존 정보를 업데이트
    user.setEmail(userDetails.getEmail());
    user.setSection(userDetails.getSection());
    user.setType(userDetails.getType());
    user.setMajor(userDetails.getMajor());
    user.setAttendance(userDetails.isAttendance());
    user.setGrade(userDetails.getGrade());
    return userRepository.save(user);
  }

  public void deleteUser(String loginId) {
    User user = userRepository.findByLoginId(loginId)
        .orElseThrow(() -> new RuntimeException("User not found with id: " + loginId));
    userRepository.delete(user);
  }

  public User findByIdAndPw(String loginId, String password) {
    return userRepository.findByloginIdAndPassword(loginId, password)
        .orElse(null); // 사용자 없으면 null 반환
  }

}



