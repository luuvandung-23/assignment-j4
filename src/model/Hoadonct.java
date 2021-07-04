package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the hoadonct database table.
 * 
 */
@Entity
@Table(name="hoadonct")
@NamedQuery(name="Hoadonct.findAll", query="SELECT h FROM Hoadonct h")
public class Hoadonct implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private HoadonctPK id;

	private double gia;

	private int soluong;

	private double thanhtien;

	//bi-directional many-to-one association to Hoadon
	@ManyToOne
	@JoinColumn(name="id_hd",insertable=false, updatable=false)
	private Hoadon hoadon;

	//bi-directional many-to-one association to Sanpham
	@ManyToOne
	@JoinColumn(name="id_sp",insertable=false, updatable=false)
	private Sanpham sanpham;

	public Hoadonct() {
	}

	public HoadonctPK getId() {
		return this.id;
	}

	public void setId(HoadonctPK id) {
		this.id = id;
	}

	public double getGia() {
		return this.gia;
	}

	public void setGia(double gia) {
		this.gia = gia;
	}

	public int getSoluong() {
		return this.soluong;
	}

	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}

	public double getThanhtien() {
		return this.thanhtien;
	}

	public void setThanhtien(double thanhtien) {
		this.thanhtien = thanhtien;
	}

	public Hoadon getHoadon() {
		return this.hoadon;
	}

	public void setHoadon(Hoadon hoadon) {
		this.hoadon = hoadon;
	}

	public Sanpham getSanpham() {
		return this.sanpham;
	}

	public void setSanpham(Sanpham sanpham) {
		this.sanpham = sanpham;
	}

}