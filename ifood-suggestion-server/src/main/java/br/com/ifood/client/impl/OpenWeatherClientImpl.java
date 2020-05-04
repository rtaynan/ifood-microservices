package br.com.ifood.client.impl;

import br.com.ifood.client.OpenWeatherClient;
import br.com.ifood.constants.OpenWeatherConstants;
import br.com.ifood.domain.openweather.OpenWeather;
import br.com.ifood.exception.CityNotFoundException;
import br.com.ifood.exception.NetworkException;
import br.com.ifood.properties.OpenWeatherProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Service responsible for finding the current temperature of the place
 *
 * @author Taynan Rezende
 *
 */
@Component
public class OpenWeatherClientImpl implements OpenWeatherClient {
	
	private static final Logger LOG = LoggerFactory.getLogger(OpenWeatherClientImpl.class);
	
	private final RestTemplate restTemplate;
	private final OpenWeatherProperties openWeatherProperties;
	
	public OpenWeatherClientImpl(RestTemplate restTemplate, OpenWeatherProperties openWeatherProperties) {
		this.restTemplate = restTemplate;
		this.openWeatherProperties = openWeatherProperties;
	}
	
	/**
	 * Search the temperature from the city
	 *
	 * @param city Name of the city
	 */
	@Cacheable("openWeatherCity")
	@Override
	public OpenWeather getTemperatureFromCity(String city) throws CityNotFoundException, NetworkException {
		LOG.info("Searching temperature by city {}", city);
		UriComponentsBuilder builder = getUriComponentsBuilderCommon();
		builder.queryParam(OpenWeatherConstants.PARAM_Q, city);
		try {
			return restTemplate.getForObject(builder.build().toUriString(), OpenWeather.class);
		} catch (HttpClientErrorException e) {
			throw new CityNotFoundException("Error - invalid informed city");
		} catch (Exception e) {
			throw new NetworkException(e);
		}
	}
	
	/**
	 * Search the temperature from the location
	 *
	 * @param latitude latitude coordinates
	 * @param longitude longitude coordinates
	 */
	@Cacheable("openWeatherLocation")
	@Override
	public OpenWeather getTemperatureFromLocation(Double latitude, Double longitude)
			throws CityNotFoundException, NetworkException {
		LOG.info("Searching temperature by latitude {} and longitude {}", latitude, longitude);
		UriComponentsBuilder builder = getUriComponentsBuilderCommon();
		builder.queryParam(OpenWeatherConstants.PARAM_LAT, latitude);
		builder.queryParam(OpenWeatherConstants.PARAM_LON, longitude);
		try {
			return restTemplate.getForObject(builder.build().toUriString(), OpenWeather.class);
		} catch (HttpClientErrorException e) {
			throw new CityNotFoundException("Error - invalid informed location");
		} catch (Exception e) {
			throw new NetworkException(e);
		}
	}
	
	private UriComponentsBuilder getUriComponentsBuilderCommon() {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(openWeatherProperties.getUrl());
		builder.queryParam(OpenWeatherConstants.PARAM_UNITS, openWeatherProperties.getUnits());
		builder.queryParam(OpenWeatherConstants.PARAM_APPID, openWeatherProperties.getAppId());
		return builder;
	}

}
