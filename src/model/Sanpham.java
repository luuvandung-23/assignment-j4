package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Sanpham database table.
 * 
 */
@Entity
@NamedQuery(name="Sanpham.findAll", query="SELECT s FROM Sanpham s")
@NamedQuery(name="Sanpham.findAllActive", query="SELECT s FROM Sanpham s where s.trangthai = 1 and s.danhmuc.idDm like :id")
public class Sanpham implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_sp")
	private String idSp;

	private String anh;

	private double gia;

	private int kg;

	private String mieuta;

	private int soluong;

	private String tensp;

	private boolean trangthai;

	//bi-directional many-to-one association to Danhmuc
	@ManyToOne
	@JoinColumn(name="dm_id")
	private Danhmuc danhmuc;

	//bi-directional many-to-one association to Hoadonct
	@OneToMany(mappedBy="sanpham")
	private List<Hoadonct> hoadoncts;

	public Sanpham() {
	}

	public String getIdSp() {
		return this.idSp;
	}

	public void setIdSp(String idSp) {
		this.idSp = idSp;
	}

	public String getAnh() {
		return this.anh;
	}

	public void setAnh(String anh) {
		this.anh = anh;
	}

	public double getGia() {
		return this.gia;
	}

	public void setGia(double gia) {
		this.gia = gia;
	}

	public int getKg() {
		return this.kg;
	}

	public void setKg(int kg) {
		this.kg = kg;
	}

	public String getMieuta() {
		return this.mieuta;
	}

	public void setMieuta(String mieuta) {
		this.mieuta = mieuta;
	}

	public int getSoluong() {
		return this.soluong;
	}

	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}

	public String getTensp() {
		return this.tensp;
	}

	public void setTensp(String tensp) {
		this.tensp = tensp;
	}

	public boolean getTrangthai() {
		return this.trangthai;
	}

	public void setTrangthai(boolean trangthai) {
		this.trangthai = trangthai;
	}

	public Danhmuc getDanhmuc() {
		return this.danhmuc;
	}

	public void setDanhmuc(Danhmuc danhmuc) {
		this.danhmuc = danhmuc;
	}

	public List<Hoadonct> getHoadoncts() {
		return this.hoadoncts;
	}

	public void setHoadoncts(List<Hoadonct> hoadoncts) {
		this.hoadoncts = hoadoncts;
	}

	public Hoadonct addHoadonct(Hoadonct hoadonct) {
		getHoadoncts().add(hoadonct);
		hoadonct.setSanpham(this);

		return hoadonct;
	}

	public Hoadonct removeHoadonct(Hoadonct hoadonct) {
		getHoadoncts().remove(hoadonct);
		hoadonct.setSanpham(null);

		return hoadonct;
	}

}