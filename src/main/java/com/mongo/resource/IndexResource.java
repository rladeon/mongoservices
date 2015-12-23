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
import com.mongo.metier.Data;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
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
	private DBCollection collection;
	 
    public IndexResource(DBCollection collection2) {
        this.collection = collection2;
    }
	
	@GET
    @Produces(value = MediaType.APPLICATION_JSON)
    @Timed
    public List<String> index() {
    //return Arrays.asList(new Data("Peugeot","106",1997,new Fuel("Super Sans plomb"),0.95,0.80));
		
		com.mongodb.DBCursor cursor = collection.find();
	    List<String> datas = new ArrayList<String>();
	        
		try {
		    while (cursor.hasNext()) {
		       // System.out.println(cursor.next().toJson());
		    	String data = cursor.next().toString();
	            datas.add(data);
		    }
		} finally {
		    cursor.close();
		}
		return datas;
    
    }
	

}
