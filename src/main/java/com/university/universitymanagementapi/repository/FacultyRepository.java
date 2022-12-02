package com.university.universitymanagementapi.repository;

import com.university.universitymanagementapi.entity.Department;
import com.university.universitymanagementapi.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    public Faculty findByCode(String faculty_code);

}
