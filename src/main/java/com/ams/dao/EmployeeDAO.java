package com.ams.dao;


import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.ams.entity.Employee;
public interface EmployeeDAO  {
	
	
	public Object addemployee(Employee employee);
	
	 Optional<Employee> findbyemail(String email);
	 
		List<Employee> getEmpById(Long empid);

		  List<Employee> getallEmployee();
		
		List<Long> getEmployeeIdlist();
		  
		 ResponseEntity<?> updateEmployee(long empid,Employee updatedEmployee);

}