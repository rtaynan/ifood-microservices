package br.com.ifood.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

import br.com.ifood.client.OpenWeatherClient;
import br.com.ifood.client.SpotifyClient;
import br.com.ifood.dto.SuggestionDTO;
import br.com.ifood.exception.CityNotFoundException;
import br.com.ifood.util.MockUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SuggestionServiceImplTest {
	
	private static final String POP_GENRE = "pop";
	private static final int SIZE_DEFAULT = 20;
	private static final double LOCATION_ZONE = 0.0;
	
	@Mock
	private OpenWeatherClient mockOpenWeatherClient;
	@Mock
	private SpotifyClient mockSpotifyClient;
	
	private SuggestionServiceImpl suggestionServiceImplUnderTest;
	
	@Before
	public void setUp() {
		suggestionServiceImplUnderTest = Mockito.spy(new SuggestionServiceImpl(mockOpenWeatherClient, mockSpotifyClient));
	}
	
	@Test
	public void testSuggestionTracksByCity() throws Exception {
		doReturn(MockUtil.getOpenWeatherMock()).when(mockOpenWeatherClient).getTemperatureFromCity(any());
		doReturn(MockUtil.getSpotifyTracksMock()).when(mockSpotifyClient).findTracksByGenre(any());
		
		final SuggestionDTO result = suggestionServiceImplUnderTest.suggestionTracksByCity("city");
		
		assertEquals(POP_GENRE, result.getGenre());
		assertEquals(SIZE_DEFAULT, result.getTracks().size());
	}
	
	@Test(expected = CityNotFoundException.class)
	public void testSuggestionTracksByCityThrowsCityNotFoundException() throws Exception {
		doReturn(null).when(mockOpenWeatherClient).getTemperatureFromCity(any());

		suggestionServiceImplUnderTest.suggestionTracksByCity("city");
	}
	
	@Test
	public void testSuggestionTracksByLocation() throws Exception {
		doReturn(MockUtil.getOpenWeatherMock()).when(mockOpenWeatherClient).getTemperatureFromLocation(any(), any());
		doReturn(MockUtil.getSpotifyTracksMock()).when(mockSpotifyClient).findTracksByGenre(any());
		
		final SuggestionDTO result = suggestionServiceImplUnderTest.suggestionTracksByLocation(LOCATION_ZONE,
				LOCATION_ZONE);
		
		assertEquals(POP_GENRE, result.getGenre());
		assertEquals(SIZE_DEFAULT, result.getTracks().size());
	}
	
	@Test(expected = CityNotFoundException.class)
	public void testSuggestionTracksByLocationThrowsCityNotFoundException() throws Exception {
		doReturn(null).when(mockOpenWeatherClient).getTemperatureFromLocation(any(), any());
		
		suggestionServiceImplUnderTest.suggestionTracksByLocation(LOCATION_ZONE, LOCATION_ZONE);
	}

}
