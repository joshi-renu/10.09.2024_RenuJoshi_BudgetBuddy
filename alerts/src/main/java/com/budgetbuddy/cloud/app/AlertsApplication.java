package com.budgetbuddy.cloud.app;

import com.budgetbuddy.cloud.app.service.AlertService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@Slf4j
public class AlertsApplication {

	public static void main(String[] args) {

		SpringApplication.run(AlertsApplication.class, args);
		printLogger();

	}
	//    slf4j - simple logging facade 4java- abstraction on top of logback
	// instead of below instance creation of logger, we can use @slf4j annotation and
	// instance will be created auto, then use log.info()
	private static final Logger logger = LoggerFactory.getLogger(AlertsApplication.class);

	public static void printLogger(){
		logger.error("print info using logger");
		logger.warn("warn logger");
		logger.info("print info using logger");
		logger.debug("debug logger");
		logger.trace("trace logger");

	}

}
