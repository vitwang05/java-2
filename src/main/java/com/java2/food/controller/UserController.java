package com.java2.food.controller;


import java.util.List;
import java.util.Map;

import com.java2.food.dto.request.ApiResponse;
import com.java2.food.dto.request.UserCreationRequest;
import com.java2.food.dto.response.UserReponse;
import com.java2.food.service.UserService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@CrossOrigin(origins = "*")
public class UserController {
    UserService userService;

    @PostMapping
    ApiResponse<UserReponse> createUser(@RequestBody @Valid UserCreationRequest request) {
        return ApiResponse.<UserReponse>builder()
                .result(userService.createUser(request))
                .build();

    }

    @GetMapping
    ApiResponse<List<UserReponse>> getUsers(){
        return ApiResponse.<List<UserReponse>>builder()
                .result(userService.getUser())
                .build();
    }

    @PostMapping("/login")
    ApiResponse<UserReponse> login(@RequestBody Map<String, Object> body){
        return ApiResponse.<UserReponse>builder()
                .result(userService.login(body))
                .build();
    }

}
