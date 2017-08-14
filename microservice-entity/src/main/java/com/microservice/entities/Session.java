package com.microservice.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

/**
 * This class is used to implement a session.
 * It contains the session id along with a number of 
 * session objects
 * @author gaurav
 *
 */
public class Session extends BaseEntity implements Serializable{

	private int sessionTimeout = 20*60*1000;
	/**
	 * This is the session Id.
	 */
	private String sessionId;
	
	/**
	 * This is the user associated with the session.
	 */
	private User user;
	
	/**
	 * This method returns the external facing user
	 * @return The user for the session.
	 */
	public User getUser(){
		return this.user;
	}
	
	/**
	 * This is the map of session objects
	 */
	private HashMap<String, Object> sessionObjects;
	

	public Session(User user){
		this.setSessionId(UUID.randomUUID().toString().toUpperCase());
		this.setUserId(user.getId());
		this.user = user;
		super.setCreateDateTime(new Date());
		super.setUpdateDateTime(new Date());
	}

	/**
	 * Default constructor
	 */
	public Session(){
		
	}
	
	/**
	 * This method sets the user for the session
	 * @param user The user for the session
	 */
	public void setUser(User user){
		this.user = user;
	}
	
	
	/**
	 * The user id associated with the session
	 */
	private String userId;
	
	/**
	 * This methods returns the session id
	 * @return Returns a session id
	 */
	public String getSessionId() {
		return sessionId;
	}

	/**
	 * This method sets a session id
	 * @param sessionId The session id 
	 */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	/**
	 * Sets the user id
	 * @return the user id
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * Gets the user id
	 * @param userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	/**
	 *A map to store custom session attributes. This map should not be a persistant store
	 *and will be ignored by JSON in interest of security
	 */
	private HashMap<String,Object> sessionAttribute = new HashMap<String,Object>();
	
	/**
	 * This method adds a custom attribute to session 
	 * @param key The key of the attribute
	 * @return The value of the attribute
	 */
	public Object getSessionAttribute(String key){
		return this.sessionAttribute.get(key);
	}
	
	/**
	 * This method adds a custom attribute to session
	 * @param key The key of the attribute
	 * @param attribute The value of the attribute
	 */
	public void addSessionAttribute(String key, Object attribute){
		this.sessionAttribute.put(key, attribute);
	}

	@Override
	public boolean isValid() {
		//session is valid if it is not expired
		long nowTime = new Date().getTime();
		long lastUpdateTime = this.getUpdateDateTime().getTime();
		long diff =(nowTime-lastUpdateTime)/1000l;
		if(diff>this.sessionTimeout){
			return false;
		}
		else{
			return true;
		}
	}
}