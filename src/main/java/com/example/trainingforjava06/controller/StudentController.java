package com.example.trainingforjava06.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
@RequestMapping("/student")
public class StudentController {
    @GetMapping("/showList")
    public String showList(Model model){
        model.addAttribute("time", new Date());
        return "index";
    }
}
