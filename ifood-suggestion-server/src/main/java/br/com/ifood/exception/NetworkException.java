package br.com.ifood.exception;

public class NetworkException extends Exception {
	
	public NetworkException(Exception e) {
		super(e);
	}
	
	public NetworkException(String message) {
		super(message);
	}
	
}
