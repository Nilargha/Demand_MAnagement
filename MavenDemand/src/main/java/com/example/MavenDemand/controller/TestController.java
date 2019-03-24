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

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    if(credentials.getPass().equals(lorealdao.getUserPass(credentials.getName()) ) )
    		{
    //	response.sendRedirect("http://localhost:8087/dashboard");
//    	URI uri = new URI("");
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setLocation(uri);
//        return new ResponseEntity<>(httpHeaders, HttpStatus.TEMPORARY_REDIRECT);
    	int uid=lorealdao.getUserDetails(credentials.getName());
//    	
    				//return new ResponseEntity<>(new ResponseModel(credentials.getIduser()),HttpStatus.OK);
    	return uid;
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
    
    
    @RequestMapping( path =BASE_URL+"/getusers", method = RequestMethod.GET )
    public Object allCredInfo(@RequestParam("username") String username)  {
    	Integer cred = lorealdao.getUserDetails(username);
    	return cred;
    }
    
    @RequestMapping( path =BASE_URL+"/createDemand", method = RequestMethod.POST )
    public Object login(@RequestBody DemandModel dem ,HttpServletResponse response){
    	
    	if(lorealdao.createDemand(dem.getId(),dem.getTitle(), dem.getDesc(),dem.getStatus(),dem.getIduser()) != null){
    		
    		return new ResponseEntity<>(new ResponseModel("Success"),HttpStatus.OK);
    	}
    	
    	 return new ResponseEntity<>(new ResponseModel("Not Done"),HttpStatus.BAD_REQUEST);
    	
    	
    }
    
    @RequestMapping( path =BASE_URL+"/ShowMyDemands", method = RequestMethod.GET )
    public Object showDemand(@RequestParam("iduser") int iduser)  {
    	List<DemandModel> cred = lorealdao.getAllActiveDemands(iduser);
		ResponseModel res=new ResponseModel();
		for(DemandModel c: cred){
		List<String> temp=new ArrayList<>();
		temp.add(c.getId());
		temp.add(c.getTitle());
		temp.add(c.getDesc());
		temp.add(c.getStatus());
		//temp.add((String)c.getIduser());
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
		temp.add(c.getDesc());
		temp.add(c.getStatus());
	    int s=(c.getIduser());
	    String s1=Integer.toString(s);
		temp.add(s1);
		res.getData().add(temp);
		}
		return res;
    }
    
    @RequestMapping( path =BASE_URL+"/approveDem", method = RequestMethod.GET )
    public int apprDem(@RequestParam("id") String id)  {
    	Integer cred = lorealdao.approveDemand(id);
    	return cred;
    }
    
    @RequestMapping( path =BASE_URL+"/rejectDem", method = RequestMethod.GET )
    public int rejectDem(@RequestParam("id") String id)  {
    	Integer cred = lorealdao.rejectDemand(id);
    	return cred;
    }
    
    @RequestMapping( path =BASE_URL+"/getRole", method = RequestMethod.GET )
    public int getRoleOfUser(@RequestParam("id") int id)  {
    	Integer cred = lorealdao.getUserRole(id);
    	return cred;
    }
    
    }
    

