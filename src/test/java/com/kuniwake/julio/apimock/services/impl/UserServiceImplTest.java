package com.kuniwake.julio.apimock.services.impl;

import com.kuniwake.julio.apimock.domain.User;
import com.kuniwake.julio.apimock.domain.dto.UserDto;
import com.kuniwake.julio.apimock.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class UserServiceImplTest {


    public static final Integer ID = 1;
    public static final String NAME = "julio";
    public static final String EMAIL = "julio@email.com";
    public static final String PASSWORD = "123";

    @InjectMocks // Para criar uma instancia real de UserServiceImpl
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper mapper;

    private User user;
    private UserDto userDto;
    private Optional<User> optionalUser;

    @BeforeEach // Significa para antes de tudo realizar os trechos de codigo que estão dentro desse metodo
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void When_findById_Then_Return_UserInstance() {
        Mockito.when(userRepository.findById(Mockito.anyInt())).thenReturn(optionalUser);

        User response = userService.findById(ID);

        assertEquals(User.class, response.getClass());
        assertNotNull(response, "Deu certo");
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
    }

    @Test
    void findAllUser() {
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
        optionalUser = Optional.of(User.builder().id(ID).name(NAME).email(EMAIL).password(PASSWORD).build());

        List<User> listUser = Collections.singletonList(user);
        listUser.stream().forEach(e -> System.out.println(e));
    }
}