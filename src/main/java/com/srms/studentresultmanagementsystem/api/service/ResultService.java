package com.srms.studentresultmanagementsystem.api.service;

import com.srms.studentresultmanagementsystem.api.model.Result;
import com.srms.studentresultmanagementsystem.repository.CourseRepository;
import com.srms.studentresultmanagementsystem.repository.ResultRepository;
import com.srms.studentresultmanagementsystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ResultService {
    private final ResultRepository resultRepository;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public ResultService(ResultRepository resultRepository, CourseRepository courseRepository, StudentRepository studentRepository) {
        this.resultRepository = resultRepository;
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    public Result addResult(Result result) {
        // Check if the selected course and student exist before adding the result
        Long courseId = result.getCourse().getId();
        Long studentId = result.getStudent().getId();

        if (!courseRepository.existsById(courseId)) {
            throw new NoSuchElementException("Course not found");
        }

        if (!studentRepository.existsById(studentId)) {
            throw new NoSuchElementException("Student not found");
        }

        return resultRepository.save(result);
    }

    public List<Result> getAllResults() {
        return resultRepository.findAll();
    }

    public void deleteResult(Long id) {
        // Check if the result exists before deleting
        if (!resultRepository.existsById(id)) {
            throw new NoSuchElementException("Result not found");
        }

        resultRepository.deleteById(id);
    }
}
