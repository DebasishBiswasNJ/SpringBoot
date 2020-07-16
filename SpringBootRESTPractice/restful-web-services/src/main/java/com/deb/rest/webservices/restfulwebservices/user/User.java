package com.deb.rest.webservices.restfulwebservices.user;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * @ApiModel is for swagger Documentation
 * @author ARIES_PC
 *
 */
//to create a new class click [ctrl + n]
@ApiModel(description="All details about the user.")
public class User {

	private Integer id;
	
	/**
	 * Validation of "name"
	 * The name has to be minimum 2 chars
	 * 
	 * message : is an optional arg, will show in the [default message] in response, while validation fails
	 * 
	 * if in the request "name" has value of <2 char, e.g. "name": "R"
	 * Then, you'll get Bad Request : 400
	 * 
	 * @ApiModelProperty is for swagger Documentation
	 */
	@Size(min = 2, message = "Name should have atleast 2 characters")
	@ApiModelProperty(notes="Name should have atleast 2 characters")
	private String name;
	
	/**
	 * Validation of "birthDate"
	 * The BirthDate cannot be a future Date
	 * 
	 * if in the request "birthDate" has future value, e.g. "birthDate": "2035-06-14T15:57:35.908+00:00"
	 * Then, you'll get Bad Request : 400
	 * 
	 * @ApiModelProperty is for swagger Documentation
	 */
	@Past
	@ApiModelProperty(notes="Birth date should be in the past")
	private Date birthDate;
	
	public User() {
		super();
	}
	public User(Integer id, String name, Date birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
	}
		
}
