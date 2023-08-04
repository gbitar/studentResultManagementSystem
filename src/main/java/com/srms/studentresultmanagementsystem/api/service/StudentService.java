package com.srms.studentresultmanagementsystem.api.service;

import com.srms.studentresultmanagementsystem.api.model.Result;
import com.srms.studentresultmanagementsystem.api.model.Student;
import com.srms.studentresultmanagementsystem.repository.ResultRepository;
import com.srms.studentresultmanagementsystem.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final ResultRepository resultRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, ResultRepository resultRepository) {
        this.studentRepository = studentRepository;
        this.resultRepository = resultRepository;
    }

    public Student addStudent(Student student) {
        // Validate email format
        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        if (!student.getEmailAddress().matches(emailRegex)) {
            throw new IllegalArgumentException("Invalid email address format");
        }

        // Validate date of birth and age
        LocalDate currentDate = LocalDate.now();
        LocalDate dateOfBirth = student.getDateOfBirth();
        if (dateOfBirth == null || dateOfBirth.isAfter(currentDate)) {
            throw new IllegalArgumentException("Invalid date of birth");
        }

        int age = Period.between(dateOfBirth, currentDate).getYears();
        if (age < 10) {
            throw new IllegalArgumentException("Student must be at least 10 years old");
        }

        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Student not found"));
    }

    public void deleteStudent(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student with ID " + studentId + " not found."));

        // Disassociate student from results
        for (Result result : student.getResults()) {
            result.setStudent(null);
            result.setCourse(null);
        }

        studentRepository.delete(student);
    }


}

