package br.com.ifood.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SpotifyProperties {
	
	@Value("${spotify.api.suggestionUrl}")
	private String suggestionUrl;
	@Value("${spotify.api.tokenUrl}")
	private String tokenUrl;
	@Value("${spotify.api.clientId}")
	private String clientId;
	@Value("${spotify.api.clientSecret}")
	private String clientSecret;
	
	public String getSuggestionUrl() {
		return suggestionUrl;
	}
	
	public String getTokenUrl() {
		return tokenUrl;
	}
	
	public String getClientId() {
		return clientId;
	}
	
	public String getClientSecret() {
		return clientSecret;
	}
}
