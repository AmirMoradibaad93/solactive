package com.solactive.solactive.model;

public class Index {

	private long identifier;
	private String name;
	private String statement;
	
	//constructor
	public Index() {
		
	}
	
	public Index(long id, String name,String statement) {
		this.identifier = id;
		this.name = name;
		this.statement = statement;
	}
	
	
	public long getIdentifier() {
		return identifier;
	}

	public void setIdentifier(long identifier) {
		this.identifier = identifier;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}

	
}
