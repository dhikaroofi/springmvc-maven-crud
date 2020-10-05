package com.collega.springmvc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.collega.springmvc.model.KotaModel;

public class KotaDAO implements KotaDAOInterface {
	private JdbcTemplate jdbcTemplate;
	
	public KotaDAO(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public String save(KotaModel kotaModel) {
		try{
			String sql = "INSERT INTO kota (id_kota,nama)"
					+ " VALUES (?, ?)";
			jdbcTemplate.update(sql, kotaModel.getKota_id(), kotaModel.getNama());
			return "";
		} catch(Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		
		
		
	}

	
	public String update(KotaModel kotaModel) {
		try{

			String sql = "UPDATE customer SET nama=? "
					+ " WHERE id_kota=?";
			jdbcTemplate.update(sql, kotaModel.getNama(), kotaModel.getKota_id());
			return "";
		} catch(Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		
		
		
	}
	@Override
	public String delete(String id) {
		try{
			String sql = "DELETE FROM kota WHERE id_kota=?";
			jdbcTemplate.update(sql, id);
			return "";
		} catch(Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		
		
	}

	@Override
	public List<KotaModel> list() {
		  try {
			  String sql = "select * from kota";
				List<KotaModel> listKota = jdbcTemplate.query(sql, new RowMapper<KotaModel>() {

					@Override
					public KotaModel mapRow(ResultSet rs, int rowNum) throws SQLException {
						KotaModel kota = new KotaModel();
						kota.setNama(rs.getString("nama"));
						kota.setKota_id(rs.getString("id_kota"));
						
						return kota;
					}
					
				});
				
				return listKota;
		   } catch (Exception e) {
	            System.err.println(" "+e.getMessage());
	        	return null;
	        }
		  
		
	}

	@Override
	public KotaModel get(int id) {
		String sql = "SELECT * FROM kota WHERE kota_id=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<KotaModel>() {

			@Override
			public KotaModel extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if (rs.next()) {
					KotaModel kota = new KotaModel();
					kota.setNama(rs.getString("nama"));
					kota.setKota_id(rs.getString("id_kota"));
					return kota;
				}
				
				return null;
			}
			
		});
	}
}