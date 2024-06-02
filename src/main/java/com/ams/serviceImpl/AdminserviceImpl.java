package com.ams.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ams.dao.AdminDAO;
import com.ams.entity.Admin;
import com.ams.service.Adminservice;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AdminserviceImpl implements Adminservice {

	@Autowired
	AdminDAO dao;

	@Override
	public Object addadmin(Admin admin) {
		return dao.addadmin(admin);

	}

	@Override
	public List<Admin> getallAdmin() {

		return dao.getallAdmin();
	}

	@Override
	public List<Admin> getAdminById(Long id) {

		return dao.getAdminById(id);
	}

	@Override
	public Optional<Admin> findbyemail(String email) {

		return dao.findbyemail(email);
	}

	@Override
	public List<Long> getAdminIdlist() {
		return null;
	}

	public void deleteAdmin(Long id) {

	}

	@Override
	public Object updateAdminInfo(Admin admin) {
		dao.updateAdminInfo(admin);
		return null;
	}

}
