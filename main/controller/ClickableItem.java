package main.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import main.model.StateManager;

public abstract class ClickableItem extends AbstractAction{
	StateManager state;
	public ClickableItem(StateManager state, String text) {
		super(text);
		this.state = state;
	}
	
	public void actionPerformed(ActionEvent e) {}
	
	public void updateState(String newState) {
		this.state.updateState(newState);
	}
}
