package com.example.bluedragon.service;

import com.example.bluedragon.dto.UserRequest;
import com.example.bluedragon.dto.UserRequest.SignDTO;
import com.example.bluedragon.domain.User;
import com.example.bluedragon.repository.UserRepository;
import com.example.bluedragon.service.email.EmailService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final EmailService emailService;

  @Transactional
  public Optional<User> getUserById(long userId) {
    return userRepository.findById(userId);
  }

  @Transactional
  public User createUser(SignDTO signDTO) {
    String loginId = signDTO.getLoginId();
    String password = signDTO.getPassword();
    User user = new User(loginId, password);
    return userRepository.save(user);
  }

  @Transactional
  public User updateUser(long userId, UserRequest.InfoDTO userDetails) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

    // 기존 정보를 업데이트
    user.update(userDetails);
    emailService.sendEmail(user);
    return userRepository.save(user);
  }

  @Transactional
  public User findByIdAndPw(String loginId, String password) {
    return userRepository.findByloginIdAndPassword(loginId, password)
        .orElse(null);
  }
}



