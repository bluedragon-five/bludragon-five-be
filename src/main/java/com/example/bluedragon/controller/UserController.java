package com.example.bluedragon.controller;


import com.example.bluedragon.DTO.UserRequest;
import com.example.bluedragon.domain.User;
import com.example.bluedragon.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpSession;
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
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  //아이디로 유저 조회
  @Operation(
      summary = "Get user by ID",
      description = "Retrieves a specific user by their ID"
  )
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          description = "Successfully retrieved the user",
          content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))
      ),
      @ApiResponse(
          responseCode = "404",
          description = "User not found",
          content = @Content
      )
  })
  @GetMapping("/{loginId}")
  public ResponseEntity<User> getUserById(
      @Parameter(description = "ID of the user to retrieve") @PathVariable String loginId
  ) {
    return userService.getUserById(loginId)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  //로그인
  @Operation(
      summary = "Login",
      description = "Login ID and Password"
  )
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          description = "Successfully retrieved the user",
          content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))
      ),
      @ApiResponse(
          responseCode = "404",
          description = "User not found",
          content = @Content
      )
  })
  @GetMapping("/login")
  public ResponseEntity<?> login(@RequestParam String loginId, @RequestParam String password,
      HttpSession session) {
    User entity = userService.findByIdAndPw(loginId, password);

    if (entity == null) {
      session.setAttribute("user", false);
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body("failed");
    } else {
      session.setAttribute("user", true);
      session.setAttribute("Id", entity.getId());
      return ResponseEntity.ok(entity);
    }

  }

  //회원가입
  @Operation(
      summary = "Create a new user",
      description = "Creates a new user in the system"
  )
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          description = "User successfully created",
          content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))
      )
  })
  @PostMapping
  public User createUser(
      @Parameter(description = "User details to create", required = true)
      @RequestBody UserRequest.SignDTO user
  ) {
    return userService.createUser(user);
  }


  //개인정보 수정
  @Operation(
      summary = "Update user",
      description = "Updates an existing user's information"
  )
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          description = "User successfully updated",
          content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))
      ),
      @ApiResponse(
          responseCode = "404",
          description = "서버에러",
          content = @Content
      )
  })
  @PostMapping("/info")
  public ResponseEntity<User> updateUser(
      @Parameter(description = "ID of the user to update") String loginId,
      @Parameter(description = "Updated user details", required = true)
      @RequestBody UserRequest.InfoFixDTO userDetails
  ) {
    try {
      User updatedUser = userService.updateUser(loginId, userDetails);
      return ResponseEntity.ok(updatedUser);
    } catch (RuntimeException e) {
      return ResponseEntity.notFound().build();
    }
  }


}
