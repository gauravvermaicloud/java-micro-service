package com.microservice.logger;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.microservice.common.RequestThreadLocal;
import com.microservice.configurations.ConfigurationManager;
import com.microservice.entities.LogEntity;

/**
 * This class logs all applications logs on a topic called logs.
 * The topic name essentially is the log level.
 * The logs are written onto Kafka producer which is then read by a background microservice
 * to read logs off kafka and put in a proper database.
 * 
 * @author gaurav
 *
 */
public class Logger {
	
	private static String hostName;

	/**
	 * This method returns true if the debug level is enabled or else false.
	 * @return True if the debug level is enabled or else false
	 */
	public boolean isDebugEnabled() {
		return Boolean.parseBoolean(ConfigurationManager.getInstance().get("IsDebugEnabled"));
	}

	/**
	 * This map allows controlling the instances of the logger, the idea is for one 
	 * class there should not be more than one logger.
	 */
	private static Map<Class, Logger> loggerMap = new HashMap<Class, Logger>();
	
	/**
	 * The name of the class requesting logger
	 */
	private Class loggingClass = null;
	
	
	/**
	 * This is a private constructor of the logger used to created logger.
	 */
	private Logger() {
		if(Logger.hostName ==null) {
			try {
				Logger.hostName = "";
				Logger.hostName+=InetAddress.getLocalHost().getHostName()+" : " +InetAddress.getLocalHost().getHostAddress();
			}
			catch(UnknownHostException ex) {
				Logger.hostName = "Unknown";
			}
		}
	}
	
	/**
	 * This method returns an instance of the logger
	 * @param loggingClass The logging class
	 * @return The logger
	 */
	public Logger getInstance(Class loggingClass) {
		Logger kafkaLogger = new Logger();
		kafkaLogger.loggingClass = loggingClass;
		loggerMap.put(loggingClass, kafkaLogger);	
		return kafkaLogger;
	}
	
	/**
	 * This method logs a given message at the level publisher which may then be consumed at a later stage
	 * @param logLevel This is the level of the log
	 * @param className This is the name of the class
	 * @param methodName This is the name of the method
	 * @param codeIdentifier This is the code identifier to find code location within the method
	 * @param message This is the user message
	 * @param throwable This is the throwable exception
	 * @param inputParameters The input parameters to the method
	 * @param returnValue The return value of the method
	 */
	private void log(String logLevel,String className,String methodName, String codeIdentifier, String message
			, Throwable throwable, List<Object> inputParameters, Object returnValue) {

		//First create a log entry
		LogEntity logEntity = new LogEntity(
				logLevel,
				Logger.hostName,	
				ConfigurationManager.getInstance().get(ConfigurationManager.APPLICATION_NAME) == null?"":ConfigurationManager.getInstance().get(ConfigurationManager.APPLICATION_NAME),
				ConfigurationManager.getInstance().get(ConfigurationManager.APPLICATION_VERSION) ==null?"":ConfigurationManager.getInstance().get(ConfigurationManager.APPLICATION_VERSION),
				RequestThreadLocal.getMicroserviceCommonRequestId()==null?"":RequestThreadLocal.getMicroserviceCommonRequestId(),
				RequestThreadLocal.getRequestId()==null?"":RequestThreadLocal.getRequestId(),
				RequestThreadLocal.getSession()==null?null:RequestThreadLocal.getSession(),
				RequestThreadLocal.getSession() == null ? null:RequestThreadLocal.getSession().getUser(),
				className==null?"":className,
				methodName==null?"":methodName,
				codeIdentifier==null?"":codeIdentifier,
				message==null?"":message,
				throwable,
				inputParameters==null?new ArrayList():inputParameters,
				returnValue==null?"":returnValue);
		//next publish it
		
	}
	
	/**
	 * This writes a log at debug level
	 * @param methodName The name of the method doing the logging
	 * @param codeIdentifier The identifer within the code to find where the lo was written within the method
	 * @param message The end user message
	 */
	public void logDebug(String methodName, String codeIdentifier, String message) {
		log("Debug",loggingClass.toString(), methodName,codeIdentifier,message,null, null,null);
	}
	
