package com.ams.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.ams.entity.ITTeam;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class ITTeamDAOImpl implements ITTeamDAO {
	
private EntityManager eman;
	
	public ITTeamDAOImpl() {
		super();
	}
	
	@Autowired
	public ITTeamDAOImpl(EntityManager entity) {
		super();
		this.eman=entity;
	}
	
	@Override
    public Object additteam(ITTeam itteam) {
        try {
            eman.persist(itteam);
 
        } catch (Exception e) {
            e.printStackTrace();
        }
		return itteam;
    }
	
	 @Override
		public ResponseEntity<?> updateITTeam(long itteamid, ITTeam updateITTeam) {
			ITTeam existingITTeam = eman.find(ITTeam.class, itteamid);
					
	        if (existingITTeam == null) {
	            return ResponseEntity.badRequest().body("ITTeam not found");
	        }
	        
	        // Update
	        existingITTeam.setItteamid(updateITTeam.getItteamid());
	        existingITTeam.setEmail(updateITTeam.getEmail());
	        existingITTeam.setPassword(updateITTeam.getPassword());
	        existingITTeam.setPhoneno(updateITTeam.getPhoneno());
	        existingITTeam.setLocation(updateITTeam.getLocation());
	        
	        eman.persist(existingITTeam);
	        return ResponseEntity.ok("Details updated successfully");
		}
	
	 @SuppressWarnings("unchecked")
	  	@Override
	  	    public List<ITTeam> getITTeamById(Long itteamd) {
	  	        List<ITTeam> itteam = new ArrayList<>();
	  	        try {
	  	            itteam = eman.createQuery("from ITTeam E where E.itteamid = :itteamid").setParameter("itteamid", itteamd).getResultList();
	  	        } catch (Exception e) {
	  	            e.printStackTrace();
	  	        }
	  	        return itteam;
	  	    }
	
	@Autowired
    private EntityManager entityManager;

    @Override
    public Optional<ITTeam> findbyemail(String email) {
        return entityManager.createQuery("SELECT a FROM ITTeam a WHERE a.email = :email", ITTeam.class)
              .setParameter("email", email)
              .getResultStream()
              .findFirst();
    }
    @Override
   	public List<Long> getITTeamIdlist() {
   		Query q = eman.createQuery("SELECT c.itteamid FROM ITTeam c");
   		@SuppressWarnings("unchecked")
		List<Long> list = q.getResultList();
   		return list;
   	}

	 @SuppressWarnings("unchecked")
		@Override
	    public List<ITTeam> getallITTeam() {
	        List<ITTeam> employee = new ArrayList<>();
	        try {
	            employee = eman.createQuery("from ITTeam E").getResultList();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return employee;
	    }

	 public void deleteid(Long itteamid) {
        List<ITTeam> itById = getITTeamById(itteamid);
        eman.remove(itById.get(0));
    }
   
	
}
