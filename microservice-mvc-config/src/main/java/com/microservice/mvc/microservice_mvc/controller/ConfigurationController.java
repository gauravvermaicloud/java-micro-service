package com.microservice.mvc.microservice_mvc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.microservice.database.core.HibernateUtility;
import com.microservice.entities.ConfigurationItem;

/**
 * <p>This class is used to obtain configuration for a given application.
 * It should be noted this service is not the leading way to implement any service
 * for example we have not used Kafkalogs, proper Hibernate based mapping and so on
 * this is because this service is base or core to everything else and is not
 * expected to change too much. Thus we have taken a large number of liberties on
 * how to implement this. In any case this should not be a model on how to develop 
 * services.
 * 
 * Infact to get this working some code has been duplicated and is not being used from
 * util libraries, this is because this code will not have logging enabled.
 * 
 * This means logs will be written on System.out.println 
 * 
 * This class is a bootstrap class which means it is executed before anything else
 * can be and hence we have to rely upon this to be working before we can
 * have any application piece working.
 * </p>
 * @author gaurav
 *
 */
@Controller
public class ConfigurationController {

	/**
	 * This method returns all the configurations across all enviornments
	 * and machines
	 * @return A map of all the configurations
	 */
	@RequestMapping(value="/configurations", method=RequestMethod.GET)
	public @ResponseBody Map<String,ConfigurationItem> get(){
		Map<String,ConfigurationItem> configurationMap = new HashMap<String, ConfigurationItem>();
		Session session = null;
		try {
			//now ideally this is not the right way, we should be using
			//BaseDataAccess layer, things like business layers and DI
			//however for this one service we waver off as we want fewer number
			//of possible points of failure.			
			session = HibernateUtility.getSessionFactory().openSession();
			//get all config items
			List<ConfigurationItem>configurationItems=  session.createCriteria(ConfigurationItem.class).list();
			this.mapConfigurations(configurationItems, configurationMap);
		}
		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return configurationMap;
	}

	/**
	 * This method gets the configuration for a given application
	 * @param applicationName The name of the application
	 * @param applicationVersion The version of application
	 * @param machineName The machine name
	 * @return Configuration map
	 */
	@RequestMapping(
			value="/configurations/{applicationName}/{applicationVersion}/{machineName}"
			, method=RequestMethod.GET)
	public @ResponseBody Map<String,ConfigurationItem> getFor(
			@PathVariable("applicationName") String applicationName,
			@PathVariable("applicationVersion") String applicationVersion,
			@PathVariable("machineName") String machineName
			){
		 Map<String,ConfigurationItem> configurationMap = new HashMap<String, ConfigurationItem>();
		 configurationMap = mapConfigurations(
				 this.getConfigurationFor("ALL","ALL","ALL"), configurationMap);
		 configurationMap = mapConfigurations(
				 this.getConfigurationFor(applicationName,"ALL","ALL"), configurationMap);
		 configurationMap = mapConfigurations(
				 this.getConfigurationFor("ALL",applicationVersion,"ALL"), configurationMap);
		 configurationMap = mapConfigurations(
				 this.getConfigurationFor("ALL","ALL",machineName), configurationMap);
		 configurationMap = mapConfigurations(
				 this.getConfigurationFor(applicationName,applicationVersion,"ALL"), configurationMap);
		 configurationMap = mapConfigurations(
				 this.getConfigurationFor(applicationName,applicationVersion,machineName), configurationMap);
		 return configurationMap;
	}

	/**
	 * This method gets configuration for a given application, version and machine.
	 * This is the configuration api expected to be used by end services for configuration management.
	 * @param applicationName The name of the application
	 * @param version The Version of the application
	 * @param machineName The name of the machine
	 * @return A map of configuration items
	 */
	private List<ConfigurationItem> getConfigurationFor(String applicationName, String version, String machineName){
		 Map<String, Object> queryParameters = new HashMap<String, Object>();
		 queryParameters.put("ApplicationName",applicationName);
		 queryParameters.put("Version", version);
		 queryParameters.put("MachineName", machineName);
		 List<ConfigurationItem> configurationItems = this.getConfiguration(queryParameters);
		 return configurationItems;
	}
	
	/**
	 * This method execues a hsql api
	 * @param hSQLQuery The hsql query
	 * @param queryParameters The query parameters
	 * @return a list of type
	 */
	private <T> List<T> executeSelect(String hSQLQuery,
			Map<String, Object> queryParameters) {
		Session session = null;
		try {
			// open a session
			session = HibernateUtility.getSessionFactory().openSession();
			// begin a transaction
			Transaction transaction = session.beginTransaction();
			// get the user using a hsql query
			Query query = session.createQuery(hSQLQuery);

			for (String key : queryParameters.keySet()) {
				query.setParameter(key, queryParameters.get(key));
			}
			List<T> ts = query.list();
			transaction.commit();
			return ts;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		} // end finally
	}// end method
	
	/**
	 * Executes a given query for the query parameters
	 * @param queryParameters The parameter values
	 * @return The list of configuration items
	 */
	private List<ConfigurationItem> getConfiguration(Map<String,Object> queryParameters){
		return 
				this.executeSelect(
						"From Configuration C Where C.applicationName = :ApplicationName and C.version = :Version and C.machineName = :MachineName"
						, queryParameters);
	}
	
	/**
	 * <p>
	 * This method converts a list of configuration items to a map
	 * </p>
	 * @param configurationItems This is a list of configuration items
	 * @param configurationMap A map of configuration item
	 * @return A map of configuration items
	 */
	private Map<String,ConfigurationItem> mapConfigurations(List<ConfigurationItem> configurationItems, Map<String,ConfigurationItem> configurationMap){
		
		if(configurationMap == null) {
			configurationMap = new HashMap<String, ConfigurationItem>();
		}		
		
		for(ConfigurationItem configurationItem : configurationItems) {
			configurationMap.put(configurationItem.getKey(), configurationItem);
		}
		return configurationMap;
	}
}
