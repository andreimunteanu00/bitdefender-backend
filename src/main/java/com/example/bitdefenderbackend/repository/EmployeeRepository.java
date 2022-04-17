package com.example.bitdefenderbackend.repository;

import com.example.bitdefenderbackend.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(nativeQuery = true, value = "select * " +
            "from employee e " +
            "where e.id not in (select e.id " +
            "                   from employee e " +
            "                            join team t on t.manager_id = e.id)")
    List<Employee> findAllByNoManager();

    @Query(nativeQuery = true, value = "update employee e set e.team_id = null where e.team_id =:id")
    Set<Employee> findAllByTeamIdAndSetTeamIdNull(@Param("id") Long id);

    @Query(nativeQuery = true, value = "select * from employee e where e.team_id =:id")
    Set<Employee> findAllByTeamId(@Param("id") Long id);

    @Query(nativeQuery = true, value = "delete from employee where id =:id")
    void myDelete(@Param("id") Long id);
}
