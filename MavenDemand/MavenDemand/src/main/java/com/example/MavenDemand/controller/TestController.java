/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.MavenDemand.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.MavenDemand.dao.LorealDaoImpl;

import com.example.MavenDemand.model.Credential;
import com.example.MavenDemand.model.DemandModel;
import com.example.MavenDemand.model.Person;
import com.example.MavenDemand.model.ResponseModel;
import com.example.MavenDemand.model.Worknotes;
import com.example.MavenDemand.model.ZoneModel;

import ch.qos.logback.classic.net.SyslogAppender;

/**
 *
 * @author Neel
 */
@RestController
public class TestController {
	
	
	@Autowired
	LorealDaoImpl lorealdao;
	
	
	static final String BASE_URL="/api";
			
	
	
    @RequestMapping(path =BASE_URL+"/details", method = RequestMethod.GET)
    public Object getPersonDetails() {
        Person p = new Person();
        p.setAge("25");
        p.setName("Nilargha");
        return p;
    }
    
    
    @RequestMapping( path =BASE_URL+ "/login", method = RequestMethod.POST )
    public Object login(@RequestBody Credential credentials ,HttpServletResponse response) throws URISyntaxException, IOException {
    try
    {
    if(credentials.getPass().equals(lorealdao.getUserPass(credentials.getEmail()) ) )
    		{
    //	response.sendRedirect("http://localhost:8087/dashboard");
//    	URI uri = new URI("");
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setLocation(uri);
//        return new ResponseEntity<>(httpHeaders, HttpStatus.TEMPORARY_REDIRECT);
    	String username=lorealdao.getUserDetails(credentials.getEmail());
//    	
    				//return new ResponseEntity<>(new ResponseModel(credentials.getIduser()),HttpStatus.OK);
    	return username;
    		}
    }
    catch (EmptyResultDataAccessException e) {
    	return new ResponseEntity<>(new ResponseModel("Not VALID CREDENTIALS"),HttpStatus.BAD_REQUEST);
	}    	
    	
       return new ResponseEntity<>(new ResponseModel("Not VALID CREDENTIALS"),HttpStatus.BAD_REQUEST);
    }
    
    
    @RequestMapping( path =BASE_URL+"/getallusers", method = RequestMethod.GET )
    public Object allCredInfo()  {
		List<Credential> cred = lorealdao.getAllUserDetails();
		ResponseModel res=new ResponseModel();
		for(Credential c: cred){
		List<String> temp=new ArrayList<>();
		temp.add(c.getName());
		temp.add(c.getPass());
		res.getData().add(temp);
		}
		
    	return res;
    }
    
    
   /* @RequestMapping( path =BASE_URL+"/getusers", method = RequestMethod.GET )
    public Object allCredInfo(@RequestParam("username") String username)  {
    	Integer cred = lorealdao.getUserDetails(username);
    	return cred;
    }*/
    
    @RequestMapping( path =BASE_URL+"/createDemand", method = RequestMethod.POST )
    public Object create(@RequestBody DemandModel dem ,HttpServletResponse response){
    	
    	System.out.println(dem.getStatusID() + " "+ dem.getPhaseID());
    	
    	if(lorealdao.createDemand(dem.getId(),dem.getCreateDate(),dem.getCreatedBy(),dem.getLastupdatedate(),dem.getLastUpdatedBy(),dem.getStatusID(),dem.getPhaseID(),dem.getPhaseStartDate(),dem.getZoneId(),dem.getEntity(),dem.getProjectName(),dem.getProjectManager(),dem.getTitle(),dem.getShortDesc(),dem.getLongDesc(),dem.getDemandType(),dem.getManagedServiceRequired(),dem.getDeliveryDate(),dem.getGoLiveDate(),dem.getAssignedTo(),dem.getAssignedTeam()) != null){
    		
    		return new ResponseEntity<>(new ResponseModel("Success"),HttpStatus.OK);
    	}
    	
    	 return new ResponseEntity<>(new ResponseModel("Not Done"),HttpStatus.BAD_REQUEST);
    	
    	
    }
    
