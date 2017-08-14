package com.microservice.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * This class is the log entity.
 * @author gaurav
 *
 */
public class LogEntity implements Serializable{

	/**
	 * The default 0 parameter constructor
	 */
	public LogEntity() {
		
	}
	
	/**
	 * This constructor creates a log constructor
	 * @param logLevel The level of log, this will be the topic on Kafka where log will be published
	 * @param applicationName The name of the application
	 * @param applicationVersion The version of application
	 * @param microserviceRequestId The request id unique for request across microservices
	 * @param requestId The id of the request in the application
	 * @param session The session of the user
	 * @param user The user
	 * @param className The name of the class
	 * @param methodName The name of the method
	 * @param codeIdentifier The code identifier
	 * @param message The message
	 * @param throwable The exception
	 * @param inputParameters The parameters
	 * @param returnValue The return value
	 * @param serverName The name of the server
	 */
	public LogEntity(
			String 			logLevel,
			String			serverName,
			String 			applicationName,
			String 			applicationVersion,
			String			microserviceRequestId,
			String			requestId,
			Session			session,
			User 			user,
			String			className,
			String			methodName,
			String			codeIdentifier,
			String			message,
			Throwable		throwable,
			List<Object>	inputParameters,
			Object			returnValue
			) {
		this.logLevel = logLevel;
		this.applicationName = applicationName;
		this.applicationVersion = applicationVersion;
		this.microserviceRequestId = microserviceRequestId;
		this.requestId = requestId;
		this.session = session;
		this.user = user;
		this.className = className;
		this.methodName = methodName;
		this.codeIdentifier = codeIdentifier;
		this.message = message;
		this.throwable = throwable;
		this.inputParameters = inputParameters;
		this.returnValue=returnValue;
		this.serverName = serverName;
	}
	
	/**
	 * This is the name of the server from which log is done.
	 */
	private String serverName;
	
	/**
	 * This gets the thorable exception
	 * @return The Throwable
	 */
	public Throwable getThrowable() {
		return throwable;
	}

	/**
	 * This sets throwable
	 * @param throwable The throwable
	 */
	public void setThrowable(Throwable throwable) {
		this.throwable = throwable;
	}

	
	/**
	 * This is the date time for creation of the log
	 */
	private Date logDate = new Date();
	
	/**
	 * This is the name of the application.
	 */
	private String applicationName;
	
	/**
	 * This is the version of the application.
	 */
	private String applicationVersion;
	
	/**
	 * This is the microservice request id
	 */
	private String microserviceRequestId;
	
	/**
	 * This is the request id within the application
	 */
	private String requestId;
	
	/**
	 * This is the session for the object
	 */
	private Session session;
	
	/**
	 * This is the user under whose context the request is being fulfilled
	 */
	private User user;
	
	/**
	 * This is the name of the class which is doing the logging
	 */
	private String className;
	
	/**
	 * This is the name of the method in which logging is done
	 */
	private String methodName;
	
	/**
	 * This is the code identifier, like trace entry ot trace exist, trace exception or loops etc
	 */
	private String codeIdentifier;
	
	/**
	 * This is the user message
	 */
	private String message;
	
	/**
	 * This is the throwable for any exception/
	 */
	private Throwable throwable;
	
	/**
	 * This is a list of input parameters
	 */
	private List<Object> inputParameters;
	
	/**
	 * This is the return value;
	 */
	private Object returnValue;

	/**
	 * This is the level of the log
	 */
	private String logLevel;
	
	/**
	 * This returns the name of the application
	 * @return The application name
	 */
	public String getApplicationName() {
		return applicationName;
	}

	/**
	 * This sets the application name
	 * @param applicationName The name of the application
	 */
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	/**
	 * This gets the application version
	 * @return The application version
	 */
	public String getApplicationVersion() {
		return applicationVersion;
	}

	/**
	 * This sets the application version
	 * @param applicationVersion The application version
	 */
	public void setApplicationVersion(String applicationVersion) {
		this.applicationVersion = applicationVersion;
	}

	/**
	 * This gets microservice request id
	 * @return The microservice request id
	 */
	public String getMicroserviceRequestId() {
		return microserviceRequestId;
	}

	/**
	 * This sets microservice request id
	 * @param microserviceRequestId
	 */
	public void setMicroserviceRequestId(String microserviceRequestId) {
		this.microserviceRequestId = microserviceRequestId;
	}

	/**
	 * This gets request id
	 * @return The request id
	 */
	public String getRequestId() {
		return requestId;
	}

	/**
	 * This sets the request id
	 * @param requestId The request id
	 */
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	/**
	 * This gets the session
	 * @return The session
	 */
	public Session getSession() {
		return session;
	}

	/**
	 * This sets the session
	 * @param session The session
	 */
	public void setSession(Session session) {
		this.session = session;
	}

	/**
	 * This gets the user
	 * @return The user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * This sets the user 
	 * @param user The user
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * This gets the class name
	 * @return The class name
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * This sets the class name
	 * @param className The class name
	 */
	public void setClassName(String className) {
		this.className = className;
	}

	/**
	 * This gets the method name
	 * @return The method name
	 */
	public String getMethodName() {
		return methodName;
	}

	/**
	 * This sets the method name
	 * @param methodName The method name
	 */
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	/**
	 * This gets the code identifier
	 * @return The code identifier
	 */
	public String getCodeIdentifier() {
		return codeIdentifier;
	}

	/**
	 * This sets the code identifier
	 * @param codeIdentifier The code identifier
	 */
	public void setCodeIdentifier(String codeIdentifier) {
		this.codeIdentifier = codeIdentifier;
	}

	/**
	 * This gets the message
	 * @return The message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * This sets the message
	 * @param message The message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * This gets the input parameters
	 * @return The input parameters
	 */
	public List<Object> getInputParameters() {
		return inputParameters;
	}

	/**
	 * This sets the input parameters
	 * @param inputParameters The input parameters
	 */
	public void setInputParameters(List<Object> inputParameters) {
		this.inputParameters = inputParameters;
	}
	
	/**
	 * This gets the return value
	 * @return The return value
	 */
	public Object getReturnValue() {
		return returnValue;
	}

	/**
	 * This sets the return value
	 * @param returnValue The return value
	 */
	public void setReturnValue(Object returnValue) {
		this.returnValue = returnValue;
	}

	/**
	 * This gets the date for log
	 * @return The log
	 */
	public Date getLogDate() {
		return logDate;
	}

	/**
	 * This method returns a exception message
	 * @return The exception message
	 */
	public String getExceptionMessage() {
		return this.throwable.getMessage();
	}
	
	/**
	 * This gets the stack trace as a string
	 * @return The stack trace string
	 */
	public String getStackTrace() {
		//Create a string builder, which will have better perfomrance than string
		StringBuilder stackTraceString = new StringBuilder();
		//get the array of stack trace elements
		StackTraceElement[] stackTraceElements = throwable.getStackTrace();
		for (StackTraceElement stackTraceElement : stackTraceElements) {
			//for each element convert the message to string and seerate it with ::
			stackTraceString.append(stackTraceElement.toString() + " :=> ");
		}
		//return the constructre string
		return stackTraceString.toString();
	}

	/**
	 * This is the name of the server
	 * @return The name of the server
	 */
	public String getServerName() {
		return serverName;
	}

	/**
	 * Sets the nsmae of the server
	 * @param serverName The name of the server
	 */
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	
}
