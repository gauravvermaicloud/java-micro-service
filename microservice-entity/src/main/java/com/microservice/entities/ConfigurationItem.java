package com.microservice.entities;

import java.io.Serializable;

/**
 * This class represents a given configuration item.
 * @author gaurav
 *
 */
public class ConfigurationItem extends BaseEntity  implements Serializable{
	/**
	 * This is the configuration key
	 */
	private String key;
	
	/**
	 * This is the name of the application for which configuration is present
	 */
	private String applicationName;
	
	/**
	 * This is the version of the configuration
	 */
	private String version;
	
	/**
	 * This is the value of configuration
	 */
	private String value;
	
	/**
	 * This is the name of the machine
	 */
	private String machineName;
	
	/**
	 * @see BaseEntity.isValid
	 */
	@Override
	public boolean isValid() {
		return true;
	}

	/**
	 * Returns the configuration Key
	 * @return The Key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Sets the configuration Key
	 * @param key The configuration Key
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * Gets the name of the application
	 * @return The application Name
	 */
	public String getApplicationName() {
		return applicationName;
	}

	/**
	 * Sets the application name
	 * @param applicationName The application name
	 */
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	/**
	 * Gets the version of configuration
	 * @return The version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * Sets the version of configuration
	 * @param version The version of configuration
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getMachineName() {
		return machineName;
	}

	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}
	
	
	
	
}
