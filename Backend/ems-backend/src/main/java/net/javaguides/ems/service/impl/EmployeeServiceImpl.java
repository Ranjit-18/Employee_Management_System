package net.javaguides.ems.service.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import net.javaguides.ems.dto.EmployeeDto;
import net.javaguides.ems.entity.Employee;
import net.javaguides.ems.exception.ResourceNotFoundException;
import net.javaguides.ems.mapper.EmployeeMapper;
import net.javaguides.ems.repository.EmployeeRepository;
import net.javaguides.ems.service.EmployeeService;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

	
	private EmployeeRepository employeeRepository;
	
	@Override
	public EmployeeDto createEmployee(EmployeeDto employeeDto) {
		 Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
		 Employee savedEmployee = employeeRepository.save(employee);
		 
		return EmployeeMapper.mapToEmployeeDto(savedEmployee);
	}

	
	@Override
	public EmployeeDto getEmployeeById(Long employeeId) {
		Employee employee = employeeRepository.findById(employeeId).orElseThrow(()->
											new ResourceNotFoundException("Employee is not exist with given id : "+employeeId));
		return EmployeeMapper.mapToEmployeeDto(employee);
	}


	@Override
	public List<EmployeeDto> getAllEmployees() {
		List<Employee> employees = employeeRepository.findAll();
		return employees.stream().map((employee) -> EmployeeMapper.mapToEmployeeDto(employee))
				.collect(Collectors.toList());
	}


	@Override
	public EmployeeDto updateEmployee(Long employeeId, EmployeeDto employeeDto) {
		Employee employee = employeeRepository.findById(employeeId).orElseThrow(()->new ResourceNotFoundException("Employee is not Exists with given id : "+employeeId));
		
		employee.setFirstName(employeeDto.getFirstName());
		employee.setLastName(employeeDto.getLastName());
		employee.setEmail(employeeDto.getEmail());
		
		Employee updatedEmployee = employeeRepository.save(employee);
		
		return EmployeeMapper.mapToEmployeeDto(updatedEmployee);
	}


	@Override
	public void deleteById(Long employeeId) {
		Employee employee = employeeRepository.findById(employeeId).orElseThrow(()->new ResourceNotFoundException("Employee is not Exists with given id : "+employeeId));
		employeeRepository.deleteById(employeeId);
	}

}
