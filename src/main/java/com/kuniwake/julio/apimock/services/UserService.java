package com.kuniwake.julio.apimock.services;

import com.kuniwake.julio.apimock.domain.User;
import com.kuniwake.julio.apimock.domain.dto.UserDto;

import java.util.List;

public interface UserService { // Classe que Faz 'Contratos'
    User findById(Integer id);

    List<User> findAllUser();

    User createUser(UserDto userDto);

    User updateUser(UserDto userDto);

    void deleteUser(Integer id);
}
