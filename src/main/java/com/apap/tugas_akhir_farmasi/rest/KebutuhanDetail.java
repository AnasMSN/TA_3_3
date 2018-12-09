package com.apap.tugas_akhir_farmasi.rest;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class KebutuhanDetail {
	/**
	 * Kebutuhan Detail
	 */

    private int id;
	
    private MedicalDetail reagen;
	
    private Date tanggalUpdate;

    private int jumlah;

    private int status;
	
    
	public KebutuhanDetail() {}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public MedicalDetail getReagen() {
		return reagen;
	}
	public void setReagen(MedicalDetail reagen) {
		this.reagen = reagen;
	}
	public Date getTanggalUpdate() {
		return tanggalUpdate;
	}
	public void setTanggalUpdate(Date tanggalUpdate) {
		this.tanggalUpdate = tanggalUpdate;
	}
	public int getJumlah() {
		return jumlah;
	}
	public void setJumlah(int jumlah) {
		this.jumlah = jumlah;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
    
    
    

}




