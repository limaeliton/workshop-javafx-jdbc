package model.exception;

import java.util.HashMap;
import java.util.Map;

public class ValidationException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private Map<String, String> errors = new HashMap<>();
	
	// força a instanciação de exceção com String.
	public ValidationException(String msg) {
		super(msg);
	}
	
	public Map<String, String> getErros(){
		return errors;
	}
	
	public void addError(String fieldName, String erroMessage) {
		errors.put(fieldName, erroMessage);
	}
}
