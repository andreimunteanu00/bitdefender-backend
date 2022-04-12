package com.example.bitdefenderbackend.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Set;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TeamDTO implements Serializable {

    private Long id;
    private String name;
    private ZonedDateTime createdDate;
    private Set<EmployeeDTO> employees;
    private EmployeeDTO manager;

}
