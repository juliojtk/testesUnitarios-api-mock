package com.kuniwake.julio.apimock.config;

import com.kuniwake.julio.apimock.domain.User;
import com.kuniwake.julio.apimock.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("local")
public class localConfig {

    @Autowired
    private UserRepository userRepository;

    @Bean //Necessario para que o suba essas configurações sempre que este perfil estiver ativo
    public void startDb(){
        User u1 = new User(null, "julio", "julio@email.com", "123");
        User u2 = new User(null, "Carol", "carol@email.com", "123");

        userRepository.saveAll(List.of(u1, u2));
    }
}
