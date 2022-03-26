package com.kuniwake.julio.apimock.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean // Sempre que subir o projeto essa configuração vai ser injetada
    public ModelMapper mapper(){
        return new ModelMapper();
    }

}
