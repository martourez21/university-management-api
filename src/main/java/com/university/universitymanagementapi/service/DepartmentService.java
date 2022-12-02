package com.university.universitymanagementapi.service;

import com.university.universitymanagementapi.entity.Department;
import com.university.universitymanagementapi.error.DepartmentNotFoundException;
import com.university.universitymanagementapi.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository department_repository;

    public List<Department> getAllDepartments(){
        return department_repository.findAll();
    }

    public Department getDepartmentById(long department_id)
                throws DepartmentNotFoundException{
        Optional<Department> department = department_repository.findById(department_id);

        if(!department.isPresent()){
            throw new DepartmentNotFoundException("Department with this ID '"+department_id+ "' is not found!");
        }

        return department.get();
    }

    public void addDepartment(Department department){
        department_repository.save(department);
    }

    public Department updateDepartment(Department department, long department_id){
        Department depDB = department_repository.findById(department_id).get();

        if(Objects.nonNull(department.getName())
            && !"".equalsIgnoreCase(department.getName())){
            depDB.setName(department.getName());
        }

        if(Objects.nonNull(department.getCode())
            && !"".equalsIgnoreCase(department.getCode())){
            depDB.setCode(department.getCode());
        }
        return department_repository.save(depDB);
    }

    public void deleteDepartment(long department_id) throws DepartmentNotFoundException{
        department_repository.deleteById(department_id);
    }
}
