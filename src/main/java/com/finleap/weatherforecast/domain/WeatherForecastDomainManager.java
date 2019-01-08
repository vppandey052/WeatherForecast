package com.finleap.weatherforecast.domain;

import java.util.concurrent.CompletableFuture;

import com.finleap.weatherforecast.dto.Weather;

/**
 * 
 * @author Vinay.Pandey
 * interface - WeatherForecastDomainManager
 */
public interface WeatherForecastDomainManager {
	
	CompletableFuture<Weather> getAverageDailyTemperaturesInCelsius(String city) throws Exception;
	
}
