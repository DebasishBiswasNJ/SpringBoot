package com.deb.rest.webservices.restfulwebservices.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {

	@GetMapping("/filtering")
	public SomeBean retrieveSomeBean() {
		return new SomeBean("value1", "value2", "value3");
	}
	
	@GetMapping("/filtering-list")
	public List<SomeBean> retrieveListOfSomeBean() {
		return Arrays.asList(new SomeBean("value1", "value2", "value3"),
				new SomeBean("value12", "value22", "value32"));
	}
	
	//will send field1 & field2
	@GetMapping("/dynamic-filtering")
	public MappingJacksonValue retriveSomeBeanForDynamicFiltering() {
		SomeBeanForDynamicFiltering someBean = new SomeBeanForDynamicFiltering("Value1","Value2","Value3");
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2");
		
		// This id = "SomeBeanFilter" needs to be added in the Bean SomeBeanForDynamicFiltering class like this --> @JsonFilter("SomeBeanFilter")
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(someBean);
		mapping.setFilters(filters);
		
		return mapping;
	}
	
	//will send field2 & field3
	@GetMapping("/dynamic-filtering-list")
	public MappingJacksonValue retriveListOfSomeBeanForDynamicFiltering() {
		List<SomeBeanForDynamicFiltering> list = Arrays.asList(new SomeBeanForDynamicFiltering("Value1","Value2","Value3"), 
				new SomeBeanForDynamicFiltering("Value12","Value22","Value32"));
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2", "field3");
		
		// This id = "SomeBeanFilter" needs to be added in the Bean SomeBeanForDynamicFiltering class like this --> @JsonFilter("SomeBeanFilter")
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(list);
		mapping.setFilters(filters);
		
		return mapping;
	}
}
