package com.guru84.todo.app.enums;

public enum Status {
	Completed("Completed"),
	OnGoing("OnGoing"),
	Start("Start") ;
	
	private final String text;
	
	Status(final String text) {
		this.text = text;
	}

	@Override 
	public String toString() {
		return text;
	}
}
