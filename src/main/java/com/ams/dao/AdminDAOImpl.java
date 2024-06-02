package com.ams.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ams.entity.Admin;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
@Transactional
@Service
public class AdminDAOImpl implements AdminDAO {
	
private EntityManager eman;
	
	public AdminDAOImpl() {
		super();
	}
	
	@Autowired
	public AdminDAOImpl(EntityManager entity) {
		super();
		this.eman=entity;
	}
	
	@Override
    public Object addadmin(Admin admin) {
        try {
            eman.persist(admin);
 
        } catch (Exception e) {
            e.printStackTrace();
        }
		return admin;
    }
 
    @SuppressWarnings("unchecked")
	@Override
    public List<Admin> getallAdmin() {
        List<Admin> admin = new ArrayList<>();
        try {
            admin = eman.createQuery("from Admin E").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return admin;
    }
 
    @SuppressWarnings("unchecked")
	@Override
    public List<Admin> getAdminById(Long adminid) {
        List<Admin> admin = new ArrayList<>();
        try {
            admin = eman.createQuery("from Admin E where E.adminid = :adminid").setParameter("adminid", adminid).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return admin;
    }
 
 
    public Object updateAdminInfo(Admin admin) {
        try {
            eman.createQuery
            ("UPDATE Admin E SET E.email= :email, E.password = :password, E.location = :location, E.phoneno = :phoneno WHERE E.adminid= :adminid")
            .setParameter("adminid", admin.getAdminid())
            .setParameter("email", admin.getEmail())
            .setParameter("password", admin.getPassword())
            .setParameter("location", admin.getLocation())
            .setParameter("phoneno", admin.getPhoneno())
     
            .executeUpdate();
 
        } catch (Exception e) {
            e.printStackTrace();
        }
		return admin;
    }
 

	public void deleteAdmin(Long id) {
        List<Admin> adminById = getAdminById(id);
        eman.remove(adminById.get(0));
    }
	
	@Autowired
    private EntityManager entityManager;

    @Override
    public Optional<Admin> findbyemail(String email) {
        return entityManager.createQuery("SELECT a FROM Admin a WHERE a.email = :email", Admin.class)
              .setParameter("email", email)
              .getResultStream()
              .findFirst();
    }

	  @Override
	public List<Long> getAdminIdlist() {
		Query q = eman.createQuery("SELECT c.adminid FROM Admin c");
		@SuppressWarnings("unchecked")
		List<Long> list = q.getResultList();
		return list;
	}
	
	
	
	
	
}