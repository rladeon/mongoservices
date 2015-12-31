/**
 * 
 */
package com.mongo;

import com.fiestacabin.dropwizard.guice.AutoConfigApplication;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.meltmedia.dropwizard.mongo.MongoBundle;

import com.mongo.health.DataHealthCheck;
import com.mongo.managed.MongoManaged;
import com.mongo.resource.DataResource;
import com.mongo.resource.IndexResource;
import com.mongo.service.MongoService;
import com.mongo.service.MongoServiceImpl;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import javax.inject.Inject;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * @author rudi
 *
 *
 */
public class DataApplication extends AutoConfigApplication<DataConfiguration>{
		
	@Inject
	private MongoService m;
	
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
		protected Injector createInjector(DataConfiguration configuration) {
		 	DB db = mongoBundle.getDB();
		 	m = new MongoServiceImpl(); 
			m.setCollection( db.getCollection(getName()) );
			return Guice.createInjector(new DataModule(m));
		}
		
		@Override
		protected void runWithInjector(
				DataConfiguration configuration, Environment environment,
				Injector injector) throws Exception {
			super.runWithInjector(configuration, environment, injector);
		}
	/*@Override
	public void run(DataConfiguration configuration, Environment environment) throws Exception {
		// TODO Auto-generated method stub
		DB db = mongoBundle.getDB();
		collection.setCollection( db.getCollection(getName()) );
		
		MongoClient cl = mongoBundle.getClient();
        environment.healthChecks().register("MongoHealthCheck", new DataHealthCheck(cl));
        MongoManaged mongoManaged = new MongoManaged(cl);
        environment.lifecycle().manage(mongoManaged);        
        
       // DataComponent objectGraph = DaggerDataComponent.builder.dataModule(new DataModule(collection)).build;
                
       // environment.jersey().register(objectGraph.indexResource());
        //environment.jersey().register(new IndexResource(collection));
        //environment.jersey().register(new DataResource(collection));
   	   
	}*/
	
}
