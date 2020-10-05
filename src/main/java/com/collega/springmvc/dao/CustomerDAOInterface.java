package com.collega.springmvc.dao;

import java.util.List;
import com.collega.springmvc.model.CustomerModel;


public interface CustomerDAOInterface {
	public String save(CustomerModel customerModel);
	
	public String update(CustomerModel customerModel);
	
	public String delete(String cust_id);
	
	public CustomerModel get(int cust_id);
	
	public List<CustomerModel> list();
}
