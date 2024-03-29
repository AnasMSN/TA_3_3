package com.apap.tugas_akhir_farmasi.model;


import java.io.Serializable;
import java.util.List;

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
    
    @OneToMany(mappedBy = "flagUrgentModel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<JenisMedicalSuppliesModel> jenisMedList;


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
