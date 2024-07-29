package com.example.trainingforjava06.repository;

import com.example.trainingforjava06.entity.Student;
import com.example.trainingforjava06.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
}
