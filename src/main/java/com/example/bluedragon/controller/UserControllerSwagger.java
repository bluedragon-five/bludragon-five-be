package com.example.bluedragon.controller;

import com.example.bluedragon.domain.User;
import com.example.bluedragon.dto.UserRequest;
import com.example.bluedragon.dto.UserRequest.InfoDTO;
import com.example.bluedragon.dto.request.LoginRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;

public interface UserControllerSwagger {

    //개인 정보 조회
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
    ResponseEntity<InfoDTO> getUserById(@Parameter(description = "ID of the user to retrieve") long userId);


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
    ResponseEntity<?> login(LoginRequest loginRequest);

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
    User createUser(@Parameter(description = "User details to create", required = true) UserRequest.SignDTO user);

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
    ResponseEntity<User> updateUser(
            @Parameter(description = "ID of the user to update") long userId,
            @Parameter(description = "Updated user details", required = true) UserRequest.InfoDTO userDetails

    );
}
