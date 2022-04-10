package com.example.bitdefenderbackend.controller;

import com.example.bitdefenderbackend.service.TeamService;
import com.example.bitdefenderbackend.service.dto.TeamDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("api/team")
public class TeamController {
    
    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("")
    public ResponseEntity<List<TeamDTO>> getAllTeam(Pageable pageable) {
        Page<TeamDTO> page = teamService.findAll(pageable);
        return ResponseEntity.ok(page.getContent());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamDTO> createTeam(@PathVariable(value = "id") Long id) {
        TeamDTO result = teamService.findOne(id).orElse(null);
        if (result == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("")
    public ResponseEntity<TeamDTO> createTeam(@RequestBody TeamDTO teamDTO) {
        TeamDTO result = teamService.save(teamDTO);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeamDTO> updateTeam(
            @PathVariable(value = "id") Long id,
            @RequestBody TeamDTO teamDTO) {
        if (teamDTO.getId() == null || !Objects.equals(id, teamDTO.getId()) || !teamService.existsById(id)) {
            return ResponseEntity.badRequest().body(teamDTO);
        }
        TeamDTO result = teamService.save(teamDTO);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable(value = "id") Long id) {
        teamService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
