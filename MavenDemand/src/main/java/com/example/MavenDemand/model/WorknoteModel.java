/**
 * 
 */
package com.example.MavenDemand.model;

/**
 * @author nilargha.ghosh
 *
 */
public class WorknoteModel {

	private int worknoteid;
	private String worknotedesc;
	private String createdby;
	private String createdt;
	private String demandid;
	public int getWorknoteid() {
		return worknoteid;
	}
	public void setWorknoteid(int worknoteid) {
		this.worknoteid = worknoteid;
	}
	public String getWorknotedesc() {
		return worknotedesc;
	}
	public void setWorknotedesc(String worknotedesc) {
		this.worknotedesc = worknotedesc;
	}
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	public String getCreatedt() {
		return createdt;
	}
	public void setCreatedt(String createdt) {
		this.createdt = createdt;
	}
	public String getDemandid() {
		return demandid;
	}
	public void setDemandid(String demandid) {
		this.demandid = demandid;
	}
	
}
