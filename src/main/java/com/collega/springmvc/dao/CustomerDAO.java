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

public class CustomerDAO implements CustomerDAOInterface {
	private JdbcTemplate jdbcTemplate;
	
	public CustomerDAO(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public String save(CustomerModel customerModel) {
		try{
			String sql = "INSERT INTO customer (nama, alamat, pendapatan, kota_id_kota)"
					+ " VALUES (?, ?, ?, ?)";
			jdbcTemplate.update(sql, customerModel.getNama(), customerModel.getAlamat(),
				customerModel.getPendapatan(), customerModel.getKota_id());
			return "";
		} catch(Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	
		
	}

	
	public String update(CustomerModel customerModel) {
		try{
			String sql = "UPDATE customer SET nama=?, alamat=?, pendapatan=?, "
					+ "kota_id_kota=? WHERE cust_id=?";
			jdbcTemplate.update(sql, customerModel.getNama(), customerModel.getAlamat(),
					customerModel.getPendapatan(), customerModel.getKota_id(),customerModel.getCust_id());
			return "";
		} catch(Exception e) {
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
		    	e.printStackTrace();
				return e.getMessage();
		    }
		
	}

	@Override
	public List<CustomerModel> list() {
		String sql = "select customer.*,kota_id_kota,kota.nama as nama_kota from customer inner join kota on customer.kota_id_kota=kota.id_kota";
		List<CustomerModel> listCustomer = jdbcTemplate.query(sql, new RowMapper<CustomerModel>() {

			@Override
			public CustomerModel mapRow(ResultSet rs, int rowNum) throws SQLException {
				CustomerModel cust = new CustomerModel();
				cust.setNama(rs.getString("nama"));
				cust.setCust_id(rs.getString("cust_id"));
				cust.setAlamat(rs.getString("alamat"));
				cust.setPendapatan(rs.getFloat("pendapatan"));
				cust.setKota_id(rs.getString("kota_id_kota"));
				cust.setNama_kota(rs.getString("nama_kota"));
				return cust;
			}
			
		});
		
		return listCustomer;
	}

	@Override
	public CustomerModel get(int cust_id) {
		String sql = "SELECT * FROM contact WHERE contact_id=" + cust_id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<CustomerModel>() {

			@Override
			public CustomerModel extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if (rs.next()) {
					CustomerModel cust = new CustomerModel();
					cust.setCust_id(rs.getString("cust_id"));
					cust.setAlamat(rs.getString("alamat"));
					cust.setPendapatan(rs.getFloat("pendapatan"));
					cust.setKota_id(rs.getString("kota_id_kota"));
					cust.setNama_kota(rs.getString("nama_kota"));
					return cust;
				}
				
				return null;
			}
			
		});
	}
	
}
