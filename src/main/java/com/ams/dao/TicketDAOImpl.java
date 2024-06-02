package com.ams.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import com.ams.entity.Ticket;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class TicketDAOImpl implements TicketDAO {

	private EntityManager eman;

	public TicketDAOImpl() {
		super();
	}

	@Autowired
	public TicketDAOImpl(EntityManager entity) {
		super();
		this.eman = entity;
	}

	@Override
	public Object addticket(Ticket ticket) {

		try {
			eman.persist(ticket);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void deleteTicket(Long id) {
		Ticket ticketById = getTicketById(id);
		eman.remove(ticketById);
	}

	public void updateTicketInfo(Ticket ticket) {
		try {
			eman.createQuery("UPDATE Ticket E SET E.status = :status, E.itteam.itteamid = :itteam WHERE E.id= :id")
					.setParameter("id", ticket.getId()).setParameter("status", ticket.getStatus())
					.setParameter("itteam", ticket.getItteam()).executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Ticket getTicketById(Long id) {
		return eman.find(Ticket.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ticket> getallTicket() {
		List<Ticket> ticket = new ArrayList<>();
		try {
			ticket = eman.createQuery("from Ticket E").getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ticket;
	}

	@Override
	public List<Ticket> findAll() {
		Query list = eman.createQuery("SELECT id FROM Ticket ");
		@SuppressWarnings("unchecked")
		List<Ticket> list1 = list.getResultList();
		return list1;
	}

	@Override
	public ResponseEntity<?> updateBill(long ticketId, Ticket updatedTicket) {
		Ticket existingTicket = eman.find(Ticket.class, ticketId);

		if (existingTicket == null) {
			return ResponseEntity.badRequest().body("Ticket not found");
		}

		// Update
		existingTicket.setStatus(updatedTicket.getStatus());
		existingTicket.setItteam(updatedTicket.getItteam());

		eman.persist(existingTicket);
		return ResponseEntity.ok("Details updated successfully");
	}

	@Override
	public ResponseEntity<?> updatestatus(long ticketId, Ticket updatedTicket) {
		Ticket existingTicket = eman.find(Ticket.class, ticketId);

		if (existingTicket == null) {
			return ResponseEntity.badRequest().body("Ticket not found");
		}

		// Update
		existingTicket.setStatus(updatedTicket.getStatus());

		eman.persist(existingTicket);
		return ResponseEntity.ok("Details updated successfully");
	}

	@Override
	public List<Ticket> getByemployeeId(Long empid) {
		try {
			return eman.createQuery("FROM Ticket where employee.empid =:empid", Ticket.class)
					.setParameter("empid", empid).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}

	@Override
	public List<Ticket> getByitteamId(Long itteamid) {
		try {
			return eman.createQuery("FROM Ticket where itteam.itteamid =:itteamid", Ticket.class)
					.setParameter("itteamid", itteamid).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}

}
