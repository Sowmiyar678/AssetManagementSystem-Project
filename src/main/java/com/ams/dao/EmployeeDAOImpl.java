package com.ams.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.ams.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class EmployeeDAOImpl implements EmployeeDAO {
	
private EntityManager eman;
	public EmployeeDAOImpl() {
		super();
	}
	
	@Autowired
	public EmployeeDAOImpl(EntityManager entity) {
		super();
		this.eman=entity;
	}
	
	@Override
    public Object addemployee(Employee employee) {
        try {
            eman.persist(employee);
 
        } catch (Exception e) {
            e.printStackTrace();
        }
		return employee;
    }

	 @Override
		public ResponseEntity<?> updateEmployee(long empid, Employee updateEmployee) {
			Employee existingEmployee = eman.find(Employee.class, empid);
					
	        if (existingEmployee == null) {
	            return ResponseEntity.badRequest().body("Employee not found");
	        }
	        
	        // Update
	        existingEmployee.setEmail(updateEmployee.getEmail());
	        existingEmployee.setPassword(updateEmployee.getPassword());
	        existingEmployee.setPhoneno(updateEmployee.getPhoneno());
	        existingEmployee.setLocation(updateEmployee.getLocation());
	        
	        eman.persist(existingEmployee);
	        return ResponseEntity.ok("Details updated successfully");
		}
	
	
	 @SuppressWarnings("unchecked")
		@Override
	    public List<Employee > getEmpById(Long empid) {
	        List<Employee> employee = new ArrayList<>();
	        try {
	            employee = eman.createQuery("from Employee E where E.empid = :empid").setParameter("empid",empid).getResultList();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return employee;
	    }
	 
	 @SuppressWarnings("unchecked")
		@Override
	    public List<Employee> getallEmployee() {
	        List<Employee> employee = new ArrayList<>();
	        try {
	            employee = eman.createQuery("from Employee E").getResultList();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return employee;
	    }
	
	 
	
	@Autowired
    private EntityManager entityManager;

    @Override
    public Optional<Employee> findbyemail(String email) {
        return entityManager.createQuery("SELECT a FROM Employee a WHERE a.email = :email", Employee.class)
              .setParameter("email", email)
              .getResultStream()
              .findFirst();
    }

    
    public void deleteEmp(Long empid) {
        List<Employee> empById = getEmpById(empid);
        eman.remove(empById.get(0));
    }
	

    @Override
	public List<Long> getEmployeeIdlist() {
		Query q = eman.createQuery("SELECT c.empid FROM Employee c");
		@SuppressWarnings("unchecked")
		List<Long> list = q.getResultList();
		return list;
	}

	

	
    

}