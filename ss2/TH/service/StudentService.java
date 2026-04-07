package com.example.mvc3_j11.service;

import com.example.mvc3_j11.model.Student;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class StudentService {
    private List<Student> students = Arrays.asList(
            new Student("SV001", "Nguyễn Văn An", "CNTT", 3, 8.5),
            new Student("SV002", "Trần Thị Bình", "Kinh tế", 2, 7.2),
            new Student("SV003", "Lê Minh Cường", "CNTT", 4, 3.8)
    );

    public Student findByMsv(String msv) {
        return students.stream()
                .filter(s -> s.getMsv().equalsIgnoreCase(msv.trim()))
                .findFirst().orElse(null);
    }
}
