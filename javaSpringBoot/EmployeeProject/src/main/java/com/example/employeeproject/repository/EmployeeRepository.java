package com.example.employeeproject.repository;

import com.example.employeeproject.model.Employee;
import com.example.employeeproject.projection.EmployeeProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    @Query(value = "SELECT employee_name, email FROM Employee",nativeQuery = true)
    List<EmployeeProjection> findAllEmployeeProjection();

    @Query(value = "FROM Employee WHERE employeeName = :employeeName AND (email =:email OR :email IS NULL) AND (age = :age OR :age IS NULL)")
    List<Employee> getEmployeeByNameEmailAndAge(@Param("employeeName") String employeeName, @Param("email") String email, @Param("age") Optional<Integer> age);
}
