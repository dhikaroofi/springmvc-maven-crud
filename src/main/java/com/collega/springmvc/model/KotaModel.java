package com.collega.springmvc.model;

public class KotaModel {

	private String kota_id;
	private String nama;
	public KotaModel() {
		
	}
	
	public KotaModel(String kota_id, String nama) {
	
		this.kota_id = kota_id;
		this.nama = nama;
	}
	
	public String getKota_id() {
		return kota_id;
	}
	public void setKota_id(String kota_id) {
		this.kota_id = kota_id;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	
}
