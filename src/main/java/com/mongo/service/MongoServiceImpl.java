/**
 * 
 */
package com.mongo.service;

import com.mongodb.DBCollection;

/**
 * @author rudi
 *
 */
public class MongoServiceImpl implements MongoService {

	/**
	 * 
	 */
	private DBCollection collection;
	public MongoServiceImpl(DBCollection o) {
		// TODO Auto-generated constructor stub
		this.collection = o;
	
	}

	public MongoServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public DBCollection getCollection() {
		// TODO Auto-generated method stub
		return collection;
	}

	@Override
	public void setCollection(DBCollection o) {
		// TODO Auto-generated method stub
		collection = o;
	}

}
