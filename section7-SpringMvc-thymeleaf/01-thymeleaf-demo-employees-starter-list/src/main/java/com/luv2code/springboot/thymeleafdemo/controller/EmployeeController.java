package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	EmployeeService employeeService;
	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	// load employee data

	private List<Employee> theEmployees;

	// add mapping for "/list"

	@GetMapping("/list")
	public String listEmployees(Model theModel) {
		List<Employee> employees = employeeService.findAllByOrderByLastNameAsc();
		// add to the spring model
		theModel.addAttribute("employees", employees);

		return "employees/list-employees";
	}

	@GetMapping("/showFormForAdd")
	public String addEmployee(Model theModel){
		Employee employee = new Employee();
		theModel.addAttribute("employee", employee);
		return "employees/employee-form";
	}

	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee employee){
		employeeService.save(employee);

		//Redirect to prevent duplicate submission
		return "redirect:/employees/list";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int id, Model model){
		Employee employee = employeeService.findById(id);
		model.addAttribute("employee", employee);

		return "employees/employee-form";
	}

	@GetMapping("/delete")
	public String deleteEmployee(@RequestParam("employeeId") int id){
		employeeService.deleteById(id);
		return "redirect:/employees/list";
	}
}
