package com.luv2code.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springboot.cruddemo.entity.Employee;

@Repository
public class EmployeeDAOHivernateImpl implements EmployeeDAO {

	// define entitymanager
	
	public EntityManager entityManager;
	
	//setup cons inj
	
	@Autowired
	public EmployeeDAOHivernateImpl(EntityManager theEntityManager) {
	
		entityManager=theEntityManager;
	}

	@Override
	public List<Employee> findAll() {

		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// create a query
		Query<Employee> theQuery =
				currentSession.createQuery("from Employee", Employee.class);
		
		// execute query and get result list
		List<Employee> employees = theQuery.getResultList();
		
		// return the results		
		return employees;
	}


	@Override
	public Employee findOne(int theId) {

		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// create a query
		Query<Employee> theQuery =
				currentSession.createQuery("from Employee where id=:theEmployeeId", Employee.class);
		theQuery.setParameter("theEmployeeId", theId);
		
		// execute query and get result list asagidaki ikisi de calisiyor
		// Employee employee = theQuery.uniqueResult();
		Employee employee = theQuery.getSingleResult();
		
		// return the results		
		return employee;
	}

	@Override
	public void save(Employee theEmployee) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		currentSession.saveOrUpdate(theEmployee);
		
	}

	@Override
	public void deleteById(int theId) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		Query theQuery =
				currentSession.createQuery("delete from Employee where id=:theEmployeeId");
		theQuery.setParameter("theEmployeeId", theId);
		
		theQuery.executeUpdate();
	}

}
