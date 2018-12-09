package com.apap.tugas_akhir_farmasi.rest;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class KebutuhanDetail {
	/**
	 * Kebutuhan Detail
	 */
	@JsonProperty("id")
    private int id;
	
	@JsonProperty("reagen")
    private MedicalDetail reagen;
	
	@JsonProperty("tanggalUpdate")
    private Date tanggalUpdate;
	
	@JsonProperty("jumlah")
    private int jumlah;
	
	@JsonProperty("status")
    private int status;
    
    
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




