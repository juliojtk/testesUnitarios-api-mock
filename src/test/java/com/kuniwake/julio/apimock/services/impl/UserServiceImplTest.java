package com.kuniwake.julio.apimock.services.impl;

import com.kuniwake.julio.apimock.domain.User;
import com.kuniwake.julio.apimock.domain.dto.UserDto;
import com.kuniwake.julio.apimock.repositories.UserRepository;
import com.kuniwake.julio.apimock.services.exceptions.MyDataIntegratyViolationException;
import com.kuniwake.julio.apimock.services.exceptions.MyObjectNotFoundException;
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
    public static final String OBJETO_NAO_ENCONTRADO = "Objeto não encontrado";

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
    void when_findById_then_return_userInstance() {
        Mockito.when(userRepository.findById(Mockito.anyInt())).thenReturn(optionalUser);

        User response = userService.findById(ID);

        assertEquals(User.class, response.getClass());
        assertNotNull(response, "Deu certo");
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
    }

    @Test
    void when_findById_then_return_NotFoundException(){ // Quando buscar por Id, retornar NotFoundException
        Mockito.when(userRepository.findById(Mockito.anyInt()))
                .thenThrow(new MyObjectNotFoundException(OBJETO_NAO_ENCONTRADO));
        try {
            userService.findById(ID);
        } catch (Exception ex){
            Assertions.assertEquals(MyObjectNotFoundException.class, ex.getClass());
            Assertions.assertEquals(OBJETO_NAO_ENCONTRADO, ex.getMessage());
        }
    }

    @Test
    void when_find_all_return_ListOfUser() {
        Mockito.when(userRepository.findAll()).thenReturn(List.of(user));

        List<User> response = userService.findAllUser();

        Assertions.assertNotNull(response);
        Assertions.assertEquals(1, response.size());// Asegurar que vai vim apenas uma lista
        Assertions.assertEquals(User.class, response.get(0).getClass());// Assegurar que o objeto do index 0 dentro dessa lista tenha a classe do tipo User
        Assertions.assertEquals(ID, response.get(0).getId());//Testando se o ID da da classe de index 0 é mesmo passado
        Assertions.assertEquals(NAME, response.get(0).getName());
        Assertions.assertEquals(EMAIL, response.get(0).getEmail());
        Assertions.assertEquals(PASSWORD, response.get(0).getPassword());
    }

    @Test
    void when_create_then_return_success() {
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);

        User respose = userService.createUser(userDto);

        Assertions.assertNotNull(respose);
        Assertions.assertEquals(User.class, respose.getClass());
        Assertions.assertEquals(ID, respose.getId());
        Assertions.assertEquals(NAME, respose.getName());
        Assertions.assertEquals(EMAIL, respose.getEmail());
    }

    @Test
    void when_create_then_return_data_integrity_violation_exception() {
        Mockito.when(userRepository.findByEmail(Mockito.anyString())).thenReturn(optionalUser);

        try {
            optionalUser.get().setId(2); // Trocando o Id para verificar se esse usuario é diferente, caso seja diferente é Create senão é update
            userService.createUser(userDto);
        }catch (Exception ex){
            Assertions.assertEquals(MyDataIntegratyViolationException.class, ex.getClass());
            Assertions.assertEquals("Email Já Cadastrado no Sistema", ex.getMessage());
        }

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