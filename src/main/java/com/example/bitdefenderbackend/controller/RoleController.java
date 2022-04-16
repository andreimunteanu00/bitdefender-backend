package com.example.bitdefenderbackend.controller;

import com.example.bitdefenderbackend.service.RoleService;
import com.example.bitdefenderbackend.service.dto.RoleDTO;
import com.example.bitdefenderbackend.util.Pagination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("api/role")
@CrossOrigin(origins = "http://localhost:4200")
public class RoleController {
    
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("")
    public ResponseEntity<List<RoleDTO>> getAllRoles(Pageable pageable) {
        Page<RoleDTO> page = roleService.findAll(pageable);
        HttpHeaders headers = Pagination.generatePaginationHttpHeaders(page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDTO> getRole(@PathVariable(value = "id") Long id) {
        RoleDTO result = roleService.findOne(id).orElse(null);
        if (result == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("")
    public ResponseEntity<RoleDTO> createRole(@RequestBody RoleDTO roleDTO) {
        RoleDTO result = roleService.save(roleDTO);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleDTO> updateRole(
            @PathVariable(value = "id") Long id,
            @RequestBody RoleDTO roleDTO) {
        if (roleDTO.getId() == null || !Objects.equals(id, roleDTO.getId()) || !roleService.existsById(id)) {
            return ResponseEntity.badRequest().body(roleDTO);
        }
        RoleDTO result = roleService.save(roleDTO);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable(value = "id") Long id) {
        roleService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
