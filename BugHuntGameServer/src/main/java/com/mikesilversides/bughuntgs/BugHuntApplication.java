package com.mikesilversides.bughuntgs;
 
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.assets.AssetsBundle; //Swagger-ui

import com.mikesilversides.bughuntgs.resources.*;
import com.mikesilversides.bughuntgs.core.*;
import com.mikesilversides.bughuntgs.health.*;

import com.wordnik.swagger.config.*;
import com.wordnik.swagger.jaxrs.config.DefaultJaxrsScanner;
import com.wordnik.swagger.jaxrs.listing.ApiDeclarationProvider;
import com.wordnik.swagger.jaxrs.listing.ApiListingResourceJSON;
import com.wordnik.swagger.jaxrs.listing.ResourceListingProvider;
import com.wordnik.swagger.jaxrs.reader.DefaultJaxrsApiReader;
import com.wordnik.swagger.reader.ClassReaders;

//Swagger-ui
import com.federecio.dropwizard.swagger.SwaggerBundle;

//Cross domain support 
import org.eclipse.jetty.servlets.CrossOriginFilter;
import java.util.EnumSet;
import javax.servlet.DispatcherType;

//logging support
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BugHuntApplication extends Application<BugHuntConfiguration> {
	final static Logger logger = LoggerFactory.getLogger(BugHuntApplication.class);
	
	public Scores theScores;
	
	public static void main(String[] args) throws Exception {
		logger.debug("BugHuntApplication main called");
		new BugHuntApplication().run(args);
	}
 
    @Override
    public void initialize(Bootstrap<BugHuntConfiguration> bootstrap) {
		logger.debug("BugJuntApplication.initialize called");
		
		bootstrap.addBundle(new SwaggerBundle());  // Swagger-ui
		     
        this.theScores = new Scores();
    }

    @Override
    public void run(BugHuntConfiguration configuration,
                    Environment environment) {
		logger.debug("BugHuntApplication.run called");
        final String defaultName = configuration.getDefaultName();
        
        // optional CORS support
        //environment.addFilter(CrossOriginFilter.class, "/*");  // 0.6 method
        environment.getApplicationContext().addFilter(CrossOriginFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));
        
        // Swagger Resource  - not needed when using SwaggerBundle
//        environment.jersey().register(new ApiListingResourceJSON());      
        // Swagger providers - not needed when using SwaggerBundle
//        environment.jersey().register(new ApiDeclarationProvider());
//        environment.jersey().register(new ResourceListingProvider());
        
        // Swagger Scanner, which finds all the resources for @Api Annotations
        ScannerFactory.setScanner(new DefaultJaxrsScanner());

        // Add the reader, which scans the resources and extracts the resource information
        ClassReaders.setReader(new DefaultJaxrsApiReader());
        
        // Set the swagger config options - doesn't seem to be required so taken out
        //SwaggerConfig config = ConfigFactory.config();
        //config.setApiVersion("1.0.1");
        //config.setBasePath("http://localhost:8080");
        
        // bughunt resource
        //environment.addResource(new BugHuntResource(theScores));
        environment.jersey().register(new BugHuntResource(theScores));
        
        
        //environment.addHealthCheck(new TemplateHealthCheck("dummy health check"));
        final TemplateHealthCheck healthCheck =
                new TemplateHealthCheck(configuration.getDefaultName());
        environment.healthChecks().register("DefaultName", healthCheck);       
//    	final BugHuntResource resource = new BugHuntResource(theScores);
//        final TemplateHealthCheck healthCheck =
//                new TemplateHealthCheck("dummy health check");
//            environment.healthChecks().register("template", healthCheck);
//    	environment.jersey().register(resource);
    
    }
}
