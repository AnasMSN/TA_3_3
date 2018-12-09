package com.apap.tugas_akhir_farmasi.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "status_permintaan")
public class StatusPermintaanModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Size(max = 255)
    @Column(name = "nama",nullable = false)
    private String nama;

    @NotNull
    @Size(max = 255)
    @Column(name = "deskripsi",nullable = false)
    private String deskripsi;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getDeskripsi() {
		return deskripsi;
	}

	public void setDeskripsi(String deskripsi) {
		this.deskripsi = deskripsi;
	}
}
