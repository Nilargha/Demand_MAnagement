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
		String sql = "INSERT INTO `demand`.`demands` (`DemandID`, `CreateDt`, `CreatedBy`, `LastUpdateDt`, `UpdatedBy`, `Status`, `PhaseID`, `PhaseStartDt`, `ZoneID`, `Entity`, `ProjectName`, `ProjectManager`, `DemandTitle`, `DemandShrDesc`, `DemandLngDesc`, `DemandType`, `MngdServReqd`, `DeliveryDt`, `GoLiveDt`, `AssignedTo`, `AssignedTeam`, `autoFlag`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        int i=0;
		Object[] params = new Object[] { id, createDate, createdBy, Lastupdatedates,LastupdatedBy,status,phaseId,PhaseStartDate,zoneId,entity,projectName,projectManager,title,shortDesc,longDesc,demandType,ManagedServiceRequired,deliveryDate,goLiveDate,assignedTo,assignedTeam,i};
		 int row =  jdbcTemplate.update(sql, params);
		return row;

	}
	
	public Integer createDemandUploadDocument(String id,String path,String name,String desc,Date date) {

		String sql = "INSERT INTO demand.demand_files (`FileID`, `File`, `FileDesc`, `CreatedBy`, `CreateDt`, `DemandID`) values (?,?,?,?,?,?)";

System.out.println("----------------------------");
		Object[] params = new Object[] {0,path,desc,name,date,id};
		 int row =  jdbcTemplate.update(sql,params);
		 System.out.println("-------------------------"+sql);
		return row;

	}
	
	
	public Integer postingWorknotes(String desc,String createdby,String demid,Date date) {

		String sql = "insert into demand.work_notes (`WorkNoteID`, `WorkNoteDesc`, `CreatedBy`, `CreatedDt`, `DemandID`) values (?,?,?,?,?)";

System.out.println("----------------------------");
		Object[] params = new Object[] {0,desc,createdby,date,demid};
		 int row =  jdbcTemplate.update(sql,params);
		 System.out.println("-------------------------"+sql);
		return row;

	}
	
public List<DemandModel> getAllActiveDemands(String userID) {
		
		String sql="SELECT * FROM demand.demands as dem inner join demand.phase as phase on dem.PhaseID=phase.PhaseID inner join demand.status as status1 on dem.Status=status1.StatusID where CreatedBy = ?";
		 List<DemandModel> demList = new ArrayList<DemandModel>();
         //List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
         List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql,new Object[] {userID});
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

public List<DemandModel> getAllAcceptedDemands(String userID) {
	
	String sql="SELECT * FROM demand.demands as dem inner join demand.phase as phase on dem.PhaseID=phase.PhaseID inner join demand.status as status1 on dem.Status=status1.StatusID where dem.AssignedTo = ? and dem.AssignedTeam ='TPDL' and dem.Status ='AC' and dem.PhaseID = 'DQ' ";
	 List<DemandModel> demList = new ArrayList<DemandModel>();
     //List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
     List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql,new Object[] {userID});
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

public List<DemandModel> getAllAssignedELDemands(String userID) {
	
	String sql="SELECT * FROM demand.demands as dem inner join demand.phase as phase on dem.PhaseID=phase.PhaseID inner join demand.status as status1 on dem.Status=status1.StatusID inner join demand.demand_sla as sla on dem.DemandID=sla.DemandID where dem.Status = 'QL' and dem.PhaseID='EL' and sla.PhaseID='EL' and dem.AssignedTo = ? and dem.AssignedTeam ='TPDL'";
	 List<DemandModel> demList = new ArrayList<DemandModel>();
     //List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
     List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql,new Object[] {userID});
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
    	 dem.setSLAID((int)row.get("SLAID"));
    	 dem.setSlacreatedate((Date)row.get("SLACreateDt"));
    	 dem.setPendingStrDt((Date)row.get("PendingStrDt"));
    	 dem.setPendingStpDt((Date)row.get("PendingStpDt"));
    	 dem.setPhaseElapsedTime((int)row.get("PhaseElapsedTime"));
    	 demList.add(dem);
      }

         return demList;
	
	
}


