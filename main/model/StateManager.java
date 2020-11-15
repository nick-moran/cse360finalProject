package main.model;

import java.util.Observable;

public class StateManager extends Observable{
	private String currentState;
	
	public void updateState(String newState){
		currentState = newState;
		this.setChanged();
		this.notifyObservers(newState);
	}
	
	public String getState() {
		return this.currentState;
	}
}
