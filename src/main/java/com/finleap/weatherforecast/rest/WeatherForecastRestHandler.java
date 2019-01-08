package com.finleap.weatherforecast.rest;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.finleap.weatherforecast.domain.WeatherForecastDomainManager;
import com.finleap.weatherforecast.domain.WeatherForecastDomainManagerImpl;
import com.finleap.weatherforecast.dto.Weather;

@RestController
@Component
public class WeatherForecastRestHandler {
	
	private static final Logger log = LoggerFactory.getLogger(WeatherForecastDomainManagerImpl.class);
	
	@Autowired
	private WeatherForecastDomainManager weatherForecastDomainManager;
	
	@RequestMapping(value = "/data", method = RequestMethod.GET, produces = "application/json")
	public CompletableFuture<Weather>  getAverageWeatherMetrics(@RequestParam(required = true, value = "city") String city) throws Exception{
		log.info("inside rest Handler to fetch average weather metrics" + city);
		return weatherForecastDomainManager.getAverageDailyTemperaturesInCelsius(city);
	}

}
