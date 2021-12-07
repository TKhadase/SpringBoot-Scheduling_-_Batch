package com.tushar.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tushar.model.OStudents;

public interface OStudentRepo extends JpaRepository<OStudents, Integer> {

}
