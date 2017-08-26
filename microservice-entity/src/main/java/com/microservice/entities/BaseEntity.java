package com.microservice.entities;

import java.io.Serializable;
import java.util.Date;

/**
 * This is the base entity of all business objects
 * @author gaurav
 *
 */
public abstract class BaseEntity extends Base implements Serializable{

	/**
	 * This is the time at which create entity
	 */
	private Date createDateTime;
	
	/**
	 * This is the update time for the entity
	 */
	private Date updateDateTime;
	
	/**
	 * This is the id of the entity
	 */
	private String id;
	
	/**
	 * This method checks if the entity is valid
	 * @return True if the entity is valid and false if the entity is not valid
	 */
	public abstract boolean isValid();

	/**
	 * This method returns the create time
	 * @return The create time
	 */
	public Date getCreateDateTime() {
		return createDateTime;
	}

	/**
	 * This method sets the create time of the object
	 * @param createDateTime The create time
	 */
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	/**
	 * This method returns the update time
	 * @return The update time
	 */
	public Date getUpdateDateTime() {
		return updateDateTime;
	}

	/**
	 * This method sets the update time of the object
	 * @param updateDateTime The updates time
	 */
	public void setUpdateDateTime(Date updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

	/**
	 * This method gets the id of the object
	 * @return The id of the object
	 */
	public String getId() {
		return id;
	}

	/**
	 * This method sets the id
	 * @param id The id of the object
	 */
	public void setId(String id) {
		this.id = id;
	}	
}
