package br.com.ifood.factory;

import br.com.ifood.domain.spotify.Track;
import br.com.ifood.dto.SuggestionDTO;
import br.com.ifood.enums.Genre;
import java.util.HashSet;
import java.util.Set;

public final class SuggestionFactory {
	
	private SuggestionFactory() {
	}
	
	public static SuggestionDTO bindToDTO(Genre genre, Set<Track> tracks) {
		SuggestionDTO suggestionDTO = new SuggestionDTO();
		suggestionDTO.setGenre(genre.getDescription());
		suggestionDTO.setTracks(getTracksName(tracks));
		return suggestionDTO;
	}
	
	private static Set<String> getTracksName(Set<Track> tracks) {
		Set<String> tracksName = new HashSet<>();
		tracks.forEach(track -> tracksName.add(track.getName()));
		return tracksName;
	}
	
	public static SuggestionDTO suggestionTrackFallBack() {
		Set<Track> tracks = new HashSet<>();
		tracks.add(new Track("Witchcraft"));
		tracks.add(new Track("Kinderszenen (Scenes of Childhood), Op. 15: VII. Traumerei (Dreaming)"));
		tracks.add(new Track("Appalachian Spring: VIII. Coda. Moderato"));
		tracks.add(new Track("The Way"));
		tracks.add(new Track("Youngblood"));
		tracks.add(new Track("Dance To This (feat. Ariana Grande)"));
		tracks.add(new Track("Starving"));
		tracks.add(new Track("Get Free"));
		tracks.add(new Track("Infra 4"));
		tracks.add(new Track("Start Me Up - Remastered"));
		tracks.add(new Track("Feeling Myself"));
		tracks.add(new Track("Ghosts"));
		tracks.add(new Track("Just Like You"));
		tracks.add(new Track("3 Intermezzi, Op. 117: No. 3 in C-Sharp Minor, Andante con moto"));
		tracks.add(new Track("Cocaine"));
		tracks.add(new Track("Too Good At Goodbyes"));
		tracks.add(new Track("G Song"));
		tracks.add(new Track("Naive"));
		tracks.add(new Track("Halo"));
		tracks.add(new Track("Flashlight (Sweet Life Mix) - From Pitch Perfect 2 Soundtrack"));
		return SuggestionFactory.bindToDTO(Genre.ALL, tracks);
	}
}
