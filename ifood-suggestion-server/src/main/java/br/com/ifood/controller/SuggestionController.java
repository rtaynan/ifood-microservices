package br.com.ifood.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import br.com.ifood.domain.AbstractSuggestionController;
import br.com.ifood.dto.SuggestionDTO;
import br.com.ifood.exception.CityNotFoundException;
import br.com.ifood.exception.NetworkException;
import br.com.ifood.service.SuggestionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Api(tags = "Track Suggestion")
@Validated
@RestController
@RequestMapping("/suggestion")
public class SuggestionController extends AbstractSuggestionController {
	
	private final SuggestionService suggestionService;
	
	public SuggestionController(SuggestionService suggestionService) {
		this.suggestionService = suggestionService;
	}
	
	@GetMapping(path = "/{city}", produces = APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Suggestion by city name", notes = "Return track suggestions by city name")
	public ResponseEntity<SuggestionDTO> getSuggestionByCity(
			@ApiParam(value = "City name description", required = true)
			@NotBlank(message = "The city (name) could not be null or empty") @PathVariable String city)
			throws CityNotFoundException, NetworkException {
		return ResponseEntity.ok(suggestionService.suggestionTracksByCity(city));
	}
	
	@GetMapping(produces = APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Suggestion by location", notes = "Return track suggestions by latitude and longitude")
	public ResponseEntity<SuggestionDTO> getSuggestionByLocation(
			@NotNull(message = "The latitude (lat) could not be null") @Min(-90) @Max(90)
			@ApiParam(value = "Value for latitude", required = true)
			@RequestParam(name = "lat") Double latitude,
			@NotNull(message = "The longitude (lon) could not be null") @Min(-180) @Max(180)
			@ApiParam(value = "Value for longitude", required = true)
			@RequestParam(name = "lon") Double longitude) throws CityNotFoundException, NetworkException {
		return ResponseEntity.ok(suggestionService.suggestionTracksByLocation(getDoubleWith2Decimal(latitude),
				getDoubleWith2Decimal(longitude)));
	}

}