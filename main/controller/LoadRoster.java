package main.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import main.model.StateManager;

public class LoadRoster extends ClickableItem{
	public LoadRoster(StateManager state) {
		super(state, "Load the Roster");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		super.updateState("home");
	}
}
