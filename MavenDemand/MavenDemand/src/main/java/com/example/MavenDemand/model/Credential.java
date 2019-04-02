package com.example.MavenDemand.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Credential {
	private String name;
	   private String pass;
	   private String iduser;
	   private String role;
	   private String email;
	   private String zoneid;

	    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getZoneid() {
		return zoneid;
	}

	public void setZoneid(String zoneid) {
		this.zoneid = zoneid;
	}

		public String getIduser() {
		return iduser;
	}

	public void setIduser(String iduser) {
		this.iduser = iduser;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

		/**
	     * @return the name
	     */
	    public String getName() {
	        return name;
	    }

	    /**
	     * @param name the name to set
	     */
	    public void setName(String name) {
	        this.name = name;
	    }

	    /**
	     * @return the age
	     */
	    public String getPass() {
	        return pass;
	    }

	    /**
	     * @param age the age to set
	     */
	    public void setPass(String pass) {
	        this.pass = pass;
	    }
	   
	}


