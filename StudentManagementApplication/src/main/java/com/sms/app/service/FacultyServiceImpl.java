package com.sms.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sms.app.entity.Faculty;
import com.sms.app.repository.FacultyRepository;

@Service
public class FacultyServiceImpl implements FacultyService{

	@Autowired
	private FacultyRepository facultyRepository;
	
	@Override
	public List<Faculty> findAll() {
		return facultyRepository.findAll();
	}

	@Override
	public Faculty findById(int id) {
		Optional<Faculty> result = facultyRepository.findById(id);

		Faculty faculty = null;

		if (result.isPresent()) {
			faculty = result.get();
		} else {
			// we didn't find the student
			throw new RuntimeException("Did not find faculty id - " + id);
		}

		return faculty;
	}

	@Override
	public void save(Faculty faculty) {
		facultyRepository.save(faculty);
		
	}

	@Override
	public void deleteById(int id) {
		facultyRepository.deleteById(id);
		
	}

}
