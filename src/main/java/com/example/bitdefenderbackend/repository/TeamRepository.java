package com.example.bitdefenderbackend.repository;

import com.example.bitdefenderbackend.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
