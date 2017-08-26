package com.microservice.entities.microservice_entitty.base;

import java.awt.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.microservice.entities.Base;

@JsonSerialize
public class TestEntity extends Base{

	private String idString = "X";
	
	private long idInt = 101;
	
	private ArrayList<String> listString = new ArrayList<String>();
	private Map<String,String> mapString = new HashMap<String,String>();
	
	private InnerTestEntity innerEntity = new InnerTestEntity();
	
	private ArrayList<InnerTestEntity> innerEntityList = new ArrayList();
	
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

	public InnerTestEntity getInnerEntity() {
		return innerEntity;
	}

	public void setInnerEntity(InnerTestEntity innerEntity) {
		this.innerEntity = innerEntity;
	}

	public ArrayList<InnerTestEntity> getInnerEntityList() {
		return innerEntityList;
	}

	public void setInnerEntityList(ArrayList<InnerTestEntity> innerEntityList) {
		this.innerEntityList = innerEntityList;
	}

	public TestEntity() {
		listString.add("A");
		listString.add("B");
		listString.add("C");
		mapString.put("A", "A");
		mapString.put("B", "B");
		mapString.put("C", "C");
		innerEntityList.add(new InnerTestEntity());
		innerEntityList.add(new InnerTestEntity());
		innerEntityList.add(new InnerTestEntity());
	}
}


