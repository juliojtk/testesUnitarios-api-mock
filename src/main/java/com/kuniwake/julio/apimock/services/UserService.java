package com.kuniwake.julio.apimock.services;

import com.kuniwake.julio.apimock.domain.User;
import com.kuniwake.julio.apimock.domain.dto.UserDto;

import java.util.List;

public interface UserService {
    User findById(Integer id);

    List<User> findAllUser();
}
