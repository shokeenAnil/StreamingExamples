package com.games24x7.streaming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.games24x7"})
public class StreamingDemoMain {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(StreamingDemoMain.class, args);
	}
}