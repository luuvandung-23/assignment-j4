package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Danhmuc database table.
 * 
 */
@Entity
@NamedQuery(name="Danhmuc.findAll", query="SELECT d FROM Danhmuc d")
public class Danhmuc implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_dm")
	private String idDm;

	private String tendm;

	//bi-directional many-to-one association to Sanpham
	@OneToMany(mappedBy="danhmuc")
	private List<Sanpham> sanphams;

	public Danhmuc() {
	}

	public String getIdDm() {
		return this.idDm;
	}

	public void setIdDm(String idDm) {
		this.idDm = idDm;
	}

	public String getTendm() {
		return this.tendm;
	}

	public void setTendm(String tendm) {
		this.tendm = tendm;
	}

	public List<Sanpham> getSanphams() {
		return this.sanphams;
	}

	public void setSanphams(List<Sanpham> sanphams) {
		this.sanphams = sanphams;
	}

	public Sanpham addSanpham(Sanpham sanpham) {
		getSanphams().add(sanpham);
		sanpham.setDanhmuc(this);

		return sanpham;
	}

	public Sanpham removeSanpham(Sanpham sanpham) {
		getSanphams().remove(sanpham);
		sanpham.setDanhmuc(null);

		return sanpham;
	}

}