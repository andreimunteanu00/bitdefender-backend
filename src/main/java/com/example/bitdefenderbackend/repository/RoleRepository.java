package com.example.bitdefenderbackend.repository;

import com.example.bitdefenderbackend.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query(nativeQuery = true, value = "delete from employee_role where role_id =:id")
    void findByIdAndDeleteRoleFromEmployee(@Param("id") Long id);
}
