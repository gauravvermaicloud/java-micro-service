package com.microservice.entities.microservice_entitty.base;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.microservice.entities.Base;

@JsonSerialize
public class InnerTestEntity extends Base{
	
	private String idString = "Y";
	
	private long idInt = 102;
	
	private ArrayList<String> listString = new ArrayList<String>();
	private Map<String,String> mapString = new HashMap<String,String>();
	
	public String getIdString() {
		return idString;
	}

	public void setIdString(String idString) {
		this.idString = idString;
	}

	public long getIdInt() {
		return idInt;
	}

	public void setIdInt(long idInt) {
		this.idInt = idInt;
	}

	public ArrayList<String> getListString() {
		return listString;
	}

	public void setListString(ArrayList<String> listString) {
		this.listString = listString;
	}

	public Map<String, String> getMapString() {
		return mapString;
	}

	public void setMapString(Map<String, String> mapString) {
		this.mapString = mapString;
	}

	public InnerTestEntity() {
		listString.add("Q");
		listString.add("W");
		listString.add("R");
		mapString.put("Q", "Q");
		mapString.put("W","W");
		mapString.put("R", "R");
	}
}
