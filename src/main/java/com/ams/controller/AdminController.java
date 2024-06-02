package com.ams.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ams.entity.Admin;
import com.ams.serviceImpl.AdminserviceImpl;


@RestController
@CrossOrigin("http://localhost:3000/")

public class AdminController {

	@Autowired
	AdminserviceImpl dao;
	

//	@Autowired
//	JavaMailSender emailsender;
	
	
	
	
	@GetMapping("/GetAdminById/{adminid}")
    public List<Admin> getAdmin(@PathVariable Long adminid) {
        try {
            return dao.getAdminById(adminid);
        } catch (Exception e) {
            e.printStackTrace();
        }
 
        return null;
    }
 
    @GetMapping("/GetAllAdmin")
    public List<Admin> getAllAdmin() {
        try {
            return dao.getallAdmin();
        } catch (Exception e) {
            e.printStackTrace();
        }
 
        return null;
    }
 
    @PutMapping("/UpdateAdmin")
    public String updateAdminInfo(@RequestBody Admin admin) {
        String msg = "";
        try {
            dao.updateAdminInfo(admin);
            msg = "Admin Details Updated";
        } catch (Exception e) {
            msg = "Admin Details not Updated";
        }
        return msg;
    }
 
    @DeleteMapping("/DeleteAdmin/{id}")
    public String deleteAdminInfo(@PathVariable Long id) {
        String msg = "";
        try {
            dao.deleteAdmin(id);
            msg = "Admin Details Deleted";
        } catch (Exception e) {
            msg = "Admin Details not Deleted";
        }
        return msg;
    }
	

	

	
	
    @PostMapping("/RegisterAdmin")
    public ResponseEntity<String> register(@RequestBody Admin admin) {
        try {
            
            if (admin.getEmail() == null || admin.getPassword() == null || admin.getPhoneno() == null || admin.getLocation() == null || admin.getName() == null) {
//                return ResponseEntity.badRequest().body("All fields are required.");
            }

            dao.addadmin(admin);

            return ResponseEntity.ok("Admin registered successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                   .body("Error occurred during registration: " + e.getMessage());
        }
    }

    
    @PostMapping("/LoginAdmin")
    public ResponseEntity<String> login(@RequestBody Admin admin) {
        try {
        
            Optional<Admin> adminOptional = dao.findbyemail(admin.getEmail());
            if (!adminOptional.isPresent()) {
                return ResponseEntity.badRequest().body("Invalid email");
            }

            Admin adminFound = adminOptional.get();

           
            if (!admin.getPassword().equals(adminFound.getPassword())) {
                return ResponseEntity.badRequest().body("Invalid password");
            }
            
            

            return ResponseEntity.ok("login");
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                   .body("Error occurred during login: " + e.getMessage());
        }
    }
//    @PostMapping("/sendEmail/{id}")
//	public ResponseEntity<?> sendAdminEmail(@PathVariable("adminid") Long adminid) {
//	     Optional<Admin> adminOptional = Optional.empty();
//	     if (!adminOptional.isPresent()) {
//	         return ResponseEntity.badRequest().body("Admin not found");
//	     }
//	     Admin admin = adminOptional.get();
//	 
//	     SimpleMailMessage message = new SimpleMailMessage();
//	     message.setFrom("msvarshinee@gmail.com");     
//	     message.setTo(admin.getEmail());
//	     message.setSubject("Registration Successful...!!!");
//	     message.setText("Your Registration has been approved " + admin.getName()+" "+
//	     "You can Login through the following credentials"+" "+ "Username - "+" "
//	    		 +admin.getEmail()+" "+ "Password - "+" "+admin.getPassword());
//	 
//	     emailsender.send(message);
//	 
//	     return ResponseEntity.ok().body("Email sent to " + admin.getEmail());
//	}
    
    @GetMapping("/GetAdminIds")
    public List<Long> getAdminIdlist() {
        try {
            return dao.getAdminIdlist();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}

