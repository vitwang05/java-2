package com.java2.food.service;

import com.java2.food.dto.request.UserCreationRequest;
import com.java2.food.dto.response.UserReponse;
import com.java2.food.entity.Users;
import com.java2.food.exception.AppException;
import com.java2.food.exception.ErrorCode;
import com.java2.food.mapper.UserMapper;
import com.java2.food.repository.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;


    public UserReponse createUser(UserCreationRequest request){
        Users user = userMapper.toUser(request);
        try{
            user = userRepository.save(user);
        }catch (DataIntegrityViolationException exception){
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        return userMapper.toUserReponse(user);
    }

    public List<UserReponse> getUser(){
        return userRepository.findAll().stream().map(userMapper::toUserReponse).toList();
    }
}
