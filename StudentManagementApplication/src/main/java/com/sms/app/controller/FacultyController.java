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

import com.sms.app.entity.Faculty;
import com.sms.app.service.FacultyService;

@RestController
@RequestMapping("/sms")
public class FacultyController {

	@Autowired
	private FacultyService facultyService;

	@GetMapping("/faculties")
	@PreAuthorize("hasRole('ADMIN') or hasRole('FACULTY')")
	public List<Faculty> findAll() {
		return facultyService.findAll();
	}

	@GetMapping("/faculties/{facultyId}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('FACULTY')")
	public Faculty getStudent(@PathVariable int facultyId) {

		Faculty faculty = facultyService.findById(facultyId);

		return faculty;
	}

	@PostMapping("/faculties")
	@PreAuthorize("hasRole('ADMIN')")
	public Faculty addFaculty(@Valid @RequestBody Faculty faculty) {

		faculty.setFacultyId(0);

		facultyService.save(faculty);

		return faculty;
	}

	@PutMapping("/faculties")
	@PreAuthorize("hasRole('ADMIN')")
	public Faculty updateFaculty(@RequestBody Faculty faculty) {

		facultyService.save(faculty);

		return faculty;
	}

	@DeleteMapping("/faculties/{facultyId}")
	@PreAuthorize("hasRole('ADMIN')")
	public String deleteFaculty(@PathVariable int facultyId) {

		Faculty tempFaculty = facultyService.findById(facultyId);

		// throw exception if null

		if (tempFaculty == null) {
			throw new RuntimeException("Faculty id not found - " + facultyId);
		}

		facultyService.deleteById(facultyId);

		return "Deleted faculty id - " + facultyId;
	}
}
