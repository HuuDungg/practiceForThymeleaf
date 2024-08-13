package com.example.trainingforjava06.controller;

import com.example.trainingforjava06.entity.Student;
import com.example.trainingforjava06.entity.Subject;
import com.example.trainingforjava06.repository.StudentRepository;
import com.example.trainingforjava06.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

//@Controller
//@RequestMapping("/student")
public class StudentController {
//    @Autowired
//    StudentRepository studentRepository;
//    @Autowired
//    SubjectRepository subjectRepository;
//
//    @GetMapping("/showList")
//    public String showList(Model model){
//        model.addAttribute("listStudent", studentRepository.findAll());
//        model.addAttribute("Student", new Student());
//        return "index";
//    }
//
//    @PostMapping("/create")
//    public String createStudent(@ModelAttribute("Student") Student student){
//        studentRepository.save(student);
//        return "redirect:/student/showList";
//    }
//
//    @GetMapping("/delete/{id}")
//    public String deleteStudent(@PathVariable("id")int id){
//        studentRepository.deleteById(id);
//        return "redirect:/student/showList";
//    }
//
//    @GetMapping("/detail")
//    public String showDetail(Model model, @RequestParam("id") int id){
//        model.addAttribute("Student", studentRepository.findById(id));
//        return "detail";
//    }
//
//    @PostMapping("/update")
//    public String updateStudent(@ModelAttribute("Student") Student student){
//        studentRepository.save(student);
//        return "redirect:/student/showList";
//    }
//
//    @GetMapping("/data")
//    @ResponseBody
//    public String getData(@RequestParam String query) {
//        // Xử lý dữ liệu và trả về kết quả
//        return "Kết quả cho: " + query;
//    }
//
//    @ModelAttribute("listSubject")
//    public List<Subject> listSubject(){
//        return subjectRepository.findAll();
//    }

}
