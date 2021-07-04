package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the hoadonct database table.
 * 
 */
@Embeddable
public class HoadonctPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_hd", insertable=false, updatable=false)
	private int idHd;

	@Column(name="id_sp", insertable=false, updatable=false)
	private String idSp;

	public HoadonctPK() {
	}
	public int getIdHd() {
		return this.idHd;
	}
	public void setIdHd(int idHd) {
		this.idHd = idHd;
	}
	public String getIdSp() {
		return this.idSp;
	}
	public void setIdSp(String idSp) {
		this.idSp = idSp;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof HoadonctPK)) {
			return false;
		}
		HoadonctPK castOther = (HoadonctPK)other;
		return 
			(this.idHd == castOther.idHd)
			&& this.idSp.equals(castOther.idSp);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idHd;
		hash = hash * prime + this.idSp.hashCode();
		
		return hash;
	}
}