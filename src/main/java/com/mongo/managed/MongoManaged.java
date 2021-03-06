/**
 * 
 */
package com.mongo.managed;



import com.google.inject.Inject;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;

import io.dropwizard.lifecycle.Managed;

/**
 * @author rudi
 *
 */
public class MongoManaged implements Managed{

	/**
	 * 
	 */
	private Mongo mongo;
	 @Inject
    public MongoManaged(MongoClient mongo) {
        this.mongo = mongo;
    }
 
    public void start() throws Exception {
    	
    }
 
    public void stop() throws Exception {
        mongo.close();
    }
}
