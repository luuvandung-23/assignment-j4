package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import model.Sanpham;
import model.User_;
import utils.JpaUtils;


public class SanPhamDao {
	public List<Sanpham> fillAll(int page,int pageSize,String dm){
		EntityManager em = JpaUtils.getEntityManager();
		TypedQuery<Sanpham> query = em.createNamedQuery("Sanpham.findAllActive", Sanpham.class);
		query.setParameter("id", dm);
		query.setFirstResult(page*pageSize);
		query.setMaxResults(pageSize);
		return query.getResultList();
	}
	public void insert(Sanpham sanpham) {
		EntityManager em = JpaUtils.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.persist(sanpham);
			trans.commit();
		}catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		}finally {
			em.close();
		}
	}
	public void update(Sanpham sanpham) {
		EntityManager em = JpaUtils.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.merge(sanpham);
			trans.commit();
		}catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		}finally {
			em.close();
		}
	}	
	public List<Sanpham> findAll(){
		EntityManager em = JpaUtils.getEntityManager();
		TypedQuery<Sanpham> query = em.createNamedQuery("Sanpham.findAll",Sanpham.class);
		return query.getResultList();
	}
	public Sanpham findById(String id){
		EntityManager em = JpaUtils.getEntityManager();
		Sanpham sp = em.find(Sanpham.class,id);
		return sp;
	}
	public int count(String dm){
		EntityManager em = JpaUtils.getEntityManager();
		String jqpl = "select count(s) from Sanpham s where s.trangthai = 1 and s.danhmuc.idDm like :id";
		Query query = em.createQuery(jqpl);
		query.setParameter("id", dm);
		return ((Long)query.getSingleResult()).intValue();
	}
}
