package com.example.bitdefenderbackend.service;

import com.example.bitdefenderbackend.service.dto.TeamDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface TeamService {
    Page<TeamDTO> findAll(Pageable pageable);

    Optional<TeamDTO> findOne(Long id);

    TeamDTO save(TeamDTO teamDTO);

    boolean existsById(Long id);

    void deleteById(Long id);
}
