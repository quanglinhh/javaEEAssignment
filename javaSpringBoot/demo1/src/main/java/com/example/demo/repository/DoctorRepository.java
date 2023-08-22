package com.example.demo.repository;

import com.example.demo.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Integer> {

    @Query(value = "FROM Doctor d WHERE d.user.id = :userId")
    Doctor getDoctorByUserId(@Param("userId") Integer userId);
}
