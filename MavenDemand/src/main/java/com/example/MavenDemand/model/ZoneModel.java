package com.example.MavenDemand.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ZoneModel {
	
	private String ZoneID;
	   private String ZoneName;
	public String getZoneID() {
		return ZoneID;
	}
	public void setZoneID(String zoneID) {
		ZoneID = zoneID;
	}
	public String getZoneName() {
		return ZoneName;
	}
	public void setZoneName(String zoneName) {
		ZoneName = zoneName;
	}
	   
}
