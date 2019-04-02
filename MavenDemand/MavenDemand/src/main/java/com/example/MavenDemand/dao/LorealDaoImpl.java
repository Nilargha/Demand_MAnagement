package com.example.MavenDemand.dao;

import java.sql.Types;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;
import java.sql.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.MavenDemand.model.Credential;
import com.example.MavenDemand.model.DemandModel;
import com.example.MavenDemand.model.PhaseModel;
import com.example.MavenDemand.model.StatusModel;
import com.example.MavenDemand.model.Worknotes;
import com.example.MavenDemand.model.ZoneModel;

@Repository
public class LorealDaoImpl {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public String getUserDetails(String email) {

		String sql = "SELECT UserName FROM demand_user WHERE Email = ?";

		String customer =  jdbcTemplate.queryForObject(sql, new Object[] {email},String.class);

		return customer;

	}

	public String getUserPass(String email) {

		String sql = "SELECT Password FROM demand_user WHERE Email = ?";

		String customer =  jdbcTemplate.queryForObject(sql, new Object[] {email},String.class);

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
	
	public Integer createDemand(String id,Date createDate,String createdBy,Date Lastupdatedates,String LastupdatedBy,String status,String phaseId,Date PhaseStartDate,String zoneId,String entity,String projectName,String projectManager,String title,String shortDesc,String longDesc,String demandType,String ManagedServiceRequired,Date deliveryDate,Date goLiveDate,String assignedTo,String assignedTeam) {

		
		System.out.println(status + " "+ phaseId);
		String sql = "INSERT INTO `demand`.`demands` (`DemandID`, `CreateDt`, `CreatedBy`, `LastUpdateDt`, `UpdatedBy`, `Status`, `PhaseID`, `PhaseStartDt`, `ZoneID`, `Entity`, `ProjectName`, `ProjectManager`, `DemandTitle`, `DemandShrDesc`, `DemandLngDesc`, `DemandType`, `MngdServReqd`, `DeliveryDt`, `GoLiveDt`, `AssignedTo`, `AssignedTeam`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";


		Object[] params = new Object[] { id, createDate, createdBy, Lastupdatedates,LastupdatedBy,status,phaseId,PhaseStartDate,zoneId,entity,projectName,projectManager,title,shortDesc,longDesc,demandType,ManagedServiceRequired,deliveryDate,goLiveDate,assignedTo,assignedTeam};
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
	
public List<DemandModel> getAllActiveDemands(String userID) {
		
		String sql="SELECT * FROM demand.demands as dem inner join demand.phase as phase on dem.PhaseID=phase.PhaseID inner join demand.status as status1 on dem.Status=status1.StatusID";
		 List<DemandModel> demList = new ArrayList<DemandModel>();
         //List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
         List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        // List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, args, argTypes)
         for (Map<String, Object> row : rows) 
         {
        	 DemandModel dem = new DemandModel();
        	 /*PhaseModel phasemodel=new PhaseModel();
        	 StatusModel statusmodel=new StatusModel();*/
        	 dem.setId((String)row.get("DemandID"));
        	 dem.setCreateDate((Date)row.get("CreateDt"));
        	 System.out.println((Date)row.get("CreateDt"));
        	 dem.setCreatedBy((String)row.get("CreatedBy"));
        	 System.out.println((String)row.get("CreatedBy"));
        	 dem.setLastupdatedate((Date)row.get("LastUpdateDt"));
        	 dem.setLastUpdatedBy((String)row.get("UpdatedBy"));
        	 dem.setZoneId((String)row.get("ZoneID"));    
        	 dem.setPhaseName((String)row.get("PhaseName")); 
        	 dem.setEntity((String)row.get("Entity"));
        	 dem.setProjectName((String)row.get("ProjectName"));
        	 dem.setProjectManager((String)row.get("ProjectManager"));
        	 dem.setTitle((String)row.get("DemandTitle"));
        	 dem.setShortDesc((String)row.get("DemandShrDesc"));
        	dem.setStatusName((String)row.get("StatusName"));
        	dem.setLongDesc((String)row.get("DemandLngDesc"));
        	 dem.setDemandType((String)row.get("DemandType"));
        	 dem.setManagedServiceRequired((String)row.get("MngdServReqd"));
        	 dem.setDeliveryDate((Date)row.get("DeliveryDt"));
        	 dem.setGoLiveDate((Date)row.get("GoLiveDt"));
        	 dem.setAssignedTo((String)row.get("AssignedTo"));
        	 dem.setAssignedTeam((String)row.get("AssignedTeam"));
        	 dem.setPhaseStartDate((Date)row.get("PhaseStartDt"));
        	 demList.add(dem);
          }

             return demList;
		
		
	}


public List<Worknotes> getAllWorknotes(String DemandID) {
	
	
	String sql="SELECT * FROM demand.work_notes where DemandID= ?";
	List<Worknotes> demList = new ArrayList<Worknotes>();
	 List<Map<String, Object>> rows =jdbcTemplate.queryForList(sql,  new Object[] {DemandID});
	 for (Map<String, Object> row : rows) 
     {
    	 Worknotes dem = new Worknotes();
    	
    	 dem.setWorkNoteID((String)row.get("WorkNoteID"));
    	 dem.setWorkNoteDesc((String)row.get("WorkNoteDesc"));
    	 dem.setCreatedDt((Date)row.get("CreatedDt"));
    	 dem.setCreatedBy((String)row.get("CreatedBy"));
    	 demList.add(dem);
    	
     }
	return demList;

	
	
	
	
}

public List<DemandModel> getAllActiveDemandsForApprover() {
	
	String sql="SELECT * FROM loreal.demands where status='Active'";
	 List<DemandModel> demList = new ArrayList<DemandModel>();
     //List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
     List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
    // List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, args, argTypes)
     for (Map<String, Object> row : rows) 
     {
    	 DemandModel dem = new DemandModel();
    	 /*PhaseModel phasemodel=new PhaseModel();
    	 StatusModel statusmodel=new StatusModel();*/
    	 dem.setId((String)row.get("DemandID"));
    	 dem.setCreateDate((Date)row.get("CreateDt"));
    	 dem.setCreatedBy((String)row.get("CreatedBy"));
    	 dem.setLastupdatedate((Date)row.get("LastUpdateDt"));
    	 dem.setLastUpdatedBy((String)row.get("UpdatedBy"));
    	 dem.setZoneId((String)row.get("ZoneID"));    
    	 dem.setPhaseName((String)row.get("PhaseName")); 
    	 dem.setEntity((String)row.get("Entity"));
    	 dem.setProjectName((String)row.get("ProjectName"));
    	 dem.setProjectManager((String)row.get("ProjectManager"));
    	 dem.setTitle((String)row.get("DemandTitle"));
    	 dem.setShortDesc((String)row.get("DemandShrDesc"));
    	dem.setStatusName((String)row.get("StatusName"));
    	dem.setLongDesc((String)row.get("DemandLngDesc"));
    	 dem.setDemandType((String)row.get("DemandType"));
    	 dem.setManagedServiceRequired((String)row.get("MngdServReqd"));
    	 dem.setDeliveryDate((Date)row.get("DeliveryDt"));
    	 dem.setGoLiveDate((Date)row.get("GoLiveDt"));
    	 dem.setAssignedTo((String)row.get("AssignedTo"));
    	 dem.setAssignedTeam((String)row.get("AssignedTeam"));
    	 dem.setPhaseStartDate((Date)row.get("PhaseStartDt"));
    	 demList.add(dem);
      }

         return demList;
	
	
}

public List<DemandModel> getAllApprovedDemandsForApprover() {
	
	String sql="SELECT * FROM loreal.demands where status='Approved'";
	 List<DemandModel> demList = new ArrayList<DemandModel>();
     //List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
     List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
    // List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, args, argTypes)
     for (Map<String, Object> row : rows) 
     {
    	 DemandModel dem = new DemandModel();
    	 /*PhaseModel phasemodel=new PhaseModel();
    	 StatusModel statusmodel=new StatusModel();*/
    	 dem.setId((String)row.get("DemandID"));
    	 dem.setCreateDate((Date)row.get("CreateDt"));
    	 dem.setCreatedBy((String)row.get("CreatedBy"));
    	 dem.setLastupdatedate((Date)row.get("LastUpdateDt"));
    	 dem.setLastUpdatedBy((String)row.get("UpdatedBy"));
    	 dem.setZoneId((String)row.get("ZoneID"));    
    	 dem.setPhaseName((String)row.get("PhaseName")); 
    	 dem.setEntity((String)row.get("Entity"));
    	 dem.setProjectName((String)row.get("ProjectName"));
    	 dem.setProjectManager((String)row.get("ProjectManager"));
    	 dem.setTitle((String)row.get("DemandTitle"));
    	 dem.setShortDesc((String)row.get("DemandShrDesc"));
    	dem.setStatusName((String)row.get("StatusName"));
    	dem.setLongDesc((String)row.get("DemandLngDesc"));
    	 dem.setDemandType((String)row.get("DemandType"));
    	 dem.setManagedServiceRequired((String)row.get("MngdServReqd"));
    	 dem.setDeliveryDate((Date)row.get("DeliveryDt"));
    	 dem.setGoLiveDate((Date)row.get("GoLiveDt"));
    	 dem.setAssignedTo((String)row.get("AssignedTo"));
    	 dem.setAssignedTeam((String)row.get("AssignedTeam"));
    	 dem.setPhaseStartDate((Date)row.get("PhaseStartDt"));
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
    	 /*PhaseModel phasemodel=new PhaseModel();
    	 StatusModel statusmodel=new StatusModel();*/
    	 dem.setId((String)row.get("DemandID"));
    	 dem.setCreateDate((Date)row.get("CreateDt"));
    	 dem.setCreatedBy((String)row.get("CreatedBy"));
    	 dem.setLastupdatedate((Date)row.get("LastUpdateDt"));
    	 dem.setLastUpdatedBy((String)row.get("UpdatedBy"));
    	 dem.setZoneId((String)row.get("ZoneID"));    
    	 dem.setPhaseName((String)row.get("PhaseName")); 
    	 dem.setEntity((String)row.get("Entity"));
    	 dem.setProjectName((String)row.get("ProjectName"));
    	 dem.setProjectManager((String)row.get("ProjectManager"));
    	 dem.setTitle((String)row.get("DemandTitle"));
    	 dem.setShortDesc((String)row.get("DemandShrDesc"));
    	dem.setStatusName((String)row.get("StatusName"));
    	dem.setLongDesc((String)row.get("DemandLngDesc"));
    	 dem.setDemandType((String)row.get("DemandType"));
    	 dem.setManagedServiceRequired((String)row.get("MngdServReqd"));
    	 dem.setDeliveryDate((Date)row.get("DeliveryDt"));
    	 dem.setGoLiveDate((Date)row.get("GoLiveDt"));
    	 dem.setAssignedTo((String)row.get("AssignedTo"));
    	 dem.setAssignedTeam((String)row.get("AssignedTeam"));
    	 dem.setPhaseStartDate((Date)row.get("PhaseStartDt"));
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

public Integer updateDemand(String id,String title ,String desc){
	
	String sql = "UPDATE `loreal`.`demands` set DemandTitle='"+title+"',DemandDesc='"+desc+"' where idDemands='"+id+"'";
	int row =  jdbcTemplate.update(sql);
	return row;

}

public String getUserRole(String uname) {

	String sql = "SELECT RoleID FROM demand_user WHERE UserName = ?";

	String role =  jdbcTemplate.queryForObject(sql, new Object[] {uname},String.class);

	return role;

}

public List<DemandModel> getUpdateFields(String idDem) {
	
	String sql="SELECT DemandTitle,DemandDesc FROM loreal.demands where idDemands= ?";
	 List<DemandModel> demList = new ArrayList<DemandModel>();
     //List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
     List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, new Object[] {idDem});
    // List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, args, argTypes)
     for (Map<String, Object> row : rows) 
     {
    	 DemandModel dem = new DemandModel();
    	// dem.setId((String)row.get("idDemands"));
    	 dem.setTitle((String)row.get("DemandTitle"));
    	 dem.setShortDesc((String)row.get("DemandDesc"));
    	// dem.setStatus((String)row.get("Status"));
    	// dem.setIduser((int)row.get("iduser"));
    	 demList.add(dem);
      }

         return demList;
	
	
}

public List<ZoneModel> getAllZones() {
	
	String sql="SELECT * FROM demand.zone";
	 List<ZoneModel> demList = new ArrayList<ZoneModel>();
     //List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
     List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
    // List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, args, argTypes)
     for (Map<String, Object> row : rows) 
     {
    	 ZoneModel dem = new ZoneModel();
    	 dem.setZoneID((String)row.get("ZoneID"));
    	 //dem.setZoneName((String)row.get("ZoneName"));
    	
    	 demList.add(dem);
      }

         return demList;
	
	
}

public String getDemandId(){
	
	String sql="SELECT DemandID FROM demand.demands order by DemandID desc LIMIT 1";
	String demand =  jdbcTemplate.queryForObject(sql,String.class);
	return demand;
	
	
}


}
