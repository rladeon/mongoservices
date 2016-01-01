/**
 * 
 */
package com.mongo.metier;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author rudi
 *
 */
public class Fuel {

	/**
	 * 
	 */
	@JsonProperty
	private String name;
	public Fuel(String _name) {
		// TODO Auto-generated constructor stub
		this.name = _name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
