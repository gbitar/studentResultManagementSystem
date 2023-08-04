package com.srms.studentresultmanagementsystem.repository;

import com.srms.studentresultmanagementsystem.api.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}