package com.finleap.weatherforecast;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import com.finleap.weatherforecast.app.WeatherforecastApplication;

/**
 * 
 * @author Vinay Pandey WeatherforecastApplicationTests - to test the
 *         Application class.
 *
 */
@Configuration
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WeatherforecastApplication.class)
@ComponentScan(basePackages = { "com.finleap.weatherforecast.domain" })
public class WeatherforecastApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void main() {
		WeatherforecastApplication.main(new String[] {});
	}

}
