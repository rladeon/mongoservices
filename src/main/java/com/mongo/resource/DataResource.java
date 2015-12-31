/**
 * 
 */
package com.mongo.resource;

import java.io.IOException;
import java.util.Map;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.bson.types.ObjectId;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.mongo.metier.Data;
import com.mongo.service.MongoService;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

/**
 * @author rudi
 *
 */

@Path("/add")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class DataResource {
	
	private MongoService data;
	@Inject
	
	public DataResource(MongoService collection2) {
        this.data = collection2;
    }
		
	/*@POST
    @Timed
    public Response publishNewData(@Context UriInfo uriInfo, Map<String, JsonNode> doc) {
		ObjectId id = new ObjectId();
		DBObject document = new BasicDBObject();
	      document.put("id", JsonNodeFactory.instance.textNode(id.toString()));
	      document.put("mark", JsonNodeFactory.instance.textNode(doc.get("mark").toString()));
	      collection.save(document);
	      return Response.created(uriInfo.getAbsolutePathBuilder().path(id.toString()).build())
	          .header("X-Document-ID", id.toString()).build();
    }*/
	// These steps seems to be SOP
	@POST
    @Timed
    public Response publishNewData(@Context UriInfo uriInfo,String inputJsonObj) throws JsonParseException, IOException {
		ObjectId id = new ObjectId();
    ObjectMapper mapper = new ObjectMapper();
    JsonFactory factory = mapper.getFactory();
    JsonParser jp = factory.createJsonParser( inputJsonObj );
    JsonNode actualObj = mapper.readTree( jp );
    
    // If you want a value
    String the_associated_value = actualObj.get( "mark").textValue();
    DBObject document = new BasicDBObject();
    document.put("id", JsonNodeFactory.instance.textNode(id.toString()));

    document.put("mark", the_associated_value);
    data.getCollection().save(document);
    return Response.created(uriInfo.getAbsolutePathBuilder().path(id.toString()).build())
        .header("X-Document-ID", id.toString()).build();
	}

}
