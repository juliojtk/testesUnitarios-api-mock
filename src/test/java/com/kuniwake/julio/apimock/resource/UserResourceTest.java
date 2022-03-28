package com.kuniwake.julio.apimock.resource;

import com.kuniwake.julio.apimock.domain.User;
import com.kuniwake.julio.apimock.domain.dto.UserDto;
import com.kuniwake.julio.apimock.services.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@SpringBootTest
class UserResourceTest {

    public static final Integer ID = 1;
    public static final String NAME = "julio";
    public static final String EMAIL = "julio@email.com";
    public static final String PASSWORD = "123";
    public static final int INDEX = 0;

    @InjectMocks
    private UserResource userResource;

    @Mock
    private ModelMapper mapper;

    @Mock
    private UserServiceImpl userService;

    private User user = new User();
    private UserDto userDto = new UserDto();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void when_findById_then_return_success() {
        Mockito.when(userService.findById(Mockito.anyInt())).thenReturn(user);
        Mockito.when(mapper.map(Mockito.any(), Mockito.any())).thenReturn(userDto);

        ResponseEntity<UserDto> response = userResource.findById(ID);

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertEquals(UserDto.class, response.getBody().getClass());

        Assertions.assertEquals(ID, response.getBody().getId());
        Assertions.assertEquals(NAME, response.getBody().getName());
        Assertions.assertEquals(EMAIL, response.getBody().getEmail());
        Assertions.assertEquals(PASSWORD, response.getBody().getPassword());
    }

    @Test
    void when_findAll_then_return_listUserDto() {
        Mockito.when(userService.findAllUser()).thenReturn(List.of(user));
        Mockito.when(mapper.map(Mockito.any(), Mockito.any())).thenReturn(userDto);

        ResponseEntity<List<UserDto>> response = userResource.findAll();

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertEquals(ArrayList.class, response.getBody().getClass()); // Testando se o retorno da chamada vai ser um Array
        Assertions.assertEquals(UserDto.class, response.getBody().get(INDEX).getClass()); // Verificando se a classe que esta no indice 0 é do tipo UserDto

        Assertions.assertEquals(ID, response.getBody().get(INDEX).getId());
        Assertions.assertEquals(NAME, response.getBody().get(INDEX).getName());
        Assertions.assertEquals(EMAIL, response.getBody().get(INDEX).getEmail());
        Assertions.assertEquals(PASSWORD, response.getBody().get(INDEX).getPassword());

    }

    @Test
    void when_createUser_then_return_created_status() {
        Mockito.when(userService.createUser(Mockito.any())).thenReturn(user);

        ResponseEntity<UserDto> response = userResource.createUser(userDto);

        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertNotNull(response.getHeaders().get("Location")); // No Header tem que haver o Location

    }

    @Test
    void when_update_then_return_success() {
        Mockito.when(userService.updateUser(userDto)).thenReturn(user);
        Mockito.when(mapper.map(Mockito.any(), Mockito.any())).thenReturn(userDto);

        ResponseEntity<UserDto> response = userResource.updateUser(ID, userDto);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertEquals(UserDto.class, response.getBody().getClass());

        Assertions.assertEquals(ID, response.getBody().getId());
        Assertions.assertEquals(NAME, response.getBody().getName());
        Assertions.assertEquals(EMAIL, response.getBody().getEmail());
        Assertions.assertEquals(PASSWORD, response.getBody().getPassword());

    }

    @Test
    void when_delete_then_return_success() {
        Mockito.doNothing().when(userService).deleteUser(Mockito.anyInt()); // doNothing() é quando o metodo não possui return

        ResponseEntity<UserDto>  response = userResource.deleteUser(ID);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Mockito.verify(userService, Mockito.times(1)).deleteUser(Mockito.anyInt());

    }

    private void startUser(){ // Para não iniciar os valores da instancia de Usuario
        user = User.builder().id(ID).name(NAME).email(EMAIL).password(PASSWORD).build();
        userDto = UserDto.builder().id(ID).name(NAME).email(EMAIL).password(PASSWORD).build();

        List<User> listUser = Collections.singletonList(user);
        listUser.stream().forEach(e -> System.out.println(e));
    }

}