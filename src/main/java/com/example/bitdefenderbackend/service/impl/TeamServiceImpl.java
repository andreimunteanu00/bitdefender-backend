package com.example.bitdefenderbackend.service.impl;

import com.example.bitdefenderbackend.entity.Employee;
import com.example.bitdefenderbackend.entity.Team;
import com.example.bitdefenderbackend.repository.EmployeeRepository;
import com.example.bitdefenderbackend.repository.TeamRepository;
import com.example.bitdefenderbackend.service.TeamService;
import com.example.bitdefenderbackend.service.dto.TeamDTO;
import com.example.bitdefenderbackend.service.mapper.EmployeeMapper;
import com.example.bitdefenderbackend.service.mapper.TeamMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;

    private final EmployeeRepository employeeRepository;

    private final TeamMapper teamMapper;

    public TeamServiceImpl(TeamRepository teamRepository, EmployeeRepository employeeRepository, TeamMapper teamMapper) {
        this.teamRepository = teamRepository;
        this.employeeRepository = employeeRepository;
        this.teamMapper = teamMapper;
    }

    @Override
    public Page<TeamDTO> findAll(Pageable pageable) {
        return teamRepository.findAll(pageable)
                .map(teamMapper::toDto);
    }

    @Override
    public Optional<TeamDTO> findOne(Long id) {
        return teamRepository.findById(id)
                .map(teamMapper::toDto);
    }

    @Override
    public TeamDTO save(TeamDTO teamDTO) {
        Team team = teamMapper.toEntity(teamDTO);
        team.setEmployees(employeeRepository.findAllByTeamId(team.getId()));
        team = teamRepository.save(team);
        return teamMapper.toDto(team);
    }

    @Override
    public boolean existsById(Long id) {
        return teamRepository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        teamRepository.setManagerIdNull(id);
        employeeRepository.findAllByTeamIdAndSetTeamIdNull(id);
        teamRepository.deleteById(id);
    }
}
