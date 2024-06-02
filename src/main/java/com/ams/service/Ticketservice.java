package com.ams.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.ams.entity.Ticket;

@Repository
public interface Ticketservice {

	public Object addticket(Ticket ticket);

	List<Ticket> getallTicket();

	Ticket getTicketById(Long id);

	List<Ticket> findAll();

	ResponseEntity<?> updateBill(long id, Ticket updatedTicket);

	ResponseEntity<?> updatestatus(long ticketId, Ticket updatedTicket);

	List<Ticket> getByemployeeId(Long empid);

	List<Ticket> getByitteamId(Long itteamid);
}
