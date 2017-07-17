package com.games24x7.streaming.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.games24x7.streaming.services.DownloadService;

@RestController
public class DownloadReportControllerSpring {

	@Autowired
	DownloadService service;

	@RequestMapping(value = "/downloadBoot", method = RequestMethod.GET)
	StreamingResponseBody getStream(HttpServletResponse response) {

		response.addHeader("Content-disposition", "attachment;filename=report.zip");
		response.setContentType("application/octet-stream");
		response.setStatus(HttpServletResponse.SC_OK);
		return service.getSpringStream("test.csv");
	}

}
