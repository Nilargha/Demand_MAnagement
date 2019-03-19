package com.example.MavenDemand.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class ResponseModel {
private String message;
private List<List<String>> data=new ArrayList();

public List<List<String>> getData() {
	return data;
}

public void setData(List<List<String>> data) {
	this.data = data;
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
