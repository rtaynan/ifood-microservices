package br.com.ifood.domain.openweather;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

public class OpenWeather implements Serializable {
	
	@NotNull
	@JsonProperty("main")
	private Main main;
	
	public Main getMain() {
		return main;
	}
	
	public void setMain(Main main) {
		this.main = main;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof OpenWeather)) {
			return false;
		}
		OpenWeather that = (OpenWeather) o;
		return main.equals(that.main);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(main);
	}
	
}
