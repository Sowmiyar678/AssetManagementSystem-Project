package com.ams.dao;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ams.entity.Ticket;

public interface TicketDAO {

	public Object addticket(Ticket ticket);

	List<Ticket> getallTicket();

	Ticket getTicketById(Long id);

	List<Ticket> findAll();

	ResponseEntity<?> updateBill(long id, Ticket updatedTicket);

	List<Ticket> getByemployeeId(Long empid);

	List<Ticket> getByitteamId(Long itteamid);

	ResponseEntity<?> updatestatus(long ticketId, Ticket updatedTicket);

}
