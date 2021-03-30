package com.rest.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rest.model.Employee;
import com.rest.repository.EmployeeRepository;

@RequestMapping("/api/v1/")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;

	@GetMapping("/employees")
	public List<Employee> getEmployees() {

		return employeeRepository.findAll();
	}

	@GetMapping("/employee/{id}")
	public Employee getEmployeeById(@PathVariable(value = "id") int id) {
		return this.employeeRepository.findById(id);

	}

	@PostMapping("/save/employee")
	public Employee createEmployee(@RequestBody Employee employee) {
		Employee emmp = employeeRepository.save(employee);
		return emmp;
	}

	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmp(@PathVariable(value = "id") int id, @Validated @RequestBody Employee e) {
		Employee em = employeeRepository.findById(id);
		em.setEmail(e.getEmail());
		em.setId(e.getId());
		em.setAddress(e.getAddress());
		em.setName(e.getName());

		return ResponseEntity.ok(employeeRepository.save(em));
	}
	
	//Delete Method is not written yet
	/*
	 * @DeleteMapping("/employees/{id}") public ResponseEntity<Employee> deleteEmp()
	 * {
	 * 
	 * }
	 */
}
