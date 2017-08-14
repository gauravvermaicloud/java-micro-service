package com.microservice.entities;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

/**
 * <p>This class is the base of all objects and allows core methods
 * to be consumed by all entities.</p>
 * @author gaurav
 *
 */
public class Base  implements Serializable{

	/**
	 * <p>This method checks if the given object is equal to the object</p>
	 * 
	 * <p>The object uses the benefit of toString to match equals by
	 * converting the objects to string. As the toString method on object
	 * has been overriden to improve results, this is a good enough implementation
	 * Better implementations may include conversion into a map and then checking
	 * each and every value</p>
	 * 
	 * @param obj <p> The object to be compared <p>
	 * 
	 */
	@Override 
	public boolean equals(Object obj) {
		return this.toString().equals(obj.toString());
	}
	
	/**
	 * <p>This method checks if two objects of a given type are equal or not<p>
	 * 
	 * <p>The object uses the benefit of toString to match equals by
	 * converting the objects to string. As the toString method on object
	 * has been overriden to improve results, this is a good enough implementation
	 * Better implementations may include conversion into a map and then checking
	 * each and every value</p>
	 * 
	 * @param object1 This is the first object to be compared.
	 * @param object2 This is the second object to be compared.
	 * 
	 */
	public  static boolean equals(Base object1, Base object2) {
		return object1.equals(object2);
	}

	/**
	 * <p>This method checks if two objects of a given type are equal or not<p>
	 * 
	 * <p>The object uses the benefit of toString to match equals by
	 * converting the objects to string. As the toString method on object
	 * has been overriden to improve results, this is a good enough implementation
	 * Better implementations may include conversion into a map and then checking
	 * each and every value</p>
	 * 
	 * @param object1 This is the first object to be compared.
	 * @param object2 This is the second object to be compared.
	 * 
	 */
	public  static boolean equals(Object object1, Object object2) {
		return Base.toJSON(object1).equals(Base.toJSON(object2));
	}
	
	/**
	 * <p> This method converts an object to an equivalent JSON String.
	 * The method essentially calls the toJSON to get a string that is JSON.
	 * <p>
	 * @param The JSON string
	 */
	@Override 
	public String toString() {
		return this.toJSON();
	}
	
	/**
	 * <p>This method returns a JSON string of current object. </p>
	 * @return A json equivalent of the object.
	 */
	public String toJSON() {
		return Base.toJSON(this);
	}
	
