package com.luv2code.springboot.cruddemo.rest;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
	
	private EmployeeService employeeService;

	// inj emlployee DAO

	public EmployeeRestController (EmployeeService theEmployeeService) {
		employeeService = theEmployeeService;
	}
	
	
		// expoae /employee
	@GetMapping("/employees")
	public List<Employee> getAll() {
		return employeeService.findAll();
	}

	
	// expoae /employee/id
@GetMapping("/employees/{employeeId}")
public Employee getOne(@PathVariable int employeeId) {
	return employeeService.findOne(employeeId);
}

@PostMapping("/employees")
public Employee addEmployee(@RequestBody Employee theEmployee) {
	theEmployee.setId(0);
	employeeService.save(theEmployee);
	return theEmployee;
	
}

@PutMapping("/employees")
public Employee updateEmployee(@RequestBody Employee theEmployee) {
	employeeService.save(theEmployee);
	return theEmployee;
}

@DeleteMapping("/employees/{employeeId}")
public String deleteEmployee(@PathVariable int employeeId) {
	employeeService.delete(employeeId);
	return "Emplyoee by ID : " + employeeId + "  deleteted ";
}

}


