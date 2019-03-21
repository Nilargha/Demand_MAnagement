package com.example.MavenDemand.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class DemandModel {

	public DemandModel() {
		// TODO Auto-generated constructor stub
	}

	private String id;
	   private String title;
	   private String desc;
	   private String status;
	   private int iduser;

		    public int getIduser() {
		return iduser;
	}

	public void setIduser(int iduser) {
		this.iduser = iduser;
	}

			public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

			
		   
		}

	
