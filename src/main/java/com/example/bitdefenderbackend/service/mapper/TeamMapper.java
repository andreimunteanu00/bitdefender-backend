package com.example.bitdefenderbackend.service.mapper;

import com.example.bitdefenderbackend.entity.Employee;
import com.example.bitdefenderbackend.entity.Role;
import com.example.bitdefenderbackend.entity.Team;
import com.example.bitdefenderbackend.service.dto.EmployeeDTO;
import com.example.bitdefenderbackend.service.dto.RoleDTO;
import com.example.bitdefenderbackend.service.dto.TeamDTO;
import org.mapstruct.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", uses = {EmployeeMapper.class})
public interface TeamMapper extends EntityMapper<TeamDTO, Team> {

    @Mapping(target = "manager", qualifiedByName = "manager")
    TeamDTO toDto(Team team);

    Team toEntity(TeamDTO teamDTO);

    List<TeamDTO> toDto(List<Team> teamList);

    List<Team> toEntity(List<TeamDTO> teamDTOList);

    @Named("manager")
    default EmployeeDTO manager(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setFirstName(employee.getFirstName());
        employeeDTO.setLastName(employee.getLastName());
        Set<RoleDTO> roleDTOList = new HashSet<>();
        for (Role role : employee.getRoles()) {
            RoleDTO aux = new RoleDTO();
            aux.setName(role.getName());
            aux.setCreatedDate(role.getCreatedDate());
            aux.setId(role.getId());
            aux.setId(role.getId());
            roleDTOList.add(aux);
        }
        employeeDTO.setRoles(roleDTOList);
        return employeeDTO;
    }

    @AfterMapping
    default void ignoreFieldsInEmployees(@MappingTarget Set<EmployeeDTO> employeeDTOSet) {
        employeeDTOSet.forEach(e -> e.setTeam(null));
    }

}
