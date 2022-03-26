package com.kuniwake.julio.apimock.resource.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class ManagerStandardError {
    private LocalDate timestamp;
    private Integer status;
    private String error;
    private String path;
}
