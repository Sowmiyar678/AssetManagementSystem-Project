package com.ams.dao;


import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.ams.entity.ITTeam;

public interface ITTeamDAO  {
	
	
	public Object additteam(ITTeam itteam);
	
	 Optional<ITTeam> findbyemail(String email);
	
	  List<ITTeam> getITTeamById(Long itteamid);
	  
	  List<Long> getITTeamIdlist();
	  
	  List<ITTeam> getallITTeam();

	ResponseEntity<?> updateITTeam(long itteamid, ITTeam updateITTeam);
}