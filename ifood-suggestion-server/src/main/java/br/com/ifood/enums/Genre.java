package br.com.ifood.enums;

import static br.com.ifood.enums.Temperature.LOW_TEMPERATURE;
import static br.com.ifood.enums.Temperature.HIGH_TEMPERATURE;
import static br.com.ifood.enums.Temperature.AVERAGE_TEMPERATURE;

/**
 * Enum of genres / usage logic
 *
 * @author Taynan Rezende
 */
public enum Genre {
	
	PARTY("electronic"),
	POP("pop"),
	ROCK("rock"),
	CLASSICAL("classical"),
	ALL("electronic,pop,rock,classical");
	
	private String description;
	
	Genre(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
	public static Genre findGenreByTemperature(Float temperature) {
		if (temperature > HIGH_TEMPERATURE.getValue()) {
			return PARTY;
		} else if (temperature >= AVERAGE_TEMPERATURE.getValue()) {
			return POP;
		} else if (temperature >= LOW_TEMPERATURE.getValue()) {
			return ROCK;
		} else {
			return CLASSICAL;
		}
	}
}
