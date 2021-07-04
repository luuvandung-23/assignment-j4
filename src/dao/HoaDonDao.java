package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import model.Hoadon;
import model.Hoadonct;
import model.HoadonctPK;
import utils.JpaUtils;

public class HoaDonDao {
	public Hoadon findByTrangthai(String id, String trangthai){
		EntityManager em = JpaUtils.getEntityManager();
		String jqpl = "select h from Hoadon h where h.user.idUser = :Id and h.trangthai like :trangthai";
		TypedQuery<Hoadon> query = em.createQuery(jqpl,Hoadon.class);
		query.setParameter("Id",id);
		query.setParameter("trangthai",trangthai);
		return query.getSingleResult();
	}
	public List<Hoadonct> findHoadonct(int i){
		EntityManager em = JpaUtils.getEntityManager();
		List<Hoadonct> hd = em.find(Hoadon.class, i).getHoadoncts();
		return hd;
	}
	public Hoadonct findHoadonct(int i,String a){
		EntityManager em = JpaUtils.getEntityManager();
		HoadonctPK hdctpk = new HoadonctPK();
		hdctpk.setIdHd(i);
		hdctpk.setIdSp(a);
		Hoadonct hd = em.find(Hoadonct.class,hdctpk);
		return hd;
	}
	public void inserthdct(Hoadonct hdct) {
		EntityManager em = JpaUtils.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.persist(hdct);
			trans.commit();
		}catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		}finally {
			em.close();
		}
	}
	public void updatehdct(Hoadonct hdct) {
		EntityManager em = JpaUtils.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.merge(hdct);
			trans.commit();
		}catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		}finally {
			em.close();
		}
	}
	public void inserthd(Hoadon hd) {
		EntityManager em = JpaUtils.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.persist(hd);
			trans.commit();
		}catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		}finally {
			em.close();
		}
	}
	public void updatehd(Hoadon hd) {
		EntityManager em = JpaUtils.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.merge(hd);
			trans.commit();
		}catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		}finally {
			em.close();
		}
	}
	public void delete(int i,String a) throws Exception {
		EntityManager em = JpaUtils.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		HoadonctPK hdctpk = new HoadonctPK();
		hdctpk.setIdHd(i);
		hdctpk.setIdSp(a);
		try {
			trans.begin();
			Hoadonct hdct = em.find(Hoadonct.class,hdctpk);
			if(hdct != null) {
				em.remove(hdct);
			}else {
				throw new Exception("Hoa don ct can not found");
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
}
