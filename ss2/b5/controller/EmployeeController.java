package com.example.b5.controller;

import com.example.b5.model.Employee;
import com.example.b5.service.EmployeeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public String list(HttpSession session, Model model) {
        if (session.getAttribute("loggedUser") == null)
            return "redirect:/login";

        model.addAttribute("empList", employeeService.getAll());
        model.addAttribute("techTotalSalary", employeeService.calculateTotalSalaryByDept("Kỹ thuật"));
        return "employees/list";
    }

    @GetMapping("/{code}")
    public String detail(@PathVariable String code, HttpSession session, Model model) throws Exception {
        if (session.getAttribute("loggedUser") == null)
            return "redirect:/login";

        Employee emp = employeeService.getByCode(code);
        if (emp == null)
            throw new Exception("Nhân viên [" + code + "] không tồn tại trong hệ thống");

        model.addAttribute("employee", emp);
        return "employees/detail";
    }
}