package com.example.bitdefenderbackend.service;

import com.example.bitdefenderbackend.service.dto.EmployeeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface EmployeeService {

    Page<EmployeeDTO> findAll(Pageable pageable);

    Optional<EmployeeDTO> findOne(Long id);

    EmployeeDTO save(EmployeeDTO employeeDTO);

    boolean existsById(Long id);

    void deleteById(Long id);

}
