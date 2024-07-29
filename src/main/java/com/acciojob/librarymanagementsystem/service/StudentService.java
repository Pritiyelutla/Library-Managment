package com.acciojob.librarymanagementsystem.service;

import com.acciojob.librarymanagementsystem.entities.Student;
import com.acciojob.librarymanagementsystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired

    private StudentRepository studentRepository;

    public String addStudent(Student student)
    {

        studentRepository.save(student);

        return "student has been saved to the DB";

    }

    public List<Student> findStudents(String branch, double cgpa)
    {

        List<Student> students = studentRepository.findStudentByBranchAndCgpaGreaterThan(branch, cgpa);
        return students;

    }
}
