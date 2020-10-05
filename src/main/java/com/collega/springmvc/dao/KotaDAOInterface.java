package com.collega.springmvc.dao;

import java.util.List;

import com.collega.springmvc.model.KotaModel;

public interface KotaDAOInterface {
	
		public String save(KotaModel kotaModel);
		
		public String update(KotaModel kotaModel);
		
		public String delete(String id);
		
		public KotaModel get(int id);
		
		public List<KotaModel> list();


}
