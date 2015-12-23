/**
 * 
 */
package com.mongo;

import com.meltmedia.dropwizard.mongo.MongoBundle;

import com.mongo.health.DataHealthCheck;
import com.mongo.managed.MongoManaged;
import com.mongo.resource.DataResource;
import com.mongo.resource.IndexResource;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * @author rudi
 *
 *
 */
public class DataApplication extends Application<DataConfiguration>{
	public static void main(String[] args) throws Exception {
        new DataApplication().run(args);
    }
	MongoBundle<DataConfiguration> mongoBundle;

	@Override
	public void initialize(Bootstrap<DataConfiguration> bootstrap) {
		
	  bootstrap.addBundle(mongoBundle = MongoBundle.<DataConfiguration>builder()
	    .withConfiguration(DataConfiguration::getMongo)
	    .build());
	}
	
	 @Override
	    public String getName() {
	        return "data";
	    }
	@Override
	public void run(DataConfiguration configuration, Environment environment) throws Exception {
		// TODO Auto-generated method stub
		DB db = mongoBundle.getDB();
		DBCollection collection = db.getCollection(getName());
		
		MongoClient cl = mongoBundle.getClient();
        environment.healthChecks().register("MongoHealthCheck", new DataHealthCheck(cl));
        MongoManaged mongoManaged = new MongoManaged(cl);
        environment.lifecycle().manage(mongoManaged);        
       
        environment.jersey().register(new IndexResource(collection));
        environment.jersey().register(new DataResource(collection));
   	   
	}
	
}