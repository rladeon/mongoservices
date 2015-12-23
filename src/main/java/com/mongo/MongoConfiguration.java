/**
 * 
 */
package com.mongo;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.dropwizard.Configuration;

/**
 * @author rudi
 *
 */
public class MongoConfiguration extends Configuration{
	@NotNull
    public String host;

    @Min(1)
    @Max(65535)
    public int port;

    @NotNull
    public String database;

    @NotNull
    public String user;

    @NotNull
    public String password;

}