    @RequestMapping( path =BASE_URL+"/ShowMyDemands", method = RequestMethod.GET )
    public Object showDemand(@RequestParam("UserID") String UserID)  {
    	List<DemandModel> cred = lorealdao.getAllActiveDemands(UserID);
		ResponseModel res=new ResponseModel();
		for(DemandModel c: cred){
		List<String> temp=new ArrayList<>();
		temp.add(c.getId());
		temp.add(c.getTitle());
		temp.add(c.getShortDesc());
		temp.add(c.getStatusName());
		temp.add(c.getZoneId());
		temp.add(c.getPhaseName());
		String date=(c.getCreateDate()).toString();
		temp.add(date);
		temp.add(c.getCreatedBy());
	    temp.add(c.getDemandType());
	    temp.add(c.getEntity());
	    temp.add(c.getLastUpdatedBy());
	    String date1=(c.getLastupdatedate()).toString();
	    temp.add(date1);
	    temp.add(c.getLongDesc());
	    temp.add(c.getManagedServiceRequired());
	    temp.add(c.getProjectManager());
	    temp.add(c.getProjectName());
	    
	    temp.add(c.getDemandType());
		res.getData().add(temp);
		}
		return res;
    }
    
    @RequestMapping( path =BASE_URL+"/ShowAllActiveDemands", method = RequestMethod.GET )
    public Object showActiveDemand()  {
    	List<DemandModel> cred = lorealdao.getAllActiveDemandsForApprover();
		ResponseModel res=new ResponseModel();
		for(DemandModel c: cred){
		List<String> temp=new ArrayList<>();
		temp.add(c.getId());
		temp.add(c.getTitle());
		temp.add(c.getAssignedTeam());
		temp.add(c.getAssignedTo());
	    temp.add(c.getDemandType());
	    temp.add(c.getEntity());
	    temp.add(c.getLastUpdatedBy());
	    temp.add(c.getLongDesc());
	    temp.add(c.getManagedServiceRequired());
	    temp.add(c.getProjectManager());
	    temp.add(c.getProjectName());
	    temp.add(c.getShortDesc());
	    temp.add(c.getStatusID());
	    temp.add(c.getZoneId());
	    temp.add(c.getDemandType());
		res.getData().add(temp);
		}
		return res;
    }
    
    @RequestMapping( path =BASE_URL+"/ShowAllApprovedDemands", method = RequestMethod.GET )
    public Object showApprovedDemand()  {
    	List<DemandModel> cred = lorealdao.getAllApprovedDemandsForApprover();
		ResponseModel res=new ResponseModel();
		for(DemandModel c: cred){
		List<String> temp=new ArrayList<>();
		temp.add(c.getId());
		temp.add(c.getTitle());
		temp.add(c.getAssignedTeam());
		temp.add(c.getAssignedTo());
	    temp.add(c.getDemandType());
	    temp.add(c.getEntity());
	    temp.add(c.getLastUpdatedBy());
	    temp.add(c.getLongDesc());
	    temp.add(c.getManagedServiceRequired());
	    temp.add(c.getProjectManager());
	    temp.add(c.getProjectName());
	    temp.add(c.getShortDesc());
	    temp.add(c.getStatusID());
	    temp.add(c.getZoneId());
	    temp.add(c.getDemandType());
		res.getData().add(temp);
		}
		return res;
    }
    
