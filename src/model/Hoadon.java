package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the hoadon database table.
 * 
 */
@Entity
@Table(name="hoadon")
@NamedQuery(name="Hoadon.findAll", query="SELECT h FROM Hoadon h")
public class Hoadon implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_hd")
	private int idHd;

	private String ngaytao;

	private String tenkh;

	private double tongtien;

	private String trangthai;

	//bi-directional many-to-one association to User_
	@ManyToOne
	@JoinColumn(name="id_user")
	private User_ user;

	//bi-directional many-to-one association to Hoadonct
	@OneToMany(mappedBy="hoadon")
	private List<Hoadonct> hoadoncts;

	public Hoadon() {
	}

	public int getIdHd() {
		return this.idHd;
	}

	public void setIdHd(int idHd) {
		this.idHd = idHd;
	}

	public String getNgaytao() {
		return this.ngaytao;
	}

	public void setNgaytao(String ngaytao) {
		this.ngaytao = ngaytao;
	}

	public String getTenkh() {
		return this.tenkh;
	}

	public void setTenkh(String tenkh) {
		this.tenkh = tenkh;
	}

	public double getTongtien() {
		return this.tongtien;
	}

	public void setTongtien(double tongtien) {
		this.tongtien = tongtien;
	}

	public String getTrangthai() {
		return this.trangthai;
	}

	public void setTrangthai(String trangthai) {
		this.trangthai = trangthai;
	}

	public User_ getUser() {
		return this.user;
	}

	public void setUser(User_ user) {
		this.user = user;
	}

	public List<Hoadonct> getHoadoncts() {
		return this.hoadoncts;
	}

	public void setHoadoncts(List<Hoadonct> hoadoncts) {
		this.hoadoncts = hoadoncts;
	}

	public Hoadonct addHoadonct(Hoadonct hoadonct) {
		getHoadoncts().add(hoadonct);
		hoadonct.setHoadon(this);

		return hoadonct;
	}

	public Hoadonct removeHoadonct(Hoadonct hoadonct) {
		getHoadoncts().remove(hoadonct);
		hoadonct.setHoadon(null);

		return hoadonct;
	}

}