package pl.tymoteuszborkowski.configuration;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;
import pl.tymoteuszborkowski.controllers.Endpoint;

@Configuration
public class JerseyConfig extends ResourceConfig {


    public JerseyConfig() {
        register(Endpoint.class);
    }
}