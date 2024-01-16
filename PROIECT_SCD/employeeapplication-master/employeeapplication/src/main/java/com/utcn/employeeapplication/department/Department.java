package com.utcn.employeeapplication.department;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Entity
@Data
@Getter
@Setter
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer departmentID;

    private String description;
    private Integer parentID;
    private Integer managerID;

    //@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    //private Set<Employee> employees = new HashSet<>();
}