public List<DemandModel> getAllELDemandsForCCOE() {
	
	String sql="SELECT * FROM demand.demands as dem inner join demand.phase as phase on dem.PhaseID=phase.PhaseID inner join demand.status as status1 on dem.Status=status1.StatusID inner join demand.demand_sla as sla on dem.DemandID=sla.DemandID where dem.Status = 'QL' and dem.PhaseID='EL' and sla.PhaseID='EL'";
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
    	 dem.setSLAID((int)row.get("SLAID"));
    	 dem.setSlacreatedate((Date)row.get("SLACreateDt"));
    	 dem.setPendingStrDt((Date)row.get("PendingStrDt"));
    	 dem.setPendingStpDt((Date)row.get("PendingStpDt"));
    	 dem.setPhaseElapsedTime((int)row.get("PhaseElapsedTime"));
    	 demList.add(dem);
      }

         return demList;
	
	
}



public List<Worknotes> getAllWorknotes(String DemandID) {
	
	
	String sql="SELECT * FROM demand.work_notes where DemandID= ? order by WorkNoteID desc";
	List<Worknotes> demList = new ArrayList<Worknotes>();
	 List<Map<String, Object>> rows =jdbcTemplate.queryForList(sql,  new Object[] {DemandID});
	 for (Map<String, Object> row : rows) 
     {
    	 Worknotes dem = new Worknotes();
    	
    	 //dem.setWorkNoteID((String)row.get("WorkNoteID"));
    	 dem.setWorkNoteDesc((String)row.get("WorkNoteDesc"));
    	 dem.setCreatedDt((Date)row.get("CreatedDt"));
    	 dem.setCreatedBy((String)row.get("CreatedBy"));
    	 demList.add(dem);
    	
     }
	return demList;

	
	
	
	
}

