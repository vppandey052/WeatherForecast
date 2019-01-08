package com.finleap.weatherforecast.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 
 * @author Vinay.Pandey
 * class WeatherforecastApplication 
 * instantiation of spring boot application 
 */
@SpringBootApplication
@ComponentScan(basePackages ="com.finleap.weatherforecast")
public class WeatherforecastApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherforecastApplication.class, args);
	}

}

