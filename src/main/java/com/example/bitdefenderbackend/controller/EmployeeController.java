package com.example.bitdefenderbackend.controller;

import com.example.bitdefenderbackend.service.EmployeeService;
import com.example.bitdefenderbackend.service.dto.EmployeeDTO;
import com.example.bitdefenderbackend.util.Pagination;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("api/employee")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(Pageable pageable) {
        Page<EmployeeDTO> page = employeeService.findAll(pageable);
        HttpHeaders headers = Pagination.generatePaginationHttpHeaders(page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> createEmployee(@PathVariable(value = "id") Long id) {
        EmployeeDTO result = employeeService.findOne(id).orElse(null);
        if (result == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/free/manager")
    public ResponseEntity<List<EmployeeDTO>> getAllByNoManager() {
        List<EmployeeDTO> result = employeeService.findAllByNoManager();
        return ResponseEntity.ok(result);
    }

    @PostMapping("")
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO result = employeeService.save(employeeDTO);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(
            @PathVariable(value = "id") Long id,
            @RequestBody EmployeeDTO employeeDTO) {
        if (employeeDTO.getId() == null || !Objects.equals(id, employeeDTO.getId()) || !employeeService.existsById(id)) {
            return ResponseEntity.badRequest().body(employeeDTO);
        }
        EmployeeDTO result = employeeService.save(employeeDTO);
        return ResponseEntity.ok(result);
    }

    @Cascade(CascadeType.DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable(value = "id") Long id) {
        employeeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
