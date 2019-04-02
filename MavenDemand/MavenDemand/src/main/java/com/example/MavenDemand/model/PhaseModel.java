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
public class PhaseModel {

	private String PhaseID;
	private String PhaseName;
	public String getPhaseID() {
		return PhaseID;
	}
	public void setPhaseID(String phaseID) {
		PhaseID = phaseID;
	}
	public String getPhaseName() {
		return PhaseName;
	}
	public void setPhaseName(String phaseName) {
		PhaseName = phaseName;
	}
}
