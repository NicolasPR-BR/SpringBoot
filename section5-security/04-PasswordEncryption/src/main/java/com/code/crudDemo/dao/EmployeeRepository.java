package com.code.crudDemo.dao;

import com.code.crudDemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

//Returns HATEOAS standard
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
