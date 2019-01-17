package com.tscloud.address.ui.restful;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;


@ApplicationPath("/rest/*")
public class RestApplication extends ResourceConfig {
	public RestApplication() {
		register(RestConstraint.class);
	}
}
