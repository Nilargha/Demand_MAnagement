package com.example.MavenDemand.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class ResponseModel {
private String message;
private List<List<String>> data=new ArrayList();
private List<String> info=new ArrayList();

public List<List<String>> getData() {
	return data;
}

public List<String> getInfo() {
	return info;
}


public void setData(List<String> info) {
	this.info = info;
}


public String getMessage() {
	return message;
}

public void setMessage(String message) {
	this.message = message;
}

public ResponseModel(String message) {
	
	this.message = message;
};
public ResponseModel() {
	
};

}