public List<DemandModel> getAllActiveDemandsForApprover() {
	
	//String sql="SELECT * FROM demand.demands where Status='AK'";
	
	String sql="SELECT * FROM demand.demands as dem inner join demand.phase as phase on dem.PhaseID=phase.PhaseID inner join demand.status as status1 on dem.Status=status1.StatusID where dem.Status = 'AK' and dem.PhaseID='DI'";
	
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

public List<DemandModel> getAllAcceptedDemandsforCCOE() {
	
	//String sql="SELECT * FROM demand.demands where Status='AK'";
	
	String sql="SELECT * FROM demand.demands as dem inner join demand.phase as phase on dem.PhaseID=phase.PhaseID inner join demand.status as status1 on dem.Status=status1.StatusID where dem.Status = 'AC' and dem.PhaseID='DQ'";
	
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

	String sql = "update demand.demands set Status='N' where DemandID= ?";

	//jdbcTemplate.queryForObject(sql, new Object[] {id},String.class);
	jdbcTemplate.update(sql,  new Object[] {id});
	return 1;

}


public int updateDemWithAssgnie(String demid,String status,String phaseid,String assignedto,String updatedby,Date lastupdatedate,String assignedteam) {

	String sql = "UPDATE demand.demands set Status='"+status+"',PhaseID='"+phaseid+"',AssignedTo='"+assignedto+"',AssignedTeam='"+assignedteam+"',LastUpdateDt='"+lastupdatedate+"',UpdatedBy='"+updatedby+"' where DemandID='"+demid+"'";

	//jdbcTemplate.queryForObject(sql, new Object[] {id},String.class);
	jdbcTemplate.update(sql);
	return 1;

}

public int updateDemToEL(String demid,String status,String phaseid,String assignedto,String updatedby,Date lastupdatedate,String assignedteam) {

	String sql = "UPDATE demand.demands set Status='"+status+"',PhaseID='"+phaseid+"',AssignedTo='"+assignedto+"',AssignedTeam='"+assignedteam+"',LastUpdateDt='"+lastupdatedate+"',UpdatedBy='"+updatedby+"' where DemandID='"+demid+"'";

	//jdbcTemplate.queryForObject(sql, new Object[] {id},String.class);
	jdbcTemplate.update(sql);
	String sql2="INSERT INTO demand.demand_sla (`SLAID`, `PhaseID`, `DemandID`, `SLACreateDt`, `PendingStrDt`, `PendingStpDt` , `PhaseElapsedTime`) values (?,?,?,?,?,?,?)";
	Object[] params = new Object[] {0,phaseid,demid,lastupdatedate,null,null,0};
	 int row =  jdbcTemplate.update(sql2,params);
	return 1;

}


public int updateDemToISR(String demid,String status,String phaseid,String assignedto,String updatedby,Date lastupdatedate,String assignedteam) {

	String sql = "UPDATE demand.demands set Status='"+status+"',PhaseID='"+phaseid+"',AssignedTo='"+assignedto+"',AssignedTeam='"+assignedteam+"',LastUpdateDt='"+lastupdatedate+"',UpdatedBy='"+updatedby+"' where DemandID='"+demid+"'";

	//jdbcTemplate.queryForObject(sql, new Object[] {id},String.class);
	jdbcTemplate.update(sql);
	String sql2="INSERT INTO demand.demand_sla (`SLAID`, `PhaseID`, `DemandID`, `SLACreateDt`, `PendingStrDt`, `PendingStpDt` , `PhaseElapsedTime`) values (?,?,?,?,?,?,?)";
	Object[] params = new Object[] {0,phaseid,demid,lastupdatedate,null,null,0};
	 int row =  jdbcTemplate.update(sql2,params);
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

public int updateSLAPendingStart(String idDem,String username,Date date) {
	
	String sql2="Select SLACreateDt from demand.demand_sla where DemandID='"+idDem+"' and PhaseID = 'EL'";
	Date date1=jdbcTemplate.queryForObject(sql2,Date.class);
	
	java.util.Date utilDate = new java.util.Date(date.getTime());
	java.util.Date utilDate1 = new java.util.Date(date1.getTime());
	
	long sal = utilDate.getTime() - utilDate1.getTime();
	long days =  sal / (24 * 60 * 60 * 1000);
	int elapsedTime = (int) days;
	String sql = "UPDATE `demand`.`demand_sla` set PendingStrDt='"+date+"' , PhaseElapsedTime='"+elapsedTime+"' where DemandID='"+idDem+"'";
	int row =  jdbcTemplate.update(sql);
	 String sql1="UPDATE `demand`.`demands` set UpdatedBy='"+username+"' where DemandID='"+idDem+"'";
	 int row1 =  jdbcTemplate.update(sql1);
	return row1;
}



public int updateSLAPendingStartforISR(String idDem,String username,Date date) {
	
	String sql2="Select SLACreateDt from demand.demand_sla where DemandID='"+idDem+"' and PhaseID = 'ISR'";
	Date date1=jdbcTemplate.queryForObject(sql2,Date.class);
	
	java.util.Date utilDate = new java.util.Date(date.getTime());
	java.util.Date utilDate1 = new java.util.Date(date1.getTime());
	
	long sal = utilDate.getTime() - utilDate1.getTime();
	long days =  sal / (24 * 60 * 60 * 1000);
	int elapsedTime = (int) days;
	String sql = "UPDATE `demand`.`demand_sla` set PendingStrDt='"+date+"' , PhaseElapsedTime='"+elapsedTime+"' where DemandID='"+idDem+"'";
	int row =  jdbcTemplate.update(sql);
	 String sql1="UPDATE `demand`.`demands` set UpdatedBy='"+username+"' where DemandID='"+idDem+"'";
	 int row1 =  jdbcTemplate.update(sql1);
	return 1;
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

public List<Credential> getAllTpdl() {
	
	String sql="SELECT UserName FROM demand.demand_user where RoleID='4'";
	 List<Credential> demList = new ArrayList<Credential>();
     //List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
     List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
    // List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, args, argTypes)
     for (Map<String, Object> row : rows) 
     {
    	 Credential dem = new Credential();
    	 dem.setName((String)row.get("UserName"));
    	 //dem.setZoneName((String)row.get("ZoneName"));
    	
    	 demList.add(dem);
      }

         return demList;
	
	
}



public String getDemandId(){
	String demand ="";

	
	String sql="SELECT DemandID FROM demand.demands order by autoFlag desc LIMIT 1";
	
	try {
		
		demand=jdbcTemplate.queryForObject(sql,String.class);
	} catch (Exception e) {
		 demand =  "DEM0";
	}
	
	
	return demand;
	
	
}


}
