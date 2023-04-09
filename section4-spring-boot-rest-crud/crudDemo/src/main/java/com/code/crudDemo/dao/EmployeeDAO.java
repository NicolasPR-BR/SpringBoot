package com.code.crudDemo.dao;

import com.code.crudDemo.entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    List<Employee> findAll();
    Employee findById(int id);

    Employee save(Employee employee);
    void deleteById(int id);
}