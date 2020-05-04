package br.com.ifood.dto;

import java.io.Serializable;
import java.util.Set;

public class SuggestionDTO implements Serializable {
	
	private String genre;
	private Set<String> tracks;
	
	public String getGenre() {
		return genre;
	}
	
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	public Set<String> getTracks() {
		return tracks;
	}
	
	public void setTracks(Set<String> tracks) {
		this.tracks = tracks;
	}
	
}
