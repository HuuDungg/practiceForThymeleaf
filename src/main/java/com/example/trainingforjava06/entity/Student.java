package com.example.trainingforjava06.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String studentCode;

    private String name;

    @ManyToOne
    @JoinColumn(name = "id_subject", referencedColumnName = "id")
    private Subject subject;


}
