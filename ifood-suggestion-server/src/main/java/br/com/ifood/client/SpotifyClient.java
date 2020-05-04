package br.com.ifood.client;

import br.com.ifood.domain.spotify.SpotifyTracks;
import br.com.ifood.exception.NetworkException;

public interface SpotifyClient {
	
	SpotifyTracks findTracksByGenre(String genre) throws NetworkException;
}
