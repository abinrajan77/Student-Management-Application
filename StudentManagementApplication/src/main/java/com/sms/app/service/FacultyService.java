package com.sms.app.service;

import java.util.List;

import com.sms.app.entity.Faculty;

public interface FacultyService {

	public List<Faculty> findAll();

	public Faculty findById(int id);

	public void save(Faculty faculty);

	public void deleteById(int id);
}
