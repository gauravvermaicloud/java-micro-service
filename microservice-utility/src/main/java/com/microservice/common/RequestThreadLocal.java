package com.microservice.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.microservice.entities.RequestParameters;
import com.microservice.entities.Session;

/**
 * This class is a request thread local system, it allows setting up attributes required 
 * to make common items such as request id etc on thread.
 * @author gaurav
 *
 */
public class RequestThreadLocal {

	
	/**
	 * This is the instance of thread local
	 */
	public static final ThreadLocal<RequestParameters> threadLocal
		= new ThreadLocal<RequestParameters>();
	
	/**
	 * This sets the request id on thread
	 * @param requestId The request id
	 * @param microserviceCommonRequestId The microservice request id
	 * @param httpServletRequest The http request
	 * @param httpServletResponse The http response
	 * @param session The user session if available
	 */
	public static void setRequest(String requestId,String microserviceCommonRequestId,
			HttpServletRequest httpServletRequest
			, HttpServletResponse httpServletResponse, Session session){
		RequestParameters requestParameters = new RequestParameters();
		requestParameters.setRequestId(requestId);
		requestParameters.setMicroserviceCommonRequestId(microserviceCommonRequestId);
		requestParameters.setHttpServletRequest(httpServletRequest);
		requestParameters.setHttpResponse(httpServletResponse);
		requestParameters.setSession(session);
		RequestThreadLocal.threadLocal.set(requestParameters);
		
	}
	
	/**
	 * This gets the request id on thread
	 * @return The request id
	 */
	public static String getRequestId(){
		if(RequestThreadLocal.threadLocal.get() != null){ 
			return RequestThreadLocal.threadLocal.get().getRequestId();
		}
		else{
			return null;
		}
	}
	
	
	public static String getMicroserviceCommonRequestId() {
		if(RequestThreadLocal.threadLocal.get() != null){ 
			return RequestThreadLocal.threadLocal.get().getMicroserviceCommonRequestId();
		}
		else{
			return null;
		}
	}
	
	/**
	 * This gets the Session from thread
	 * @return The session
	 */
	public static Session getSession(){
		if(RequestThreadLocal.threadLocal.get() != null){ 
			return RequestThreadLocal.threadLocal.get().getSession();
		}
		else{
			return null;
		}
	}
	
	/**
	 * This method returns the Http request 
	 * @return The Http request
	 */
	public static HttpServletRequest getHttpRequest(){
		if(RequestThreadLocal.threadLocal.get() != null){
			return RequestThreadLocal.threadLocal.get().getHttpServletRequest();
		}
		else{
			return null;
		}
	}
	
	/**
	 * This method sends back the http response
	 * @return
	 */
	public static HttpServletResponse getHttpResponse(){
		if(RequestThreadLocal.threadLocal.get() != null){
			return RequestThreadLocal.threadLocal.get().getHttpServletResponse();
		}
		else{
			return null;
		}
	}
	
	/**
	 * This removes the request id from thread.
	 */
	public static void remove(){
		if(RequestThreadLocal.threadLocal != null){
			RequestThreadLocal.threadLocal.remove();
		}
	}
	
	/**
	 * This method sets a custom attribute on the thread
	 * @param key This is the key
	 * @return The object for the key
	 */
	public static Object getAttribute(String key){
		if(RequestThreadLocal.threadLocal != null){
			return RequestThreadLocal.threadLocal.get().getAttribute(key);
		}
		else{
			return null;
		}
	}
	
	/**
	 * This method sets a custom attribute on the thread
	 * @param key The key
	 * @param object The object
	 */
	public static void setAttribute(String key, Object object){
		if(RequestThreadLocal.threadLocal != null){
			RequestThreadLocal.threadLocal.get().setAttribute(key, object);
		}
	}

}
