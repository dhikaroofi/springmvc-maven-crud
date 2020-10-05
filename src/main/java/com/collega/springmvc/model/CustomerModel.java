package com.collega.springmvc.model;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
public class CustomerModel {

	private String cust_id;
	@NotNull 
	@Size(min=1,max=50)
	private String nama;
	@NotNull
	@Size(min=1,max=50)
	private String alamat;
	private Float pendapatan;
	@NotNull
	private String kota_id;
	private String nama_kota;
	

	public CustomerModel() {
	}
	
	public String getNama_kota() {
		return nama_kota;
	}
	public void setNama_kota(String nama_kota) {
		this.nama_kota = nama_kota;
	}
	public CustomerModel(String cust_id, String nama, String alamat, Float pendapatan, String kota_id) {
		
		this.cust_id = cust_id;
		this.nama = nama;
		this.alamat = alamat;
		this.pendapatan = pendapatan;
		this.kota_id = kota_id;
	}
	
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public String getAlamat() {
		return alamat;
	}
	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}
	public Float getPendapatan() {
		return pendapatan;
	}
	public void setPendapatan(Float pendapatan) {
		this.pendapatan = pendapatan;
	}
	public String getKota_id() {
		return kota_id;
	}
	public void setKota_id(String kota_id) {
		this.kota_id = kota_id;
	}
	

}
