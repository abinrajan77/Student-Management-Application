package com.sms.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "faculty")
public class Faculty {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "faculity_id")
	private int facultyId;

	@Column(name = "faculty_name")
	private String facultyName;

	@Column(name = "faculty_email")
	private String facultyEmail;

	// Generate Constructors

	public Faculty() {

	}

	public Faculty(int facultyId, String facultyName, String facultyEmail) {
		super();
		this.facultyId = facultyId;
		this.facultyName = facultyName;
		this.facultyEmail = facultyEmail;
	}

	// Generate Getters and Setters

	public int getFacultyId() {
		return facultyId;
	}

	public void setFacultyId(int facultyId) {
		this.facultyId = facultyId;
	}

	public String getFacultyName() {
		return facultyName;
	}

	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}

	public String getFacultyEmail() {
		return facultyEmail;
	}

	public void setFacultyEmail(String facultyEmail) {
		this.facultyEmail = facultyEmail;
	}
	
	
	// Generate toString

	@Override
	public String toString() {
		return "Faculty [facultyId=" + facultyId + ", facultyName=" + facultyName + ", facultyEmail=" + facultyEmail
				+ "]";
	}

}
