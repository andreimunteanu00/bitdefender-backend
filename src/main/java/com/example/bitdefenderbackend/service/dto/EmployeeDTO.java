package com.example.bitdefenderbackend.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Set;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeDTO implements Serializable {

    private Long id;
    private String lastName;
    private String firstName;
    private ZonedDateTime birthDate;
    private Set<RoleDTO> roles;
    private TeamDTO team;

}
