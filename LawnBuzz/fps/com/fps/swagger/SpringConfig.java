package com.fps.swagger;

import java.util.Set;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
public class SpringConfig extends ResourceConfig {

  public SpringConfig() {
	  final String myRestPackage = "com.fps.api";
      final String jacksonPackage = "org.codehaus.jackson.jaxrs";

      final String swaggerJaxrsJsonPackage = "com.wordnik.swagger.jaxrs.json";
      final String swaggerJaxrsListingPackage = "com.wordnik.swagger.jaxrs.listing";

      packages(swaggerJaxrsJsonPackage, swaggerJaxrsListingPackage, jacksonPackage, myRestPackage);

      // enable multipart
      register(MultiPartFeature.class);
  }

 
}
