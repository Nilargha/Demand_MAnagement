/**
 * 
 */
package com.example.MavenDemand.model;

/**
 * @author nilargha.ghosh
 *
 */
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class StatusModel {

	private String StatusID;
	private String StatusName;
	public String getStatusID() {
		return StatusID;
	}
	public void setStatusID(String statusID) {
		StatusID = statusID;
	}
	public String getStatusName() {
		return StatusName;
	}
	public void setStatusName(String statusName) {
		StatusName = statusName;
	}
	
}
