package br.com.ifood.service;

import br.com.ifood.dto.SuggestionDTO;
import br.com.ifood.exception.CityNotFoundException;
import br.com.ifood.exception.NetworkException;

public interface SuggestionService {
	
	SuggestionDTO suggestionTracksByCity(String city) throws CityNotFoundException, NetworkException;
	
	SuggestionDTO suggestionTracksByLocation(Double latitude, Double longitude)
			throws CityNotFoundException, NetworkException;
}