	/**
	 * <p>This method converts an object to JSON</p>
	 * @param object This is the object which is being converted to JSON
	 * @return The JSON string for the object.
	 */
	public static String toJSON(Object object) {
		String returnValue = null;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			returnValue= objectMapper.writeValueAsString(object);
		}catch(Exception ex) {
			//not bubbelliing this exception up
			//because this is a core piece of framework
			//expected to be used all over the place
			//we do not think there will be exception here at run time
			//and expect issues to be taken care at design.
			//if something does happen then it would lead to a null
			//pointer which can help us figure things out.
			System.out.println(ex.toString());
		}
		return returnValue;
	}
	
	/**
	 * <p>This method deserializes a JSON string to an object of type T</p>
	 * @param json The JSON string
	 * @param type The type into which the object is expected to be deserialized
	 * @return The object converted from string
	 */
	public static <T>  T fromJSON(String json, Class<T> type) {
		T t =null;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			t= (T) objectMapper.readValue(json, type);
		} catch (Exception ex) {
			//not bubbelliing this exception up
			//because this is a core piece of framework
			//expected to be used all over the place
			//we do not think there will be exception here at run time
			//and expect issues to be taken care at design.
			//if something does happen then it would lead to a null
			//pointer which can help us figure things out.
			System.out.println(ex.toString());		
		}
		return t;
	}
	
	/**
	 * This method converts an object to a map
	 * @return The map equivalent of the object
	 */
	public Map<String, Object> toMap(){
		return Base.toMap(this);
	}
	
	/**
	 * <p>Ths method converts an object into a map, the conversion makes te property name into
	 * key and the value into said value</p>
	 * @param obj The object to be converted
	 * @return The map
	 */
	public static Map<String,Object> toMap(Object obj){
		ObjectMapper objectMapper = new ObjectMapper();
		@SuppressWarnings("unchecked")
		Map<String,Object> map = objectMapper.convertValue(obj, Map.class);
		return map;
	}

	/**
	 * <p>This method converts a map into an object by mapping the properties with keys</p>
	 * @param map The map to be converted
	 * @param type The type to be converted
	 * @return The object it is coverted into.
	 */
	 public static <T> T fromMap(Map<String,Object> map, Class<T> type) {
		ObjectMapper objectMapper = new ObjectMapper();
		T t = (T)objectMapper.convertValue(map, type);
		return t;
	}
	 
	 /**
	  * This method converts a map into a JSON
	  * @param map The map to be be conerted into JSON
	  * @return The JSON
	  */
	 public static String mapToJson(Map<String,Object> map) {
		 String json = null;
		 try {
			 ObjectMapper objectMapper = new ObjectMapper();
			 json= objectMapper.writeValueAsString(map);
		} catch (Exception ex) {
			//not bubbelliing this exception up
			//because this is a core piece of framework
			//expected to be used all over the place
			//we do not think there will be exception here at run time
			//and expect issues to be taken care at design.
			//if something does happen then it would lead to a null
			//pointer which can help us figure things out.
			System.out.println(ex.toString());		
		}
		 return json;
	 }
	 
	/**
	 * <p>This method converts a json into a map</p> 
	 * @param json The json to be converted into a map
	 * @return The map
	 */
	@SuppressWarnings("unchecked")
	public static Map<String,Object> jsonToMap(String json){
		 Map<String,Object> map = null;
		 try {
			 ObjectMapper objectMapper = new ObjectMapper();
			 map = objectMapper.readValue(json,Map.class);
		} catch (Exception ex) {
			//not bubbelliing this exception up
			//because this is a core piece of framework
			//expected to be used all over the place
			//we do not think there will be exception here at run time
			//and expect issues to be taken care at design.
			//if something does happen then it would lead to a null
			//pointer which can help us figure things out.
			System.out.println(ex.toString());		
		}
		 return map;
	 }
	 
 
	/**
	 * <p>This method converts the object into an xml</p>
	 * @return The xml converted
	 */
	public String toXML() {
		return Base.toXML(this);
	}

	/**
	 * <p>This method converts an object into an xml</p>
	 * @param obj The object to be converted
	 * @return The xml
	 */
	public static String toXML(Object obj) {
		String xml = null;
		try {
			XmlMapper xmlMapper = new XmlMapper();
			xml = xmlMapper.writeValueAsString(obj);
		} catch (Exception ex) {
			//not bubbelliing this exception up
			//because this is a core piece of framework
			//expected to be used all over the place
			//we do not think there will be exception here at run time
			//and expect issues to be taken care at design.
			//if something does happen then it would lead to a null
			//pointer which can help us figure things out.
			System.out.println(ex.toString());		
		}
		return xml;
	}
	
	/**
	 * <p>This method converts an object into xml</p>
	 * @param xml The xml to e converter
	 * @param type The type to be converted
	 * @return The object returned
	 */
	public static <T> T fromXML(String xml,Class<T> type) {
		T t = null;
		try {
			XmlMapper xmlMapper = new XmlMapper();
			t = (T) xmlMapper.readValue(xml, type);
		} catch (Exception ex) {
			//not bubbelliing this exception up
			//because this is a core piece of framework
			//expected to be used all over the place
			//we do not think there will be exception here at run time
			//and expect issues to be taken care at design.
			//if something does happen then it would lead to a null
			//pointer which can help us figure things out.
			System.out.println(ex.toString());		
		}
		return t;
	}
	
	/**
	 * This method creates a clone of the given object
	 * @param object The object to be cloned
	 * @return The cloned object
	 */
	@SuppressWarnings("unchecked")
	public static <T> T clone(T object) {
		return (T)Base.fromMap(Base.toMap(object), object.getClass());
	}


	/**
	 * <p>This method returns a hash code for the object</p>
	 * @return The hashcode
	 */
	public int hashcode() {
		return this.toJSON().hashCode();
	}
}
