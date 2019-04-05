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
import java.util.Calendar;
import java.util.List;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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

import com.example.MavenDemand.MailReport;
import com.example.MavenDemand.SimpleDateFormatter;
import com.example.MavenDemand.dao.LorealDaoImpl;

import com.example.MavenDemand.model.Credential;
import com.example.MavenDemand.model.DemandModel;
import com.example.MavenDemand.model.Person;
import com.example.MavenDemand.model.ResponseModel;
import com.example.MavenDemand.model.WorknoteModel;
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
    
    @RequestMapping( path =BASE_URL+ "/postWorknotes", method = RequestMethod.POST )
    public Object worknotes(@RequestBody WorknoteModel worknote ,HttpServletResponse response) throws URISyntaxException, IOException {
    	 Date date=Date.valueOf(new SimpleDateFormatter().format());
    	 
    	 System.out.println("am hit from frontend");
    	
    	 Integer cred = lorealdao.postingWorknotes(worknote.getWorknotedesc(),worknote.getCreatedby(),worknote.getDemandid(), date);
    	return cred;
    	
    	
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
    	
    	//System.out.println(dem.getStatusID() + " "+ dem.getPhaseID());
    	
    	 Date date=Date.valueOf(new SimpleDateFormatter().format());
    	
    	if(lorealdao.createDemand(dem.getId(),date,dem.getCreatedBy(),dem.getLastupdatedate(),dem.getLastUpdatedBy(),dem.getStatusID(),dem.getPhaseID(),dem.getPhaseStartDate(),dem.getZoneId(),dem.getEntity(),dem.getProjectName(),dem.getProjectManager(),dem.getTitle(),dem.getShortDesc(),dem.getLongDesc(),dem.getDemandType(),dem.getManagedServiceRequired(),dem.getDeliveryDate(),dem.getGoLiveDate(),dem.getAssignedTo(),dem.getAssignedTeam()) != null){
    		new MailReport().sendMail();
    		return new ResponseEntity<>(new ResponseModel("Success"),HttpStatus.OK);
    	}
    	
    	 return new ResponseEntity<>(new ResponseModel("Not Done"),HttpStatus.BAD_REQUEST);
    	
    	
    }
    
    @RequestMapping( path =BASE_URL+"/updateAssignie", method = RequestMethod.POST )
    public Object updateWithAssignie(@RequestBody DemandModel dem ,HttpServletResponse response){
		
    	
		Date date=Date.valueOf(new SimpleDateFormatter().format());
		if(lorealdao.updateDemWithAssgnie(dem.getId(), dem.getStatusID(), dem.getPhaseID(), dem.getAssignedTo(), dem.getLastUpdatedBy(), date, dem.getAssignedTeam()) !=0){
			
			return new ResponseEntity<>(new ResponseModel("Success"),HttpStatus.OK);
    	}
    	
    	 return new ResponseEntity<>(new ResponseModel("Not Done"),HttpStatus.BAD_REQUEST);
    }
    
    @RequestMapping( path =BASE_URL+"/updateslapendingstart", method = RequestMethod.POST )
    public Object slapendingstart(@RequestBody DemandModel dem ,HttpServletResponse response){
		
    	
		Date date=Date.valueOf(new SimpleDateFormatter().format());
		if(lorealdao.updateSLAPendingStart(dem.getId(), dem.getLastUpdatedBy(), date) !=0){
			
			return new ResponseEntity<>(new ResponseModel("Success"),HttpStatus.OK);
    	}
    	
    	 return new ResponseEntity<>(new ResponseModel("Not Done"),HttpStatus.BAD_REQUEST);
    }
    
    
    
    @RequestMapping( path =BASE_URL+"/updateslapendingstartISR", method = RequestMethod.POST )
    public Object slapendingstartISR(@RequestBody DemandModel dem ,HttpServletResponse response){
		
    	
		Date date=Date.valueOf(new SimpleDateFormatter().format());
		if(lorealdao.updateSLAPendingStartforISR(dem.getId(), dem.getLastUpdatedBy(), date) !=0){
			
			return new ResponseEntity<>(new ResponseModel("Success"),HttpStatus.OK);
    	}
    	
    	 return new ResponseEntity<>(new ResponseModel("Not Done"),HttpStatus.BAD_REQUEST);
    }
    
    
    @RequestMapping( path =BASE_URL+"/insertToEL", method = RequestMethod.POST )
    public Object insertingEL(@RequestBody DemandModel dem ,HttpServletResponse response){
		
    	
		Date date=Date.valueOf(new SimpleDateFormatter().format());
		if(lorealdao.updateDemToEL(dem.getId(), dem.getStatusID(), dem.getPhaseID(), dem.getAssignedTo(), dem.getLastUpdatedBy(), date, dem.getAssignedTeam()) !=0){
			
			return new ResponseEntity<>(new ResponseModel("Success"),HttpStatus.OK);
    	}
    	
    	 return new ResponseEntity<>(new ResponseModel("Not Done"),HttpStatus.BAD_REQUEST);
        
		
    }
    
    @RequestMapping( path =BASE_URL+"/insertToISR", method = RequestMethod.POST )
    public Object insertingISR(@RequestBody DemandModel dem ,HttpServletResponse response){
		
    	
		Date date=Date.valueOf(new SimpleDateFormatter().format());
		if(lorealdao.updateDemToISR(dem.getId(), dem.getStatusID(), dem.getPhaseID(), dem.getAssignedTo(), dem.getLastUpdatedBy(), date, dem.getAssignedTeam()) !=0){
			
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
	    //String date1=(c.getLastupdatedate()).toString();
	   // temp.add(date1);
	    temp.add(c.getLongDesc());
	    temp.add(c.getManagedServiceRequired());
	    temp.add(c.getProjectManager());
	    temp.add(c.getProjectName());
	    
	    temp.add(c.getDemandType());
		res.getData().add(temp);
		}
		return res;
    }
    
    @RequestMapping( path =BASE_URL+"/ShowAcceptedDemands", method = RequestMethod.GET )
    public Object showAcceptedDemand(@RequestParam("UserID") String UserID)  {
    	List<DemandModel> cred = lorealdao.getAllAcceptedDemands(UserID);
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
	    //String date1=(c.getLastupdatedate()).toString();
	   // temp.add(date1);
	    temp.add(c.getLongDesc());
	    temp.add(c.getManagedServiceRequired());
	    temp.add(c.getProjectManager());
	    temp.add(c.getProjectName());
	    
	    temp.add(c.getDemandType());
		res.getData().add(temp);
		}
		return res;
    }
    
    @RequestMapping( path =BASE_URL+"/ShowAllAssignedELDemands", method = RequestMethod.GET )
    public Object showAllAssELDemand(@RequestParam("UserID") String UserID) throws ParseException  {
    	//Date d = new Date();
    	int sla;
    	
    	Calendar p = Calendar.getInstance();    
    	//Calendar q = Calendar.getInstance();    
    	//p.add(Calendar.DATE, 5);
    	
    	
    	
    	
    	
    	List<DemandModel> cred = lorealdao.getAllAssignedELDemands(UserID);
		ResponseModel res=new ResponseModel();
		for(DemandModel c: cred){
		List<String> temp=new ArrayList<>();
		temp.add(c.getId());
		temp.add(c.getTitle());
		temp.add(c.getShortDesc());
		temp.add(c.getStatusName());
		temp.add(c.getZoneId());
		temp.add(c.getPhaseName());
		if(c.getPendingStrDt()==null)
	    {
	    	java.util.Date utilDate = new java.util.Date(c.getSlacreatedate().getTime());
	    	
	    	/*System.out.println(String.valueOf(c.getSlacreatedate()));
	    	
	    	
	    	java.util.Date utilDate = new SimpleDateFormat("dd-MM-yyyy").parse(String.valueOf(c.getSlacreatedate()));  
	    	*/
	    	
	    	System.out.println(utilDate);
	    	
	    	Date date2 =Date.valueOf(new SimpleDateFormatter().format());
	    	
	    	java.util.Date d = new java.util.Date();
	    	java.util.Date e = new java.util.Date(date2.getTime());
	    	
	    	
	    	System.out.println(e);
	    	
	    	p.setTime(utilDate);
	    	
	    	p.add(Calendar.DATE,4);
	    	
	    	d = p.getTime();
	    	
	    	System.out.println(d);
	    	
	    long sal = d.getTime() - e.getTime();
	    
	    
		System.out.println(sal);
		
		long days =  sal / (24 * 60 * 60 * 1000);
		
		 System.out.println(days);
	    
	    c.setSla((int)days);
	    
	    System.out.println((int) days);
	    
	    temp.add(String.valueOf(c.getSla()));
	    	
	    }
	    else
	    {
java.util.Date utilDate = new java.util.Date(c.getSlacreatedate().getTime());
java.util.Date pendingdt = new java.util.Date(c.getPendingStrDt().getTime());	    	
	    	/*System.out.println(String.valueOf(c.getSlacreatedate()));
	    	
	    	
	    	java.util.Date utilDate = new SimpleDateFormat("dd-MM-yyyy").parse(String.valueOf(c.getSlacreatedate()));  
	    	*/
	    	
	    	System.out.println(utilDate);
	    	
	    	java.util.Date d = new java.util.Date();
	    	
	    	
	    	
	    	System.out.println(pendingdt);
	    	
	    	p.setTime(utilDate);
	    	
	    	p.add(Calendar.DATE,4);
	    	
	    	d = p.getTime();
	    	
	    	System.out.println(d);
	    	
	    long sal = d.getTime() - pendingdt.getTime();
	    
	    
		System.out.println(sal);
		
		long days =  sal / (24 * 60 * 60 * 1000);
		
		 System.out.println(days);
	    
	    c.setSla((int)days);
	    
	    System.out.println((int) days);
	    
	    temp.add(String.valueOf(c.getSla()));
	    }
		
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
    
    
    @RequestMapping( path =BASE_URL+"/ShowAllELDemandsforCCOE", method = RequestMethod.GET )
    public Object showAllELDemandforCCOE() throws ParseException  {
    	//Date d = new Date();
    	int sla;
    	
    	Calendar p = Calendar.getInstance();    
    	//Calendar q = Calendar.getInstance();    
    	//p.add(Calendar.DATE, 5);
    	
    	
    	
    	
    	
    	List<DemandModel> cred = lorealdao.getAllELDemandsForCCOE();
		ResponseModel res=new ResponseModel();
		for(DemandModel c: cred){
		List<String> temp=new ArrayList<>();
		temp.add(c.getId());
		temp.add(c.getTitle());
		temp.add(c.getShortDesc());
		temp.add(c.getStatusName());
		temp.add(c.getZoneId());
		temp.add(c.getPhaseName());
		if(c.getPendingStrDt()==null)
	    {
	    	java.util.Date utilDate = new java.util.Date(c.getSlacreatedate().getTime());
	    	
	    	/*System.out.println(String.valueOf(c.getSlacreatedate()));
	    	
	    	
	    	java.util.Date utilDate = new SimpleDateFormat("dd-MM-yyyy").parse(String.valueOf(c.getSlacreatedate()));  
	    	*/
	    	
	    	System.out.println(utilDate);
	    	
	    	Date date2 =Date.valueOf(new SimpleDateFormatter().format());
	    	
	    	java.util.Date d = new java.util.Date();
	    	java.util.Date e = new java.util.Date(date2.getTime());
	    	
	    	
	    	System.out.println(e);
	    	
	    	p.setTime(utilDate);
	    	
	    	p.add(Calendar.DATE,4);
	    	
	    	d = p.getTime();
	    	
	    	System.out.println(d);
	    	
	    long sal = d.getTime() - e.getTime();
	    
	    
		System.out.println(sal);
		
		long days =  sal / (24 * 60 * 60 * 1000);
		
		 System.out.println(days);
	    
	    c.setSla((int)days);
	    
	    System.out.println((int) days);
	    
	    temp.add(String.valueOf(c.getSla()));
	    	
	    }
	    else
	    {
java.util.Date utilDate = new java.util.Date(c.getSlacreatedate().getTime());
java.util.Date pendingdt = new java.util.Date(c.getPendingStrDt().getTime());	    	
	    	/*System.out.println(String.valueOf(c.getSlacreatedate()));
	    	
	    	
	    	java.util.Date utilDate = new SimpleDateFormat("dd-MM-yyyy").parse(String.valueOf(c.getSlacreatedate()));  
	    	*/
	    	
	    	System.out.println(utilDate);
	    	
	    	java.util.Date d = new java.util.Date();
	    	
	    	
	    	
	    	System.out.println(pendingdt);
	    	
	    	p.setTime(utilDate);
	    	
	    	p.add(Calendar.DATE,4);
	    	
	    	d = p.getTime();
	    	
	    	System.out.println(d);
	    	
	    long sal = d.getTime() - pendingdt.getTime();
	    
	    
		System.out.println(sal);
		
		long days =  sal / (24 * 60 * 60 * 1000);
		
		 System.out.println(days);
	    
	    c.setSla((int)days);
	    
	    System.out.println((int) days);
	    
	    temp.add(String.valueOf(c.getSla()));
	    }
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
    
    @RequestMapping( path =BASE_URL+"/ShowAllAceptedDemandsforCCOE", method = RequestMethod.GET )
    public Object showAllAcceptedDemandCCOE()  {
    	List<DemandModel> cred = lorealdao.getAllAcceptedDemandsforCCOE();
		ResponseModel res=new ResponseModel();
		for(DemandModel c: cred){
		List<String> temp=new ArrayList<>();
		temp.add(c.getId());
		temp.add(c.getTitle());
		temp.add(c.getShortDesc());
		temp.add(c.getStatusName());
		temp.add(c.getZoneId());
		temp.add(c.getPhaseName());
		temp.add(c.getAssignedTeam());
		temp.add(c.getAssignedTo());
	    temp.add(c.getDemandType());
	    temp.add(c.getEntity());
	    temp.add(c.getLastUpdatedBy());
	    temp.add(c.getLongDesc());
	    temp.add(c.getManagedServiceRequired());
	    temp.add(c.getProjectManager());
	    temp.add(c.getProjectName());
	    
	    temp.add(c.getStatusID());
	    
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
    
    @RequestMapping( path =BASE_URL+"/getTPDL", method = RequestMethod.GET )
    public Object getTpdlList()  {
    	
    	List<Credential> cred = lorealdao.getAllTpdl();
		ResponseModel res=new ResponseModel();
		for(Credential c: cred){
		List<String> temp=new ArrayList<>();
		temp.add(c.getName());
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
    

