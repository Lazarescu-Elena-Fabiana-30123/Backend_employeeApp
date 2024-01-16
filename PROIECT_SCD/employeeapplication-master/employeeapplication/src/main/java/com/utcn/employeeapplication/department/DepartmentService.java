package com.utcn.employeeapplication.department;

import com.utcn.employeeapplication.employee.Employee;
import com.utcn.employeeapplication.employee.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.support.PageableUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional
    public Department create(Department department){
        return departmentRepository.save(department);
    }

    @Transactional
    public void delete(@PathVariable Integer departmentId){
        Department deletedDepartment = departmentRepository.findById(departmentId)
                .orElseThrow(()-> new ResourceNotFoundException("Department with id "+ departmentId +"does not exist"));

        departmentRepository.delete(deletedDepartment);

    }

    @Transactional
    public Department update(@PathVariable Integer departmentId, Department department){
        Department updatedDept = departmentRepository.findById(departmentId)
                .orElseThrow(()-> new ResourceNotFoundException("Department with id "+ departmentId +"does not exist"));

        department.setDepartmentID(departmentId);
        department.setManagerID(department.getManagerID());
        department.setDescription(department.getDescription());

        return departmentRepository.save(department);
    }

    @Transactional
    public ResponseEntity<Department> updateName(@PathVariable Integer ID, @PathVariable String name){
        Department updDepartment = departmentRepository.findById(ID)
                .orElseThrow(()-> new ResourceNotFoundException("Department with id " + ID + "does not exist"));

        updDepartment.setDescription(name);
        departmentRepository.save(updDepartment);

        return ResponseEntity.ok(updDepartment);
    }

    @Transactional
    public Optional<Department> getDepartmentById(@PathVariable Integer id){
        return departmentRepository.findById(id);
    }

    @Transactional
    public Department getDepartment(@PathVariable String description){
        Department department = null;
        department = departmentRepository.findByDescription(description);
        return department;
    }

    @Transactional
    public List<Department> getAllDepartments(){
        return departmentRepository.findAll();
    }


    @Transactional
    public  List<Employee> getDepartmentEmployees(@PathVariable Integer departmentID){
        List<Employee> employees = employeeRepository.findAll();
        List<Employee> deptEmployees = new ArrayList<>();

        for (Employee employee:
             employees) {
            if (employee.getDepartmentID().equals(departmentID)){
                deptEmployees.add(employee);
            }
        }
        return deptEmployees;
    }

}
