package com.example.bitdefenderbackend.service;

import com.example.bitdefenderbackend.service.dto.RoleDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface RoleService {
    Page<RoleDTO> findAll(Pageable pageable);

    Optional<RoleDTO> findOne(Long id);

    RoleDTO save(RoleDTO roleDTO);

    boolean existsById(Long id);

    void deleteById(Long id);

}
