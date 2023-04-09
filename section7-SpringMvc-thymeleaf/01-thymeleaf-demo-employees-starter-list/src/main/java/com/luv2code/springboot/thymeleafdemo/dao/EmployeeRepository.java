package com.luv2code.springboot.thymeleafdemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	// that's it ... no need to write any code LOL!

    //Sort by last name
    //JPA Looks for a specific format and pattern. It then creates the appropriate query behind the scenes
    public List<Employee> findAllByOrderByLastNameAsc();

}
