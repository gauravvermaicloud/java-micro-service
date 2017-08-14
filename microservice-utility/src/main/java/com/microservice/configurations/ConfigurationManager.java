package com.microservice.configurations;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.microservice.entities.ConfigurationItem;
import com.microservice.logger.Logger;

/**
 * <p>This class is the standard configuration manager for the applications
 * across micro services. The data store for this class is
 * 1. A MySQL Database
 * 2. Properties File - of name microservices.local.properties
 * 
 * The objective is to have a common configuration store across applications
 * and services.
 * </p> 
 * @author gaurav
 *
 */
public class ConfigurationManager {

	
	public final static String APPLICATION_NAME = "ApplicationName";
	public final static String APPLICATION_VERSION = "ApplicationVersion";
	/**
	 * <p>This is the private instance of configuration manager exposed
	 * as a singleton</p>
	 */
	private static ConfigurationManager configurationManager = null;
	
	/**
	 * This is the configuration map.
	 */
	private Map<String,ConfigurationItem> configurations = new HashMap<String, ConfigurationItem>();
	
	/**
	 * <p>This is the private constructor of the object.
	 * It allows singleton pattern and allows for creation of only one instance
	 * of the configuration manager.
	 * The said constructor
	 * 1. Reads basic set of properties from local file such as application name and version
	 *  configuration service url etc/
	 * and
	 * 2. Application specific properties from the centeral configuration service
	 * </p>
	 */
	private ConfigurationManager() {
		//read from local file
		this.loadPropertyFile();
		
		//read from the service
		this.loadServiceConfiguration();
	}
	
	/**
	 * Provides a lock for the object
	 */
	private static Object lock = new Object();
	
	private void loadServiceConfiguration(){
		
	}
	
	/**
	 * Returns an instance of the configuration manager
	 * @return An instance of the configuration manager
	 */
	public static ConfigurationManager getInstance() {
		
		//if the object is null then we create a new instance of the object
		if(ConfigurationManager.configurationManager == null) {
			//because multiple threads may come along at the same time so next 
			//few statements are in a lock
			synchronized(lock) {
				//now we make another check because what if the previous thread had
				//made the object.
				//Please note that this is not a recomended practice as it is not 
				//guranteed to work against all platforms and implementations of JVM
				//however the alternate of putting a method level sync lock
				//will slow down this very critical and often used piece of code.
				if(ConfigurationManager.configurationManager == null) {
					ConfigurationManager.configurationManager = new ConfigurationManager();
				}//end if
			}//end lock
		}//end if
		return ConfigurationManager.configurationManager;
	}
	
	/**
	 * This method forces configuration reload. This allows changing properties
	 * on the fly and reloading them as required without an application restart.
	 */
	public static void forceConfgurationReload() {
		ConfigurationManager.configurationManager =null;
	}
	
	
	/**
	 * Returns a full configuration item against the given key.
	 * @param key The key against which configuration is expected.
	 * @return The configuration Item
	 */
	public ConfigurationItem getConfigurationItem(String key) {
		return this.configurations.get(key);
	}
	
	/**
	 * This method returns the value for the key. This method throws a null pointer
	 * exception if the key doesnt exist in the system
	 * @param key This is the key
	 * @return The value associated with the key
	 */
	public String get(String key) {
		return this.getConfigurationItem(key).getValue();
	}
	
	/**
	 * This method informs if the key exists in the system
	 * @param key The key
	 * @return True if key exists and false if the key doesnt exist
	 */
	public boolean hasKey(String key) {
		return this.configurations.containsKey(key);
	}
	
	/**
	 * This method loads properties file into the configuration manager.
	 */
	private void loadPropertyFile(){
		Properties properties  = null;
		InputStream inputStream =null;
		
		try{
			properties = new Properties();
			//Using the .properties file in the class  path load the file
			//into the properties class
			inputStream = 
					this.getClass().getClassLoader().getResourceAsStream("microservices.local.properties");
			properties.load(inputStream);
			
			//we read the values of the required fields Application name and version as
			//they are required across the the entities.
			ConfigurationItem configurationItem = null;
			String applicationName = properties.getProperty(ConfigurationManager.APPLICATION_NAME);
			String applicationVersion = properties.getProperty(ConfigurationManager.APPLICATION_VERSION);
			//for each key that exists in the properties file put it into
			//the configuration map
			for(String key : properties.stringPropertyNames()){
				String value = properties.getProperty(key);
				configurationItem = new ConfigurationItem();
				configurationItem.setApplicationName(applicationName);
				configurationItem.setVersion(applicationVersion);
				configurationItem.setKey(key);
				configurationItem.setId(key);
				configurationItem.setValue(value);
				this.configurations.put(key, configurationItem);
			}
			
			try {
				String hostName=InetAddress.getLocalHost().getHostName();				
				String ipAddress = InetAddress.getLocalHost().getHostAddress();
				this.addToConfigurationItem("MachineName", hostName);
				this.addToConfigurationItem("IpAddress", ipAddress);
			}
			catch(UnknownHostException ex) {
				this.addToConfigurationItem("MachineName", "None");
				this.addToConfigurationItem("IpAddress", "None");
			}
		}
		catch(IOException ioException){
			//we do not generally expect an exception here
			//and because we are start of the code even before loggers
			//have been enabled if something goes wrong we will have to print it to
			//console. We do not throw this exception because we do not expect it
			//and if we do throw it then it would have to be handeled in all code 
			//making it bloated, it is hence a safe assumption this exception ideally will not
			//happen unless the file access has  issues
			System.out.println(ioException.toString());
		}
		finally{
			//close the input stream if it is not null
			if(inputStream !=null){
				try{
					inputStream.close();
				}
				catch(Exception ex){
					//if there is an issue closing it we just print it
					//and move forward as there is not a good way to inform user.
					System.out.println(ex.toString());
				}
			}
		}//end finally
	}//end method
	
	/**
	 * This method adds a key value pair to configuration
	 * @param key The configuration key
	 * @param value The configuration value
	 */
	private void addToConfigurationItem(String key,String value) {
		ConfigurationItem confirguationItem = new ConfigurationItem();
		confirguationItem.setKey(key);
		confirguationItem.setValue(value);
		this.configurations.put(key, confirguationItem);
	}
}