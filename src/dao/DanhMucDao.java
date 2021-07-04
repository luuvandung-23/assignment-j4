package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Danhmuc;
import model.Sanpham;
import utils.JpaUtils;

public class DanhMucDao {
	public List<Danhmuc> findAll(){
		EntityManager em = JpaUtils.getEntityManager();
		TypedQuery<Danhmuc> query = em.createNamedQuery("Danhmuc.findAll",Danhmuc.class);
		return query.getResultList();
	}
}
