package com.finleap.weatherforecast.dto;

/**
 * 
 * @author Vinay.Pandey
 * class - Weather dto which is send as response object
 */
public class Weather {
	
	private double averageTemperature;
	private double pressure;
	
	public Weather() {
		super();
	}
	public Weather(double averageTemperature, int pressure) {
		super();
		this.averageTemperature = averageTemperature;
		this.pressure = pressure;
	}
	public double getAverageTemperature() {
		return averageTemperature;
	}
	public void setAverageTemperature(double averageTemperature) {
		this.averageTemperature = averageTemperature;
	}
	public double getPressure() {
		return pressure;
	}
	public void setPressure(double pressure) {
		this.pressure = pressure;
	}
	@Override
	public String toString() {
		return "Weather [averageTemperature=" + averageTemperature
				+ ", pressure=" + pressure + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(averageTemperature);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(pressure);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Weather other = (Weather) obj;
		if (Double.doubleToLongBits(averageTemperature) != Double
				.doubleToLongBits(other.averageTemperature))
			return false;
		if (Double.doubleToLongBits(pressure) != Double
				.doubleToLongBits(other.pressure))
			return false;
		return true;
	}
	
}
