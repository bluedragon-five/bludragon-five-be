package com.example.bluedragon.dto;

import com.example.bluedragon.domain.Major;
import com.example.bluedragon.domain.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserRequest {

  @Builder
  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class SignDTO{
    String loginId;
    String password;
  }

  @Builder
  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class InfoDTO{
    String email;
    Long section;
    Type type;
    Major major;
    boolean attendance;
    long grade;


  }

}
