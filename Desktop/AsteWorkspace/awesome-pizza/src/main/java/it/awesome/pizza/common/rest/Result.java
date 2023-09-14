package it.awesome.pizza.common.rest;

import java.io.Serializable;

public class Result implements Serializable{

	
	private static final long serialVersionUID = -1615355031529475845L;
	
	private String result;
	
	private String message;
	
	public Result(String result, String message) {
		super();
		this.result = result;
		this.message = message;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
