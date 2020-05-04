package br.com.ifood.domain.spotify;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

public class SpotifyTracks implements Serializable {
	
	@JsonProperty("tracks")
	private Set<Track> tracks;
	
	public Set<Track> getTracks() {
		return tracks;
	}
	
	public void setTracks(Set<Track> tracks) {
		this.tracks = tracks;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof SpotifyTracks)) {
			return false;
		}
		SpotifyTracks that = (SpotifyTracks) o;
		return tracks.equals(that.tracks);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(tracks);
	}
	
}
