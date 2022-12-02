package com.university.universitymanagementapi.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tbl_faculty")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Faculty {

    @Id
    @SequenceGenerator(
            name = "faculty_Sequence",
            sequenceName = "faculty_id",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "faculty_id"
    )
    private long faculty_id;

    @Column(unique = true)
    @NotBlank(message = "Sorry faculty name must be inserted")
    private String faculty_name;

    @Column(unique = true)
    @NotBlank(message = "Sorry faculty code must be inserted")
    private String code;

    @OneToMany(mappedBy = "faculties", cascade = CascadeType.ALL)
    private Set<Department> departments = new HashSet<>();

    public void addDepartment(List<Department> department){
        departments.addAll(department);
    }

}
