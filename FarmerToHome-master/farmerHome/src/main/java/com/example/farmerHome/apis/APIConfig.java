package com.example.farmerHome.apis;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class APIConfig extends ResourceConfig {
	public APIConfig() {
		
		register(BasketService.class);

	}
}
