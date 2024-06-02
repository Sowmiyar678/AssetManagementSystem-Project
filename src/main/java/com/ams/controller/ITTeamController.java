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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ams.entity.ITTeam;
import com.ams.serviceImpl.ITTeamserviceImpl;

@RestController
@CrossOrigin("http://localhost:3000/")

public class ITTeamController {
	
		@Autowired
ITTeamserviceImpl dao;
		
		@Autowired
		JavaMailSender emailsender;
		
		
		
	    @PostMapping("/RegisterITTeam")
	    public ResponseEntity<String> register(@RequestBody ITTeam itteam) {
	        try {
	         
	            if (itteam.getEmail() == null || itteam.getPassword() == null || itteam.getPhoneno() == null || itteam.getLocation() == null || itteam.getName() == null) {
//	                return ResponseEntity.badRequest().body("All fields are required.");
	            }

	            dao.additteam(itteam);

	            
	            SimpleMailMessage message=new SimpleMailMessage();
		         message.setFrom("msvarshinee@gmail.com");
		         message.setTo(itteam.getEmail());
		         message.setSubject("Registration Successfull...!!!");
		         message.setText("Your ticket has been raised");
		         message.setText("Your Registered email and password ," + itteam.getName()
		         +" "+"You can Login through the following credentials"+" "+
		        		 "Username - "+" "+itteam.getEmail()+" "+ "Password - "+" "+itteam.getPassword()
		        		 +" Employee ID - "+" "+itteam.getItteamid());
		         
		         emailsender.send(message);
	             
	            

	            return ResponseEntity.ok("ITTeam registered successfully!");
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                   .body("Error occurred during registration: " + e.getMessage());
	        }
	    }

	    @PostMapping("/LoginITTeam")
	    public ResponseEntity<?> login(@RequestBody ITTeam itteam) {
	        try {
	            
	            Optional<ITTeam> adminOptional = dao.findbyemail(itteam.getEmail());
	            if (!adminOptional.isPresent()) {
	                return ResponseEntity.badRequest().body("Invalid email");
	            }

	            ITTeam adminFound = adminOptional.get();

	            if (!itteam.getPassword().equals(adminFound.getPassword())) {
	                return ResponseEntity.badRequest().body("Invalid password");
	            }

	            Map<String, Long> map=new HashMap<>();
	            map.put("userId", adminFound.getItteamid());
	            return ResponseEntity.ok(map);
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                   .body("Error occurred during login: " + e.getMessage());
	        }
	    }
	         
	    
	    @GetMapping("/GetITTeamById/{itteamid}")
	    public List<ITTeam> getITTeam(@PathVariable Long itteamid) {
	        try {
	            return dao.getITTeamById(itteamid);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	 
	        return null;
	    }
	    @GetMapping("/GetITTeamIds")
	    public List<Long> getITTeamIdlist() {
	        try {
	            return dao.getITTeamIdlist();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
	    
	    @GetMapping("/GetAllITeam")
	    public List<ITTeam> getallITTeam() {
	        try {
	            return dao.getallITTeam();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	 
	        return null;
	    }
	    
	    @PutMapping("/UpdateITTeam/{itteamid}")
	    public ResponseEntity<?> updateITTeam(@PathVariable("itteamid") Long itteamid,
	                                          @RequestBody ITTeam updatedITTeam) {
	        return dao.updateITTeam(itteamid, updatedITTeam);
	    }

}
