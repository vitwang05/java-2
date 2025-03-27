package com.java2.food.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;


import jakarta.validation.constraints.Size;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class UserCreationRequest {

    @Size(min = 4, message = "USERNAME_INVALID")
    String username;

    String email;
    @Size(min = 4, message = "INVALID_PASSWORD")
    String password;


}
