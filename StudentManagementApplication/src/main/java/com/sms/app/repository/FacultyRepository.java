package com.sms.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sms.app.entity.Faculty;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Integer>{

}
