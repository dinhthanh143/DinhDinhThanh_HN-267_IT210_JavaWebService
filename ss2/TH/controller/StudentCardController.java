package com.example.mvc3_j11.controller;

import com.example.mvc3_j11.model.Student;
import com.example.mvc3_j11.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StudentCardController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/student-card")
    public String showCard(@RequestParam(value = "msv", required = false) String msv, Model model) {
        if (msv != null && !msv.trim().isEmpty()) {
            Student s = studentService.findByMsv(msv);
            if (s != null) {
                model.addAttribute("student", s);
            } else {
                model.addAttribute("errorMessage", "Không tìm thấy sinh viên: " + msv);
            }
        }
        return "student-card";
    }
}