package com.srms.studentresultmanagementsystem.api.data;

import com.srms.studentresultmanagementsystem.api.model.Course;
import com.srms.studentresultmanagementsystem.api.model.Result;
import com.srms.studentresultmanagementsystem.api.model.Student;
import com.srms.studentresultmanagementsystem.api.service.CourseService;
import com.srms.studentresultmanagementsystem.api.service.ResultService;
import com.srms.studentresultmanagementsystem.api.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final StudentService studentService;
    private final CourseService courseService;
    private final ResultService resultService;

    @Autowired
    public DataLoader(StudentService studentService, CourseService courseService, ResultService resultService) {
        this.studentService = studentService;
        this.courseService = courseService;
        this.resultService = resultService;
    }

    @Override
    public void run(String... args) throws Exception {
        loadInitialData();
    }

    private void loadInitialData() {
        // Populate Students
        Student student1 = new Student("John", "Smith", LocalDate.of(1999, 12, 24), "js@google.com");
        Student student2 = new Student("Lisa", "test", LocalDate.of(2001, 11, 11), "jd@hotmail.com");
        Student student3 = new Student("Georges", "Die", LocalDate.of(2003, 10, 11), "jd@amazon.com");
        Student student4 = new Student("Lama", "Die", LocalDate.of(2002, 12, 11), "jd@amazon.com");
        Student student5 = new Student("Andrew", "Die", LocalDate.of(2004, 11, 11), "jd@amazon.com");
        studentService.addStudent(student1);
        studentService.addStudent(student2);
        studentService.addStudent(student3);
        studentService.addStudent(student4);
        studentService.addStudent(student5);

        // Populate Courses
        Course course1 = new Course("Web Application Scripting");
        Course course2 = new Course("Database Management");
        Course course3 = new Course("Intro to computing");
        Course course4 = new Course("Economics 101");
        Course course5 = new Course("Management 101");
        courseService.addCourse(course1);
        courseService.addCourse(course2);
        courseService.addCourse(course3);
        courseService.addCourse(course4);
        courseService.addCourse(course5);

        // Populate Results
        Result result1 = new Result(course1, student1, "A");
        Result result2 = new Result(course2, student1, "B");
        Result result3 = new Result(course1, student2, "C");
        resultService.addResult(result1);
        resultService.addResult(result2);
        resultService.addResult(result3);
    }
}
