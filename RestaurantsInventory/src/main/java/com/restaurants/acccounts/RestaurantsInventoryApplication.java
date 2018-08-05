package com.restaurants.acccounts;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RestaurantsInventoryApplication {

	public static void main(String[] args) {
		ApplicationContext context =SpringApplication.run(RestaurantsInventoryApplication.class, args);
		System.out.println("******************************************************");
		/*ScheduledFolder readingRuby=context.getBean(ScheduledFolder.class);
		 readingRuby.scheduledMidNightfile();*/
	}
	
	
	
	
	
	
}
