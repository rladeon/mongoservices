/**
 * 
 */
package com.mongo;

import javax.inject.Singleton;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Provides;
import com.mongo.resource.IndexResource;
import com.mongo.service.MongoService;
import com.mongo.service.MongoServiceImpl;

/**
 * @author rudi
 *
 */

public class DataModule implements Module{

	/**
	 * 
	 */
	private MongoService m;
	
	public DataModule(MongoService _m) 
	{
		// TODO Auto-generated constructor stub
		this.m = _m;
	}	
	public DataModule() 
	{
		// TODO Auto-generated constructor stub
		
	}

	@Provides
	@Singleton
	MongoService provideMongoService() {
	    return new MongoServiceImpl(m.getCollection());
	  }
	@Provides
	@Singleton
	IndexResource provideIndexResource() {
	    return new IndexResource(m);
	  }


	@Override
	public void configure(Binder arg0) {
		// TODO Auto-generated method stub
		
	}
}
