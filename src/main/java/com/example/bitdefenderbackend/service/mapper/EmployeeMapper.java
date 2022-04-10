package com.example.bitdefenderbackend.service.mapper;

import com.example.bitdefenderbackend.entity.Employee;
import com.example.bitdefenderbackend.entity.Role;
import com.example.bitdefenderbackend.service.dto.EmployeeDTO;
import com.example.bitdefenderbackend.service.dto.RoleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface EmployeeMapper extends EntityMapper<EmployeeDTO, Employee> {

    @Mapping(target = "team.manager", qualifiedByName = "manager")
    @Mapping(target = "team.employees", ignore = true)
    EmployeeDTO toDto(Employee employee);

    Employee toEntity(EmployeeDTO employeeDTO);

    List<EmployeeDTO> toDto(List<Employee> employeeList);

    List<Employee> toEntity(List<EmployeeDTO> employeeDTOList);

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

}
