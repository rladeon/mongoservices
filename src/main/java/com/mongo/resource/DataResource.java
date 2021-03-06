/**
 * 
 */
package com.mongo.resource;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongo.metier.Data;
import com.mongo.service.MongoService;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
/**
 * @author rudi
 *
 */

@Path("/crud")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class DataResource {
	
	private MongoService data;
	@Inject	
	public DataResource(MongoService collection2) {
        this.data = collection2;
    }
	@Path("/add")	
	@POST
    @Timed
    public Response publishNewData(@Context UriInfo uriInfo,String inputJsonObj) throws JsonParseException, IOException {
		String id = UUID.randomUUID().toString();
	    ObjectMapper mapper = new ObjectMapper();
	    JsonFactory factory = mapper.getFactory();
	    JsonParser jp = factory.createJsonParser( inputJsonObj );
	    JsonNode actualObj = mapper.readTree( jp );
	    
	    // If you want a value
	    String mark = actualObj.get( "mark").textValue();
	    String model = actualObj.get( "model").textValue();
	    String serie = actualObj.get( "serie").textValue();
	    Data d = new Data(mark, model, serie); // Create java object
	    	    
	    DBObject document = new BasicDBObject();
	
	    document.put("_id", d.getId());
	    document.put("mark", d.getMark());
	    document.put("model", d.getModel());
	    document.put("serie", d.getSerie());
	    
	    data.getCollection().save(document);
	    
	    return Response.created(uriInfo.getAbsolutePathBuilder().path(id.toString()).build())
	        .header("X-Document-ID", id.toString()).build();
	}
	@Path("/edit/{id}")	
	@POST
    @Timed
    public Response editData(@Context UriInfo uriInfo,@PathParam(value = "id") String _id, String inputJsonObj) throws JsonParseException, IOException {
		String id = UUID.randomUUID().toString();

		ObjectMapper mapper = new ObjectMapper();
		JsonFactory factory = mapper.getFactory();
		JsonParser jp = factory.createJsonParser( inputJsonObj );
		JsonNode actualObj = mapper.readTree( jp );
    
	    // If you want a value
	    String mark = actualObj.get( "mark").textValue();
	    String model = actualObj.get( "model").textValue();
	    String serie = actualObj.get( "serie").textValue();
	    
	    DBObject document = new BasicDBObject();
	    DBObject query = new BasicDBObject();
	    
	    document.put("mark", mark);
	    document.put("model", model);
	    document.put("serie", serie);
	    query.put("_id", _id);
	    data.getCollection().update(query, document);
    
	    return Response.created(uriInfo.getAbsolutePathBuilder().path(id.toString()).build())
        .header("X-Document-ID", id.toString()).build();
	}
	@Path("/delete/{id}")	
	@DELETE
    @Timed
    public Response deleteData(@Context UriInfo uriInfo,@PathParam(value = "id") String _id) throws JsonParseException, IOException {
		String id = UUID.randomUUID().toString();
	    DBObject o = new BasicDBObject();
	    o.put("_id", _id);
		data.getCollection().remove(o);		
    
		return Response.created(uriInfo.getAbsolutePathBuilder().path(id.toString()).build())
        .header("X-Document-ID", id.toString()).build();
	}
	@Path("/query/{id}")	
	@GET
    @Timed
    public List<Object> queryDataId(@Context UriInfo uriInfo,@PathParam(value = "id") String _id) throws JsonParseException, IOException {
		List<Object> res = new ArrayList<Object>();
	    DBObject ref = new BasicDBObject();
	    ref.put("_id", _id);
	    com.mongodb.DBCursor cursor = data.getCollection().find(ref);		

		try 
		{
			while (cursor.hasNext()) 
			{
			  	res.add(cursor.next());
			}
		}
		finally 
		{
			    cursor.close();
		}
		return res;
	}
	@Path("/querymark/{keyword}")	
	@GET
    @Timed
    public List<Object> queryDataMark(@Context UriInfo uriInfo,@PathParam(value = "keyword") String words) throws JsonParseException, IOException 
	{
		List<Object> res = new ArrayList<Object>();
		BasicDBObject ref = new BasicDBObject();

	    	    ref.put("mark", words);

	    		 com.mongodb.DBCursor cursor = data.getCollection().find(ref);		

	    			try  
	    			{
	    				while (cursor.hasNext()) 
	    				{
	    				  	res.add(cursor.next());
	    				}
	    			}
	    			finally 
	    			{
	    				    cursor.close();
	    			}

	    return res;
	}
	
	@Path("/querymodel/{keyword}")	
	@GET
    @Timed
    public List<Object> queryDataModel(@Context UriInfo uriInfo,@PathParam(value = "keyword") String words) throws JsonParseException, IOException 
	{
		List<Object> res = new ArrayList<Object>();
		BasicDBObject ref = new BasicDBObject();

	    	    ref.put("model", words);

	    		 com.mongodb.DBCursor cursor = data.getCollection().find(ref);		

	    			try  
	    			{
	    				while (cursor.hasNext()) 
	    				{
	    				  	res.add(cursor.next());
	    				}
	    			}
	    			finally 
	    			{
	    				    cursor.close();
	    			}

	    return res;
	}
	
	@Path("/queryserie/{keyword}")	
	@GET
    @Timed
    public List<Object> queryDataSerie(@Context UriInfo uriInfo,@PathParam(value = "keyword") String words) throws JsonParseException, IOException 
	{
		List<Object> res = new ArrayList<Object>();
		BasicDBObject ref = new BasicDBObject();

	    	    ref.put("serie", words);

	    		 com.mongodb.DBCursor cursor = data.getCollection().find(ref);		

	    			try  
	    			{
	    				while (cursor.hasNext()) 
	    				{
	    				  	res.add(cursor.next());
	    				}
	    			}
	    			finally 
	    			{
	    				    cursor.close();
	    			}

	    return res;
	}
}
