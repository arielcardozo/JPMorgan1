package com.jpmg.common;

public class Entity {
	
	private String name;

	/**
	 * @param name
	 */
	public Entity(String name) {
		super();
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public synchronized String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public synchronized void setName(String name) {
		this.name = name;
	}
	
	

}
