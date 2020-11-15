package main.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import main.model.StateManager;

public class AboutClicked extends ClickableItem{
	public AboutClicked(StateManager state) {
		super(state, "About");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		super.updateState("about");
	}
}
