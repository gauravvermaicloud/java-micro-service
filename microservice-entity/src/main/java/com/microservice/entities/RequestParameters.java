package com.microservice.entities;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestParameters  implements Serializable{
	/**
	 * This is the request Id, there is one unique id per request
	 */
	private String requestId;
	
	/**
	 * This is the distributed id of the request across microservices.
	 */
	private String microserviceCommonRequestId;
	
	/**
	 * This is http request
	 */
	
	private HttpServletRequest httpServletRequest;
	
	/**
	 * This is the session of the request
	 */
	private Session session;
	
	/**
	 * This returns the request id
	 * @return The request id
	 */
	public String getRequestId() {
		return requestId;
	}
	
	/**
	 * This sets the request id
	 * @param requestId
	 */
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	
	/**
	 * This returns the http request
	 * @return The http request
	 */
	public HttpServletRequest getHttpServletRequest() {
		return httpServletRequest;
	}
	
	/**
	 * This sets the http request
	 * @param httpServletRequest The http request
	 */
	public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
		this.httpServletRequest = httpServletRequest;
	}

	/**
	 * This gets the session
	 * @return The instance of session
	 */
	public Session getSession() {
		return session;
	}

	/**
	 * This sets the session
	 * @param session The instance of session.
	 */
	public void setSession(Session session) {
		this.session = session;
	}
	
	/**
	 * This is the http servlet response
	 */
	private HttpServletResponse httpServletResponse;
	
	/**
	 * This method sets the http servlet response
	 * @param httpServletResponse
	 */
	public void setHttpResponse(HttpServletResponse httpServletResponse){
		this.httpServletResponse = httpServletResponse;
	}
	
	/**
	 * This method returns the Http response associated with rwquest
	 * @return
	 */
	public HttpServletResponse getHttpServletResponse(){
		return this.httpServletResponse;
	}
	
	/**
	 * This is a dictionary to define custom attributes on thread.
	 * These attributes will be removed once the thread is out
	 * of execution context via web or job
	 */
	private Map<String, Object> attributes = new HashMap<String,Object>();
	
	/**
	 * Gets the object for the attribute
	 * @param key This is the key
	 * @return The object
	 */
	public Object getAttribute(String key){
		return attributes.get(key);
	}
	
	/**
	 * Puts an attribute on thread context
	 * @param key The key
	 * @param object The object
	 */
	public void setAttribute(String key, Object object){
		attributes.put(key, object);
	}

	/**
	 * This returns the micro service common request id for the request.
	 * This id is common across a request spanning multiple microservices.
	 * @return The microservice request id
	 */
	public String getMicroserviceCommonRequestId() {
		return microserviceCommonRequestId;
	}

	/**
	 * This sets the microservice request Id
	 * @param microserviceCommonRequestId The microservice request id which is common across
	 * objects.
	 */
	public void setMicroserviceCommonRequestId(String microserviceCommonRequestId) {
		this.microserviceCommonRequestId = microserviceCommonRequestId;
	}
}
