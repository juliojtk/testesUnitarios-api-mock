package com.kuniwake.julio.apimock.services.impl;

import com.kuniwake.julio.apimock.domain.User;
import com.kuniwake.julio.apimock.domain.dto.UserDto;
import com.kuniwake.julio.apimock.repositories.UserRepository;
import com.kuniwake.julio.apimock.services.UserService;
import com.kuniwake.julio.apimock.services.exceptions.CustomObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public User findById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new CustomObjectNotFoundException("Objeto não encontrado"));
    }

    @Override
    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(UserDto userDto) {
        return userRepository.save(mapper.map(userDto, User.class)); //Sempre salva a Entidade User e não o UserDto
    }


}
