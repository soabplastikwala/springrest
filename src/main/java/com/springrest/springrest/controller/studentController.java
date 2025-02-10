package com.springrest.springrest.controller;

import com.springrest.springrest.entity.Student;
import com.springrest.springrest.repo.studentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class studentController {

    // Logger instance
    private static final Logger logger = LoggerFactory.getLogger(studentController.class);

    @Autowired
    private studentRepo repo;

    // Get all students
    @GetMapping
    public List<Student> getStudents() {
        return repo.findAll();
    }

    // Get student by ID
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable int id) {
        Optional<Student> student = repo.findById(id);
        return student.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    // Create or update a student
    @PostMapping("/create")
    @Transactional
    public ResponseEntity<?> createStudent(@RequestBody Student student) {
        try {
            if (student.getId() == 0) {
                // If ID is not set, treat it as a new entity
                Student savedStudent = repo.save(student);
                return ResponseEntity.ok(savedStudent);
            } else {
                // If ID is set, check if the entity exists
                Optional<Student> existingStudent = repo.findById(student.getId());
                if (existingStudent.isPresent()) {
                    // Update existing entity
                    Student updatedStudent = existingStudent.get();
                    updatedStudent.setName(student.getName());
                    updatedStudent.setSubject(student.getSubject());
                    updatedStudent.setGrade(student.getGrade());
                    updatedStudent.setCity(student.getCity());
                    repo.save(updatedStudent);
                    return ResponseEntity.ok(updatedStudent);
                } else {
                    // Insert new entity
                    Student savedStudent = repo.save(student);
                    return ResponseEntity.ok(savedStudent);
                }
            }
        } catch (ObjectOptimisticLockingFailureException e) {
            // Handle concurrent updates
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("The student has been modified or deleted by another transaction.");
        } catch (Exception e) {
            // Log the exception and propagate it
            logger.error("Error occurred while creating/updating student: ", e);
            throw e; // Propagate the exception to trigger a rollback
        }
    }

    // Update a student by ID
    @PutMapping("/update/{id}")
    @Transactional
    public ResponseEntity<?> updateStudent(@PathVariable int id, @RequestBody Student student) {
        try {
            Optional<Student> existingStudent = repo.findById(id);
            if (existingStudent.isPresent()) {
                // Update the existing student
                Student updatedStudent = existingStudent.get();
                updatedStudent.setName(student.getName());
                updatedStudent.setSubject(student.getSubject());
                updatedStudent.setGrade(student.getGrade());
                updatedStudent.setCity(student.getCity());
                repo.save(updatedStudent);
                return ResponseEntity.ok(updatedStudent);
            } else {
                // Student not found
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Student with ID " + id + " not found.");
            }
        } catch (ObjectOptimisticLockingFailureException e) {
            // Handle concurrent updates
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("The student has been modified or deleted by another transaction.");
        } catch (Exception e) {
            // Log the exception and propagate it
            logger.error("Error occurred while updating student: ", e);
            throw e; // Propagate the exception to trigger a rollback
        }
    }

    // Delete a student by ID
    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<?> deleteStudent(@PathVariable int id) {
        try {
            Optional<Student> existingStudent = repo.findById(id);
            if (existingStudent.isPresent()) {
                // Delete the student
                repo.deleteById(id);
                return ResponseEntity.ok("Student with ID " + id + " deleted successfully.");
            } else {
                // Student not found
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Student with ID " + id + " not found.");
            }
        } catch (Exception e) {
            // Log the exception and propagate it
            logger.error("Error occurred while deleting student: ", e);
            throw e; // Propagate the exception to trigger a rollback
        }
    }
}