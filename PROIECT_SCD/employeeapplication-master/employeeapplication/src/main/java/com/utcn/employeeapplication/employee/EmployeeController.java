package com.utcn.employeeapplication.employee;

import com.utcn.employeeapplication.department.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employee")

public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/create")
    public Employee createEmployee(@RequestBody Employee employee) {
       /// if(employee.isManager())

        return employeeService.create(employee);
        //else return  null;
    }

    @DeleteMapping("/delete")
    public void deleteAllEmployees() {
        employeeService.deleteAllEmployees();
    }

    @DeleteMapping("/delete/{ID}")
    public void deleteById(@PathVariable("ID") Integer ID) {
        employeeService.deleteById(ID);
    }

    @DeleteMapping("deleteByName/{name}")
    public void deleteByName(@PathVariable("name") String name) {
         employeeService.deleteByName(name);
    }

    @GetMapping("/get/{id}")
    public Employee getEmployeeById(@PathVariable("id") Integer id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping("/get")
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

   /* @GetMapping("/get/managers")
    public List<Employee> getManagers(){
        return employeeService.getManagers();
    }*/

    @GetMapping("/get/names")
    public List<String> getEmpNames(){
        return employeeService.getEmpNames();
    }

    @GetMapping("/get/department/{ID}")
    public Optional<Department> getDepartment(@PathVariable("ID") Integer ID){
        return employeeService.getDepartment(ID);
    }

    @PutMapping("/update/name/{ID}/{name}")
    ResponseEntity<Employee> updateName(@PathVariable("ID") Integer ID, @PathVariable("name") String name) {
        return employeeService.updateName(ID, name);
    }

    @PutMapping("/update/email/{ID}/{email}")
    ResponseEntity<Employee> updateEmail(@PathVariable("ID") Integer ID, @PathVariable("email") String email) {
        return employeeService.updateEmail(ID, email);
    }

    @GetMapping("/get/employee/{name}")
    public  Employee getEmpByName(@PathVariable("name") String name){
        return  employeeService.getEmpByName(name);
    }

    @PutMapping("/update/{ID}")
    ResponseEntity<Employee> updateEmployee(@PathVariable("ID") Integer ID, @RequestBody Employee employee) {
        return employeeService.updateEmployee(ID, employee);
    }

}
