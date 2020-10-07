package com.collega.springmvc.model;
import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;  
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
public class CustomerModel {

	private String cust_id;
	
	
	@Size(min=1,max=50)
	@Pattern(regexp="^[a-zA-Z]",message="Invalid nama")  
	private String nama;
	

	@Size(min=1,max=50)
	@Pattern(regexp="^[a-zA-Z0-9]{50}",message="Invalid alamat")   
	private String alamat;
	
	@Pattern(regexp="[0-9]*\\.?[0-9]{0,2}",message="Invalid pendapatan")  
	private double pendapatan;
	
	@NotNull
	@Pattern(regexp="^[a-zA-Z]{3}",message="Invalid kota")  
	private String kota_id;
	
	private String nama_kota;
	private String page;

	public String getPage() {
		return page;
	}

	public String isValid(){
		
		Validation valid = new Validation();
		String msg = "";
		if(!valid.validateString(this.nama,25)){
			msg += "Nama ";
		}
		if(!valid.validateString(this.alamat,50)){
			msg += "Alamat ";
		}
		if(!valid.validateString(this.kota_id,3)){
			msg += "kota ";
		}
		if(!valid.validateDouble(this.pendapatan)){
			msg += "pendapatan ";
		}
		if(msg.length()>0){
			msg += "Invalid";
		}
		
		return msg;
		
	}

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
	public double getPendapatan() {
		return pendapatan;
	}
	public void setPendapatan(double pendapatan) {
		this.pendapatan = pendapatan;
	}
	public String getKota_id() {
		return kota_id;
	}
	public void setKota_id(String kota_id) {
		this.kota_id = kota_id;
	}
	

}
