package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Halmar Arteaga - harteagabran
 * CIS175 - Spring 2024
 * Jan 28, 2024
 */
@Entity
@Table(name="heroes")
public class Unit {
	@Id
	@GeneratedValue
	@Column(name="ID")
	private int id;
	@Column(name="NAME")
	private String name;
	@Column(name="CLASS")
	private String type;
	
	public Unit() {
		super();
	}
	
	public Unit(String name, String type) {
		super();
		this.name = name;
		this.type = type;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	public String returnInfo() {
		return "Unit: " + this.name + " : " + this.type;
	}
}
