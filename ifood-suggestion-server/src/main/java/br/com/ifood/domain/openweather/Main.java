package br.com.ifood.domain.openweather;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

public class Main implements Serializable {
	
	@JsonProperty("temp")
	private Float temperature;
	
	public Float getTemperature() {
		return temperature;
	}
	
	public void setTemperature(Float temperature) {
		this.temperature = temperature;
	}
}
