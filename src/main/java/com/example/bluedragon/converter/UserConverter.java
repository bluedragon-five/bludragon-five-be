package com.example.bluedragon.converter;

import com.example.bluedragon.dto.UserRequest;
import com.example.bluedragon.domain.User;

public class UserConverter {

  public static UserRequest.InfoDTO toQueryDTO(User user){
    return UserRequest.InfoDTO.builder()
        .email(user.getEmail())
        .major(user.getMajor())
        .grade(user.getGrade())
        .type(user.getType())
        .attendance(user.isAttendance())
        .section(user.getSection())
        .build();
  }
}
