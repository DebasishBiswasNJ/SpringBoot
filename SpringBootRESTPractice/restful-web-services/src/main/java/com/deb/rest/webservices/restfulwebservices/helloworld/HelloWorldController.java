package com.deb.rest.webservices.restfulwebservices.helloworld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//Controller
@RestController
public class HelloWorldController {
	
	@Autowired
	private MessageSource messageSource;

	//GET
	//URI - /hello-world
	//method - "Hello World"
	@RequestMapping(method = RequestMethod.GET, path = "/hello-world1")
	public String helloWorld1() {
		return "Hello World 1";
	}
	
	@GetMapping(path = "/hello-world2")
	public String helloWorld2() {
		return "Hello World 2";
	}
	
	//hello-world-bean
	@GetMapping(path = "/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		//click [ctrl + 1] --> to create a class, constructor directly from here
		return new HelloWorldBean("Hello World Bean");
	}
	
	///hello-world-bean/path-variable/deba
	@GetMapping(path = "/hello-world-bean/path-variable/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello World Bean, %s", name));
	}
	
	/**
	 * Internationalization I18N
	 * 
	 * To hit from postman, in headers need to add one more key. i.e, Accept-Language    us/fr/de/jp/...
	 */
	@GetMapping(path = "/hello-world-Internationalized")
	public String helloWorldInternationalized(@RequestHeader(name="Accept-Language", required=false) Locale locale) {
		return messageSource.getMessage("good.morning.message", null, locale);
	}
		
	/**
	 * We want the application to pick Locale directly.
	 * It is a pain to pass header as argument to each method every time.
	 * Will use LocaleContextHolder to pick the Locale directly from Header.
	 */
	@GetMapping(path = "/hello-world-InternationalizedAdv")
	public String helloWorldInternationalizedAdv() {
		return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
	}
		
	
		
}
