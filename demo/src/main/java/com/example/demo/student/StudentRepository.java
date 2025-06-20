package com.example.demo.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
//after @Repository this interface is responsible for data accessing or data accessing layer
public interface StudentRepository extends JpaRepository <Student, Long>{

    @Query("SELECT s FROM Student s WHERE s.email = ?1")
    Optional<Student> findStudentByEmail(String email);
    //this function is use for checking email is available or not
}
