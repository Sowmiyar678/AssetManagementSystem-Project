package com.ams.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.ams.entity.ITTeam;

@Repository
public interface ITTeamservice {

	public Object additteam(ITTeam itteam);

	Optional<ITTeam> findbyemail(String email);

	List<ITTeam> getITTeamById(Long itteamid);

	List<Long> getITTeamIdlist();

	List<ITTeam> getallITTeam();

	ResponseEntity<?> updateITTeam(long itteamid, ITTeam updateITTeam);

}
