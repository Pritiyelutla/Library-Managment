package com.acciojob.librarymanagementsystem.controller;

import com.acciojob.librarymanagementsystem.entities.Student;
import com.acciojob.librarymanagementsystem.service.StudentService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")

public class StudentController {

    @Autowired

    private StudentService studentService;
    @PostMapping("/add")
    public String addStudent(@RequestBody Student student)
    {
        return studentService.addStudent(student);
    }

    @GetMapping("getTopperStudents")
    public List<Student> getStudents(@RequestParam("branch")String branch,
                                          @RequestParam("cgpa")double cgpa)
    {
        return studentService.findStudents(branch, cgpa);
    }
}
