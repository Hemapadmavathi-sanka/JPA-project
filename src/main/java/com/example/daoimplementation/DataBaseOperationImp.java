package com.example.daoimplementation;
import java.util.List;
import com.example.Entity.Employee;
import com.example.dao.DataBaseOperation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class DataBaseOperationImp implements DataBaseOperation{
	
	private static EntityManagerFactory emf;
	Employee emp=null;
	static {
		try {
			 emf = Persistence.createEntityManagerFactory("employeePU");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public int save(Employee emp) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
		em.persist(emp);
		tx.commit();
		}catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
		}
		finally {
			em.close();
		}
		if(emp.getId()<=0) 
			return 0;
		else
			return 1;
	}
	 public boolean findloginData(String name,String password) {
		 EntityManager em = emf.createEntityManager();

	        try {
	            TypedQuery<Employee> q = em.createNamedQuery("Employee.login", Employee.class);

	            q.setParameter("name", name);
	            q.setParameter("password", password);

	            Employee emp = q.getSingleResult();  
	            return emp != null;

	        } catch (NoResultException e) {
	            return false;
	        } finally {
	            em.close();
	        }
	   
	    }
	 public List<Employee> findAll(){
		 EntityManager em = emf.createEntityManager();

	        List<Employee> list =  em.createNamedQuery("Employee.findAll", Employee.class)
	                .getResultList();

	        em.close();
	        return list;
	    }
	 public boolean updateEmployeeData(Employee emp) {
		 EntityManager em = emf.createEntityManager();
	        EntityTransaction tx = em.getTransaction();

	        try {
	            tx.begin();
	            int count = em.createNamedQuery("Employee.updateById")
	                    .setParameter("id", emp.getId())
	                    .setParameter("name", emp.getName())
	                    .setParameter("mail", emp.getMail())
	                    .setParameter("phone", emp.getPhonenumber())
	                    .setParameter("role", emp.getRole())
	                    .setParameter("company", emp.getCompany())
	                    .setParameter("password", emp.getPassword())
	                    .executeUpdate();  
	            tx.commit();
	        } catch (Exception e) {
	            tx.rollback();
	            e.printStackTrace();
	        } finally {
	            em.close();
	        }
			return true;
	 }
	 public boolean deleteEmployeeData(Employee emp) {
		 EntityManager em = emf.createEntityManager();
	        EntityTransaction tx = em.getTransaction();

	        try {
	            tx.begin();
	            int count = em.createNamedQuery("Employee.deleteById")
	                      .setParameter("id", emp.getId())
	                      .executeUpdate();
	            
	            tx.commit();
	        } catch (Exception e) {
	            tx.rollback();
	            e.printStackTrace();
	        } finally {
	            em.close();
	        }
			return true;
		  
	 }
	 
}
