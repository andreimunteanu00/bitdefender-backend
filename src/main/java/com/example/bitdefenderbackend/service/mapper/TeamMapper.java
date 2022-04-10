package com.example.bitdefenderbackend.service.mapper;

import com.example.bitdefenderbackend.entity.Team;
import com.example.bitdefenderbackend.service.dto.TeamDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {EmployeeMapper.class})
public interface TeamMapper extends EntityMapper<TeamDTO, Team> {

    @Mapping(target = "employees", ignore = true)
    @Mapping(target = "manager", ignore = true)
    TeamDTO toDto(Team team);

    Team toEntity(TeamDTO teamDTO);

    List<TeamDTO> toDto(List<Team> teamList);

    List<Team> toEntity(List<TeamDTO> teamDTOList);

}
