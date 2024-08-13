package com.example.trainingforjava06.controller;

import com.example.trainingforjava06.entity.Subject;
import com.example.trainingforjava06.repository.SubjectRepository;
import org.hibernate.mapping.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subject")
public class SubjectController {
    @Autowired
    SubjectRepository subjectRepository;
    @GetMapping("/getAll")
    public ResponseEntity<Page<Subject>> showList(
            @RequestParam(name = "page", defaultValue = "1")int page,
            @RequestParam(name = "size", defaultValue = "10")int size
    ){
        Pageable pageable = PageRequest.of(page-1, size);
        Page<Subject> listSubject = subjectRepository.findAll(pageable);
        return ResponseEntity.ok(listSubject);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneById(@PathVariable("id")int id){
        try {
            Subject subject = subjectRepository.findById(id).get();
            return ResponseEntity.ok(subject);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PostMapping("/")
    public ResponseEntity<?> createNewSubject(@RequestBody Subject subject){
        return ResponseEntity.ok().body(subjectRepository.save(subject));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOneById(@PathVariable("id")int id){
        try {
            subjectRepository.deleteById(id);
            return ResponseEntity.ok("deleete succesfuly");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping("/")
    public ResponseEntity<?> postSubject(@RequestBody Subject subject){
        return ResponseEntity.ok().body(subjectRepository.save(subject));
    }
}

