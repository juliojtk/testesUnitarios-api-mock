package com.kuniwake.julio.apimock.resource;

import com.kuniwake.julio.apimock.domain.User;
import com.kuniwake.julio.apimock.domain.dto.UserDto;
import com.kuniwake.julio.apimock.services.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;


@SpringBootTest
class UserResourceTest {

    public static final Integer ID = 1;
    public static final String NAME = "julio";
    public static final String EMAIL = "julio@email.com";
    public static final String PASSWORD = "123";

    @InjectMocks
    private UserResource userResource;

    @Mock
    private ModelMapper mapper;

    @Mock
    private UserServiceImpl userService;

    private User user;
    private UserDto userDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void findById() {
    }

    @Test
    void findAll() {
    }

    @Test
    void createUser() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {
    }

    private void startUser(){ // Para não iniciar os valores da instancia de Usuario
        user = User.builder().id(ID).name(NAME).email(EMAIL).password(PASSWORD).build();
        userDto = UserDto.builder().id(ID).name(NAME).email(EMAIL).password(PASSWORD).build();

        List<User> listUser = Collections.singletonList(user);
        listUser.stream().forEach(e -> System.out.println(e));
    }

}