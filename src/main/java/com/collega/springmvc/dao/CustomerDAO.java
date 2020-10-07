package com.collega.springmvc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.collega.springmvc.model.CustomerModel;
import com.collega.springmvc.model.KotaModel;



public class CustomerDAO implements CustomerDAOInterface {
	private JdbcTemplate jdbcTemplate;
	
	public CustomerDAO(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public String save(CustomerModel customerModel) {
		try{
			String isValid = customerModel.isValid();
			if(isValid!=""){
				throw new SQLException(isValid);
			}
			String sql = "INSERT INTO customer (nama, alamat, pendapatan, kota_id_kota)"
					+ " VALUES (?, ?, ?, ?)";
			jdbcTemplate.update(sql, customerModel.getNama(), customerModel.getAlamat(),
				customerModel.getPendapatan(), customerModel.getKota_id());
			return "";
		} catch(SQLException e) {
			e.printStackTrace();
			return e.getMessage();
		}
	
		
	}

	
	public String update(CustomerModel customerModel) {
		try{
			String isValid = customerModel.isValid();
			if(isValid!=""){
				throw new SQLException(isValid);
			}
			String sql = "UPDATE customer SET nama=?, alamat=?, pendapatan=?, "
					+ "kota_id_kota=? WHERE cust_id=?";
			jdbcTemplate.update(sql, customerModel.getNama(), customerModel.getAlamat(),
					customerModel.getPendapatan(), customerModel.getKota_id(),customerModel.getCust_id());
			return "";
		} catch(SQLException e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	@Override
	public String delete(String cust_id) {
		   try {
			   String sql = "DELETE FROM customer WHERE cust_id=?";
				jdbcTemplate.update(sql, cust_id);
				return "";
		    } catch(Exception e) {
		    	
				return e.getMessage();
		    }
		
	}

	@Override
	public List<CustomerModel> list(int page) {
		if(page > 0){
			page = page * 10;
			Object[] params = new Object[] {page};
			String sql = "select customer.*,kota_id_kota,kota.nama as nama_kota from customer inner join kota on customer.kota_id_kota=kota.id_kota LIMIT ?,10";
			List<CustomerModel> listCustomer = jdbcTemplate.query(
				sql,params, new RowMapper<CustomerModel>() {

					@Override
					public CustomerModel mapRow(ResultSet rs, int rowNum) throws SQLException {
						CustomerModel cust = new CustomerModel();
						cust.setNama(rs.getString("nama"));
						cust.setCust_id(rs.getString("cust_id"));
						cust.setAlamat(rs.getString("alamat"));
						cust.setPendapatan(rs.getDouble("pendapatan"));
						cust.setKota_id(rs.getString("kota_id_kota"));
						cust.setNama_kota(rs.getString("nama_kota"));
						return cust;
					}
				
			});
			return listCustomer;
		}else{
			String sql = "select customer.*,kota_id_kota,kota.nama as nama_kota from customer inner join kota on customer.kota_id_kota=kota.id_kota";
			List<CustomerModel> listCustomer = jdbcTemplate.query(
				sql, new RowMapper<CustomerModel>() {

					@Override
					public CustomerModel mapRow(ResultSet rs, int rowNum) throws SQLException {
						CustomerModel cust = new CustomerModel();
						cust.setNama(rs.getString("nama"));
						cust.setCust_id(rs.getString("cust_id"));
						cust.setAlamat(rs.getString("alamat"));
						cust.setPendapatan(rs.getDouble("pendapatan"));
						cust.setKota_id(rs.getString("kota_id_kota"));
						cust.setNama_kota(rs.getString("nama_kota"));
						return cust;
					}
				
			});
			return listCustomer;
		}
	
		
		
	
	}

	@Override
	public String getTotalData(int active) {
		String page = "";
		String sql = "select count(*) from customer";
		
		Integer total = jdbcTemplate.queryForObject(sql, Integer.class);
		if (total == null){
			total = 0;
		}
		
		for(int i = 0;i<=(int)(total/10);i++){
			if(i == active){
				 page += "<li class=\"page-item active\" aria-current=\"page\"><span class=\"page-link\">"+i+"<span class=\"sr-only\">(current)</span></span></li>";
			}else{
				page +=   "<li class=\"page-item\"><a class=\"page-link\" href=\"?page="+i+"\">"+i+"</a></li>";
							
			}
		}
		return page;
	}
	
}
