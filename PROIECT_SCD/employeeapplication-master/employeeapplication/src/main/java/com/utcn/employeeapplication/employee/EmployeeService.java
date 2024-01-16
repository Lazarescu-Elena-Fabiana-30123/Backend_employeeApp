package com.utcn.employeeapplication.employee;

import com.utcn.employeeapplication.department.Department;
import com.utcn.employeeapplication.department.DepartmentRepository;
import jakarta.transaction.Transactional;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    @Transactional
    public Employee create(Employee employee){
       return employeeRepository.save(employee);
    }

    @Transactional
    public void deleteAllEmployees(){
        employeeRepository.deleteAll();
    }

    @Transactional
    public void deleteById(@PathVariable Integer ID){
        Employee deletedEmployee = employeeRepository.findById(ID)
                .orElseThrow(()-> new ResourceNotFoundException("Employee with id " + ID +" does not exist."));

        employeeRepository.delete(deletedEmployee);
    }

    @Transactional
    public ResponseEntity<Employee> deleteByName(@PathVariable String name) {
        Employee employee = employeeRepository.findByName(name);

        if (employee != null) {
            employeeRepository.delete(employee);
            return ResponseEntity.ok(employee);
        }
        else return ResponseEntity.notFound().build();
    }

   /* @Transactional
    public List<Employee> getManagers(){
        List<Employee> employees = employeeRepository.findAll();
        List<Employee> managers = null;

        for (Employee employee:
             employees) {
            if(employee.isManager())
                managers.add(employee);
        }
        return managers;
    }
*/
    @Transactional
    public Employee getEmployeeById(@PathVariable Integer ID){
        return employeeRepository.findById(ID)
                .orElseThrow(()-> new ResourceNotFoundException("Employee with id " + ID +" does not exist."));
    }

    public List<Employee> getAllEmployees(){

        return employeeRepository.findAll();
    }
    @Transactional
    public Employee getEmpByName(String name){
        return employeeRepository.findByName(name);
    }

    @Transactional
    public List<String> getEmpNames(){
        List<Employee> employees = employeeRepository.findAll();
        List<String> names = new ArrayList<>();

        for (Employee employee:
                employees) {
            names.add(employee.getName());
        }
        return names;
    }

    @Transactional
    public Optional<Department> getDepartment(@PathVariable Integer ID){
        Employee employee = employeeRepository.findById(ID)
                .orElseThrow(()-> new ResourceNotFoundException("Employee with ID "+ID +" not found"));

        return departmentRepository.findById(employee.getDepartmentID());
    }

    @Transactional
    public ResponseEntity<Employee> updateName(@PathVariable Integer ID, @PathVariable String name){
        Employee updatedEmployee = employeeRepository.findById(ID)
                .orElseThrow(()-> new ResourceNotFoundException("Employee with id "+ ID +"does not exist"));

        updatedEmployee.setName(name);
        employeeRepository.save(updatedEmployee);

        return ResponseEntity.ok(updatedEmployee);
    }

    @Transactional
    public ResponseEntity<Employee> updateEmail(@PathVariable Integer ID, @PathVariable String email){

        Employee updatedEmployee = employeeRepository.findById(ID)
                .orElseThrow(()-> new ResourceNotFoundException("Employee with id "+ ID +"does not exist"));

        updatedEmployee.setEmail(email);
        employeeRepository.save(updatedEmployee);

        return ResponseEntity.ok(updatedEmployee);
    }

    @Transactional
    public ResponseEntity<Employee> updateEmployee(@PathVariable Integer ID, @RequestBody Employee employee){
        Employee updatedEmployee = employeeRepository.findById(ID)
                .orElseThrow(()-> new ResourceNotFoundException("Employee with id "+ ID +"does not exist"));

        updatedEmployee.setID(employee.getID());
        updatedEmployee.setManagerID(employee.getManagerID());
        updatedEmployee.setDepartmentID(employee.getDepartmentID());
        updatedEmployee.setName(employee.getName());
        updatedEmployee.setEmail(employee.getEmail());

        employeeRepository.save(updatedEmployee);

        return ResponseEntity.ok(updatedEmployee);
    }

}
