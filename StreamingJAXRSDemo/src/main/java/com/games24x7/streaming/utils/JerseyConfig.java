package com.games24x7.streaming.utils;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import com.games24x7.streaming.controller.DownloadReportControllerJAXRS;

@Configuration
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		registerEndpoints();
	}

	private void registerEndpoints() {
		register(DownloadReportControllerJAXRS.class);
	}

}
