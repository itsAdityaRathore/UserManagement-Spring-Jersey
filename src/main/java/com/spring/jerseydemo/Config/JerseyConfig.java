package com.spring.jerseydemo.Config;

import com.spring.jerseydemo.Controller.UserController;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(UserController.class);
    }
}
