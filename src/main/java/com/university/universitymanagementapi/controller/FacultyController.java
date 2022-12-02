package com.university.universitymanagementapi.controller;

import com.university.universitymanagementapi.entity.Faculty;
import com.university.universitymanagementapi.error.FacultyNotFoundException;
import com.university.universitymanagementapi.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class FacultyController {

    @Autowired
    private FacultyService faculty_service;

    @GetMapping("/faculties")
    public List<Faculty> get_all_faculties(){
        return faculty_service.getAllFaculty();
    }

    @GetMapping("/faculties/{id}")
    public Faculty get_faculty_by_id(@PathVariable("id") long faculty_id)
            throws FacultyNotFoundException {
        return faculty_service.getFacultyById(faculty_id);
    }

    @GetMapping("/faculties/code")
    public Faculty get_faculty_by_code(@RequestParam String code)
            throws FacultyNotFoundException{
        return faculty_service.getFacultyByCode(code);
    }

    @PostMapping("/faculties")
    public String add_faculty(@Valid  @RequestBody Faculty faculty){
        faculty_service.addFaculty(faculty);
        return "Faculty added successfully!";
    }

    @PutMapping("/faculties/{id}")
    public String update_faculty(@RequestBody Faculty faculty,
                                 @PathVariable("id") long faculty_id){
        faculty_service.updateFaculty(faculty, faculty_id);

        return "faculty profile updated successfully";
    }

    @PutMapping("/faculties/{faculty_id}/departments/{department_id}")
    public String add_department_to_faculty(
            @PathVariable("faculty_id") long faculty_id, @PathVariable("department_id") long department_id ){
        faculty_service.addDepartmentToFaculty(faculty_id, department_id);

        return "Department Added to Faculty Successfully!";
    }

    @DeleteMapping("/faculties/{id}")
    public String delete_faculty(
            @PathVariable("id") long faculty_id) throws FacultyNotFoundException{
        faculty_service.deleteFaculty(faculty_id);

        return "faculty deleted successfully";
    }
}
