package com.ams.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ams.entity.Employee;
import com.ams.serviceImpl.EmployeeserviceImpl;

@RestController
@CrossOrigin("http://localhost:3000/")
public class EmployeeController {
	
	@Autowired
	EmployeeserviceImpl dao;
	
	@Autowired
	JavaMailSender emailsender;
	
		
	    @PostMapping("/RegisterEmployee")
	    public ResponseEntity<String> register(@RequestBody Employee employee) {
	        try {
	        
	            if (employee.getEmail() == null || employee.getPassword() == null || employee.getPhoneno() == null || employee.getLocation() == null || employee.getName() == null) {
	                return ResponseEntity.badRequest().body("All fields are required.");
	            }

	            dao.addemployee(employee);
	            
	        	
	        	
	        		
	        	
	            
	            SimpleMailMessage message=new SimpleMailMessage();
		         message.setFrom("msvarshinee@gmail.com");
		         message.setTo(employee.getEmail());
		         message.setSubject("Registration Successfull...!!!");
		         message.setText("Your ticket has been raised");
		         message.setText("Your Registered email and password ," + employee.getName()
		         +" "+"You can Login through the following credentials"+" "+
		        		 "Username - "+" "+employee.getEmail()+" "+ "Password - "+" "+employee.getPassword()
		        		 +" Employee ID - "+" "+employee.getEmpid());
		         
		         emailsender.send(message);
	             
	           

	            return ResponseEntity.ok("Employee registered successfully!");
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                   .body("Error occurred during registration: " + e.getMessage());
	        }
	    }

	    @PostMapping("/LoginEmployee")
	    public ResponseEntity<?> login(@RequestBody Employee employee) {
	        try {
	           
	            Optional<Employee> adminOptional = dao.findbyemail(employee.getEmail());
	            if (!adminOptional.isPresent()) {
	                return ResponseEntity.badRequest().body("Invalid email");
	            }

	            Employee adminFound = adminOptional.get();

	          
	          
	            if (!employee.getPassword().equals(adminFound.getPassword())) {
	                return ResponseEntity.badRequest().body("Invalid password");
	            }
	            
	            Map<String, Long> map=new HashMap<>();
	            map.put("userId", adminFound.getEmpid());
	            

	            return ResponseEntity.ok(map);
	            
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                   .body("Error occurred during login: " + e.getMessage());
	        }
	    }
	    
	    @GetMapping("/GetEmployeeById/{empid}")
	    public List<Employee> getEmp(@PathVariable Long empid) {
	        try {
	            return dao.getEmpById(empid);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	 
	        return null;
	    }
	    
	    @PutMapping("/UpdateEmployee/{empid}")
	    public ResponseEntity<?> updateEmployee(@PathVariable("empid") Long empid,
	                                          @RequestBody Employee updatedEmployee) {
	        return dao.updateEmployee(empid, updatedEmployee);
	    }
	    
	    @GetMapping("/GetAllEmployee")
	    public List<Employee> getAllEmployee() {
	        try {
	            return dao.getallEmployee();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	 
	        return null;
	    }
	   
	    
	    @GetMapping("/GetEmployeeIds")
	    public List<Long> getEmployeeIdlist() {
	        try {
	            return dao.getEmployeeIdlist();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
	    
	    



}