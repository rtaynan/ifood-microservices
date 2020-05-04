package br.com.ifood.domain.spotify;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

public class Album implements Serializable {
	
	@JsonProperty("name")
	private String name;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}
