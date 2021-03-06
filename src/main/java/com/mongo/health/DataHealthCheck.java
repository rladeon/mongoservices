/**
 * 
 */
package com.mongo.health;

import com.codahale.metrics.health.HealthCheck;
import com.google.inject.Inject;
import com.mongodb.MongoClient;

/**
 * @author rudi
 *
 */
public class DataHealthCheck extends HealthCheck{

	private MongoClient mongo;
	 @Inject
    public DataHealthCheck(MongoClient mongo) {
        super();
        this.mongo = mongo;
    }
 
    @Override
    protected Result check() throws Exception {
        
    	mongo.getAddress();
        return Result.healthy();
    }

}
