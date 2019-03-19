package com.example.MavenDemand.dao;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.MavenDemand.model.Credential;

@Repository
public class LorealDaoImpl {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public Integer getUserDetails(String username) {

		String sql = "SELECT ID FROM USER WHERE USERNAME = ?";

		Integer customer =  jdbcTemplate.queryForObject(sql, new Object[] {username},Integer.class);

		return customer;

	}

	public String getUserPass(String username) {

		String sql = "SELECT PASS FROM USER WHERE USERNAME = ?";

		String customer =  jdbcTemplate.queryForObject(sql, new Object[] {username},String.class);

		return customer;

	}

	public List<Credential> getAllUserDetails() {
		
		String sql="SELECT * FROM USER";
		 List<Credential> credList = new ArrayList<Credential>();
         List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
         
         for (Map<String, Object> row : rows) 
         {
        	 Credential cred = new Credential();
        	 cred.setName((String)row.get("username"));
        	 cred.setPass((String)row.get("pass"));
              
        	 credList.add(cred);
          }

             return credList;
		
		
	}
	
	public Integer createDemand(int id,String title ,String desc,String status) {

		String sql = "INSERT INTO `loreal`.`demands` (`idDemands`, `DemandTitle`, `DemandDesc`, `Status`) VALUES (?,?,?,?)";


		Object[] params = new Object[] { id, title, desc, status };
		int[] types = new int[] { Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };
		 int row =  jdbcTemplate.update(sql, params, types);
		return row;

	}

}
