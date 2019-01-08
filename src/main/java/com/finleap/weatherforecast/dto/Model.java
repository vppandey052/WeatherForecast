package com.finleap.weatherforecast.dto;

import java.util.Date;
/**
 * 
 * @author Vinay.Pandey
 *	class Model - dto
 */
public class Model {

	private Date dt;
	private Main main;

	public Date getDt() {
		return dt;
	}

	public void setDt(Date dt) {
		this.dt = dt;
	}

	public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;
	}

}
