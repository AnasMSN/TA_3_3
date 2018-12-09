package com.apap.tugas_akhir_farmasi.rest;

public class MedicalDetail {
	
	private int id;
	private String jenis;
	private String nama;
	private int jumlah;
	private String deskripsi;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getJenis() {
		return jenis;
	}
	public void setJenis(String jenis) {
		this.jenis = jenis;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
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

	
}
