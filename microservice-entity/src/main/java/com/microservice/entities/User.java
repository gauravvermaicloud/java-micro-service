package com.microservice.entities;

import java.io.Serializable;

public class User extends BaseEntity  implements Serializable{

	/**
	 * Checks if the user entity is valid
	 */
	@Override
	public boolean isValid() {
		return true;
	}
	
	/**
	 * This is the user name of the user
	 */
	private String userName;
	
	/**
	 * This is the provider of the user store
	 */
	private String provider;
	
	/**
	 * This is the password of the user
	 */
	private String password;

	/**
	 * Gets the user name
	 * @return
	 */
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
