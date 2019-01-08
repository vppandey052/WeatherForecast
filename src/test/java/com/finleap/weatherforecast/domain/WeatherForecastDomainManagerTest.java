package com.finleap.weatherforecast.domain;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.concurrent.CompletableFuture;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.finleap.weatherforecast.WeatherforecastApplicationTests;
import com.finleap.weatherforecast.dto.Weather;

/**
 * 
 * @author Vinay Pandey 
 * WeatherForecastDomainManagerTest - to test the Service layer.
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WeatherforecastApplicationTests.class)
public class WeatherForecastDomainManagerTest {

	@Autowired
	private WeatherForecastDomainManager weatherForecastDomainManager;

	@Test
	public void domain_manager_with_valid_request() throws Exception {
		CompletableFuture<Weather> averageDailyTemperaturesInCelsius = weatherForecastDomainManager
				.getAverageDailyTemperaturesInCelsius("Delhi,india");
		Weather weather = averageDailyTemperaturesInCelsius.get();
		Assert.assertNotNull(weather.getPressure());
		Assert.assertNotNull(weather.getAverageTemperature());
	}

	@Test
	public void domain_managertest_with_invalid_request() {
		CompletableFuture<Weather> averageDailyTemperaturesInCelsius;
		try {
			averageDailyTemperaturesInCelsius = weatherForecastDomainManager
					.getAverageDailyTemperaturesInCelsius("De");
			averageDailyTemperaturesInCelsius.get();
		} catch (Exception e) {
			assertThat(e.getMessage(),
					containsString("Specify valid city in the URL"));
		}
	}
	
	@Test
	public void check_readjson_method_with_valid_json() throws Exception {
		InputStream responseJson = new FileInputStream("C:\\Weather_Forecast\\weatherforecast\\JsonResponse.txt");
		Weather readJson = WeatherForecastDomainManagerImpl.readJson(responseJson);
		Assert.assertNotNull(readJson.getAverageTemperature());
		Assert.assertNotNull(readJson.getPressure());
	}
	
	@Test
	public void check_readjson_method_with_invalid_json() throws Exception {
		 boolean notEpochTime = WeatherForecastDomainManagerImpl.isInBetween_instant(new Date());
		Assert.assertNotNull(notEpochTime);
		Assert.assertTrue(notEpochTime);
	}
}
