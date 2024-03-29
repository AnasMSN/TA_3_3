package com.apap.tugas_akhir_farmasi.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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
    @Column(name = "waktu_selesai",nullable = false)
    private Time waktuSelesai;

    @NotNull
    @Column(name = "id_staff",nullable = false)
    private int idStaff;


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

	public String mulai(){
	    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
	    return waktuMulai.toLocalTime().format(dateTimeFormatter);
    }

    public String selesai(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        return waktuSelesai.toLocalTime().format(dateTimeFormatter);
    }


	public void setWaktuSelesai(Time waktuSelesai) {
		this.waktuSelesai = waktuSelesai;
	}


	public int getIdStaff() {
		return idStaff;
	}


	public void setIdStaff(int idStaff) {
		this.idStaff = idStaff;
	}

	public void setWaktuJaga(String mulaiText , String selesaiText){
        LocalTime mulaiLocal = LocalTime.parse(mulaiText);
        LocalTime selesaiLocal = LocalTime.parse(selesaiText);




        Time mulai = Time.valueOf(mulaiLocal);
        Time selesai = Time.valueOf(selesaiLocal);

        setWaktuMulai(mulai);
        setWaktuSelesai(selesai);


    }



}
