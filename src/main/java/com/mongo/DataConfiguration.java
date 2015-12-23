/**
 * 
 */
package com.mongo;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.meltmedia.dropwizard.mongo.MongoConfiguration;

import io.dropwizard.Configuration;


/**
 * @author rudi
 *
 */
public class DataConfiguration extends Configuration{

	@JsonProperty
	 protected MongoConfiguration mongo;
	 public MongoConfiguration getMongo() {
		    return mongo;
		  }
	 public void setMongo(MongoConfiguration m)
	 {
		 this.mongo = m;
	 }
	 
}
