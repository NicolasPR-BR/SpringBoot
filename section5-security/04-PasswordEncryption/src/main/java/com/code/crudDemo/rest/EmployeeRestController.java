package com.code.crudDemo.rest;

import com.code.crudDemo.entity.Employee;
import com.code.crudDemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    private EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        return employeeService.findAll();
    }
    @GetMapping("/employees/{employeeid}")
    public Employee getEmployee(@PathVariable int employeeid){
        Employee employee = employeeService.findById(employeeid);
        if(employee == null){
            throw new RuntimeException("Employee not found in the database - " + employeeid);
        }

        return employee;
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee newEmployee){
        //In case an id is passed in the JSON
        newEmployee.setId(0);

        Employee saved = employeeService.save(newEmployee);

        return saved;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee){
        Employee updatedEmployee = employeeService.save(employee);
        return updatedEmployee;
    }

    @DeleteMapping("/employees/{employeeid}")
    public String deleteEmployee(@PathVariable int employeeid){
        Employee employee = employeeService.findById(employeeid);

        if(employee == null){
            throw new RuntimeException("Could not find employee with id - " + employeeid);
        }

        employeeService.deleteById(employeeid);
        return "Deleted employee id - " + employeeid;
    }


}