package com.apap.tugas_akhir_farmasi.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Entity
@Table(name = "jadwal_jaga")
public class JadwalJagaModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(name = "tanggal",nullable = false)
    private Date tanggal;

    @NotNull
    @Column(name = "waktu_mulai",nullable = false)
    private Time waktuMulai;

    @NotNull
    @Column(name = "wkatu_selesai",nullable = false)
    private Time waktuSelesai;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_staff",referencedColumnName = "id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private StaffModel staffModels;


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


	public Time getWaktuMulai() {
		return waktuMulai;
	}


	public void setWaktuMulai(Time waktuMulai) {
		this.waktuMulai = waktuMulai;
	}


	public Time getWaktuSelesai() {
		return waktuSelesai;
	}


	public void setWaktuSelesai(Time waktuSelesai) {
		this.waktuSelesai = waktuSelesai;
	}


	public StaffModel getStaffModels() {
		return staffModels;
	}


	public void setStaffModels(StaffModel staffModels) {
		this.staffModels = staffModels;
	}
    
    
}
