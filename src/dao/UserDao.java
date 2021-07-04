package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import model.User_;
import utils.JpaUtils;

public class UserDao {
	public void insert(User_ user) {
		EntityManager em = JpaUtils.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.persist(user);
			trans.commit();
		}catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		}finally {
			em.close();
		}
	}
	public void update(User_ user) {
		EntityManager em = JpaUtils.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.merge(user);
			trans.commit();
		}catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		}finally {
			em.close();
		}
	}
	public void delete(String id) throws Exception {
		EntityManager em = JpaUtils.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			User_ user = em.find(User_.class,id);
			if(user != null) {
				em.remove(user);
			}else {
				throw new Exception("User can not found");
			}
			trans.commit();
		}catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		}finally {
			em.close();
		}
	}
	public User_ findById(String id){
		EntityManager em = JpaUtils.getEntityManager();
		User_ user = em.find(User_.class,id);
		return user;
	}
	public List<User_> findAll(){
		EntityManager em = JpaUtils.getEntityManager();
		TypedQuery<User_> query = em.createNamedQuery("User_.findAll",User_.class);
		return query.getResultList();
	}
	public List<User_> findAll(int page,int pageSize){
		EntityManager em = JpaUtils.getEntityManager();
		TypedQuery<User_> query = em.createNamedQuery("User_.findAll",User_.class);
		query.setFirstResult(page* pageSize);
		query.setMaxResults(pageSize);
		return query.getResultList();
	}
	
}
