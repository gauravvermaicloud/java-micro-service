package com.microservice.database.core;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtility {


		/**
		 * This is the map of session factory, it has a session factory for each
		 * connection string file
		 */
		private static Map<String, SessionFactory> sessionFactoryMap = new HashMap<String, SessionFactory>();

		/**
		 * This is the name of the core connection string.
		 */
		private static String coreConnectionStringFileName = "core.mysql.hibernate.cfg.xml";

		/**
		 * This methods creates a new session factory.
		 * 
		 * @param hibernateCfgFileName
		 *            The name of the session factory configuration file
		 * @return An instance of session factory
		 */
		private static SessionFactory buildSessionFactory(
				String hibernateCfgFileName) {
				Configuration configuration = new Configuration();
				configuration.configure(hibernateCfgFileName);
				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
						.applySettings(configuration.getProperties()).build();
				return configuration.buildSessionFactory(serviceRegistry);	
		}

		/**
		 * Gets an instance of session factory for the given file name
		 * 
		 * @param hibernateCfgFileName
		 *            The name of hibernate configuration file
		 * @return The session factory
		 */
		public static SessionFactory getSessionFactory(
				String hibernateCfgFileName) {
			if (sessionFactoryMap.containsKey(hibernateCfgFileName)) {
				return sessionFactoryMap.get(hibernateCfgFileName);
			} else {
				SessionFactory sessionFactory = new org.hibernate.cfg.Configuration()
						.configure(hibernateCfgFileName).buildSessionFactory();
				sessionFactoryMap.put(hibernateCfgFileName, sessionFactory);
				return sessionFactoryMap.get(hibernateCfgFileName);
			}
		}

		/**
		 * This method returns session factory for core connection file
		 * 
		 * @return The session factory
		 */
		public static SessionFactory getSessionFactory() {
			return HibernateUtility.getSessionFactory(coreConnectionStringFileName);
		}

		/**
		 * This method closes the core connection
		 */
		public static void close() {
			HibernateUtility.close(coreConnectionStringFileName);
		}

		/**
		 * This method closes the connection for the given file name
		 * 
		 * @param hibernateCfgFileName
		 *            The hibernate config file.
		 */
		public static void close(String hibernateCfgFileName) {
			if (sessionFactoryMap.containsKey(hibernateCfgFileName)) {
				sessionFactoryMap.get(hibernateCfgFileName).close();
			}
		}
}
