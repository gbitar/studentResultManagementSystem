package com.srms.studentresultmanagementsystem.api.service;

import com.srms.studentresultmanagementsystem.api.model.Course;
import com.srms.studentresultmanagementsystem.api.model.Result;
import com.srms.studentresultmanagementsystem.repository.CourseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Course not found"));
    }

    public void deleteCourse(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course with ID " + courseId + " not found."));

        // Disassociate course from results
        for (Result result : course.getResults()) {
            result.setCourse(null);
            result.setStudent(null);
        }

        courseRepository.delete(course);
    }
}
