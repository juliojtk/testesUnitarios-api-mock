package com.kuniwake.julio.apimock.resource;

import com.kuniwake.julio.apimock.domain.User;
import com.kuniwake.julio.apimock.domain.dto.UserDto;
import com.kuniwake.julio.apimock.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/user")
public class UserResource {

    public static final String ID = "/{id}";
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserService userService;

    @GetMapping(value = ID)
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

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        User user = userService.createUser(userDto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path(ID)
                .buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = ID)
    public ResponseEntity<UserDto> updateUser(@PathVariable Integer id, @RequestBody UserDto userDto){
        userDto.setId(id);
        User newUser = userService.updateUser(userDto);
        return ResponseEntity.ok().body(mapper.map(newUser, UserDto.class)); // Passa as informações de User para UserDto
    }

    @DeleteMapping(value = ID)
    public ResponseEntity<UserDto> deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
