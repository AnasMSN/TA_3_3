package com.apap.tugas_akhir_farmasi.model;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "flag_urgent")
public class FlagUrgentModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Column(name = "flag",nullable = false)
    private short flag;

    @NotNull
    @Size(max = 255)
    @Column(name = "deskripsi_flag_urgent")
    private String deskripsiFlagUrgent;
    

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public short getFlag() {
		return flag;
	}

	public void setFlag(short flag) {
		this.flag = flag;
	}

	public String getDeskripsiFlagUrgent() {
		return deskripsiFlagUrgent;
	}

	public void setDeskripsiFlagUrgent(String deskripsiFlagUrgent) {
		this.deskripsiFlagUrgent = deskripsiFlagUrgent;
	}
    
    
}
