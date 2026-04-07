package com.example.TH.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StudentCardController {

    @GetMapping("/student-card")
    public String getStudentCard(@RequestParam(value = "msv", required = false) String msv, Model model) {

        if (msv == null || msv.trim().isEmpty()) {
            return "student-card";
        }

        boolean isFound = false;
        if ("SV001".equalsIgnoreCase(msv)) {
            setStudentData(model, "Nguyễn Văn An", "Công nghệ thông tin", 3, 8.5);
            isFound = true;
        } else if ("SV002".equalsIgnoreCase(msv)) {
            setStudentData(model, "Trần Thị Bình", "Kinh tế", 2, 7.2);
            isFound = true;
        } else if ("SV003".equalsIgnoreCase(msv)) {
            setStudentData(model, "Lê Minh Cường", "Công nghệ thông tin", 4, 3.8);
            isFound = true;
        }

        model.addAttribute("msv", msv);

        if (!isFound) {
            model.addAttribute("errorMessage", "Không tìm thấy sinh viên với mã " + msv);
        }

        return "student-card";
    }

    private void setStudentData(Model model, String name, String faculty, int year, double gpa) {
        model.addAttribute("studentName", name);
        model.addAttribute("faculty", faculty);
        model.addAttribute("year", year);
        model.addAttribute("gpa", gpa);
    }
}