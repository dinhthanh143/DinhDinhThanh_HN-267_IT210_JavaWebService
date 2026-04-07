package com.example.b5.service;

import com.example.b5.model.Employee;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class EmployeeService {
    private List<Employee> employees = Arrays.asList(
            new Employee("NV001", "Nguyễn Thị Lan", "Kế toán", 15000000, new GregorianCalendar(2020, 2, 1).getTime(),
                    "Đang làm"),
            new Employee("NV002", "Trần Văn Hùng", "Kỹ thuật", 25000000, new GregorianCalendar(2019, 6, 15).getTime(),
                    "Đang làm"),
            new Employee("NV003", "Lê Minh Đức", "Kinh doanh", 18500000, new GregorianCalendar(2021, 10, 20).getTime(),
                    "Nghỉ phép"),
            new Employee("NV004", "Phạm Thu Hương", "Kỹ thuật", 22000000, new GregorianCalendar(2022, 0, 5).getTime(),
                    "Đang làm"),
            new Employee("NV005", "Hoàng Văn Nam", "Kế toán", 12000000, new GregorianCalendar(2023, 5, 10).getTime(),
                    "Thử việc"));

    public List<Employee> getAll() {
        return employees;
    }

    public Employee getByCode(String code) {
        return employees.stream().filter(e -> e.getCode().equals(code)).findFirst().orElse(null);
    }

    public double calculateTotalSalaryByDept(String dept) {
        return employees.stream()
                .filter(e -> e.getDepartment().equalsIgnoreCase(dept))
                .mapToDouble(Employee::getSalary).sum();
    }
}