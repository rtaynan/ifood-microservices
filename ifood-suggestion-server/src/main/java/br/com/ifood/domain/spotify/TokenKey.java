package br.com.ifood.domain.spotify;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TokenKey {
	
	@JsonProperty("access_token")
	private String accessToken;
	
	public String getAccessToken() {
		return accessToken;
	}
	
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
}
