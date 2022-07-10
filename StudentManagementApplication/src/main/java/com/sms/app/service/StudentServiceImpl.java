package com.sms.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sms.app.entity.Student;
import com.sms.app.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService{
	
	@Autowired
	private StudentRepository studentRepository;

	@Override
	public List<Student> findAll() {
		return studentRepository.findAll();
	}

	@Override
	public Student findById(int id) {
		
		Optional<Student> result = studentRepository.findById(id);

		Student student = null;

		if (result.isPresent()) {
			student = result.get();
		} else {
			// we didn't find the student
			throw new RuntimeException("Did not find student id - " + id);
		}

		return student;
	}

	@Override
	public void save(Student student) {
		studentRepository.save(student);
		
	}

	@Override
	public void deleteById(int id) {
		studentRepository.deleteById(id);
		
	}

	
}
