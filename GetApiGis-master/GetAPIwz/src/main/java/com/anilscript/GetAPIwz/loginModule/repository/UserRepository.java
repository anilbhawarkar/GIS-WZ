package com.anilscript.GetAPIwz.loginModule.repository;


import com.anilscript.GetAPIwz.loginModule.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);


    List<User> findByLocationCode(String division);


    // Native query with parameter
//    @Query(value = "SELECT * FROM employee WHERE department = :dept", nativeQuery = true)
//    List<Employee> findByDepartment(@Param("dept") String department);
}
