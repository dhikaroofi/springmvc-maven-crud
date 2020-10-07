package com.collega.springmvc.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
	
	private String kota_id;
	private String nama;
	public Validation() {
		
	}
	
	public boolean validateString(String data,int max) {
		if((data.length() < 1) && (data.length() > max)){
			return false;
		}
        String regex = "[a-zA-Z\\s']+";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(data); 
	       
	  
		return  m.matches();
	}
	

	
	public boolean validateDouble(double data) {
		String dataString = String.valueOf(data);
		
		String regex = "[0-9]*\\.?[0-9]{0,2}";
	
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(dataString); 
	       
	  
		return  m.matches();
	
	}
	
	
	
}

