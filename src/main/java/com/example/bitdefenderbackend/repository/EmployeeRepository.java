package com.example.bitdefenderbackend.repository;

import com.example.bitdefenderbackend.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(nativeQuery = true, value = "update team set manager_id = null where manager_id =:id")
    void findByIdAndSetManagerNull(@Param("id") Long id);

    @Query(nativeQuery = true, value = "select * " +
            "from employee e " +
            "where e.id not in (select e.id " +
            "                   from employee e " +
            "                            join team t on t.manager_id = e.id)")
    List<Employee> findAllByNoManager();

    @Query(nativeQuery = true, value = "select * from employee e where e.team_id =:id")
    Set<Employee> findAllByTeamId(@Param("id") Long id);
}
