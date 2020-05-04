package br.com.ifood.domain;

import java.util.Locale;

/**
 * Abstraction for treating latitude and longitude received
 *
 * @author Taynan Rezende
 */
public abstract class AbstractSuggestionController {
	
	protected Double getDoubleWith2Decimal(Double value) {
		return Double.valueOf(String.format(Locale.US, "%.2f", value));
	}
}
