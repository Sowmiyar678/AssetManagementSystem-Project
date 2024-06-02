package com.ams.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ams.dao.ITTeamDAO;
import com.ams.entity.ITTeam;
import com.ams.service.ITTeamservice;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ITTeamserviceImpl implements ITTeamservice {

	@Autowired
	ITTeamDAO dao;

	@Override
	public Object additteam(ITTeam itteam) {
		return dao.additteam(itteam);

	}

	@Override
	public Optional<ITTeam> findbyemail(String email) {

		return dao.findbyemail(email);
	}

	@Override
	public List<ITTeam> getITTeamById(Long itteamid) {

		return dao.getITTeamById(itteamid);
	}

	@Override
	public List<Long> getITTeamIdlist() {
		return dao.getITTeamIdlist();
	}

	@Override
	public List<ITTeam> getallITTeam() {
		return dao.getallITTeam();
	}

	@Override
	public ResponseEntity<?> updateITTeam(long itteamid, ITTeam updateITTeam) {

		return dao.updateITTeam(itteamid, updateITTeam);
	}

}
