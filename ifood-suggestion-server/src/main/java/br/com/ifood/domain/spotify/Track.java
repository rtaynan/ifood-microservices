package br.com.ifood.domain.spotify;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Track implements Serializable {
	
	@JsonProperty("album")
	private Album album;
	
	@JsonProperty("available_markets")
	private List<String> availableMarkets;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("popularity")
	private Integer popularity;
	
	@JsonProperty("preview_url")
	private String previewUrl;
	
	public Track(String name) {
		this.name = name;
	}
	
	public Track(Album album, List<String> availableMarkets, String name, Integer popularity, String previewUrl) {
		this.album = album;
		this.availableMarkets = availableMarkets;
		this.name = name;
		this.popularity = popularity;
		this.previewUrl = previewUrl;
	}
	
	public Album getAlbum() {
		return album;
	}
	
	public void setAlbum(Album album) {
		this.album = album;
	}
	
	public List<String> getAvailableMarkets() {
		return availableMarkets;
	}
	
	public void setAvailableMarkets(List<String> availableMarkets) {
		this.availableMarkets = availableMarkets;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getPopularity() {
		return popularity;
	}
	
	public void setPopularity(Integer popularity) {
		this.popularity = popularity;
	}
	
	public String getPreviewUrl() {
		return previewUrl;
	}
	
	public void setPreviewUrl(String previewUrl) {
		this.previewUrl = previewUrl;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Track track = (Track) o;
		return Objects.equals(getAlbum(), track.getAlbum()) &&
				Objects.equals(getAvailableMarkets(), track.getAvailableMarkets()) &&
				getName().equals(track.getName()) && Objects.equals(getPopularity(), track.getPopularity()) &&
				Objects.equals(getPreviewUrl(), track.getPreviewUrl());
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(getAlbum(), getAvailableMarkets(), getName(), getPopularity(), getPreviewUrl());
	}
}
