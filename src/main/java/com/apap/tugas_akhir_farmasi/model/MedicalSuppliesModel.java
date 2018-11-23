package com.apap.tugas_akhir_farmasi.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "medical_supplies")
public class MedicalSuppliesModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(max = 255)
    @Column(name = "nama",nullable = false)
    private String nama;

    @NotNull
    @Column(name = "price",nullable = false)
    private long price;

    @NotNull
    @Column(name = "jumlah",nullable = false)
    private int jumlah;

    @NotNull
    @Size(max = 255)
    @Column(name = "deskripsi",nullable = false)
    private String deskripsi;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_jenis_medical_supplies", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    private JenisMedicalSuppliesModel jenisMedicalSuppliesModel;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
    
    //@JsonProperty
    public JenisMedicalSuppliesModel getJenisMedicalSuppliesModel() {
        return jenisMedicalSuppliesModel;
    }

    public void setJenisMedicalSuppliesModel(JenisMedicalSuppliesModel jenisMedicalSuppliesModel) {
        this.jenisMedicalSuppliesModel = jenisMedicalSuppliesModel;
    }
}
