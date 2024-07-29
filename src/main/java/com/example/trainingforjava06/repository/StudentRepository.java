package com.example.trainingforjava06.repository;

import com.example.trainingforjava06.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
