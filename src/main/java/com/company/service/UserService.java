package com.company.service;

import com.company.controller.dto.UserDto;
import com.company.jpa.entity.UserEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserEntity register(UserDto userDto) throws Exception;
}
