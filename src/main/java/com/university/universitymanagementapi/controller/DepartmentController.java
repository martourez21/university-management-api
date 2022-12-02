package com.university.universitymanagementapi.controller;

import com.university.universitymanagementapi.entity.Department;
import com.university.universitymanagementapi.error.DepartmentNotFoundException;
import com.university.universitymanagementapi.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class DepartmentController {

    @Autowired
    private DepartmentService department_service;

    @GetMapping("/departments")
    public List<Department> get_all_departments(){
        return department_service.getAllDepartments();
    }

    @GetMapping("/departments/{id}")
    public Department get_department_by_id(@PathVariable("id") long department_id)
            throws DepartmentNotFoundException {
        return department_service.getDepartmentById(department_id);
    }

    @PostMapping("/departments")
    public String add_department(@Valid  @RequestBody Department department){
        department_service.addDepartment(department);

        return "Department added successfully!";
    }

    @PutMapping("/departments/{id}")
    public String update_department(@RequestBody  Department department, @PathVariable("id") long department_id){
        department_service.updateDepartment(department, department_id);

        return "Department updated successfully!";
    }

    @DeleteMapping("/departments/{department_id}")
    public String delete_department_by_id(
            @PathVariable long department_id) throws DepartmentNotFoundException{
        department_service.deleteDepartment(department_id);

        return "Department successfully deleted!";
    }






}
