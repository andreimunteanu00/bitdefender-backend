package com.example.bitdefenderbackend.service.mapper;

import com.example.bitdefenderbackend.entity.Role;
import com.example.bitdefenderbackend.service.dto.RoleDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper extends EntityMapper<RoleDTO, Role> {

    RoleDTO toDto(Role role);

    Role toEntity(RoleDTO roleDTO);

    List<RoleDTO> toDto(List<Role> roleList);

    List<Role> toEntity(List<RoleDTO> roleDTOList);

}
