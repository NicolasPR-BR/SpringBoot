package com.code.crudDemo.service;

import com.code.crudDemo.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();
    Employee findById(int id);

    Employee save(Employee employee);
    void deleteById(int id);
}
