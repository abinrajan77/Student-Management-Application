package com.sms.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "std_id")
	private int studentId;

	@Column(name = "std_name")
	private String studentName;

	@Column(name = "course")
	private String course;

	@Column(name = "std_email")
	private String studentEmail;

	// Generate Constructors

	public Student() {

	}

	public Student(int studentId, String studentName, String course, String studentEmail) {
		super();
		this.studentId = studentId;
		this.studentName = studentName;
		this.course = course;
		this.studentEmail = studentEmail;
	}

	// Generate Getters and Setters

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getStudentEmail() {
		return studentEmail;
	}

	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}

	// Generate toString

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", studentName=" + studentName + ", course=" + course
				+ ", studentEmail=" + studentEmail + "]";
	}

}
