package com.apap.tugas_akhir_farmasi.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "permintaan")
public class PermintaanModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(name = "tanggal",nullable = false)
    private Date tanggal;

    @NotNull
    @Column(name = "id_pasien",nullable = false)
    private int idPasien;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_jadwal",referencedColumnName ="id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private JadwalJagaModel jadwalJagaModel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_status_permintaan",referencedColumnName = "id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private StatusPermintaanModel statusPermintaanModel;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_medical_supplies",referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private MedicalSuppliesModel medicalSuppliesModel;


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public Date getTanggal() {
		return tanggal;
	}


	public void setTanggal(Date tanggal) {
		this.tanggal = tanggal;
	}


	public int getIdPasien() {
		return idPasien;
	}


	public void setIdPasien(int idPasien) {
		this.idPasien = idPasien;
	}


	public JadwalJagaModel getJadwalJagaModel() {
		return jadwalJagaModel;
	}


	public void setJadwalJagaModel(JadwalJagaModel jadwalJagaModel) {
		this.jadwalJagaModel = jadwalJagaModel;
	}


	public StatusPermintaanModel getStatusPermintaanModel() {
		return statusPermintaanModel;
	}


	public void setStatusPermintaanModel(StatusPermintaanModel statusPermintaanModel) {
		this.statusPermintaanModel = statusPermintaanModel;
	}


	public MedicalSuppliesModel getMedicalSuppliesModel() {
		return medicalSuppliesModel;
	}


	public void setMedicalSuppliesModel(MedicalSuppliesModel medicalSuppliesModel) {
		this.medicalSuppliesModel = medicalSuppliesModel;
	}
    
    
}
