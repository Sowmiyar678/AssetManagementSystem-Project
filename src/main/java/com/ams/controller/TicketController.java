package com.ams.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.ams.entity.Ticket;
import com.ams.serviceImpl.TicketserviceImpl;
@RestController
@CrossOrigin("http://localhost:3000/")
public class TicketController {
	
	@Autowired
TicketserviceImpl dao;
	

	
		
		@PostMapping("/CreateTicket")
		public  Object addticket(@RequestBody Ticket ticket) {
			String msg="";
			try {
				
				dao.addticket(ticket);
				msg="Ticket Raised";
			}catch(Exception e) {	
				msg="Invalid ticket";
			}
			return msg;
		}
		
		
		
		@GetMapping("/GetAllTicket")
	    public List<Ticket> getAllTicket() {
	        try {
	            return dao.getallTicket();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	 
	        return null;
	    }
		
		
		@PutMapping("/update/{id}")
	    public ResponseEntity<?> updateAdmin(@PathVariable("id") Long id,
	                                          @RequestBody Ticket updatedBill) {
	        return dao.updateBill(id, updatedBill);
	    }
		
		@PutMapping("/updatestatus/{id}")
	    public ResponseEntity<?> updatestatus(@PathVariable("id") Long id,
	                                          @RequestBody Ticket updated) {
	        return dao.updatestatus(id, updated);
	    }
		
		  
		  

		@GetMapping("/GetTicketById/{id}")
	    public Ticket getTicket(@PathVariable Long id) {
	        try {
	            return dao.getTicketById(id);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	 
	        return null;
	    }
		
		@GetMapping("/GetAllIdTicket")
		public List<Ticket> loadFindAllTicket() {
			return (List<Ticket>) dao.findAll();
			}
		
	
		@GetMapping("/getEmployeeByid/{empid}")
		public List<Ticket> getByemployeeId(@PathVariable Long empid){
			 try {
				return dao.getByemployeeId(empid);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		
		@GetMapping("/getITTeamByid/{itteamid}")
		public List<Ticket> getByitteamId(@PathVariable Long itteamid){
			 try {
				return dao.getByitteamId(itteamid);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		
		 
	}

