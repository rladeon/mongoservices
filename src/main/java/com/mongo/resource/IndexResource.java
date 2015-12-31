/**
 * 
 */
package com.mongo.resource;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.codahale.metrics.annotation.Timed;
import com.mongo.service.MongoService;
import javax.inject.Inject;
/**
 * @author rudi
 *
 */
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class IndexResource {

	/**
	 * 
	 */
	private MongoService data;
	@Inject
    public IndexResource(MongoService collection2) {
        this.data = collection2;
    }
	
	@GET
    @Produces(value = MediaType.APPLICATION_JSON)
    @Timed
    public List<?> index() {
    //return Arrays.asList(new Data("Peugeot","106",1997,new Fuel("Super Sans plomb"),0.95,0.80));
		
		com.mongodb.DBCursor cursor = data.getCollection().find();
	    List<Object> datas = new ArrayList<Object>();
	        
		try {
		    while (cursor.hasNext()) {
		    	datas.add(cursor.next());
		    }
		} finally {
		    cursor.close();
		}
		return datas;
    
    }
	

}
