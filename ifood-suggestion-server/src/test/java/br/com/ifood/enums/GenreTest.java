package br.com.ifood.enums;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GenreTest {
	
	@Test
	public void testFindGenreByTemperature() {
		assertEquals(Genre.PARTY, Genre.findGenreByTemperature(31.0f));
		assertEquals(Genre.POP, Genre.findGenreByTemperature(15.0f));
		assertEquals(Genre.ROCK, Genre.findGenreByTemperature(11.0f));
		assertEquals(Genre.CLASSICAL, Genre.findGenreByTemperature(09.0f));
	}
	
}
