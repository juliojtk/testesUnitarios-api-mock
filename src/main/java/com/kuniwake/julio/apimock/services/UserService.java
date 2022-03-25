package com.kuniwake.julio.apimock.services;

import com.kuniwake.julio.apimock.domain.User;

public interface UserService {
    User findById(Integer id);
}
