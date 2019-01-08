package com.finleap.weatherforecast.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.finleap.weatherforecast.WeatherforecastApplicationTests;

/**
 * 
 * @author Vinay Pandey
 * WeatherForecastResthandlerTest is used to test the resthandler and in this class we are testing all the negative and positive scenarios.
 *
 */
public class WeatherForecastResthandlerTest extends
		WeatherforecastApplicationTests {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.build();
	}

	@Test
	public void RestHandlerTest_ToCheckTheStatus() throws Exception {
		mockMvc.perform(get("/data?city=Delhi,india")).andExpect(
				status().isOk());
	}

	@Test
	public void RestHandlerTest_IfCityIsNotvalid() throws Exception {
		mockMvc.perform(get("/data?city=De"))
				.andExpect(status().isBadRequest());
	}

}
