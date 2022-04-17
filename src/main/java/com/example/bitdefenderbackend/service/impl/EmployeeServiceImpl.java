package com.example.bitdefenderbackend.service.impl;

import com.example.bitdefenderbackend.entity.Employee;
import com.example.bitdefenderbackend.repository.EmployeeRepository;
import com.example.bitdefenderbackend.service.EmployeeService;
import com.example.bitdefenderbackend.service.dto.EmployeeDTO;
import com.example.bitdefenderbackend.service.mapper.EmployeeMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final EmployeeMapper employeeMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public Page<EmployeeDTO> findAll(Pageable pageable) {
        return employeeRepository.findAll(pageable)
                .map(employeeMapper::toDto);
    }

    @Override
    public Optional<EmployeeDTO> findOne(Long id) {
        return employeeRepository.findById(id)
                .map(employeeMapper::toDto);
    }

    @Override
    public EmployeeDTO save(EmployeeDTO employeeDTO) {
        Employee employee = employeeMapper.toEntity(employeeDTO);
        employee = employeeRepository.save(employee);
        return employeeMapper.toDto(employee);
    }

    @Override
    public boolean existsById(Long id) {
        return employeeRepository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        //employeeRepository.findByIdAndSetManagerNull(id);
        //employeeRepository.findByIdAndDeleteRoleFromEmployee(id);
        employeeRepository.myDelete(id);
    }

    @Override
    public List<EmployeeDTO> findAllByNoManager() {
        return employeeMapper.toDto(employeeRepository.findAllByNoManager());
    }

}
