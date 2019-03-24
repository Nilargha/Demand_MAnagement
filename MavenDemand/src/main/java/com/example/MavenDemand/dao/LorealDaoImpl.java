package com.example.MavenDemand.dao;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.MavenDemand.model.Credential;
import com.example.MavenDemand.model.DemandModel;

@Repository
public class LorealDaoImpl {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public Integer getUserDetails(String username) {

		String sql = "SELECT iduser FROM USER WHERE USERNAME = ?";

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
	
	public Integer createDemand(String id,String title ,String desc,String status,int iduser) {

		String sql = "INSERT INTO `loreal`.`demands` (`idDemands`, `DemandTitle`, `DemandDesc`, `Status`, `iduser`) VALUES (?,?,?,?,?)";


		Object[] params = new Object[] { id, title, desc, status,iduser };
		 int row =  jdbcTemplate.update(sql, params);
		return row;

	}
	
	public Integer createDemandUploadDocument(String id,String path) {

		String sql = "UPDATE `loreal`.`demands` set uploadedfilepath='"+path+"' where idDemands='"+id+"'";

System.out.println("----------------------------");
		Object[] params = new Object[] {path};
		 int row =  jdbcTemplate.update(sql);
		 System.out.println("-------------------------"+sql);
		return row;

	}
	
public List<DemandModel> getAllActiveDemands(int iduser) {
		
		String sql="SELECT * FROM loreal.demands where status='Active' and iduser= ?";
		 List<DemandModel> demList = new ArrayList<DemandModel>();
         //List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
         List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, new Object[] {iduser});
        // List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, args, argTypes)
         for (Map<String, Object> row : rows) 
         {
        	 DemandModel dem = new DemandModel();
        	 dem.setId((String)row.get("idDemands"));
        	 dem.setTitle((String)row.get("DemandTitle"));
        	 dem.setDesc((String)row.get("DemandDesc"));
        	 dem.setStatus((String)row.get("Status"));
        	 dem.setIduser((int)row.get("iduser"));
        	 demList.add(dem);
          }

             return demList;
		
		
	}

public List<DemandModel> getAllDemands() {
	
	String sql="SELECT * FROM loreal.demands";
	 List<DemandModel> demList = new ArrayList<DemandModel>();
     //List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
     List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
    // List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, args, argTypes)
     for (Map<String, Object> row : rows) 
     {
    	 DemandModel dem = new DemandModel();
    	 dem.setId((String)row.get("idDemands"));
    	 dem.setTitle((String)row.get("DemandTitle"));
    	 dem.setDesc((String)row.get("DemandDesc"));
    	 dem.setStatus((String)row.get("Status"));
    	 dem.setIduser((int)row.get("iduser"));
    	 demList.add(dem);
      }

         return demList;
	
	
}

public int approveDemand(String id) {

	String sql = "update loreal.demands set status='Approved' where idDemands= ?";

	//jdbcTemplate.queryForObject(sql, new Object[] {id},String.class);
	jdbcTemplate.update(sql,  new Object[] {id});
	return 1;

}

public int rejectDemand(String id) {

	String sql = "update loreal.demands set status='Rejected' where idDemands= ?";

	//jdbcTemplate.queryForObject(sql, new Object[] {id},String.class);
	jdbcTemplate.update(sql,  new Object[] {id});
	return 1;

}

public Integer getUserRole(int id) {

	String sql = "SELECT role FROM USER WHERE iduser = ?";

	Integer role =  jdbcTemplate.queryForObject(sql, new Object[] {id},Integer.class);

	return role;

}

}
