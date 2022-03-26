package com.kuniwake.julio.apimock.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class UserDto {

    private Integer id;
    private String name;
    private String email;

    @JsonIgnore // Sera ignorado essa informação para visualização no Client
    private String password;

}
