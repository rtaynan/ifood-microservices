package br.com.ifood.service.impl;

import br.com.ifood.client.OpenWeatherClient;
import br.com.ifood.client.SpotifyClient;
import br.com.ifood.domain.openweather.OpenWeather;
import br.com.ifood.dto.SuggestionDTO;
import br.com.ifood.enums.Genre;
import br.com.ifood.exception.CityNotFoundException;
import br.com.ifood.exception.NetworkException;
import br.com.ifood.factory.SuggestionFactory;
import br.com.ifood.service.SuggestionService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SuggestionServiceImpl implements SuggestionService {
	
	private static final Logger LOG = LoggerFactory.getLogger(SuggestionServiceImpl.class);
	
	private final OpenWeatherClient openWeatherClient;
	private final SpotifyClient spotifyClient;
	
	public SuggestionServiceImpl(OpenWeatherClient openWeatherClient, SpotifyClient spotifyClient) {
		this.openWeatherClient = openWeatherClient;
		this.spotifyClient = spotifyClient;
	}
	
	@HystrixCommand(fallbackMethod = "suggestionTrackFallBack", threadPoolKey = "byCityThreadPool",
			ignoreExceptions = CityNotFoundException.class)
	@Override
	public SuggestionDTO suggestionTracksByCity(String city) throws CityNotFoundException, NetworkException {
		LOG.info("Buscando sugestoes pelo nome da cidade {}", city);
		return getSuggestionTrackByTemperature(openWeatherClient.getTemperatureFromCity(city));
	}
	
	@HystrixCommand(fallbackMethod = "suggestionTrackFallBack", threadPoolKey = "byLocationThreadPool",
			ignoreExceptions = CityNotFoundException.class)
	@Override
	public SuggestionDTO suggestionTracksByLocation(Double latitude, Double longitude)
			throws CityNotFoundException, NetworkException {
		return getSuggestionTrackByTemperature(openWeatherClient.getTemperatureFromLocation(latitude, longitude));
	}
	
	private SuggestionDTO getSuggestionTrackByTemperature(OpenWeather openWeather)
			throws CityNotFoundException, NetworkException {
		if (openWeather == null) {
			throw new CityNotFoundException("Error - invalid arguments");
		}
		Genre genre = Genre.findGenreByTemperature(openWeather.getMain().getTemperature());
		return SuggestionFactory
				.bindToDTO(genre, spotifyClient.findTracksByGenre(genre.getDescription()).getTracks());
	}
	
	private SuggestionDTO suggestionTrackFallBack(String city) {
		LOG.info("Running suggestionTrackFallBack by city");
		return SuggestionFactory.suggestionTrackFallBack();
	}
	
	private SuggestionDTO suggestionTrackFallBack(Double latitude, Double longitude) {
		LOG.info("Running suggestionTrackFallBack by latytude and longitude");
		return SuggestionFactory.suggestionTrackFallBack();
	}
}