    @RequestMapping( path =BASE_URL+"/ShowAllDemands", method = RequestMethod.GET )
    public Object showDemand()  {
    	List<DemandModel> cred = lorealdao.getAllDemands();
		ResponseModel res=new ResponseModel();
		for(DemandModel c: cred){
		List<String> temp=new ArrayList<>();
		temp.add(c.getId());
		temp.add(c.getTitle());
		temp.add(c.getAssignedTeam());
		temp.add(c.getAssignedTo());
	    temp.add(c.getDemandType());
	    temp.add(c.getEntity());
	    temp.add(c.getLastUpdatedBy());
	    temp.add(c.getLongDesc());
	    temp.add(c.getManagedServiceRequired());
	    temp.add(c.getProjectManager());
	    temp.add(c.getProjectName());
	    temp.add(c.getShortDesc());
	    temp.add(c.getStatusID());
	    temp.add(c.getZoneId());
	    temp.add(c.getDemandType());
		res.getData().add(temp);
		}
		return res;
    }
    
    @RequestMapping( path =BASE_URL+"/approveDem", method = RequestMethod.GET )
    public int apprDem(@RequestParam("id") String id)  {
    	Integer cred = lorealdao.approveDemand(id);
    	return cred;
    }
    @RequestMapping( path =BASE_URL+"/dispworknote", method = RequestMethod.GET )
    public Object worknote(@RequestParam("id") String id)  {
    	
    	List<Worknotes> cred = lorealdao.getAllWorknotes(id);
    	ResponseModel res=new ResponseModel();
		for(Worknotes c: cred){
		List<String> temp=new ArrayList<>();
		temp.add(c.getWorkNoteID());
		temp.add(c.getWorkNoteDesc());
		temp.add(c.getCreatedBy());
		temp.add(c.getCreatedDt());
		res.getData().add(temp);
		}
		return res;
    	
    	
    }
    
    @RequestMapping( path =BASE_URL+"/rejectDem", method = RequestMethod.GET )
    public int rejectDem(@RequestParam("id") String id)  {
    	Integer cred = lorealdao.rejectDemand(id);
    	return cred;
    }
    
    @RequestMapping( path =BASE_URL+"/getRole", method = RequestMethod.GET )
    public String getRoleOfUser(@RequestParam("uname") String uname)  {
    	String cred = lorealdao.getUserRole(uname);
    	return cred;
    }
    
   /* @RequestMapping( path =BASE_URL+"/showUpdateFields", method = RequestMethod.GET )
    public Object showUpdate(@RequestParam("idDemands") String idDemands)  {
    	List<DemandModel> cred = lorealdao.getUpdateFields(idDemands);
		ResponseModel res=new ResponseModel();
		for(DemandModel c: cred){
		List<String> temp=new ArrayList<>();
		//temp.add(c.getId());
		temp.add(c.getTitle());
		temp.add(c.getDesc());
		//temp.add(c.getStatus());
		//temp.add((String)c.getIduser());
		res.getData().add(temp);
		}
		return res;
    }
    */
   /* @RequestMapping( path =BASE_URL+"/editDemand", method = RequestMethod.POST )
    public Object editDem(@RequestBody DemandModel dem ,HttpServletResponse response){
    	
    	if(lorealdao.updateDemand(dem.getId(),dem.getTitle(), dem.getDesc()) != null){
    		
    		return new ResponseEntity<>(new ResponseModel("Success"),HttpStatus.OK);
    	}
    	else {
    	
    	 return new ResponseEntity<>(new ResponseModel("Not Done"),HttpStatus.BAD_REQUEST);
    	}
    	
    }*/
    
    
    @RequestMapping( path =BASE_URL+"/getZone", method = RequestMethod.GET )
    public Object getZoneId()  {
    	
    	List<ZoneModel> cred = lorealdao.getAllZones();
		ResponseModel res=new ResponseModel();
		for(ZoneModel c: cred){
		List<String> temp=new ArrayList<>();
		temp.add(c.getZoneID());
		//temp.add(c.getZoneName());
		
		res.getInfo().addAll(temp);
		}
		return res;
    	
    }
    
    @RequestMapping( path =BASE_URL+"/getDemId", method = RequestMethod.GET )
    public String getDemandId()  {
		
    	
    	
    	String demand = lorealdao.getDemandId();
    	return demand;
    	
    	
    	
    }
    
    }
    

