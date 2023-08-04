package com.srms.studentresultmanagementsystem.repository;

import com.srms.studentresultmanagementsystem.api.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
