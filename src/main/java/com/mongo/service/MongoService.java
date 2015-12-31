/**
 * 
 */
package com.mongo.service;

import com.mongodb.DBCollection;

/**
 * @author rudi
 *
 */
public interface MongoService {
	DBCollection getCollection();
	void setCollection(DBCollection o);

}
