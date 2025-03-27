package com.java2.food.mapper;

import com.java2.food.dto.request.UserCreationRequest;
import com.java2.food.dto.response.UserReponse;
import com.java2.food.entity.Users;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface UserMapper {
    Users toUser(UserCreationRequest request);

    UserReponse toUserReponse(Users user);
}
