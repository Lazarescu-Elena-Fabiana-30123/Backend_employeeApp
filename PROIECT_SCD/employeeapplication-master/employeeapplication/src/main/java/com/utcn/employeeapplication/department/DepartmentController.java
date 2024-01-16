package com.utcn.employeeapplication.department;

import com.utcn.employeeapplication.employee.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {
    @Autowired // the injection of DepartmentService instance

    private DepartmentService departmentService;

    @GetMapping("/get")
    public List<Department> getAllDepartments(){
        return departmentService.getAllDepartments();
    }

    @DeleteMapping("/delete/{departmentId}")
    public void delete(@PathVariable("departmentId") Integer departmentId){
        departmentService.delete(departmentId);
    }

    @PutMapping("/update/{departmentId}")
    public Department update(@PathVariable("departmentId") Integer departmentId, @RequestBody Department department){
        return departmentService.update(departmentId, department);
    }

    @PutMapping("/update/name/{departmentID}/{name}")
    ResponseEntity<Department> updateName(@PathVariable("departmentID") Integer departmentID, @PathVariable("name") String name){
        return  departmentService.updateName(departmentID, name);
    }

    @PostMapping("/create")
    public Department create(@RequestBody Department department){
        return departmentService.create(department);
    }

    @GetMapping("/get/{departmentID}")
    public List<Employee> getDepartmentEmployees(@PathVariable("departmentID") Integer departmentID){
        return departmentService.getDepartmentEmployees(departmentID);
    }
    @GetMapping("/getByName/{description}")
    public Department getDepartment(@PathVariable("description") String description){
        return departmentService.getDepartment(description);
    }

    @GetMapping("/getById/{id}")
    public Optional<Department> getById(@PathVariable("id") Integer id){
        return departmentService.getDepartmentById(id);
    }
}
