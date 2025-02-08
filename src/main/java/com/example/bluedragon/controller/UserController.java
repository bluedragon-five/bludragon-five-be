package com.example.bluedragon.controller;


import com.example.bluedragon.dto.UserRequest;
import com.example.bluedragon.dto.UserRequest.InfoDTO;
import com.example.bluedragon.converter.UserConverter;
import com.example.bluedragon.domain.User;
import com.example.bluedragon.dto.request.LoginRequest;
import com.example.bluedragon.dto.response.LoginResponse;
import com.example.bluedragon.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpSession;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController implements UserControllerSwagger{

  private final UserService userService;

  @Override
  @GetMapping("/api/user/info")
  public ResponseEntity<InfoDTO> getUserById(
      @RequestParam(value = "userId") long userId
  ) {
    Optional<User> optionalUser = userService.getUserById(userId);
    User user = optionalUser.get();
    InfoDTO infoDTO = UserConverter.toQueryDTO(user);
    return ResponseEntity.ok(infoDTO);
  }

  @PostMapping("/api/user/login")
  public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
    User entity = userService.findByIdAndPw(loginRequest.loginId(), loginRequest.password());

    if (entity == null) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body("일치하는 유저 없음");
    } else {
      return ResponseEntity.ok(new LoginResponse(entity.getId()));
    }
  }

  @PostMapping("/api/user")
  public User createUser(
      @RequestBody UserRequest.SignDTO user
  ) {
    return userService.createUser(user);
  }

  @PostMapping("/api/user/info")
  public ResponseEntity<User> updateUser(
      @RequestParam(value ="userId") long userId,
      @RequestBody UserRequest.InfoDTO userDetails

  ) {
    try {
      User updatedUser = userService.updateUser(userId, userDetails);
      return ResponseEntity.ok(updatedUser);
    } catch (RuntimeException e) {
      return ResponseEntity.notFound().build();
    }
  }
}
