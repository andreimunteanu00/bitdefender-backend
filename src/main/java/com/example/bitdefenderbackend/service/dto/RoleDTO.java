package com.example.bitdefenderbackend.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleDTO implements Serializable {

    private Long id;
    private String name;
    private ZonedDateTime createdDate;

}