	/**
	 * This method is written on top of every method to define trace entry. The class name is being
	 * explictly passed in this overload as it will be used by AOP where the calling class and the 
	 * logging class will be different
	 * @param className The name of the class
	 * @param methodName The name of the method
	 * @param inputParamters The input parameters
	 */
	public void logTraceEntry(String className, String methodName, List<Object> inputParamters) {
		log("Trace",className,methodName,"TraceEntry","TraceEntry",null,inputParamters,null);
	}
	
	/**
	 * This method is written on top of every method to define trace entry.
	 * @param methodName The name of the method
	 * @param inputParamters Input parameters for the method
	 */
	public void logTraceEntry(String methodName, List<Object> inputParamters) {
		log("Trace",loggingClass.toString(),methodName,"TraceEntry","TraceEntry",null,inputParamters,null);
	}
	
	/**
	 * This method is written on top of every method to define trace entry. The class name is being
	 * explictly passed in this overload as it will be used by AOP where the calling class and the 
	 * logging class will be different
	 * @param className The name of the class
	 * @param methodName The name of the method
	 * @param inputParamters The input parameters
	 * @param returnValue This is the return value of the method
	 */
	public void logTraceExit(String className, String methodName, List<Object> inputParamters,Object returnValue) {
		log("Trace",className,methodName,"TraceExit","TraceExit",null,inputParamters,returnValue);
	}

	/**
	 * This method is written on end of every method to define trace entry.
	 * @param methodName The name of the method
	 * @param inputParamters The input parameters
	 * @param returnValue This is the return value of the method
	 */
	public void logTraceExit( String methodName, List<Object> inputParamters,Object returnValue) {
		log("Trace",loggingClass.toString(),methodName,"TraceExit","TraceExit",null,inputParamters,returnValue);
	}
	
	/**
	 * 
	 * This method is written on exception of every method to define trace entry. The class name is being
	 * explictly passed in this overload as it will be used by AOP where the calling class and the 
	 * logging class will be different
	 * @param methodName The name of the method
	 * @param inputParamters The input parameters
	 * @param th The exception
	 */
	public void logTraceException(String className, String methodName, List<Object> inputParamters,Throwable th) {
		log("Trace",className,methodName,"TraceException","TraceException",th,inputParamters,null);
	}

	/**
	 * 
	 * This method is written on exception of every method to define trace entry. 
	 * @param methodName The name of the method
	 * @param inputParamters The input parameters
	 * @param th The exception
	 */
	public void logTraceException(String methodName, List<Object> inputParamters,Throwable th) {
		log("Trace",loggingClass.toString(),methodName,"TraceException","TraceException",th,inputParamters,null);
	}	
	
	/**
	 * This writes a log at info level
	 * @param methodName The name of the method doing the logging
	 * @param codeIdentifier The identifer within the code to find where the lo was written within the method
	 * @param message The end user message
	 */
	public void logInfo(String methodName, String codeIdentifier, String message) {
		log("Info",loggingClass.getName().toString(),methodName,codeIdentifier,message,null,null,null);
	}

	/**
	 * This writes a log at warn level
	 * @param methodName The name of the method doing the logging
	 * @param codeIdentifier The identifer within the code to find where the lo was written within the method
	 * @param message The end user message
	 */
	public void logWarn(String methodName, String codeIdentifier, String message) {
		log("Warn",loggingClass.getName().toString(),methodName,codeIdentifier,message,null,null,null);
	}
	
	/**
	 * This writes a log at error level
	 * @param methodName The name of the method doing the logging
	 * @param codeIdentifier The identifer within the code to find where the lo was written within the method
	 * @param message The end user message
	 */	
	public void logError(String methodName, String codeIdentifier, String message) {
		log("Error",loggingClass.getName().toString(),methodName,codeIdentifier,message,null,null,null);
	}
	
	/**
	 * This method logs an exception
	 * @param methodName The name of the method
	 * @param codeIdentifier The location in the code where the log is made
	 * @param message  The user message
	 * @param th The exception thrown
	 */
	public void logException(String methodName, String codeIdentifier, String message,Throwable th) {
		log("Exception",loggingClass.getName().toString(),methodName,codeIdentifier,message,th,null,null);	
	}
	
	/**
	 * This writes a log at fatel level
	 * @param methodName The name of the method doing the logging
	 * @param codeIdentifier The identifer within the code to find where the lo was written within the method
	 * @param message The end user message
	 */
	public void logFatel(String methodName, String codeIdentifier, String message) {
		log("Error",loggingClass.getName().toString(),methodName,codeIdentifier,message,null,null,null);
	}
}
