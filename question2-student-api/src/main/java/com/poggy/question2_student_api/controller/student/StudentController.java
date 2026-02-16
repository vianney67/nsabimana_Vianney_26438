package com.poggy.question2_student_api.controller.student;

import com.poggy.question2_student_api.model.student.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private List<Student> students = new ArrayList<>();

    public StudentController() {
        students.add(new Student(1L, "Alice", "Johnson", "alice@example.com", "Computer Science", 3.8));
        students.add(new Student(2L, "Bob", "Smith", "bob@example.com", "Information Technology", 3.2));
        students.add(new Student(3L, "Carol", "Davis", "carol@example.com", "Computer Science", 3.6));
        students.add(new Student(4L, "David", "Wilson", "david@example.com", "Business Administration", 2.9));
        students.add(new Student(5L, "Eve", "Brown", "eve@example.com", "Mathematics", 3.9));
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long studentId) {
        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                return ResponseEntity.ok(student);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/major/{major}")
    public ResponseEntity<List<Student>> getStudentsByMajor(@PathVariable String major) {
        List<Student> result = new ArrayList<>();
        for (Student student : students) {
            if (student.getMajor() != null && student.getMajor().equalsIgnoreCase(major)) {
                result.add(student);
            }
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Student>> filterStudentsByGpa(@RequestParam("gpa") Double minGpa) {
        List<Student> result = new ArrayList<>();
        for (Student student : students) {
            if (student.getGpa() != null && student.getGpa() >= minGpa) {
                result.add(student);
            }
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<Student> registerStudent(@RequestBody Student student) {
        students.add(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long studentId, @RequestBody Student updatedStudent) {
        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                student.setFirstName(updatedStudent.getFirstName());
                student.setLastName(updatedStudent.getLastName());
                student.setEmail(updatedStudent.getEmail());
                student.setMajor(updatedStudent.getMajor());
                student.setGpa(updatedStudent.getGpa());
                return ResponseEntity.ok(student);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
