package com.deb.rest.webservices.restfulwebservices.helloworld;

public class HelloWorldBean {

	private String message;

	public HelloWorldBean(String message) {
		this.message = message;
	}

	/**
	 * ### WhiteLabel Error Page
	 * No converter found for return value of type: class com.deb.rest.webservices.restfulwebservices.HelloWorldBean
	 * 
	 * If there is no getter method found in HelloWorldBean class. Then, this error comes.
	 */
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "HelloWorldBean [message=" + message + "]";
	}

}
