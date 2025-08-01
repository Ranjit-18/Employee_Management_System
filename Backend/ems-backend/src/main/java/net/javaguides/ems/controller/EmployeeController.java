package net.javaguides.ems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.ems.dto.EmployeeDto;
import net.javaguides.ems.service.EmployeeService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@CrossOrigin("*") //star means all clients can call employee related APIs
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	//build add employee REST API
	@PostMapping
	public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto)
	{
		EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);
		return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
	}
	
	//Build get employee REST API
	@GetMapping("{id}")
	public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long employeeId)
	{
		EmployeeDto employeeDto =  employeeService.getEmployeeById(employeeId);
		return ResponseEntity.ok(employeeDto);
	}

	//Build get all employees REST API
	@GetMapping
	public ResponseEntity<List<EmployeeDto>> findAll() {
		List<EmployeeDto> employees = employeeService.getAllEmployees();
		return ResponseEntity.ok(employees);
	}
	
	//build update employee REST API
	@PutMapping("{id}")
	public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long employeeId,@RequestBody EmployeeDto updatedEmployee)
	{
		EmployeeDto employeeDto = employeeService.updateEmployee(employeeId, updatedEmployee);
		
		return ResponseEntity.ok(employeeDto);
	}
	
	//build delete employee Rest API
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteById(@PathVariable("id") Long employeeId)
	{
		employeeService.deleteById(employeeId);
		return ResponseEntity.ok("Employee deleted successfully!.....");
	}
}
