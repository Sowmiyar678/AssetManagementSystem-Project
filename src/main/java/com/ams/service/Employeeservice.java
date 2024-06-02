package com.ams.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.ams.entity.Employee;

@Repository
public interface Employeeservice {

	public Object addemployee(Employee employee);

	Optional<Employee> findbyemail(String email);

	List<Employee> getEmpById(Long empid);

	List<Employee> getallEmployee();

	List<Long> getEmployeeIdlist();

	ResponseEntity<?> updateEmployee(long empid, Employee updatedEmployee);

}
