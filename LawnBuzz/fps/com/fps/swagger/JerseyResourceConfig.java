package com.fps.swagger;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.fps.api.API;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;

@Component
@Configuration
@ApplicationPath("/api")
public class JerseyResourceConfig extends ResourceConfig {

    public JerseyResourceConfig() {
        /* Set package to scan for application Resources/Endpoints */
        
         register(API.class);
         configureSwagger();

    }
    private void configureSwagger() {
    	register(SwaggerSerializers.class);
    	register(ApiListingResource.class);
    	BeanConfig beanConfig = new BeanConfig();
    	beanConfig.setVersion("1.0.0");
    	beanConfig.setSchemes(new String[]{"http"});
    	beanConfig.setHost("localhost:8080");
    	beanConfig.setBasePath("/fps-api/api");
    	beanConfig.setTitle("Nucleus API Documentation");
		beanConfig.setDescription("Testbed for Nucleus API calls");
		beanConfig.getSwagger().addConsumes(MediaType.APPLICATION_JSON);
		beanConfig.getSwagger().addProduces(MediaType.APPLICATION_JSON);
    	beanConfig.setResourcePackage("com.fps.api");
    	beanConfig.setPrettyPrint(true);
    	beanConfig.setScan(true);
	}
}
