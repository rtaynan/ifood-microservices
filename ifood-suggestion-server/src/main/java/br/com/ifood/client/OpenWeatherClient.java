package br.com.ifood.client;

import br.com.ifood.domain.openweather.OpenWeather;
import br.com.ifood.exception.CityNotFoundException;
import br.com.ifood.exception.NetworkException;

public interface OpenWeatherClient {
	
	OpenWeather getTemperatureFromCity(String city) throws CityNotFoundException, NetworkException;
	
	OpenWeather getTemperatureFromLocation(Double latitude, Double longitude)
			throws CityNotFoundException, NetworkException;
}
