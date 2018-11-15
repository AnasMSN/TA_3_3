package com.apap.tugas_akhir_farmasi.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Time;
import java.util.List;

@Entity
@Table(name = "perencanaan")
public class PerencanaanModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(name = "tanggal",nullable = false)
    private Time tanggal;

    @NotNull
    @Size(max = 255)
    @Column(name = "status", nullable = false)
    private String status;

    @NotNull
    @Column(name = "jumlah",nullable = false)
    private int jumlah;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_medical_supplies" , referencedColumnName = "id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<MedicalSuppliesModel> medicalSupplies;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Time getTanggal() {
        return tanggal;
    }

    public void setTanggal(Time tanggal) {
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

    public List<MedicalSuppliesModel> getMedicalSupplies() {
        return medicalSupplies;
    }

    public void setMedicalSupplies(List<MedicalSuppliesModel> medicalSupplies) {
        this.medicalSupplies = medicalSupplies;
    }
}
