package com.kuniwake.julio.apimock.resource;

import com.kuniwake.julio.apimock.domain.User;
import com.kuniwake.julio.apimock.domain.dto.UserDto;
import com.kuniwake.julio.apimock.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/user")
public class UserResource {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable Integer id){
        return ResponseEntity.ok().body(mapper.map(userService.findById(id), UserDto.class));// mapper.map(), faz com que as informações do User seja informada no UserDto
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> findAll(){
        List<UserDto> lUserDto = userService.findAllUser()
                .stream().map(user -> mapper.map(user, UserDto.class)) // Passa as informações de User para UserDto
                .collect(Collectors.toList());
        return ResponseEntity.ok(lUserDto);
    }
}
