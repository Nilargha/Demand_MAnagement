package com.example.MavenDemand.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Credential {
	private String name;
	   private String pass;

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


