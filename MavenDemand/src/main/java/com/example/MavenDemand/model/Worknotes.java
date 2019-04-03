/**
 * 
 */
package com.example.MavenDemand.model;

import java.sql.Date;

/**
 * @author nilargha.ghosh
 *
 */
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Worknotes {

	private String WorkNoteID;
	private String WorkNoteDesc;
	private String CreatedBy;
	private Date CreatedDt;
	private String DemandId;
	public String getWorkNoteID() {
		return WorkNoteID;
	}
	public void setWorkNoteID(String workNoteID) {
		WorkNoteID = workNoteID;
	}
	public String getWorkNoteDesc() {
		return WorkNoteDesc;
	}
	public void setWorkNoteDesc(String workNoteDesc) {
		WorkNoteDesc = workNoteDesc;
	}
	public String getCreatedBy() {
		return CreatedBy;
	}
	public void setCreatedBy(String createdBy) {
		CreatedBy = createdBy;
	}
	public String getCreatedDt() {
		return CreatedDt.toString();
	}
	public void setCreatedDt(Date date) {
		CreatedDt = date;
	}
	public String getDemandId() {
		return DemandId;
	}
	public void setDemandId(String demandId) {
		DemandId = demandId;
	}
	
	
}
