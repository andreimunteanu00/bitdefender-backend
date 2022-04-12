package com.example.bitdefenderbackend.service.mapper;

import com.example.bitdefenderbackend.entity.Employee;
import com.example.bitdefenderbackend.entity.Team;
import com.example.bitdefenderbackend.service.dto.EmployeeDTO;
import com.example.bitdefenderbackend.service.dto.TeamDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper extends EntityMapper<EmployeeDTO, Employee> {

    @Mapping(target = "team", qualifiedByName = "team")
    EmployeeDTO toDto(Employee employee);

    Employee toEntity(EmployeeDTO employeeDTO);

    List<EmployeeDTO> toDto(List<Employee> employeeList);

    List<Employee> toEntity(List<EmployeeDTO> employeeDTOList);

    @Named("team")
    default TeamDTO team(Team team) {
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setId(team.getId());
        teamDTO.setName(team.getName());
        teamDTO.setCreatedDate(team.getCreatedDate());
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(team.getManager().getId());
        employeeDTO.setFirstName(team.getManager().getFirstName());
        employeeDTO.setLastName(team.getManager().getLastName());
        teamDTO.setManager(employeeDTO);
        return teamDTO;
    }

}
