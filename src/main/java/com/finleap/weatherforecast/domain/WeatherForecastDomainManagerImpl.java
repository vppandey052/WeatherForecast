package com.finleap.weatherforecast.domain;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.OptionalDouble;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.finleap.weatherforecast.dto.Model;
import com.finleap.weatherforecast.dto.Weather;
import com.finleap.weatherforecast.dto.WeatherForecast;

/**
 * 
 * @author Vinay.Pandey 
 * class - WeatherForecastDomainManagerImpl - Service layer
 *         - to write core business logic.
 */
@Component
public class WeatherForecastDomainManagerImpl implements
		WeatherForecastDomainManager {

	private static final Logger log = LoggerFactory.getLogger(WeatherForecastDomainManagerImpl.class);
	private static final int HTTP_BADREQUEST = 400;

	@Override
	public CompletableFuture<Weather> getAverageDailyTemperaturesInCelsius(
			String city) {
		HttpURLConnection url = null;
		try {
			log.info(" inside domain impl where the url is: " + city);

			url = (HttpURLConnection) new URL(
					"http://api.openweathermap.org/data/2.5/forecast?q="
							+ city
							+ "&mode=JSON&APPID=f0571e56b8855008cd61f9923e79ee79")
					.openConnection();

			log.info(" inside domain impl where the url is: " + url);
			writeParamsAndConnect(url, "GET");
			int responseCode = url.getResponseCode();
			if (responseCode == HTTP_BADREQUEST) {
				throw new RuntimeException("Specify valid city in the URL "
						+ url.getHeaderFields());
			}
			return CompletableFuture.completedFuture(readJson(url
					.getInputStream()));
		} catch (Exception e) {
			if (e instanceof FileNotFoundException) {
				throw new RuntimeException("Specify valid city in the URL");
			} else {
				throw new RuntimeException("OOPS!!! something went wrong" +e.getCause());
			}
		} finally {
			if (url != null) {
				url.disconnect();
			}
		}
	}

	private static <T extends HttpURLConnection> void writeParamsAndConnect(
			T url, String method) throws IOException {
		url.setRequestMethod(method);
		url.setDoOutput(true);
		url.setDoInput(true);
		url.connect();
	}

	public static Weather readJson(InputStream in) throws IOException {
		int ch = -1;
		String response = "";
		while ((ch = in.read()) > -1) {
			response += (char) ch;
		}
		in.close();
		log.info(" response " + response);

		log.info(" inside read json ");
		Weather weather = new Weather();
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		WeatherForecast readValue = mapper.readValue(response, WeatherForecast.class);

		List<Model> modelsInRange = readValue.getList()
				.stream()
				.filter(m -> isInBetween_instant(m.getDt()))
				.collect(Collectors.toList());
		OptionalDouble averagePressure = modelsInRange
				.stream()
				.filter(i -> isInBetween_instant(i.getDt()))
				.mapToInt(i -> i.getMain().getPressure()).average();
		OptionalDouble averageTemperature = modelsInRange
				.stream()
				.filter(i -> isInBetween_instant(i.getDt()))
				.mapToDouble(i -> i.getMain().getTemp()).average();

		weather.setAverageTemperature(averageTemperature.getAsDouble());
		weather.setPressure(averagePressure.getAsDouble());
		log.info(" exit ");
		return weather;
	}

	// Convert a java.util.Date into an Instant
	public static boolean isInBetween_instant(Date d) {
		Instant responseDate = Instant.ofEpochMilli(d.getTime());
		Instant instant = Instant.now();
		Instant fourthDayFromtoday = instant.plus(Duration.ofDays(4));
		return !(responseDate.isBefore(instant) || responseDate.isAfter(fourthDayFromtoday));
	}
	
}
