package com.sms.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sms.app.entity.Student;
import com.sms.app.service.StudentService;

@RestController
@RequestMapping("/sms")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@GetMapping("/students")
	@PreAuthorize("hasRole('ADMIN') or hasRole('FACULTY') or hasRole('STUDENT')")
	public List<Student> findAll() {
		return studentService.findAll();
	}

	@GetMapping("/students/{studentId}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('FACULTY')")
	public Student getStudent(@PathVariable int studentId) {

		Student student = studentService.findById(studentId);

		return student;
	}

	@PostMapping("/students")
	@PreAuthorize("hasRole('ADMIN') or hasRole('FACULTY')")
	public Student addStudent(@Valid @RequestBody Student student) {

		student.setStudentId(0);

		studentService.save(student);

		return student;
	}

	@PutMapping("/students")
	@PreAuthorize("hasRole('ADMIN') or hasRole('FACULTY')")
	public Student updateStudent(@RequestBody Student student) {

		studentService.save(student);

		return student;
	}

	@DeleteMapping("/students/{studentId}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('FACULTY')")
	public String deleteStudent(@PathVariable int studentId) {

		Student tempStudent = studentService.findById(studentId);

		if (tempStudent == null) {
			throw new RuntimeException("Student id not found - " + studentId);
		}

		studentService.deleteById(studentId);

		return "Deleted student id - " + studentId;
	}
}
