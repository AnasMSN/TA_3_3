package com.apap.tugas_akhir_farmasi.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "perencanaan")
public class PerencanaanModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(name = "tanggal",nullable = false)
    private Date tanggal;

    @NotNull
    @Size(max = 255)
    @Column(name = "status", nullable = false)
    private String status;

    @NotNull
    @Column(name = "jumlah",nullable = false)
    private int jumlah;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_medical_supplies" , referencedColumnName = "id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private MedicalSuppliesModel medicalSupplies;


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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public MedicalSuppliesModel getMedicalSupplies() {
        return medicalSupplies;
    }

    public void setMedicalSupplies(MedicalSuppliesModel medicalSupplies) {
        this.medicalSupplies = medicalSupplies;
    }
}
