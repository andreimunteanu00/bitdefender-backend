package com.example.bitdefenderbackend.service.impl;

import com.example.bitdefenderbackend.entity.Role;
import com.example.bitdefenderbackend.repository.RoleRepository;
import com.example.bitdefenderbackend.service.RoleService;
import com.example.bitdefenderbackend.service.dto.RoleDTO;
import com.example.bitdefenderbackend.service.mapper.RoleMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    private final RoleMapper roleMapper;

    public RoleServiceImpl(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public Page<RoleDTO> findAll(Pageable pageable) {
        return roleRepository.findAll(pageable)
                .map(roleMapper::toDto);
    }

    @Override
    public Optional<RoleDTO> findOne(Long id) {
        return roleRepository.findById(id)
                .map(roleMapper::toDto);
    }

    @Override
    public RoleDTO save(RoleDTO roleDTO) {
        Role role = roleMapper.toEntity(roleDTO);
        role = roleRepository.save(role);
        return roleMapper.toDto(role);
    }

    @Override
    public boolean existsById(Long id) {
        return roleRepository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        roleRepository.deleteById(id);
    }
}
