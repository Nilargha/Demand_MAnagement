package com.example.MavenDemand.model;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class DemandModel {

	


	public DemandModel() {
		// TODO Auto-generated constructor stub
	}

	private String id;
	   private String title;
	   private Date createDate;
	   private String createdBy;
	   private Date lastupdatedate;
	   private String lastUpdatedBy;
	private String statusID;
	   private String phaseID;
		   private Date phaseStartDate;
		   private String zoneId;
		   private String entity;
		   private String projectName;
		   private String projectManager;
		   private String shortDesc;
		   private String longDesc;
		   private String demandType;
		   private String managedServiceRequired;
		   private Date deliveryDate;
		   private Date goLiveDate;
		   private  String PhaseName ;
			private  String StatusName;
		   private String assignedTo;
		   private String assignedTeam;
		   private int SLAID;
		   private int sla;
		   public int getSla() {
			return sla;
		}
		public void setSla(int sla) {
			this.sla = sla;
		}
		public int getSLAID() {
			return SLAID;
		}
		public void setSLAID(int sLAID) {
			SLAID = sLAID;
		}
		public Date getSlacreatedate() {
			return slacreatedate;
		}
		public void setSlacreatedate(Date date) {
			this.slacreatedate = date;
		}
		public Date getPendingStrDt() {
			return PendingStrDt;
		}
		public void setPendingStrDt(Date date) {
			PendingStrDt = date;
		}
		public Date getPendingStpDt() {
			return PendingStpDt;
		}
		public void setPendingStpDt(Date pendingStpDt) {
			PendingStpDt = pendingStpDt;
		}
		public String getPhaseElapsedTime() {
			return String.valueOf(PhaseElapsedTime);
		}
		public void setPhaseElapsedTime(int phaseElapsedTime) {
			PhaseElapsedTime = phaseElapsedTime;
		}

		private Date slacreatedate;
		   private Date PendingStrDt;
		   private Date PendingStpDt;
		   private int PhaseElapsedTime;
		  
		   
		   public String getLastUpdatedBy() {
				return lastUpdatedBy;
			}
			public void setLastUpdatedBy(String lastUpdatedBy) {
				this.lastUpdatedBy = lastUpdatedBy;
			}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public Date getCreateDate() {
			return createDate;
		}
		public void setCreateDate(Date createDate) {
			this.createDate = createDate;
		}
		public String getCreatedBy() {
			return createdBy;
		}
		public void setCreatedBy(String createdBy) {
			this.createdBy = createdBy;
		}
		public Date getLastupdatedate() {
			return lastupdatedate;
		}
		public void setLastupdatedate(Date lastupdatedate) {
			this.lastupdatedate = lastupdatedate;
		}
		public String getStatusID() {
			System.out.println(statusID);
			return statusID;
		}
		public void setStatusID(String tatusID) {
			System.out.println(tatusID);
			this.statusID = tatusID;
		}
		public String getPhaseID() {
			System.out.println(phaseID);
			return phaseID;
		}
		public void setPhaseID(String haseID) {
			System.out.println(haseID);
			this.phaseID = haseID;
		}
		public Date getPhaseStartDate() {
			return phaseStartDate;
		}
		public void setPhaseStartDate(Date phaseStartDate) {
			this.phaseStartDate = phaseStartDate;
		}
		public String getZoneId() {
			return zoneId;
		}
		public void setZoneId(String zoneId) {
			this.zoneId = zoneId;
		}
		public String getEntity() {
			return entity;
		}
		public void setEntity(String entity) {
			this.entity = entity;
		}
		public String getProjectName() {
			return projectName;
		}
		public void setProjectName(String projectName) {
			this.projectName = projectName;
		}
		public String getProjectManager() {
			return projectManager;
		}
		public void setProjectManager(String projectManager) {
			this.projectManager = projectManager;
		}
		public String getShortDesc() {
			return shortDesc;
		}
		public void setShortDesc(String shortDesc) {
			this.shortDesc = shortDesc;
		}
		public String getLongDesc() {
			return longDesc;
		}
		public void setLongDesc(String longDesc) {
			this.longDesc = longDesc;
		}
		public String getDemandType() {
			return demandType;
		}
		public void setDemandType(String demandType) {
			this.demandType = demandType;
		}
		public String getManagedServiceRequired() {
			return managedServiceRequired;
		}
		public void setManagedServiceRequired(String managedServiceRequired) {
			this.managedServiceRequired = managedServiceRequired;
		}
		public Date getDeliveryDate() {
			return deliveryDate;
		}
		public void setDeliveryDate(Date deliveryDate) {
			this.deliveryDate = deliveryDate;
		}
		public Date getGoLiveDate() {
			return goLiveDate;
		}
		public void setGoLiveDate(Date goLiveDate) {
			this.goLiveDate = goLiveDate;
		}
		public String getPhaseName() {
			return PhaseName;
		}
		public void setPhaseName(String phaseName) {
			PhaseName = phaseName;
		}
		public String getStatusName() {
			return StatusName;
		}
		public void setStatusName(String statusName) {
			StatusName = statusName;
		}
		public String getAssignedTo() {
			return assignedTo;
		}
		public void setAssignedTo(String assignedTo) {
			this.assignedTo = assignedTo;
		}
		public String getAssignedTeam() {
			return assignedTeam;
		}
		public void setAssignedTeam(String assignedTeam) {
			this.assignedTeam = assignedTeam;
		}
		   
		   
	 
			
		   
		}

	
