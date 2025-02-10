package com.springrest.springrest.repo;

import com.springrest.springrest.entity.Student;

import org.springframework.data.jpa.repository.JpaRepository;

public interface studentRepo extends JpaRepository <Student, Integer> {

}
