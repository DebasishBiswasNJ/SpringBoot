package com.deb.microservices.limitsservice;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
/**
 * @ConfigurationProperties --> Read the application.properties file's values where it starts with 'limits-service' and assign to this below variables
 * e.g limits-service.minimum=99  and  limits-service.maximum=9999
 * 
 * @author SREEPARNA
 */
@ConfigurationProperties("limits-service")
public class Configuration {

	private int maximum;
	private int minimum;
	
	public int getMaximum() {
		return maximum;
	}
	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}
	public int getMinimum() {
		return minimum;
	}
	public void setMinimum(int minimum) {
		this.minimum = minimum;
	}
}
