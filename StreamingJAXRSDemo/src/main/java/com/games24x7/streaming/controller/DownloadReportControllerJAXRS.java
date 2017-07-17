package com.games24x7.streaming.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.games24x7.streaming.services.DownloadService;

@Service
@Path("/")
public class DownloadReportControllerJAXRS {

	@Autowired
	DownloadService service;

	@GET
	@Path("/downloadJAX")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response getStream() {
		return Response.ok(service.getJAXRSStream("test.csv"))
				.header("Content-Disposition", "attachment; filename=report.zip").build();

	}

}
