package com.university.universitymanagementapi.service;

import com.university.universitymanagementapi.entity.Department;
import com.university.universitymanagementapi.entity.Faculty;
import com.university.universitymanagementapi.error.FacultyNotFoundException;
import com.university.universitymanagementapi.repository.DepartmentRepository;
import com.university.universitymanagementapi.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FacultyService {

    @Autowired
    private FacultyRepository faculty_repository;
    @Autowired
    private DepartmentRepository department_repository;

    public List<Faculty> getAllFaculty(){
        return faculty_repository.findAll();
    }

    public Faculty getFacultyById(long faculty_id) throws FacultyNotFoundException{
        Optional<Faculty> faculty = faculty_repository.findById(faculty_id);

        if(!faculty.isPresent()){
            throw new FacultyNotFoundException("Faculty with ID '" +faculty_id+ "' is not Found!");
        }
        return faculty.get();
    }

    public Faculty getFacultyByCode(String faculty_code)
            throws FacultyNotFoundException{
        Optional<Faculty> faculty = Optional.ofNullable(faculty_repository.findByCode(faculty_code));

        if(!faculty.isPresent()){
            throw new FacultyNotFoundException("Faculty with this code '"+faculty_code+ "' is not found!");
        }
        return faculty.get();
    }

    public void addFaculty(Faculty faculty){
        faculty_repository.save(faculty);
    }

    public Faculty updateFaculty(Faculty faculty, long faculty_id){
        Faculty facultyDb = faculty_repository.findById(faculty_id).get();

        if (Objects.nonNull(faculty.getFaculty_name())
                && !"".equalsIgnoreCase(faculty.getFaculty_name())
        ) {
            facultyDb.setFaculty_name(faculty.getFaculty_name());
        }

        if(Objects.nonNull(faculty.getCode())
            && !"".equalsIgnoreCase(faculty.getCode())
        ){
            facultyDb.setCode(faculty.getCode());
        }

        return faculty_repository.save(facultyDb);
    }

    public Faculty addDepartmentToFaculty(
            long faculty_id, long department_id){
        Faculty faculty = faculty_repository.findById(faculty_id).get();
        Department department = department_repository.findById(department_id).get();

        faculty.setDepartments(department.getFaculties().getDepartments());
        return faculty_repository.save(faculty);
    }

    public void deleteFaculty(long faculty_id) throws FacultyNotFoundException{
         faculty_repository.deleteById(faculty_id);
    }

}
