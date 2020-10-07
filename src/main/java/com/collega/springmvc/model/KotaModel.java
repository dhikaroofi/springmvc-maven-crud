package com.collega.springmvc.model;
import javax.validation.constraints.Pattern;  

public class KotaModel {
	@Pattern(regexp="^[a-zA-Z0-9]{3}",message="length must be 3")  
	private String kota_id;
	
	@Pattern(regexp="^[a-zA-Z0-9]{50}",message="length must be 50")  
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
