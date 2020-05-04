package br.com.ifood.enums;

public enum Temperature {
	
	HIGH_TEMPERATURE(30.0f),
	AVERAGE_TEMPERATURE(15.0f),
	LOW_TEMPERATURE(10.0f);
	
	private Float value;
	
	Temperature(Float value) {
		this.value = value;
	}
	
	public Float getValue() {
		return value;
	}
}
