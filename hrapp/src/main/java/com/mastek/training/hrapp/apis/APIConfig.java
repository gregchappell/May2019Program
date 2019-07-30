package com.mastek.training.hrapp.apis;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class APIConfig extends ResourceConfig {
	public APIConfig() {
		register(CORSFilter.class);
		register(EmployeeService.class);
		register(ProjectService.class);
		register(DepartmentService.class);
	}
}
