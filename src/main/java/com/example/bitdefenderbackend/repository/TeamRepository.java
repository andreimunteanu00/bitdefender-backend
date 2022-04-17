package com.example.bitdefenderbackend.repository;

import com.example.bitdefenderbackend.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TeamRepository extends JpaRepository<Team, Long> {

    @Query(nativeQuery = true, value = "update team set manager_id = null where id =:id")
    void setManagerIdNull(@Param("id") Long id);
}
