package com.example.Student_Library_Management_System.Repositories;

import com.example.Student_Library_Management_System.Models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer>
{
    // Here <Student--> Data type of Entity, Integer--> Data type of primary Key>
    // Second type of custom JPA Query
    Student findNameByEmail(String email);

    // SELECT * FROM student WHERE country=India;   // It will try to return list of entities
    List<Student> findByCountry(String country);
}
