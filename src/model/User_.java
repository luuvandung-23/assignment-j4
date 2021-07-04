package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the User_s database table.
 * 
 */
@Entity
@Table(name="User_s")
@NamedQuery(name="User_.findAll", query="SELECT u FROM User_ u")
public class User_ implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_user")
	private String idUser;

	@Column(name="admi_n")
	private boolean admiN;

	private String diachi;

	private String email;

	private String fullname;

	@Column(name="passwor_d")
	private String passworD;

	private boolean trangthai;

	//bi-directional many-to-one association to Hoadon
	@OneToMany(mappedBy="user")
	private List<Hoadon> hoadons;

	public User_() {
	}

	public String getIdUser() {
		return this.idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public boolean getAdmiN() {
		return this.admiN;
	}

	public void setAdmiN(boolean admiN) {
		this.admiN = admiN;
	}

	public String getDiachi() {
		return this.diachi;
	}

	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPassworD() {
		return this.passworD;
	}

	public void setPassworD(String passworD) {
		this.passworD = passworD;
	}

	public boolean getTrangthai() {
		return this.trangthai;
	}

	public void setTrangthai(boolean trangthai) {
		this.trangthai = trangthai;
	}

	public List<Hoadon> getHoadons() {
		return this.hoadons;
	}

	public void setHoadons(List<Hoadon> hoadons) {
		this.hoadons = hoadons;
	}

	public Hoadon addHoadon(Hoadon hoadon) {
		getHoadons().add(hoadon);
		hoadon.setUser(this);

		return hoadon;
	}

	public Hoadon removeHoadon(Hoadon hoadon) {
		getHoadons().remove(hoadon);
		hoadon.setUser(null);

		return hoadon;
	}

}