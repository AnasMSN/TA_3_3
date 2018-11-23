package com.apap.tugas_akhir_farmasi.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "jenis_medical_supplies")
public class JenisMedicalSuppliesModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(max = 255)
    @Column(name = "jenis_medical_supplies",nullable = false)
    private String jenisMedicalSupplies;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_urgent", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private FlagUrgentModel flagUrgentModel;
    
    
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getJenisMedicalSupplies() {
		return jenisMedicalSupplies;
	}

	public void setJenisMedicalSupplies(String jenisMedicalSupplies) {
		this.jenisMedicalSupplies = jenisMedicalSupplies;
	}

	public FlagUrgentModel getFlagUrgentModel() {
		return flagUrgentModel;
	}

	public void setFlagUrgentModel(FlagUrgentModel flagUrgentModel) {
		this.flagUrgentModel = flagUrgentModel;
	}


	
    
    

}
