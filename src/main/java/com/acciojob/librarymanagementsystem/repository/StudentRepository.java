package com.acciojob.librarymanagementsystem.repository;

import com.acciojob.librarymanagementsystem.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {

    //List<Student> findAllByBranch(String branch);

    //Student findStudentByEmailId(String emailId);

    List<Student> findStudentByBranchAndCgpaGreaterThan(String branch, double cgpa);


}
