package br.com.ifood.client.impl;

import br.com.ifood.client.SpotifyClient;
import br.com.ifood.constants.SpotifyConstants;
import br.com.ifood.domain.spotify.SpotifyTracks;
import br.com.ifood.domain.spotify.TokenKey;
import br.com.ifood.enums.Genre;
import br.com.ifood.exception.NetworkException;
import br.com.ifood.properties.SpotifyProperties;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Service responsible for seeking music suggestions by {@link Genre} description
 *
 * @author Taynan Rezende
 *
 */
@Component
public class SpotifyClientImpl implements SpotifyClient {
	
	private static final Logger LOG = LoggerFactory.getLogger(SpotifyClientImpl.class);

	private final RestTemplate restTemplate;
	private final SpotifyProperties spotifyProperties;
	
	public SpotifyClientImpl(RestTemplate restTemplate, SpotifyProperties spotifyProperties) {
		this.restTemplate = restTemplate;
		this.spotifyProperties = spotifyProperties;
	}
	
	@Cacheable("suggestionByGenre")
	@Override
	public SpotifyTracks findTracksByGenre(String genre) throws NetworkException {
		LOG.info("Searching tracks by genre {}", genre);
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(spotifyProperties.getSuggestionUrl());
		builder.queryParam(SpotifyConstants.SEED_GENRES, genre);
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.AUTHORIZATION, SpotifyConstants.BEARER_AUTHORIZATION + getApiKey());
		HttpEntity<String> entity = new HttpEntity<>(headers);
		try {
			return restTemplate.exchange(builder.build().toUriString(), HttpMethod.GET, entity, SpotifyTracks.class)
					.getBody();
		} catch (Exception e) {
			throw new NetworkException(e);
		}
	}
	
	private String getApiKey() throws NetworkException {
		String basicEncode = new String(Base64.encodeBase64(
				(spotifyProperties.getClientId() + ":" + spotifyProperties.getClientSecret()).getBytes()));
		
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.AUTHORIZATION, SpotifyConstants.BASIC_AUTHORIZATION + basicEncode);
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
		body.add(SpotifyConstants.GRANT_TYPE, SpotifyConstants.CLIENT_CREDENTIALS);
		
		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(body, headers);
		
		TokenKey tokenKey = restTemplate
				.exchange(spotifyProperties.getTokenUrl(), HttpMethod.POST, entity, TokenKey.class).getBody();
		if (tokenKey != null) {
			return tokenKey.getAccessToken();
		} else {
			throw new NetworkException("Error getting api key");
		}
	}
}
