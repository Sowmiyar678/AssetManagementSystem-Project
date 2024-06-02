package com.ams.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ams.dao.EmployeeDAO;
import com.ams.entity.Employee;
import com.ams.service.Employeeservice;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EmployeeserviceImpl implements Employeeservice {

	@Autowired
	EmployeeDAO dao;

	@Override
	public Object addemployee(Employee employee) {
		return dao.addemployee(employee);
	}

	@Override
	public Optional<Employee> findbyemail(String email) {

		return dao.findbyemail(email);
	}

	@Override
	public List<Employee> getEmpById(Long empid) {

		return dao.getEmpById(empid);
	}

	@Override
	public List<Employee> getallEmployee() {
		return dao.getallEmployee();

	}

	@Override
	public List<Long> getEmployeeIdlist() {

		return dao.getEmployeeIdlist();
	}

	@Override
	public ResponseEntity<?> updateEmployee(long empid, Employee updatedEmployee) {

		return dao.updateEmployee(empid, updatedEmployee);
	}

}
